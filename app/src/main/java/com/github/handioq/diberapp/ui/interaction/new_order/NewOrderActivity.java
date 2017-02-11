package com.github.handioq.diberapp.ui.interaction.new_order;

import android.os.Bundle;
import android.view.MenuItem;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.base.BaseDrawerActivity;
import com.github.handioq.diberapp.base.BaseToolbarActivity;

public class NewOrderActivity extends BaseToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, NewOrderFragment.newInstance())
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
