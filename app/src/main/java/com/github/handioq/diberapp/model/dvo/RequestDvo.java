package com.github.handioq.diberapp.model.dvo;

public class RequestDvo {

    private long id;
    private OrderDvo order;
    private UserDvo courier;
    private String status;

    public RequestDvo() { }

    public RequestDvo(long id, OrderDvo order, UserDvo courier, String status) {
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
