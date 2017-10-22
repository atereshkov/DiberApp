package com.github.handioq.diberapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class RequestDto {

    @SerializedName("id")
    private long id;

    @SerializedName("status")
    private String status;

    @SerializedName("order")
    private OrderDto order;

    @SerializedName("courier")
    private UserDto courier;

    public RequestDto() { }

    public RequestDto(long id, OrderDto order, UserDto courier, String status) {
        this.id = id;
        this.order = order;
        this.courier = courier;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
