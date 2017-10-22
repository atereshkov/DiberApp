package com.github.handioq.diberapp.model.dvo;

public class ShopDvo {

    private int id;
    private String name;
    private String address;

    public ShopDvo() {
    }

    public ShopDvo(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
