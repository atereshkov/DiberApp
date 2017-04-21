package com.github.handioq.diberapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class ShopDto {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("address")
    private String address;

    public ShopDto() {
    }

    public ShopDto(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public ShopDto(String address, String name) {
        this.address = address;
        this.name = name;
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
        return "ShopDto{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
