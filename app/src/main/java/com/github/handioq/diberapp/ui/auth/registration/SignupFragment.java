package com.github.handioq.diberapp.ui.auth.registration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.application.DiberApp;
import com.github.handioq.diberapp.base.BaseFragment;
import com.github.handioq.diberapp.model.dto.RegisterDto;
import com.github.handioq.diberapp.model.dvo.UserDvo;
import com.github.handioq.diberapp.util.ErrorUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SignupFragment extends BaseFragment implements SignupMvp.View {

    public static final String TAG = "SignupFragment";

    @BindView(R.id.signup_email)
    AutoCompleteTextView emailTextView;

    @BindView(R.id.signup_fullname)
    AutoCompleteTextView fullnameTextView;

    @BindView(R.id.signup_name)
    AutoCompleteTextView nameTextView;

    @BindView(R.id.signup_password)
    EditText passwordEditText;

    @BindView(R.id.signup_repeat_password)
    EditText repeatPasswordEditText;

    @BindView(R.id.signup_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.signup_button)
    Button signupButton;

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
    public void signupSuccess(UserDvo userDvo) {
        Log.i(TAG, "Register success. User: " + userDvo.toString());

        // TODO remake this functionality: make call to getting token after registration, write to auth preferences

        //Intent intent = OrdersActivity.makeIntent(getContext(), authPreferences.getUserId());
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        //startActivity(intent);

        Toast.makeText(getContext(), "Registration successful! Please login", Toast.LENGTH_LONG).show();
        getActivity().finish();
    }

    @Override
    public void signupFailure(Throwable e) {
        Toast.makeText(getContext(), ErrorUtils.getMessage(e), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        signupButton.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        signupButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCompleted() {

    }


    @OnClick(R.id.signup_button)
    public void signupButtonOnClick() {

        String email = emailTextView.getText().toString();
        String password = passwordEditText.getText().toString();
        String fullname = fullnameTextView.getText().toString();
        String username = nameTextView.getText().toString();

        RegisterDto registerDto = new RegisterDto(email, username, true, fullname, password, true);
        signupPresenter.signupValidate(registerDto);
    }

    @OnClick(R.id.have_account)
    public void haveAccountClick() {
        getActivity().finish();
    }

}
