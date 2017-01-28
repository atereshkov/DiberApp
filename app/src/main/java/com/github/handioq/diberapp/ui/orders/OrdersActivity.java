package com.github.handioq.diberapp.ui.orders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.base.BaseDrawerActivity;

public class OrdersActivity extends BaseDrawerActivity {

    private static final String KEY_USER_ID = "user";
    private long userId;

    public static Intent makeIntent(Context context, long userId){
        Intent intent = new Intent(context, OrdersActivity.class);
        intent.putExtra(KEY_USER_ID, userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        if (getIntent().hasExtra(KEY_USER_ID)) {
            userId = getIntent().getExtras().getLong(KEY_USER_ID);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, OrdersFragment.newInstance(userId))
                    .commit();
        }
    }


}
