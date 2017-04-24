package com.github.handioq.diberapp.ui.requests;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dvo.RequestDvo;

import java.util.List;

public interface RequestsMvp {

    interface Model extends BaseMvp.Model {

        void getOrderRequests(long orderId);

        void setCallback(Callback callback);

        interface Callback {

            void onRequestsLoaded(List<RequestDvo> requests);

            void onRequestsLoadError(Throwable error);

            void onLoadRequestsCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void showLoadRequestsProgress();

        void hideLoadRequestsProgress();

        void setRequests(List<RequestDvo> requests);

        void showLoadRequestsError(Throwable error);

    }

    interface Presenter extends BaseMvp.Presenter<RequestsMvp.View> {

        void getOrderRequests(long orderId);

    }

}
