package com.github.handioq.diberapp.base.event;

import com.github.handioq.diberapp.model.dvo.AddressDvo;

public class AddressRemovedEvent {

    private final AddressDvo addressDvo;

    public AddressRemovedEvent(AddressDvo addressDvo) {
        this.addressDvo = addressDvo;
    }

    public AddressDvo getAddressDvo() {
        return addressDvo;
    }
}