package com.github.handioq.diberapp.model.dvo;

public class OrderDvo {

    private long id;
    private String date;
    private String description;
    private Double price;
    private String status;
    private ShopDvo shop;
    private AddressDvo address;
    private UserDvo courier;
    private UserDvo customer;

    public OrderDvo() { }

    public OrderDvo(long id, String date, String description, Double price, String status) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public OrderDvo(long id, String date, String description, Double price, String status, ShopDvo shop, AddressDvo address, UserDvo courier, UserDvo customer) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.price = price;
        this.status = status;
        this.shop = shop;
        this.address = address;
        this.courier = courier;
        this.customer = customer;
    }

    public ShopDvo getShop() {
        return shop;
    }

    public void setShop(ShopDvo shop) {
        this.shop = shop;
    }

    public AddressDvo getAddress() {
        return address;
    }

    public void setAddress(AddressDvo address) {
        this.address = address;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
