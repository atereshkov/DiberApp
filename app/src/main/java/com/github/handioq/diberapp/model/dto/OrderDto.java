package com.github.handioq.diberapp.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    @SerializedName("id")
    private long id;

    @SerializedName("date")
    private long date;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private Double price;

    @SerializedName("status")
    private String status;

    @SerializedName("addressFrom")
    private AddressDto addressFrom;

    @SerializedName("addressTo")
    private AddressDto addressTo;

    @SerializedName("courier")
    private UserDto courier;

    @SerializedName("customer")
    private UserDto customer;

    public AddressDto getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(AddressDto addressFrom) {
        this.addressFrom = addressFrom;
    }

    public AddressDto getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(AddressDto addressTo) {
        this.addressTo = addressTo;
    }

    public UserDto getCourier() {
        return courier;
    }

    public void setCourier(UserDto courier) {
        this.courier = courier;
    }

    public UserDto getCustomer() {
        return customer;
    }

    public void setCustomer(UserDto customer) {
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
