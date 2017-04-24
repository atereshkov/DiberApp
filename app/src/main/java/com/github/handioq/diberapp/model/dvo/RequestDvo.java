package com.github.handioq.diberapp.model.dvo;

public class RequestDvo {

    private OrderDvo order;
    private UserDvo courier;

    public RequestDvo() { }

    public RequestDvo(OrderDvo order, UserDvo courier) {
        this.order = order;
        this.courier = courier;
    }

    public OrderDvo getOrder() {
        return order;
    }

    public void setOrder(OrderDvo order) {
        this.order = order;
    }

    public UserDvo getCourier() {
        return courier;
    }

    public void setCourier(UserDvo courier) {
        this.courier = courier;
    }

    @Override
    public String toString() {
        return "RequestDvo{" +
                "order=" + order +
                ", courier=" + courier +
                '}';
    }
}
