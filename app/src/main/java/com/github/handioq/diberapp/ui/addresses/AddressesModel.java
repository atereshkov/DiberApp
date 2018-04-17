package com.github.handioq.diberapp.ui.addresses;

import com.github.handioq.diberapp.model.dto.PageableAddressListDto;
import com.github.handioq.diberapp.model.dvo.AddressDvo;
import com.github.handioq.diberapp.model.dvo.AddressListDvo;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.util.Mapper;

import java.util.List;

import rx.Subscriber;

public class AddressesModel implements AddressesMvp.Model {

    private final NetworkService networkService;
    private AddressesMvp.Model.Callback callback;

    private final static String TAG = "AddressesModel";

    public AddressesModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getUserAddresses(long userId) {
        networkService.getApiService()
                .getUserAddresses(userId)
                .map(Mapper::mapAddressListToDvo)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<AddressListDvo>() {
                    @Override
                    public void onCompleted() {
                        callback.onLoadAddressesCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onAddressesLoadError(e);
                    }

                    @Override
                    public void onNext(AddressListDvo addresses) {
                        callback.onAddressesLoaded(addresses);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
