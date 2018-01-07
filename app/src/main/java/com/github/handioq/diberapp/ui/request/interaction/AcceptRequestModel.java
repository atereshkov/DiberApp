package com.github.handioq.diberapp.ui.request.interaction;

import com.github.handioq.diberapp.model.dto.RequestStatusDto;
import com.github.handioq.diberapp.model.dvo.RequestDvo;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.util.Mapper;

import rx.Subscriber;

public class AcceptRequestModel implements AcceptRequestMvp.Model {

    private final NetworkService networkService;
    private AcceptRequestMvp.Model.Callback callback;

    private final static String TAG = "RequestModel";

    public AcceptRequestModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void acceptRequest(long requestId, RequestStatusDto requestStatusDto) {

        networkService.getApiService()
                .acceptRequest(requestId, requestStatusDto)
                .map(Mapper::mapRequestToDvo)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<RequestDvo>() {
                    @Override
                    public void onCompleted() {
                        callback.onRequestAcceptionCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onRequestAcceptError(e);
                    }

                    @Override
                    public void onNext(RequestDvo request) {
                        callback.onRequestAccepted(request);
                    }
                });
    }

    @Override
    public void setCallback(AcceptRequestMvp.Model.Callback callback) {
        this.callback = callback;
    }

}
