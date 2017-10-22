package com.github.handioq.diberapp.ui.orders.interaction;

import com.github.handioq.diberapp.model.dto.ResponseDto;
import com.github.handioq.diberapp.network.NetworkService;

import rx.Subscriber;

public class RemoveOrderModel implements RemoveOrderMvp.Model {

    private final NetworkService networkService;
    private Callback callback;

    private final static String TAG = "RemoveOrderModel";

    public RemoveOrderModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void deleteOrder(long orderId) {
        networkService.getApiService()
                .deleteOrder(orderId)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<ResponseDto>() {
                    @Override
                    public void onCompleted() {
                        callback.onRemoveCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onOrderRemoveError(e);
                    }

                    @Override
                    public void onNext(ResponseDto responseDto) {
                        callback.onOrderRemoved();
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

}