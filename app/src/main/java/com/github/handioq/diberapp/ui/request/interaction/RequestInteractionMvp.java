package com.github.handioq.diberapp.ui.request.interaction;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dto.RequestStatusDto;
import com.github.handioq.diberapp.model.dvo.RequestDvo;

public interface RequestInteractionMvp {

    interface Model extends BaseMvp.Model {
        void acceptRequest(long requestId, RequestStatusDto requestStatusDto);
        void declineRequest(long requestId, RequestStatusDto requestStatusDto);
        void setCallback(Callback callback);

        interface Callback {
            void onRequestAccepted(RequestDvo request);
            void onRequestAcceptError(Throwable error);
            void onRequestAcceptionCompleted();

            void onRequestDeclined(RequestDvo request);
            void onRequestDeclineError(Throwable error);
            void onRequestDeclineCompleted();
        }
    }

    interface View extends BaseMvp.View {
        void onRequestAcceptSuccess(RequestDvo request);
        void onRequestAcceptError(Throwable e);

        void onRequestDeclineSuccess(RequestDvo request);
        void onRequestDeclineError(Throwable e);

        void showRequestInteractionProgress();
        void hideRequestInteractionProgress();
    }

    interface Presenter extends BaseMvp.Presenter <RequestInteractionMvp.View> {
        void acceptRequest(long requestId, RequestStatusDto requestStatusDto);
        void declineRequest(long requestId, RequestStatusDto requestStatusDto);
    }

}
