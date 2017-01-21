package com.github.handioq.diberapp.model.dvo;

import java.util.ArrayList;
import java.util.List;

public class OrderListDvo {

    private List<OrderDvo> orders = new ArrayList<>();

    public OrderListDvo(List<OrderDvo> orders) {
        this.orders = orders;
    }

    public OrderListDvo() {
    }

    public List<OrderDvo> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDvo> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderListDvo{" +
                "orders=" + orders +
                '}';
    }
}
