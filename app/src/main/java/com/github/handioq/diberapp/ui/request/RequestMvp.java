package com.github.handioq.diberapp.ui.request;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dvo.RequestDvo;

public interface RequestMvp {

    interface Model extends BaseMvp.Model {

        void getRequestDetails(long requestId);

        void setCallback(Callback callback);

        interface Callback {

            void onRequestLoaded(RequestDvo request);

            void onRequestLoadError(Throwable error);

            void onRequestLoadCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void setRequest(RequestDvo request);

        void showLoadRequestProgress();

        void hideLoadRequestProgress();

        void showLoadRequestError(Throwable error);

    }

    interface Presenter extends BaseMvp.Presenter<RequestMvp.View> {

        void getRequestDetails(long requestId);

    }

}
