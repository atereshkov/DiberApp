package com.github.handioq.diberapp.ui.addresses;

import android.util.Log;

import com.github.handioq.diberapp.model.dvo.AddressListDvo;
import com.github.handioq.diberapp.network.NetworkService;

import javax.inject.Inject;

public class AddressesPresenter implements AddressesMvp.Presenter, AddressesMvp.Model.Callback {

    private AddressesMvp.View addressesView;
    private AddressesMvp.Model addressesModel;
    private NetworkService networkService;

    private final static String TAG = "AddressesPresenter";

    @Inject
    public AddressesPresenter(NetworkService networkService) {
        addressesModel = new AddressesModel(networkService);
        addressesModel.setCallback(this);
    }

    @Override
    public void setView(AddressesMvp.View view) {
        this.addressesView = view;
    }

    @Override
    public void onAddressesLoaded(AddressListDvo addresses) {
        Log.i(TAG, "get user addresses: " + addresses.getAddresses().size());

        if (addressesView != null) {
            addressesView.setAddresses(addresses);
            addressesView.hideLoadAddressesProgress();
        }
    }

    @Override
    public void onAddressesLoadError(Throwable error) {
        if (addressesView != null) {
            addressesView.showLoadAddressesError(error);
            addressesView.hideLoadAddressesProgress();
        }
    }

    @Override
    public void onLoadAddressesCompleted() {
        addressesView.hideLoadAddressesProgress();
    }

    @Override
    public void getUserAddresses(long userId) {
        if (addressesView != null) {
            addressesView.showLoadAddressesProgress();
        }

        addressesModel.getUserAddresses(userId);
    }
}
