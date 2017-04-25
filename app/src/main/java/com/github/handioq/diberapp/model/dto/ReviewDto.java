package com.github.handioq.diberapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class ReviewDto {

    @SerializedName("review")
    private String review;

    @SerializedName("rating")
    private int rating;

    @SerializedName("courier")
    private UserDto courier;

    @SerializedName("user")
    private UserDto user;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UserDto getCourer() {
        return courier;
    }

    public void setCourer(UserDto courier) {
        this.courier = courier;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "review='" + review + '\'' +
                ", rating=" + rating +
                ", courer=" + courier +
                ", user=" + user +
                '}';
    }
}