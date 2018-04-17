package com.github.handioq.diberapp.model.dvo;

import java.util.ArrayList;
import java.util.List;

public class AddressListDvo {

    private List<AddressDvo> addresses = new ArrayList<>();

    public AddressListDvo() {
    }

    public List<AddressDvo> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDvo> addresses) {
        this.addresses = addresses;
    }
}
