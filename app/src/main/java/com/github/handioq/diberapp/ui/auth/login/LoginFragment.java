package com.github.handioq.diberapp.ui.auth.login;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.application.DiberApp;
import com.github.handioq.diberapp.base.BaseFragment;
import com.github.handioq.diberapp.model.dto.AuthResponseDto;
import com.github.handioq.diberapp.ui.orders.OrdersActivity;
import com.github.handioq.diberapp.util.AuthPreferences;
import com.github.handioq.diberapp.util.ErrorUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginMvp.View {

    public static final String TAG = "LoginFragment";

    @BindView(R.id.login)
    AutoCompleteTextView loginTextView;

    @BindView(R.id.password)
    EditText passwordView;

    @BindView(R.id.login_form)
    View loginForm;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.forgot_password)
    TextView forgotPasswordView;

    @BindView(R.id.sign_in)
    Button signInButton;

    @BindView(R.id.sign_up)
    Button signUpButton;

    @Inject
    LoginMvp.Presenter loginPresenter;

    @Inject
    AuthPreferences authPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((DiberApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        loginPresenter.setView(this);
    }

    @Override
    public void loginSuccess() {
        Log.i(TAG, "Login success. User ID: " + authPreferences.getUserId());

        Intent intent = OrdersActivity.makeIntent(getContext(), authPreferences.getUserId());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void loginFailure(Throwable e) {
        Toast.makeText(getContext(), ErrorUtils.getMessage(e), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        signInButton.setVisibility(View.GONE);
        signUpButton.setEnabled(false);
        forgotPasswordView.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        signInButton.setVisibility(View.VISIBLE);
        signUpButton.setEnabled(true);
        forgotPasswordView.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCompleted() {

    }

    @OnClick(R.id.sign_in)
    void signIn() {
        String email = loginTextView.getText().toString();
        String password = passwordView.getText().toString();

        /*
        if (!Validation.isPasswordValid(password)) {
            passwordView.setError(getResources().getString(R.string.error_invalid_password));
            return;
        } else if (!Validation.isEmailValid(email)) {
            loginTextView.setError(getResources().getString(R.string.error_invalid_email));
            return;
        }
        */

        loginPresenter.loginValidate(email, password);
    }

    @OnClick(R.id.sign_up)
    void signUp() {
        Intent intent = new Intent(getContext(), OrdersActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.forgot_password)
    void onRestoreClick() {
        // TODO make restore activity
        Toast.makeText(getContext(), R.string.password_recovery_not_impl, Toast.LENGTH_SHORT).show();
    }
}
