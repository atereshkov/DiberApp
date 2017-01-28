package com.github.handioq.diberapp.ui.orders;

import com.github.handioq.diberapp.model.dvo.OrderListDvo;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.util.Mapper;

import rx.Subscriber;

public class OrdersModel implements OrdersMvp.Model {

    private final NetworkService networkService;
    private OrdersMvp.Model.Callback callback;

    private final static String TAG = "OrdersModel";

    public OrdersModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getOrders(long userId) {

        networkService.getApiService()
                .getOrders(userId)
                .map(Mapper::mapOrderListToDvo)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<OrderListDvo>applyScheduler())
                .subscribe(new Subscriber<OrderListDvo>() {
                    @Override
                    public void onCompleted() {
                        callback.onLoadOrdersCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onOrdersLoadError(e);
                    }

                    @Override
                    public void onNext(OrderListDvo orders) {
                        callback.onOrdersLoaded(orders);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}