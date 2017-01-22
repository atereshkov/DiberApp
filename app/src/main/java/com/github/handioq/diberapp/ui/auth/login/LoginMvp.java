package com.github.handioq.diberapp.ui.auth.login;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dto.AuthResponseDto;

public interface LoginMvp {

    interface Model extends BaseMvp.Model {

        void login(String login, String password);

        void setCallback(Callback callback);

        interface Callback {

            void onSuccess(AuthResponseDto authResponseDto);

            void onError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void loginSuccess(AuthResponseDto authResponseDto);

        void loginFailure(Throwable e);

        void showProgress();

        void hideProgress();

        void onCompleted();

    }

    interface Presenter extends BaseMvp.Presenter<LoginMvp.View> {

        void loginValidate(String login, String password);

    }

}
