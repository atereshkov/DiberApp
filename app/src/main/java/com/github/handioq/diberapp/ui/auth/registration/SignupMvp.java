package com.github.handioq.diberapp.ui.auth.registration;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dto.UserDto;

public interface SignupMvp {

    interface Model extends BaseMvp.Model {

        void getSignupState(UserDto userDto);

        void setCallback(Callback callback);

        interface Callback {

            void onSuccess(UserDto userDto);

            void onError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void signupSuccess(UserDto userDto);

        void signupFailure(Throwable e);

        void showProgress();

        void hideProgress();

        void onCompleted();

    }

    interface Presenter extends BaseMvp.Presenter<SignupMvp.View> {

        void signupValidate(UserDto userDto);

    }

}
