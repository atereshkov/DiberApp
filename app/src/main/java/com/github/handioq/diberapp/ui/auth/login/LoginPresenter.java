package com.github.handioq.diberapp.ui.auth.login;

import com.github.handioq.diberapp.model.dto.AuthResponseDto;
import com.github.handioq.diberapp.network.NetworkService;

import javax.inject.Inject;

public class LoginPresenter implements LoginMvp.Presenter, LoginMvp.Model.Callback {

    private NetworkService networkService;
    private LoginMvp.View view;
    private LoginModel model;

    private final static String TAG = "LoginPresenter";

    @Inject
    public LoginPresenter(NetworkService networkService) {
        model = new LoginModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void setView(LoginMvp.View view) {
        this.view = view;
    }

    @Override
    public void onSuccess(AuthResponseDto authResponseDto) {
        if (view != null) {
            view.loginSuccess(authResponseDto);
            view.hideProgress();
        }
    }

    @Override
    public void onError(Throwable error) {
        if (view != null) {
            view.loginFailure(error);
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
    public void loginValidate(String login, String password) {
        if (view != null) {
            view.showProgress();
        }

        model.login(login, password);
    }
}
