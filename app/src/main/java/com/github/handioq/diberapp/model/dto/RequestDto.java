package com.github.handioq.diberapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class RequestDto {

    @SerializedName("order")
    private OrderDto order;

    @SerializedName("courier")
    private UserDto courier;

    public RequestDto() { }

    public RequestDto(OrderDto order, UserDto courier) {
        this.order = order;
        this.courier = courier;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    public UserDto getCourier() {
        return courier;
    }

    public void setCourier(UserDto courier) {
        this.courier = courier;
    }

    @Override
    public String toString() {
        return "RequestDto{" +
                "order=" + order +
                ", courier=" + courier +
                '}';
    }
}
