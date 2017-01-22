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

    }

    @Override
    public void onError(Throwable error) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void loginValidate(String login, String password) {

    }
}
