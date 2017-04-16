package com.github.handioq.diberapp.ui.interaction.new_shop;

import com.github.handioq.diberapp.model.dto.ShopDto;
import com.github.handioq.diberapp.model.dvo.ShopDvo;
import com.github.handioq.diberapp.network.NetworkService;

import javax.inject.Inject;

public class NewShopPresenter implements NewShopMvp.Presenter, NewShopMvp.Model.Callback {

    private final static String TAG = "NewShopPresenter";

    private NetworkService networkService;
    private NewShopMvp.View view;
    private NewShopModel model;

    @Inject
    public NewShopPresenter(NetworkService networkService) {
        model = new NewShopModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void onShopAdded(ShopDvo shopDvo) {
        if (view != null) {
            view.onShopAdded(shopDvo);
            view.hideAddShopProgress();
        }
    }

    @Override
    public void onShopAddError(Throwable error) {
        if (view != null) {
            view.onAddShopError(error);
            view.hideAddShopProgress();
        }
    }

    @Override
    public void onAddShopCompleted() {
        view.hideAddShopProgress();
    }

    @Override
    public void addShop(long userId, ShopDto shopDto) {
        if (view != null) {
            view.showAddShopProgress();
        }

        model.addShop(userId, shopDto);
    }

    @Override
    public void setView(NewShopMvp.View view) {
        this.view = view;
    }

}