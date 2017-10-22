package com.github.handioq.diberapp.ui.auth.registration;

import android.util.Log;

import com.github.handioq.diberapp.model.dto.RegisterDto;
import com.github.handioq.diberapp.model.dvo.UserDvo;
import com.github.handioq.diberapp.network.NetworkService;
import com.github.handioq.diberapp.util.Mapper;

import rx.Subscriber;

public class SignupModel implements SignupMvp.Model {

    private final static String TAG = "SignupModel";

    private final NetworkService networkService;
    private SignupModel.Callback callback;

    public SignupModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getSignupState(RegisterDto registerDto) {

        networkService.getApiService()
                .signup(registerDto)
                .map(Mapper::mapUserToDvo)
                .compose(NetworkService.applyScheduler())
                .subscribe(new Subscriber<UserDvo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.toString());
                        callback.onError(e);
                    }

                    @Override
                    public void onNext(UserDvo userDvo) {
                        Log.i(TAG, "Success registration: " + userDvo.toString());
                        callback.onSuccess(userDvo);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
