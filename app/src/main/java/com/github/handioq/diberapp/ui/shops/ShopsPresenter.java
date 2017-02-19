package com.github.handioq.diberapp.ui.shops;

import android.util.Log;

import com.github.handioq.diberapp.model.dvo.ShopDvo;
import com.github.handioq.diberapp.network.NetworkService;

import java.util.List;

import javax.inject.Inject;

public class ShopsPresenter implements ShopsMvp.Presenter, ShopsMvp.Model.Callback {

    private ShopsMvp.View shopsView;
    private ShopsMvp.Model shopsModel;
    private NetworkService networkService;

    private final static String TAG = "ShopsPresenter";

    @Inject
    public ShopsPresenter(NetworkService networkService) {
        shopsModel = new ShopsModel(networkService);
        shopsModel.setCallback(this);
    }

    @Override
    public void getUserShops(long userId) {
        if (shopsView != null) {
            shopsView.showLoadShopsProgress();
        }

        shopsModel.getUserShops(userId);
    }

    @Override
    public void setView(ShopsMvp.View view) {
        this.shopsView = view;
    }

    @Override
    public void onShopsLoaded(List<ShopDvo> shops) {
        Log.i(TAG, "get user shops: " + shops.size());

        if (shopsView != null) {
            shopsView.setShops(shops);
            shopsView.hideLoadShopsProgress();
        }
    }

    @Override
    public void onShopsLoadError(Throwable error) {
        if (shopsView != null) {
            shopsView.showLoadShopsError(error);
            shopsView.hideLoadShopsProgress();
        }
    }

    @Override
    public void onLoadShopsCompleted() {
        shopsView.hideLoadShopsProgress();
    }
}
