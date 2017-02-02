package com.github.handioq.diberapp.ui.auth.registration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.application.DiberApp;
import com.github.handioq.diberapp.base.BaseFragment;
import com.github.handioq.diberapp.model.dto.UserDto;

import javax.inject.Inject;

public class SignupFragment extends BaseFragment implements SignupMvp.View {

    public static final String TAG = "SignupFragment";

    @Inject
    SignupMvp.Presenter signupPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((DiberApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        signupPresenter.setView(this);
    }

    @Override
    public void signupSuccess(UserDto userDto) {

    }

    @Override
    public void signupFailure(Throwable e) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onCompleted() {

    }
}
