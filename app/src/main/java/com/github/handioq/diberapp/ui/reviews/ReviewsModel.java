package com.github.handioq.diberapp.ui.reviews;

import com.github.handioq.diberapp.model.dvo.ReviewDvo;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.util.Mapper;

import java.util.List;

import rx.Subscriber;

public class ReviewsModel implements ReviewsMvp.Model {

    private final NetworkService networkService;
    private ReviewsMvp.Model.Callback callback;

    private final static String TAG = "ReviewsModel";

    public ReviewsModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getUserReviews(long userId) {
        networkService.getApiService()
                .getUserReviews(userId)
                .map(Mapper::mapReviewsToDvo)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<List<ReviewDvo>>() {
                    @Override
                    public void onCompleted() {
                        callback.onLoadReviewsCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onReviewsLoadError(e);
                    }

                    @Override
                    public void onNext(List<ReviewDvo> reviews) {
                        callback.onReviewsLoaded(reviews);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}