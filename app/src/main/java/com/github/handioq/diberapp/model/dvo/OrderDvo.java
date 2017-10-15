package com.github.handioq.diberapp.model.dvo;

public class OrderDvo {

    private long id;
    private long date;
    private String description;
    private Double price;
    private String status;
    private AddressDvo addressTo;
    private AddressDvo addressFrom;
    private UserDvo courier;
    private UserDvo customer;

    public OrderDvo() { }

    public OrderDvo(long id, long date, String description, Double price, String status) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public OrderDvo(long id, long date, String description, Double price, String status, AddressDvo addressTo, AddressDvo addressFrom, UserDvo courier, UserDvo customer) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.price = price;
        this.status = status;
        this.courier = courier;
        this.customer = customer;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
    }

    public AddressDvo getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(AddressDvo addressTo) {
        this.addressTo = addressTo;
    }

    public AddressDvo getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(AddressDvo addressFrom) {
        this.addressFrom = addressFrom;
    }

    public UserDvo getCourier() {
        return courier;
    }

    public void setCourier(UserDvo courier) {
        this.courier = courier;
    }

    public UserDvo getCustomer() {
        return customer;
    }

    public void setCustomer(UserDvo customer) {
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

    @Override
    public String toString() {
        return "OrderDvo{" +
                "date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
