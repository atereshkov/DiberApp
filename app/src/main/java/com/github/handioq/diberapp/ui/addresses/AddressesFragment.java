package com.github.handioq.diberapp.ui.addresses;

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
import com.github.handioq.diberapp.base.event.RemoveAddressEvent;
import com.github.handioq.diberapp.model.dvo.AddressDvo;
import com.github.handioq.diberapp.ui.addresses.adapter.AddressesRecyclerAdapter;
import com.github.handioq.diberapp.ui.addresses.interaction.RemoveAddressMvp;
import com.github.handioq.diberapp.ui.auth.login.LoginActivity;
import com.github.handioq.diberapp.ui.interaction.new_address.NewAddressActivity;
import com.github.handioq.diberapp.util.AuthPreferences;
import com.github.handioq.diberapp.util.ErrorUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressesFragment extends BaseFragment implements AddressesMvp.View, SwipeRefreshLayout.OnRefreshListener,
        RemoveAddressMvp.View {

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

    private AddressesRecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Inject
    AddressesMvp.Presenter addressesPresenter;

    @Inject
    RemoveAddressMvp.Presenter removeAddressPresenter;

    @Inject
    AuthPreferences authPreferences;

    public static AddressesFragment newInstance() {
        AddressesFragment fragment = new AddressesFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        getActivity().setTitle("Addresses");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addresses, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((DiberApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);

        adapter = new AddressesRecyclerAdapter(new ArrayList<>());
        content.setOnRefreshListener(this);
        addressesPresenter.setView(this);
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
        addressesPresenter.getUserAddresses(authPreferences.getUserId());
    }

    @Override
    public void showLoadAddressesProgress() {
        if (isHotUpdating) {
            content.setRefreshing(true);
        }

        if (!isHotUpdating) {
            progressView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoadAddressesProgress() {
        content.setRefreshing(false);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void setAddresses(List<AddressDvo> addresses) {
        isUpdating = false;
        content.setRefreshing(false);
        if (getActivity() != null) { // check for attaching to activity
            adapter.setItems(addresses);
        }
    }

    @Override
    public void showLoadAddressesError(Throwable error) {
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
        Intent intent = new Intent(getContext(), NewAddressActivity.class);
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
    public void RemoveAddressEvent(RemoveAddressEvent event) {
        removeAddressPresenter.setView(this);
        removeAddressPresenter.removeAddress(AuthPreferences.getUserId(), event.getAddressDvo().getId());
    }

    @Override
    public void onAddressRemoved() {
        Toast.makeText(getActivity(), "Address removed!", Toast.LENGTH_SHORT).show();
        loadData(false);
    }

    @Override
    public void onAddressRemoveError(Throwable e) {
        Log.e(TAG, e.toString());
        Toast.makeText(getContext(), "Error during address removing!", Toast.LENGTH_SHORT).show();
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