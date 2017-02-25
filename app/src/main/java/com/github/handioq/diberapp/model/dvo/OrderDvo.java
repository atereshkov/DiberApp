package com.github.handioq.diberapp.model.dvo;

public class OrderDvo {

    private String date;
    private String description;
    private Double price;
    private String status;

    public OrderDvo() { }

    public OrderDvo(String date, String description, Double price, String status) {
        this.date = date;
        this.description = description;
        this.price = price;
        this.status = status;
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
