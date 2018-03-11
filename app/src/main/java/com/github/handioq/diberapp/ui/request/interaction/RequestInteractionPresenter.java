package com.github.handioq.diberapp.ui.request.interaction;

import com.github.handioq.diberapp.model.dto.RequestStatusDto;
import com.github.handioq.diberapp.model.dvo.RequestDvo;
import com.github.handioq.diberapp.network.NetworkService;

import javax.inject.Inject;

public class RequestInteractionPresenter implements RequestInteractionMvp.Presenter, RequestInteractionMvp.Model.Callback {

    private RequestInteractionMvp.View requestView;
    private RequestInteractionMvp.Model requestModel;
    private NetworkService networkService;

    private final static String TAG = "RequestInteractionPresenter";

    @Inject
    public RequestInteractionPresenter(NetworkService networkService) {
        requestModel = new RequestInteractionModel(networkService);
        requestModel.setCallback(this);
    }

    @Override
    public void onRequestAccepted(RequestDvo request) {
        if (requestView != null) {
            requestView.onRequestAcceptSuccess(request);
            requestView.hideRequestInteractionProgress();
        }
    }

    @Override
    public void onRequestAcceptError(Throwable error) {
        if (requestView != null) {
            requestView.onRequestAcceptError(error);
            requestView.hideRequestInteractionProgress();
        }
    }

    @Override
    public void onRequestAcceptionCompleted() {
        requestView.hideRequestInteractionProgress();
    }

    @Override
    public void acceptRequest(long requestId, RequestStatusDto requestStatusDto) {
        if (requestView != null) {
            requestView.showRequestInteractionProgress();
        }

        requestModel.acceptRequest(requestId, requestStatusDto);
    }

    @Override
    public void declineRequest(long requestId, RequestStatusDto requestStatusDto) {
        if (requestView != null) {
            requestView.showRequestInteractionProgress();
        }

        requestModel.declineRequest(requestId, requestStatusDto);
    }

    @Override
    public void onRequestDeclined(RequestDvo request) {
        if (requestView != null) {
            requestView.onRequestDeclineSuccess(request);
            requestView.hideRequestInteractionProgress();
        }
    }

    @Override
    public void onRequestDeclineError(Throwable error) {
        if (requestView != null) {
            requestView.onRequestDeclineError(error);
            requestView.hideRequestInteractionProgress();
        }
    }

    @Override
    public void onRequestDeclineCompleted() {
        requestView.hideRequestInteractionProgress();
    }

    @Override
    public void setView(RequestInteractionMvp.View view) {
        this.requestView = view;
    }

}
