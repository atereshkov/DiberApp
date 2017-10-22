package com.github.handioq.diberapp.ui.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.base.BaseDrawerActivity;
import com.github.handioq.diberapp.base.BaseToolbarActivity;

public class OrderActivity extends BaseToolbarActivity {

    private static final String KEY_ORDER_ID = "order";
    private long orderId;

    public static Intent makeIntent(Context context, long userId){
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra(KEY_ORDER_ID, userId);
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

        if (getIntent().hasExtra(KEY_ORDER_ID)) {
            orderId = getIntent().getExtras().getLong(KEY_ORDER_ID);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, OrderFragment.newInstance(orderId))
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