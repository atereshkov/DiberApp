package com.github.handioq.diberapp.model.dvo;

public class ShopDvo {

    private String name;
    private String address;

    public ShopDvo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ShopDvo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
