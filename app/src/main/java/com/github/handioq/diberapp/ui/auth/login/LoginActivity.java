package com.github.handioq.diberapp.ui.auth.login;

import android.os.Bundle;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.base.BaseToolbarActivity;

public class LoginActivity extends BaseToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, new LoginFragment())
                    .commit();
        }
    }


}