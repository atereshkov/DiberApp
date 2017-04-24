package com.github.handioq.diberapp.ui.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.application.DiberApp;
import com.github.handioq.diberapp.base.BaseFragment;
import com.github.handioq.diberapp.model.dvo.OrderDvo;
import com.github.handioq.diberapp.util.AuthPreferences;

import javax.inject.Inject;

import butterknife.BindView;

public class OrderFragment extends BaseFragment implements OrderMvp.View {

    private static final String TAG = "OrderFragment";
    private static final String ORDER_ID_KEY = "order";
    private long orderId;

    @BindView(R.id.order_id)
    TextView orderIdView;

    @BindView(R.id.order_status)
    TextView statusView;

    @BindView(R.id.order_date)
    TextView dateView;

    @BindView(R.id.order_from)
    TextView addressFromView;

    @BindView(R.id.order_to)
    TextView orderToView;

    @BindView(R.id.order_price)
    TextView orderPriceView;

    @BindView(R.id.order_description)
    TextView orderDescriptionView;

    @BindView(R.id.order_content)
    LinearLayout orderContentLayout;

    @BindView(R.id.progress_view)
    ProgressBar progressView;

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
        Log.i(TAG, "get order: " + order);

        bindOrderInformation(order);
    }

    private void bindOrderInformation(OrderDvo order) {
        orderIdView.setText(String.valueOf(order.getId()));
        dateView.setText(order.getDate());
        orderPriceView.setText(String.valueOf(order.getPrice()));
        addressFromView.setText(order.getAddress().getCountry() + ", " + order.getAddress().getCity() + ", " + order.getAddress().getAddress()); // todo extract this
        orderToView.setText(order.getShop().getAddress());
        orderDescriptionView.setText(order.getDescription());
        statusView.setText(order.getStatus());
    }

    @Override
    public void showLoadOrderProgress() {
        orderContentLayout.setVisibility(View.GONE);
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadOrderProgress() {
        orderContentLayout.setVisibility(View.VISIBLE);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadOrderError(Throwable error) {
        Log.e(TAG, error.toString());
    }
}