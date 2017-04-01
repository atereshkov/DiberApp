package com.github.handioq.diberapp.ui.order;

import com.github.handioq.diberapp.model.dvo.OrderDvo;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.util.Mapper;

import rx.Subscriber;

public class OrderModel implements OrderMvp.Model {

    private final NetworkService networkService;
    private OrderMvp.Model.Callback callback;

    private final static String TAG = "OrderModel";

    public OrderModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getOrder(long orderId) {

        networkService.getApiService()
                .getOrder(orderId)
                .map(Mapper::mapOrderToDvo)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<OrderDvo>() {
                    @Override
                    public void onCompleted() {
                        callback.onOrderLoadCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onOrderLoadError(e);
                    }

                    @Override
                    public void onNext(OrderDvo order) {
                        callback.onOrderLoaded(order);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
