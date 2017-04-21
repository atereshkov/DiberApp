package com.github.handioq.diberapp.ui.addresses.interaction;

import com.github.handioq.diberapp.network.NetworkService;

import javax.inject.Inject;

public class RemoveAddressPresenter implements RemoveAddressMvp.Presenter, RemoveAddressMvp.Model.Callback {

    private RemoveAddressMvp.View view;
    private RemoveAddressMvp.Model model;
    private NetworkService networkService;

    private final static String TAG = "RemoveAddressPresenter";

    @Inject
    public RemoveAddressPresenter(NetworkService networkService) {
        model = new RemoveAddressModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void setView(RemoveAddressMvp.View view) {
        this.view = view;
    }

    @Override
    public void onAddressRemoved() {
        if (view != null) {
            view.onAddressRemoved();
        }
    }

    @Override
    public void onAddressRemoveError(Throwable error) {
        if (view != null) {
            view.onAddressRemoveError(error);
        }
    }

    @Override
    public void onRemoveCompleted() {

    }

    @Override
    public void removeAddress(long userId, int addressId) {
        model.removeAddress(userId, addressId);
    }
}
