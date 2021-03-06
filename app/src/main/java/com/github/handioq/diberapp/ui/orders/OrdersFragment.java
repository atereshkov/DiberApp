package com.github.handioq.diberapp.ui.orders;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.application.DiberApp;
import com.github.handioq.diberapp.base.BaseFragment;
import com.github.handioq.diberapp.base.RecyclerViewEmptySupport;
import com.github.handioq.diberapp.base.event.RemoveOrderEvent;
import com.github.handioq.diberapp.base.pagination.PaginationListener;
import com.github.handioq.diberapp.base.pagination.PaginationOnScrollListener;
import com.github.handioq.diberapp.model.dvo.OrderListDvo;
import com.github.handioq.diberapp.ui.auth.login.LoginActivity;
import com.github.handioq.diberapp.ui.interaction.new_order.NewOrderActivity;
import com.github.handioq.diberapp.ui.orders.adapter.OrdersRecyclerAdapter;
import com.github.handioq.diberapp.ui.orders.interaction.RemoveOrderMvp;
import com.github.handioq.diberapp.util.AuthPreferences;
import com.github.handioq.diberapp.util.ErrorUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class OrdersFragment extends BaseFragment implements OrdersMvp.View, SwipeRefreshLayout.OnRefreshListener,
        RemoveOrderMvp.View, PaginationListener {

    @BindView(R.id.recycler_view)
    RecyclerViewEmptySupport recyclerView;

    @BindView(R.id.empty_recycler_view)
    View emptyView;

    @BindView(R.id.progress_view)
    View progressView;

    @BindView(R.id.content)
    SwipeRefreshLayout content;

    private final String TAG = "OrdersFragment";
    private static final String USER_ID_KEY = "user";
    private long userId;

    private boolean isHotUpdating = false;
    private boolean isUpdating = false;

    private OrdersRecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Inject
    OrdersMvp.Presenter ordersPresenter;

    @Inject
    RemoveOrderMvp.Presenter removeOrderPresenter;

    @Inject
    AuthPreferences authPreferences;

    public static OrdersFragment newInstance(long userId) {
        OrdersFragment fragment = new OrdersFragment();

        Bundle args = new Bundle();
        args.putLong(USER_ID_KEY, userId);
        fragment.setArguments(args);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            userId = bundle.getLong(USER_ID_KEY);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        getActivity().setTitle("Orders");
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        readBundle(getArguments());
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((DiberApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);

        content.setOnRefreshListener(this);
        adapter = new OrdersRecyclerAdapter(new ArrayList<>());
        ordersPresenter.setView(this);
        isUpdating = true;
        ordersPresenter.getOrders(userId);
        initRecycler();
    }

    private void initRecycler() {
        layoutManager = new LinearLayoutManager(getContext()); // 1 order in a row
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setEmptyView(emptyView);

        recyclerView.addOnScrollListener(new PaginationOnScrollListener(this, layoutManager));
    }

    @Override
    public void showLoadOrdersProgress() {
        if (isHotUpdating) {
            content.setRefreshing(true);
        }

        if (!isHotUpdating) {
            progressView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoadOrdersProgress() {
        content.setRefreshing(false);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void setOrders(OrderListDvo orders) {
        isUpdating = false;
        content.setRefreshing(false);
        if (getActivity() != null) { // check for attaching to activity
            adapter.setItems(orders.getOrders());
        }
    }

    @Override
    public void showLoadOrdersError(Throwable error) {
        isUpdating = false;
        content.setRefreshing(false);
        if (ErrorUtils.isUnauthorizedError(error)) {
            // TODO extract this
            authPreferences.setUserToken(AuthPreferences.TOKEN_NULL);
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        } else {
            Log.e(TAG, error.toString());
            Toast.makeText(getContext(), ErrorUtils.getMessage(error), Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.fab)
    void newPost() {
        Intent intent = new Intent(getContext(), NewOrderActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(getContext(), "Not implemented", Toast.LENGTH_SHORT).show();
            return true;
        }

        return true;
    }

    @Override
    public void onRefresh() {
        if (!isUpdating) {
            isHotUpdating = true;
            adapter.clearItems();
            isUpdating = true;
            ordersPresenter.getOrders(userId);
        }
    }

    @Subscribe
    public void RemoveOrderEvent(RemoveOrderEvent event) {
        removeOrderPresenter.setView(this);
        removeOrderPresenter.deleteOrder(event.getOrderDvo().getId());
    }

    @Override
    public void onOrderRemoved() {
        Toast.makeText(getActivity(), "Order removed!", Toast.LENGTH_SHORT).show();
        //loadData(false); // todo

        // make loadData method and remove this repeated shit(((
        adapter.clearItems();
        isUpdating = true;
        ordersPresenter.getOrders(userId);
    }

    @Override
    public void onOrderRemoveError(Throwable e) {
        Log.e(TAG, e.toString());
        Toast.makeText(getContext(), "Error during order removing!", Toast.LENGTH_SHORT).show();
    }

    // MARK: Pagination

    @Override
    public void onPaginationLoad(boolean state, int totalItemCount, int limit) {
        //firstPaginationLoad = state;
        Log.e(TAG, "Pagination load!");
        //ordersPresenter.getOrders(userId, totalItemCount, limit);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}