package com.github.handioq.diberapp.ui.orders;

import android.os.Bundle;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.base.BaseDrawerActivity;

public class OrdersActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, new OrdersFragment())
                    .commit();
        }
    }


}
