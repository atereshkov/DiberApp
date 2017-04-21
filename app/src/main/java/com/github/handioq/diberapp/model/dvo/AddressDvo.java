package com.github.handioq.diberapp.model.dvo;

public class AddressDvo {

    private int id;
    private String name;
    private int postalCode;
    private String country;
    private String city;
    private String region;
    private String address;
    private String phone;

    public AddressDvo() {
    }

    public AddressDvo(int id, String name, int postalCode, String country, String city, String region, String address, String phone) {
        this.id = id;
        this.name = name;
        this.postalCode = postalCode;
        this.country = country;
        this.city = city;
        this.region = region;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AddressDvo{" +
                "name='" + name + '\'' +
                ", postalCode=" + postalCode +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
