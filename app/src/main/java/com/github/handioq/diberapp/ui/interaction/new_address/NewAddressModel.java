package com.github.handioq.diberapp.ui.interaction.new_address;

import com.github.handioq.diberapp.model.dto.AddressDto;
import com.github.handioq.diberapp.model.dvo.AddressDvo;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.util.Mapper;

import rx.Subscriber;

public class NewAddressModel implements NewAddressMvp.Model {

    private final static String TAG = "NewAddressModel";

    private final NetworkService networkService;
    private NewAddressModel.Callback callback;

    public NewAddressModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void addAddress(long userId, AddressDto addressDto) {
        networkService.getApiService()
                .addAddress(userId, addressDto)
                .map(Mapper::mapAddressToDvo)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<AddressDvo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onAddressAddError(e);
                    }

                    @Override
                    public void onNext(AddressDvo addressDvo) {
                        callback.onAddressAdded(addressDvo);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

}