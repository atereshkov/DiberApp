package com.github.handioq.diberapp.ui.auth.registration;

import com.github.handioq.diberapp.model.dto.RegisterDto;
import com.github.handioq.diberapp.model.dvo.UserDvo;
import com.github.handioq.diberapp.network.NetworkService;

import javax.inject.Inject;

public class SignupPresenter implements SignupMvp.Presenter, SignupMvp.Model.Callback {

    private final static String TAG = "SignupPresenter";

    private NetworkService networkService;
    private SignupMvp.View view;
    private SignupModel model;

    @Inject
    public SignupPresenter(NetworkService networkService) {
        model = new SignupModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void onSuccess(UserDvo userDvo) {
        if (view != null) {
            view.signupSuccess(userDvo);
            view.hideProgress();
        }
    }

    @Override
    public void onError(Throwable error) {
        if (view != null) {
            view.signupFailure(error);
            view.hideProgress();
        }
    }

    @Override
    public void onCompleted() {
        if (view != null) {
            view.onCompleted();
        }
    }

    @Override
    public void signupValidate(RegisterDto registerDto) {
        if (view != null) {
            view.showProgress();
        }

        model.getSignupState(registerDto);
    }

    @Override
    public void setView(SignupMvp.View view) {
        this.view = view;
    }
}
