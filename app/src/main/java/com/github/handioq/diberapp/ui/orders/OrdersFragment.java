package com.github.handioq.diberapp.ui.orders;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.github.handioq.diberapp.model.dvo.OrderDvo;
import com.github.handioq.diberapp.ui.interaction.new_order.NewOrderActivity;
import com.github.handioq.diberapp.ui.orders.adapter.OrdersRecyclerAdapter;
import com.github.handioq.diberapp.util.ErrorUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class OrdersFragment extends BaseFragment implements OrdersMvp.View {

    @BindView(R.id.recycler_view)
    RecyclerViewEmptySupport recyclerView;

    @BindView(R.id.empty_recycler_view)
    View emptyView;

    @BindView(R.id.progress_view)
    View progressView;


    private final String TAG = "OrdersFragment";
    private static final String USER_ID_KEY = "user";
    private long userId;

    private OrdersRecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Inject
    OrdersMvp.Presenter ordersPresenter;

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

        adapter = new OrdersRecyclerAdapter(new ArrayList<OrderDvo>());
        ordersPresenter.setView(this);
        ordersPresenter.getOrders(userId);
        initRecycler();
    }

    private void initRecycler() {
        layoutManager = new LinearLayoutManager(getContext()); // 1 order in a row
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setEmptyView(emptyView);

        //recyclerView.addOnScrollListener(new PaginationOnScrollListener(this, layoutManager));
    }

    @Override
    public void showLoadOrdersProgress() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadOrdersProgress() {
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void setOrders(List<OrderDvo> orders) {
        if (getActivity() != null) { // check for attaching to activity
            adapter.setItems(orders);
        }
    }

    @Override
    public void showLoadOrdersError(Throwable error) {
        Log.e(TAG, error.toString());
        Toast.makeText(getContext(), ErrorUtils.getMessage(error), Toast.LENGTH_LONG).show();
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

}