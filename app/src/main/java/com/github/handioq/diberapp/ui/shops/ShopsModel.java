package com.github.handioq.diberapp.ui.shops;

import com.github.handioq.diberapp.model.dvo.ShopDvo;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.util.Mapper;

import java.util.List;

import rx.Subscriber;

public class ShopsModel implements ShopsMvp.Model {

    private final NetworkService networkService;
    private ShopsMvp.Model.Callback callback;

    private final static String TAG = "ShopsModel";

    public ShopsModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getUserShops(long userId) {
        networkService.getApiService()
                .getUserShops(userId)
                .map(Mapper::mapShopsListToDvo)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<List<ShopDvo>>() {
                    @Override
                    public void onCompleted() {
                        callback.onLoadShopsCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onShopsLoadError(e);
                    }

                    @Override
                    public void onNext(List<ShopDvo> shops) {
                        callback.onShopsLoaded(shops);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
