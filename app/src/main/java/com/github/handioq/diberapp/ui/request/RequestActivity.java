package com.github.handioq.diberapp.ui.request;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.base.BaseToolbarActivity;

public class RequestActivity extends BaseToolbarActivity {

    private static final String KEY_REQUEST_ID = "request";
    private long requestId;

    public static Intent makeIntent(Context context, long userId) {
        Intent intent = new Intent(context, RequestActivity.class);
        intent.putExtra(KEY_REQUEST_ID, userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        if (getIntent().hasExtra(KEY_REQUEST_ID)) {
            requestId = getIntent().getExtras().getLong(KEY_REQUEST_ID);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, RequestFragment.newInstance(requestId))
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}