package com.github.handioq.diberapp.ui.shops.interaction;

import com.github.handioq.diberapp.model.dto.ResponseDto;
import com.github.handioq.diberapp.network.NetworkService;

import rx.Subscriber;

public class RemoveShopModel implements RemoveShopMvp.Model {

    private final NetworkService networkService;
    private RemoveShopMvp.Model.Callback callback;

    private final static String TAG = "RemoveAddressModel";

    public RemoveShopModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void removeShop(long userId, int shopId) {
        networkService.getApiService()
                .removeShop(userId, shopId)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<ResponseDto>() {
                    @Override
                    public void onCompleted() {
                        callback.onRemoveCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onShopRemoveError(e);
                    }

                    @Override
                    public void onNext(ResponseDto responseDto) {
                        callback.onShopRemoved();
                    }
                });
    }

    @Override
    public void setCallback(RemoveShopMvp.Model.Callback callback) {
        this.callback = callback;
    }

}