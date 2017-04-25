package com.github.handioq.diberapp.ui.reviews;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dvo.ReviewDvo;

import java.util.List;

public interface ReviewsMvp {

    interface Model extends BaseMvp.Model {

        void getUserReviews(long userId);

        void setCallback(Callback callback);

        interface Callback {

            void onReviewsLoaded(List<ReviewDvo> reviews);

            void onReviewsLoadError(Throwable error);

            void onLoadReviewsCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void showLoadReviewsProgress();

        void hideLoadReviewsProgress();

        void setReviews(List<ReviewDvo> reviews);

        void showLoadReviewsError(Throwable error);

    }

    interface Presenter extends BaseMvp.Presenter<ReviewsMvp.View> {

        void getUserReviews(long userId);

    }

}
