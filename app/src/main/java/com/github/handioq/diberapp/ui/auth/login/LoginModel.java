package com.github.handioq.diberapp.ui.auth.login;

import android.util.Log;

import com.github.handioq.diberapp.model.dto.AuthResponseDto;
import com.github.handioq.diberapp.model.dto.UserInfoDto;
import com.github.handioq.diberapp.network.NetworkConstants;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.util.AuthPreferences;

import rx.Subscriber;

public class LoginModel implements LoginMvp.Model {

    private final static String TAG = "LoginModel";

    private final NetworkService networkService;
    private final AuthPreferences authPreferences;
    private LoginModel.Callback callback;

    public LoginModel(NetworkService networkService, AuthPreferences authPreferences) {
        this.networkService = networkService;
        this.authPreferences = authPreferences;
    }

    @Override
    public void login(String login, String password) {

        networkService.getApiService()
                .login(login, password, NetworkConstants.GRANT_TYPE_VALUE, NetworkConstants.CLIENT_ID_VALUE)
                .compose(NetworkService.<AuthResponseDto>applyScheduler())
                .subscribe(new Subscriber<AuthResponseDto>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                        Log.e(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(AuthResponseDto authResponse) {
                        Log.i(TAG, "onSuccess: " + authResponse);

                        authPreferences.setUserToken(authResponse.getAccessToken());
                        authPreferences.setUserRefreshToken(authResponse.getRefreshToken());

                        // get user id and data from token
                        loadUserInfo();
                    }
                });
    }

    @Override
    public void loadUserInfo() {

        networkService.getApiService()
                .getUserInfo()
                .compose(NetworkService.<UserInfoDto>applyScheduler())
                .subscribe(new Subscriber<UserInfoDto>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                        Log.e(TAG, "onError loadUserInfo: " + e.toString());
                    }

                    @Override
                    public void onNext(UserInfoDto userInfoDto) {
                        Log.i(TAG, "get user info: " + userInfoDto.toString());
                        authPreferences.setUserId(userInfoDto.getId());
                        authPreferences.setUserEmail(userInfoDto.getEmail());
                        authPreferences.setUserFullname(userInfoDto.getFullname());

                        callback.onSuccess();
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
