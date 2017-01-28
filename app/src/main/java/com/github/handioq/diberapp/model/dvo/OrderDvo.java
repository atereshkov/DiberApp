package com.github.handioq.diberapp.model.dvo;

public class OrderDvo {

    private String date;
    private String description;
    private Double price;
    private String status;
    private String addressFrom;
    private String addressTo;

    public OrderDvo() { }

    public OrderDvo(String date, String description, Double price, String status, String addressFrom, String addressTo) {
        this.date = date;
        this.description = description;
        this.price = price;
        this.status = status;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
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

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    @Override
    public String toString() {
        return "OrderDvo{" +
                "date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", addressFrom='" + addressFrom + '\'' +
                ", addressTo='" + addressTo + '\'' +
                '}';
    }
}
