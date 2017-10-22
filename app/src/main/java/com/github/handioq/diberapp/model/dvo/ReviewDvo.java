package com.github.handioq.diberapp.model.dvo;

public class ReviewDvo {

    private String review;
    private int rating;
    private UserDvo courier;
    private UserDvo user;

    public ReviewDvo() { }

    public ReviewDvo(String review, int rating, UserDvo courier, UserDvo user) {
        this.review = review;
        this.rating = rating;
        this.courier = courier;
        this.user = user;
    }

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

    public UserDvo getCourer() {
        return courier;
    }

    public void setCourer(UserDvo courier) {
        this.courier = courier;
    }

    public UserDvo getUser() {
        return user;
    }

    public void setUser(UserDvo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ReviewDvo{" +
                "review='" + review + '\'' +
                ", rating=" + rating +
                ", courer=" + courier +
                ", user=" + user +
                '}';
    }
}
