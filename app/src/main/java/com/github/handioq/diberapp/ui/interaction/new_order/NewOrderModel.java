package com.github.handioq.diberapp.ui.interaction.new_order;

import com.github.handioq.diberapp.model.dto.NewOrderDto;
import com.github.handioq.diberapp.model.dvo.OrderDvo;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.util.Mapper;

import rx.Subscriber;

public class NewOrderModel implements NewOrderMvp.Model {

    private final static String TAG = "NewOrderModel";

    private final NetworkService networkService;
    private NewOrderModel.Callback callback;

    public NewOrderModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void addOrder(long userId, NewOrderDto orderDto) {
        networkService.getApiService()
                .addOrder(userId, orderDto)
                .map(Mapper::mapOrderToDvo)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<OrderDvo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Log.e(TAG, e.toString());
                        callback.onOrderAddError(e);
                    }

                    @Override
                    public void onNext(OrderDvo orderDvo) {
                        //Log.i(TAG, orderDvo.toString());
                        callback.onOrderAdded(orderDvo);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
