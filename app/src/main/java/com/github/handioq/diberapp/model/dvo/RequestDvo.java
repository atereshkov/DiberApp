package com.github.handioq.diberapp.model.dvo;

public class RequestDvo {

    private long id;
    private OrderDvo order;
    private UserDvo courier;

    public RequestDvo() { }

    public RequestDvo(long id, OrderDvo order, UserDvo courier) {
        this.id = id;
        this.order = order;
        this.courier = courier;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
