package com.github.handioq.diberapp.ui.interaction.new_shop;

import com.github.handioq.diberapp.model.dto.ShopDto;
import com.github.handioq.diberapp.model.dvo.ShopDvo;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.util.Mapper;

import rx.Subscriber;

public class NewShopModel implements NewShopMvp.Model {

    private final static String TAG = "NewShopModel";

    private final NetworkService networkService;
    private NewShopModel.Callback callback;

    public NewShopModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void addShop(long userId, ShopDto shopDto) {
        networkService.getApiService()
                .addShop(userId, shopDto)
                .map(Mapper::mapShopToDvo)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<ShopDvo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onShopAddError(e);
                    }

                    @Override
                    public void onNext(ShopDvo shopDvo) {
                        callback.onShopAdded(shopDvo);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

}