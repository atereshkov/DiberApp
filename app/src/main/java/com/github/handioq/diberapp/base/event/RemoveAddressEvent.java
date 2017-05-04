package com.github.handioq.diberapp.base.event;

import com.github.handioq.diberapp.model.dvo.AddressDvo;

public class RemoveAddressEvent {

    private final AddressDvo addressDvo;

    public RemoveAddressEvent(AddressDvo addressDvo) {
        this.addressDvo = addressDvo;
    }

    public AddressDvo getAddressDvo() {
        return addressDvo;
    }

}