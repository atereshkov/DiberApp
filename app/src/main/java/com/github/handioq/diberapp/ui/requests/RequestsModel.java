package com.github.handioq.diberapp.ui.requests;

import com.github.handioq.diberapp.model.dvo.RequestDvo;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.util.Mapper;

import java.util.List;

import rx.Subscriber;

public class RequestsModel implements RequestsMvp.Model {

    private final NetworkService networkService;
    private RequestsMvp.Model.Callback callback;

    private final static String TAG = "RequestsModel";

    public RequestsModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getOrderRequests(long orderId) {
        networkService.getApiService()
                .getOrderRequests(orderId)
                .map(Mapper::mapRequestsToDvo)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<List<RequestDvo>>() {
                    @Override
                    public void onCompleted() {
                        callback.onLoadRequestsCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onRequestsLoadError(e);
                    }

                    @Override
                    public void onNext(List<RequestDvo> requests) {
                        callback.onRequestsLoaded(requests);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}