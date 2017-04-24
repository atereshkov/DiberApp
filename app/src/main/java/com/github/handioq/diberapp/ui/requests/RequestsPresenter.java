package com.github.handioq.diberapp.ui.requests;

import android.util.Log;

import com.github.handioq.diberapp.model.dvo.RequestDvo;
import com.github.handioq.diberapp.network.NetworkService;

import java.util.List;

import javax.inject.Inject;

public class RequestsPresenter implements RequestsMvp.Presenter, RequestsMvp.Model.Callback {

    private RequestsMvp.View requestsView;
    private RequestsMvp.Model requestsModel;
    private NetworkService networkService;

    private final static String TAG = "RequestsPresenter";

    @Inject
    public RequestsPresenter(NetworkService networkService) {
        requestsModel = new RequestsModel(networkService);
        requestsModel.setCallback(this);
    }

    @Override
    public void getOrderRequests(long userId) {
        if (requestsView != null) {
            requestsView.showLoadRequestsProgress();
        }

        requestsModel.getOrderRequests(userId);
    }

    @Override
    public void setView(RequestsMvp.View view) {
        this.requestsView = view;
    }

    @Override
    public void onRequestsLoaded(List<RequestDvo> requests) {
        Log.i(TAG, "get order requests: " + requests.size());

        if (requestsView != null) {
            requestsView.setRequests(requests);
            requestsView.hideLoadRequestsProgress();
        }
    }

    @Override
    public void onRequestsLoadError(Throwable error) {
        if (requestsView != null) {
            requestsView.showLoadRequestsError(error);
            requestsView.hideLoadRequestsProgress();
        }
    }

    @Override
    public void onLoadRequestsCompleted() {
        requestsView.hideLoadRequestsProgress();
    }
}