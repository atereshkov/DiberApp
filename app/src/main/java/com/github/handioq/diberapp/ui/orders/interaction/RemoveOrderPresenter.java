package com.github.handioq.diberapp.ui.orders.interaction;

import com.github.handioq.diberapp.network.NetworkService;

import javax.inject.Inject;

public class RemoveOrderPresenter implements RemoveOrderMvp.Presenter, RemoveOrderMvp.Model.Callback {

    private RemoveOrderMvp.View view;
    private RemoveOrderMvp.Model model;
    private NetworkService networkService;

    private final static String TAG = "RemoveOrderPresenter";

    @Inject
    public RemoveOrderPresenter(NetworkService networkService) {
        model = new RemoveOrderModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void setView(RemoveOrderMvp.View view) {
        this.view = view;
    }

    @Override
    public void onOrderRemoved() {
        if (view != null) {
            view.onOrderRemoved();
        }
    }

    @Override
    public void onOrderRemoveError(Throwable error) {
        if (view != null) {
            view.onOrderRemoveError(error);
        }
    }

    @Override
    public void onRemoveCompleted() {

    }

    @Override
    public void deleteOrder(long orderId) {
        model.deleteOrder(orderId);
    }
}