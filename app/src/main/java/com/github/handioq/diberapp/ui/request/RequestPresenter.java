package com.github.handioq.diberapp.ui.request;

import com.github.handioq.diberapp.model.dvo.RequestDvo;
import com.github.handioq.diberapp.network.NetworkService;

import javax.inject.Inject;

public class RequestPresenter implements RequestMvp.Presenter, RequestMvp.Model.Callback {

    private RequestMvp.View requestView;
    private RequestMvp.Model requestModel;
    private NetworkService networkService;

    private final static String TAG = "RequestPresenter";

    @Inject
    public RequestPresenter(NetworkService networkService) {
        requestModel = new RequestModel(networkService);
        requestModel.setCallback(this);
    }

    @Override
    public void onRequestLoaded(RequestDvo request) {
        //Log.i(TAG, "get order: " + order);

        if (requestView != null) {
            requestView.setRequest(request);
            requestView.hideLoadRequestProgress();
        }
    }

    @Override
    public void onRequestLoadError(Throwable error) {
        if (requestView != null) {
            requestView.showLoadRequestError(error);
            requestView.hideLoadRequestProgress();
        }
    }

    @Override
    public void onRequestLoadCompleted() {
        requestView.hideLoadRequestProgress();
    }

    @Override
    public void getRequestDetails(long orderId) {
        if (requestView != null) {
            requestView.showLoadRequestProgress();
        }

        requestModel.getRequestDetails(orderId);
    }

    @Override
    public void setView(RequestMvp.View view) {
        this.requestView = view;
    }
}