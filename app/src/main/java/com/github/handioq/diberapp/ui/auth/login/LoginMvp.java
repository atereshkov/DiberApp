package com.github.handioq.diberapp.ui.auth.login;

import com.github.handioq.diberapp.base.BaseMvp;

public interface LoginMvp {

    interface Model extends BaseMvp.Model {

        void login(String login, String password);

        void loadUserInfo();

        void setCallback(Callback callback);

        interface Callback {

            void onSuccess();

            void onError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void loginSuccess();

        void loginFailure(Throwable e);

        void showProgress();

        void hideProgress();

        void onCompleted();

    }

    interface Presenter extends BaseMvp.Presenter<LoginMvp.View> {

        void loginValidate(String login, String password);

    }

}
