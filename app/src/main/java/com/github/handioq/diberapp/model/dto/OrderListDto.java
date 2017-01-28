package com.github.handioq.diberapp.model.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderListDto {

    private List<OrderDto> orders = new ArrayList<>();

    public OrderListDto(List<OrderDto> orders) {
        this.orders = orders;
    }

    public OrderListDto() {
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderListDto{" +
                "orders=" + orders +
                '}';
    }
}
