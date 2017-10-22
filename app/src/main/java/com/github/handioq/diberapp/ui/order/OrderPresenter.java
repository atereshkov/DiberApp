package com.github.handioq.diberapp.ui.order;

import com.github.handioq.diberapp.model.dvo.OrderDvo;
import com.github.handioq.diberapp.network.NetworkService;

import javax.inject.Inject;

public class OrderPresenter implements OrderMvp.Presenter, OrderMvp.Model.Callback {

    private OrderMvp.View orderView;
    private OrderMvp.Model orderModel;
    private NetworkService networkService;

    private final static String TAG = "OrderPresenter";

    @Inject
    public OrderPresenter(NetworkService networkService) {
        orderModel = new OrderModel(networkService);
        orderModel.setCallback(this);
    }

    @Override
    public void onOrderLoaded(OrderDvo order) {
        //Log.i(TAG, "get order: " + order);

        if (orderView != null) {
            orderView.setOrder(order);
            orderView.hideLoadOrderProgress();
        }
    }

    @Override
    public void onOrderLoadError(Throwable error) {
        if (orderView != null) {
            orderView.showLoadOrderError(error);
            orderView.hideLoadOrderProgress();
        }
    }

    @Override
    public void onOrderLoadCompleted() {
        orderView.hideLoadOrderProgress();
    }

    @Override
    public void getOrder(long orderId) {
        if (orderView != null) {
            orderView.showLoadOrderProgress();
        }

        orderModel.getOrder(orderId);
    }

    @Override
    public void setView(OrderMvp.View view) {
        this.orderView = view;
    }
}