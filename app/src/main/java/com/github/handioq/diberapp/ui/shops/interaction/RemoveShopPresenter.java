package com.github.handioq.diberapp.ui.shops.interaction;

import com.github.handioq.diberapp.network.NetworkService;

import javax.inject.Inject;

public class RemoveShopPresenter implements RemoveShopMvp.Presenter, RemoveShopMvp.Model.Callback {

    private RemoveShopMvp.View view;
    private RemoveShopMvp.Model model;
    private NetworkService networkService;

    private final static String TAG = "RemoveShopPresenter";

    @Inject
    public RemoveShopPresenter(NetworkService networkService) {
        model = new RemoveShopModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void setView(RemoveShopMvp.View view) {
        this.view = view;
    }

    @Override
    public void onShopRemoved() {
        if (view != null) {
            view.onShopRemoved();
        }
    }

    @Override
    public void onShopRemoveError(Throwable error) {
        if (view != null) {
            view.onShopRemoveError(error);
        }
    }

    @Override
    public void onRemoveCompleted() {

    }

    @Override
    public void removeShop(long userId, int shopId) {
        model.removeShop(userId, shopId);
    }
}