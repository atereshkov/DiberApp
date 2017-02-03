package com.github.handioq.diberapp.ui.auth.registration;

import com.github.handioq.diberapp.base.BaseMvp;
import com.github.handioq.diberapp.model.dto.RegisterDto;
import com.github.handioq.diberapp.model.dto.UserDto;
import com.github.handioq.diberapp.model.dvo.UserDvo;

public interface SignupMvp {

    interface Model extends BaseMvp.Model {

        void getSignupState(RegisterDto userDto);

        void setCallback(Callback callback);

        interface Callback {

            void onSuccess(UserDvo userDvo);

            void onError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends BaseMvp.View {

        void signupSuccess(UserDvo userDvo);

        void signupFailure(Throwable e);

        void showProgress();

        void hideProgress();

        void onCompleted();

    }

    interface Presenter extends BaseMvp.Presenter<SignupMvp.View> {

        void signupValidate(RegisterDto userDto);

    }

}
