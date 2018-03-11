package com.github.handioq.diberapp.ui.request.interaction;

import com.github.handioq.diberapp.model.dto.RequestStatusDto;
import com.github.handioq.diberapp.model.dvo.RequestDvo;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.util.Mapper;

import rx.Subscriber;

public class RequestInteractionModel implements RequestInteractionMvp.Model {

    private final NetworkService networkService;
    private RequestInteractionMvp.Model.Callback callback;

    private final static String TAG = "RequestModel";

    public RequestInteractionModel(NetworkService networkService) {
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
    public void declineRequest(long requestId, RequestStatusDto requestStatusDto) {
        networkService.getApiService()
                .declineRequest(requestId, requestStatusDto)
                .map(Mapper::mapRequestToDvo)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<RequestDvo>() {
                    @Override
                    public void onCompleted() {
                        callback.onRequestDeclineCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onRequestDeclineError(e);
                    }

                    @Override
                    public void onNext(RequestDvo request) {
                        callback.onRequestDeclined(request);
                    }
                });
    }

    @Override
    public void setCallback(RequestInteractionMvp.Model.Callback callback) {
        this.callback = callback;
    }

}
