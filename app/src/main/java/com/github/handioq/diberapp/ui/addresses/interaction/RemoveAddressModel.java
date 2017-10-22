package com.github.handioq.diberapp.ui.addresses.interaction;

import com.github.handioq.diberapp.model.dto.ResponseDto;
import com.github.handioq.diberapp.network.NetworkService;

import rx.Subscriber;

public class RemoveAddressModel implements RemoveAddressMvp.Model {

    private final NetworkService networkService;
    private RemoveAddressMvp.Model.Callback callback;

    private final static String TAG = "RemoveAddressModel";

    public RemoveAddressModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void removeAddress(long userId, int addressId) {
        networkService.getApiService()
                .removeAddress(userId, addressId)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<ResponseDto>() {
                    @Override
                    public void onCompleted() {
                        callback.onRemoveCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onAddressRemoveError(e);
                    }

                    @Override
                    public void onNext(ResponseDto responseDto) {
                        callback.onAddressRemoved();
                    }
                });
    }

    @Override
    public void setCallback(RemoveAddressMvp.Model.Callback callback) {
        this.callback = callback;
    }

}
