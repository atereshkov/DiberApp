package com.github.handioq.diberapp.ui.request.interaction;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dto.RequestStatusDto;
import com.github.handioq.diberapp.model.dvo.RequestDvo;

public interface AcceptRequestMvp {

    interface Model extends BaseMvp.Model {

        void acceptRequest(long requestId, RequestStatusDto requestStatusDto);

        void setCallback(Callback callback);

        interface Callback {

            void onRequestAccepted(RequestDvo request);

            void onRequestAcceptError(Throwable error);

            void onRequestAcceptionCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void onRequestAcceptSuccess(RequestDvo request);

        void onRequestAcceptError(Throwable e);

        void showAcceptRequestProgress();

        void hideAcceptRequestProgress();

    }

    interface Presenter extends BaseMvp.Presenter <AcceptRequestMvp.View> {

        void acceptRequest(long requestId, RequestStatusDto requestStatusDto);

    }

}
