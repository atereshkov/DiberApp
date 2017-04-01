package com.github.handioq.diberapp.ui.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.application.DiberApp;
import com.github.handioq.diberapp.base.BaseFragment;
import com.github.handioq.diberapp.model.dvo.OrderDvo;
import com.github.handioq.diberapp.util.AuthPreferences;

import javax.inject.Inject;

public class OrderFragment extends BaseFragment implements OrderMvp.View {

    private static final String TAG = "OrderFragment";
    private static final String ORDER_ID_KEY = "order";
    private long orderId;

    @Inject
    OrderMvp.Presenter orderPresenter;

    @Inject
    AuthPreferences authPreferences;

    public static OrderFragment newInstance(long orderId) {
        OrderFragment fragment = new OrderFragment();

        Bundle args = new Bundle();
        args.putLong(ORDER_ID_KEY, orderId);
        fragment.setArguments(args);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            orderId = bundle.getLong(ORDER_ID_KEY);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        readBundle(getArguments());

        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((DiberApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);

        orderPresenter.setView(this);
        orderPresenter.getOrder(orderId);
    }

    @Override
    public void setOrder(OrderDvo order) {

    }

    @Override
    public void showLoadOrderProgress() {

    }

    @Override
    public void hideLoadOrderProgress() {

    }

    @Override
    public void showLoadOrderError(Throwable error) {
        Log.e(TAG, error.toString());
    }
}