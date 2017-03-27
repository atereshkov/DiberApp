package com.github.handioq.diberapp.ui.shops;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.github.handioq.diberapp.model.dvo.ShopDvo;
import com.github.handioq.diberapp.ui.addresses.adapter.AddressesRecyclerAdapter;
import com.github.handioq.diberapp.ui.auth.login.LoginActivity;
import com.github.handioq.diberapp.ui.shops.adapter.ShopsRecyclerAdapter;
import com.github.handioq.diberapp.util.AuthPreferences;
import com.github.handioq.diberapp.util.ErrorUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopsFragment extends BaseFragment implements ShopsMvp.View {

    @BindView(R.id.recycler_view)
    RecyclerViewEmptySupport recyclerView;

    @BindView(R.id.empty_recycler_view)
    View emptyView;

    @BindView(R.id.progress_view)
    View progressView;

    private final String TAG = this.getClass().getSimpleName();

    private ShopsRecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Inject
    ShopsMvp.Presenter shopsPresenter;

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
        shopsPresenter.setView(this);
        shopsPresenter.getUserShops(authPreferences.getUserId());
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

    @Override
    public void showLoadShopsProgress() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadShopsProgress() {
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void setShops(List<ShopDvo> shops) {
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

    }

    /*
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
    */

}