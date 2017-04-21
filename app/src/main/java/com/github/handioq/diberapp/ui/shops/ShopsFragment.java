package com.github.handioq.diberapp.ui.shops;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.application.DiberApp;
import com.github.handioq.diberapp.base.BaseFragment;
import com.github.handioq.diberapp.base.RecyclerViewEmptySupport;
import com.github.handioq.diberapp.base.event.RemoveShopEvent;
import com.github.handioq.diberapp.model.dvo.ShopDvo;
import com.github.handioq.diberapp.ui.auth.login.LoginActivity;
import com.github.handioq.diberapp.ui.interaction.new_shop.NewShopActivity;
import com.github.handioq.diberapp.ui.shops.adapter.ShopsRecyclerAdapter;
import com.github.handioq.diberapp.ui.shops.interaction.RemoveShopMvp;
import com.github.handioq.diberapp.util.AuthPreferences;
import com.github.handioq.diberapp.util.ErrorUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopsFragment extends BaseFragment implements ShopsMvp.View, SwipeRefreshLayout.OnRefreshListener,
        RemoveShopMvp.View {

    @BindView(R.id.recycler_view)
    RecyclerViewEmptySupport recyclerView;

    @BindView(R.id.empty_recycler_view)
    View emptyView;

    @BindView(R.id.progress_view)
    View progressView;

    @BindView(R.id.content)
    SwipeRefreshLayout content;

    private boolean isHotUpdating = false;
    private boolean isUpdating = false;

    private final String TAG = this.getClass().getSimpleName();

    private ShopsRecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Inject
    ShopsMvp.Presenter shopsPresenter;

    @Inject
    RemoveShopMvp.Presenter removeShopPresenter;

    @Inject
    AuthPreferences authPreferences;

    public static ShopsFragment newInstance() {
        ShopsFragment fragment = new ShopsFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        getActivity().setTitle("Shops");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shops, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((DiberApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);

        adapter = new ShopsRecyclerAdapter(new ArrayList<>());
        content.setOnRefreshListener(this);
        shopsPresenter.setView(this);
        loadData(false);
        initRecycler();
    }

    private void initRecycler() {
        layoutManager = new LinearLayoutManager(getContext()); // 1 address in a row
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setEmptyView(emptyView);

        //recyclerView.addOnScrollListener(new PaginationOnScrollListener(this, layoutManager));
    }

    private void loadData(Boolean force) {
        //adapter.clearItems();
        isUpdating = true;
        shopsPresenter.getUserShops(authPreferences.getUserId());
    }

    @Override
    public void showLoadShopsProgress() {
        if (isHotUpdating) {
            content.setRefreshing(true);
        }

        if (!isHotUpdating) {
            progressView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoadShopsProgress() {
        content.setRefreshing(false);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void setShops(List<ShopDvo> shops) {
        isUpdating = false;
        content.setRefreshing(false);
        if (getActivity() != null) { // check for attaching to activity
            adapter.setItems(shops);
        }
    }

    @Override
    public void showLoadShopsError(Throwable error) {
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
    void newAddressClick() {
        Intent intent = new Intent(getContext(), NewShopActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        if (!isUpdating) {
            isHotUpdating = true;
            loadData(true);
        }
    }

    @Subscribe
    public void RemoveAddressEvent(RemoveShopEvent event) {
        removeShopPresenter.setView(this);
        removeShopPresenter.removeShop(AuthPreferences.getUserId(), event.getShopDvo().getId());
    }

    @Override
    public void onShopRemoved() {
        Toast.makeText(getActivity(), "Shop removed!", Toast.LENGTH_SHORT).show();
        loadData(false);
    }

    @Override
    public void onShopRemoveError(Throwable e) {
        Log.e(TAG, e.toString());
        Toast.makeText(getContext(), "Error during shop removing!", Toast.LENGTH_SHORT).show();
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