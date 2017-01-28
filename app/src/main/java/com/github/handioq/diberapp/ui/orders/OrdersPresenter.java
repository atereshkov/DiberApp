package com.github.handioq.diberapp.ui.orders;

import android.util.Log;

import com.github.handioq.diberapp.model.dvo.OrderDvo;
import com.github.handioq.diberapp.network.NetworkService;

import java.util.List;

import javax.inject.Inject;

public class OrdersPresenter implements OrdersMvp.Presenter, OrdersMvp.Model.Callback {

    private OrdersMvp.View ordersView;
    private OrdersMvp.Model ordersModel;
    private NetworkService networkService;

    private final static String TAG = "CatalogPresenter";

    @Inject
    public OrdersPresenter(NetworkService networkService) {
        ordersModel = new OrdersModel(networkService);
        ordersModel.setCallback(this);
    }

    @Override
    public void onOrdersLoaded(List<OrderDvo> orders) {
        Log.i(TAG, "get orders: " + orders.size());

        if (ordersView != null) {
            ordersView.setOrders(orders);
            ordersView.hideLoadOrdersProgress();
        }
    }

    @Override
    public void onOrdersLoadError(Throwable error) {
        if (ordersView != null) {
            ordersView.showLoadOrdersError(error);
            ordersView.hideLoadOrdersProgress();
        }
    }

    @Override
    public void onLoadOrdersCompleted() {
        ordersView.hideLoadOrdersProgress();
    }

    @Override
    public void getOrders(long userId) {
        if (ordersView != null) {
            ordersView.showLoadOrdersProgress();
        }

        ordersModel.getOrders(userId);
    }

    @Override
    public void setView(OrdersMvp.View view) {
        this.ordersView = view;
    }
}
