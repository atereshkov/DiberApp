package com.github.handioq.diberapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class AddressDto {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("postalCode")
    private int postalCode;

    @SerializedName("country")
    private String country;

    @SerializedName("city")
    private String city;

    @SerializedName("region")
    private String region;

    @SerializedName("address")
    private String address;

    @SerializedName("phone")
    private String phone;

    public AddressDto(String name, int postalCode, String country, String city, String region, String address, String phone) {
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
}
