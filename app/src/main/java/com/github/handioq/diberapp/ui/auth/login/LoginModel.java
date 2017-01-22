package com.github.handioq.diberapp.ui.auth.login;

import com.github.handioq.diberapp.model.dto.AuthResponseDto;
import com.github.handioq.diberapp.network.NetworkConstants;
import com.github.handioq.diberapp.network.NetworkService;

import rx.Subscriber;

public class LoginModel implements LoginMvp.Model {

    private final NetworkService networkService;
    private LoginModel.Callback callback;

    public LoginModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void login(String login, String password) {

        networkService.getApiService()
                .login(login, password, NetworkConstants.GRANT_TYPE_VALUE, NetworkConstants.CLIENT_ID_VALUE)
                .compose(NetworkService.<AuthResponseDto>applyScheduler())
                .subscribe(new Subscriber<AuthResponseDto>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onNext(AuthResponseDto authResponse) {
                        callback.onSuccess(authResponse);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
