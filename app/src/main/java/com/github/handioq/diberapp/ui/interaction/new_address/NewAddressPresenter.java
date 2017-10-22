package com.github.handioq.diberapp.ui.interaction.new_address;

import com.github.handioq.diberapp.model.dto.AddressDto;
import com.github.handioq.diberapp.model.dvo.AddressDvo;
import com.github.handioq.diberapp.network.NetworkService;

import javax.inject.Inject;

public class NewAddressPresenter implements NewAddressMvp.Presenter, NewAddressMvp.Model.Callback {

    private final static String TAG = "NewAddressPresenter";

    private NetworkService networkService;
    private NewAddressMvp.View view;
    private NewAddressModel model;

    @Inject
    public NewAddressPresenter(NetworkService networkService) {
        model = new NewAddressModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void onAddressAdded(AddressDvo addressDvo) {
        if (view != null) {
            view.onAddressAdded(addressDvo);
            view.hideAddAddressProgress();
        }
    }

    @Override
    public void onAddressAddError(Throwable error) {
        if (view != null) {
            view.onAddAddressError(error);
            view.hideAddAddressProgress();
        }
    }

    @Override
    public void onAddAddressCompleted() {
        view.hideAddAddressProgress();
    }

    @Override
    public void addAddress(long userId, AddressDto addressDto) {
        if (view != null) {
            view.showAddAddressProgress();
        }

        model.addAddress(userId, addressDto);
    }

    @Override
    public void setView(NewAddressMvp.View view) {
        this.view = view;
    }

}