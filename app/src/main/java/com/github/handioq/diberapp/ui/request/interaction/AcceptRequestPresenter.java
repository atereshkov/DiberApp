package com.github.handioq.diberapp.ui.request.interaction;

import com.github.handioq.diberapp.model.dto.RequestStatusDto;
import com.github.handioq.diberapp.model.dvo.RequestDvo;
import com.github.handioq.diberapp.network.NetworkService;

import javax.inject.Inject;

public class AcceptRequestPresenter implements AcceptRequestMvp.Presenter, AcceptRequestMvp.Model.Callback {

    private AcceptRequestMvp.View requestView;
    private AcceptRequestMvp.Model requestModel;
    private NetworkService networkService;

    private final static String TAG = "AcceptRequestPresenter";

    @Inject
    public AcceptRequestPresenter(NetworkService networkService) {
        requestModel = new AcceptRequestModel(networkService);
        requestModel.setCallback(this);
    }

    @Override
    public void onRequestAccepted(RequestDvo request) {
        if (requestView != null) {
            requestView.onRequestAcceptSuccess(request);
            requestView.hideAcceptRequestProgress();
        }
    }

    @Override
    public void onRequestAcceptError(Throwable error) {
        if (requestView != null) {
            requestView.onRequestAcceptError(error);
            requestView.hideAcceptRequestProgress();
        }
    }

    @Override
    public void onRequestAcceptionCompleted() {
        requestView.hideAcceptRequestProgress();
    }

    @Override
    public void acceptRequest(long requestId, RequestStatusDto requestStatusDto) {
        if (requestView != null) {
            requestView.showAcceptRequestProgress();
        }

        requestModel.acceptRequest(requestId, requestStatusDto);
    }

    @Override
    public void setView(AcceptRequestMvp.View view) {
        this.requestView = view;
    }

}
