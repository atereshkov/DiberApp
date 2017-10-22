package com.github.handioq.diberapp.ui.reviews;

import android.util.Log;

import com.github.handioq.diberapp.model.dvo.ReviewDvo;
import com.github.handioq.diberapp.network.NetworkService;

import java.util.List;

import javax.inject.Inject;

public class ReviewsPresenter implements ReviewsMvp.Presenter, ReviewsMvp.Model.Callback {

    private ReviewsMvp.View reviewsView;
    private ReviewsMvp.Model reviewsModel;
    private NetworkService networkService;

    private final static String TAG = "ReviewsPresenter";

    @Inject
    public ReviewsPresenter(NetworkService networkService) {
        reviewsModel = new ReviewsModel(networkService);
        reviewsModel.setCallback(this);
    }

    @Override
    public void getUserReviews(long userId) {
        if (reviewsView != null) {
            reviewsView.showLoadReviewsProgress();
        }

        reviewsModel.getUserReviews(userId);
    }

    @Override
    public void setView(ReviewsMvp.View view) {
        this.reviewsView = view;
    }

    @Override
    public void onReviewsLoaded(List<ReviewDvo> reviews) {
        Log.i(TAG, "get user reviews: " + reviews.size());

        if (reviewsView != null) {
            reviewsView.setReviews(reviews);
            reviewsView.hideLoadReviewsProgress();
        }
    }

    @Override
    public void onReviewsLoadError(Throwable error) {
        if (reviewsView != null) {
            reviewsView.showLoadReviewsError(error);
            reviewsView.hideLoadReviewsProgress();
        }
    }

    @Override
    public void onLoadReviewsCompleted() {
        reviewsView.hideLoadReviewsProgress();
    }
}