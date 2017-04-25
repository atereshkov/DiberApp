package com.github.handioq.diberapp.ui.request;

import com.github.handioq.diberapp.model.dvo.RequestDvo;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.util.Mapper;

import rx.Subscriber;

public class RequestModel implements RequestMvp.Model {

    private final NetworkService networkService;
    private RequestMvp.Model.Callback callback;

    private final static String TAG = "RequestModel";

    public RequestModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getRequestDetails(long requestId) {

        networkService.getApiService()
                .getRequestDetails(requestId)
                .map(Mapper::mapRequestToDvo)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<RequestDvo>() {
                    @Override
                    public void onCompleted() {
                        callback.onRequestLoadCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onRequestLoadError(e);
                    }

                    @Override
                    public void onNext(RequestDvo order) {
                        callback.onRequestLoaded(order);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}