package com.github.handioq.diberapp.ui.shops;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.base.BaseDrawerActivity;

public class ShopsActivity extends BaseDrawerActivity {

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, ShopsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, ShopsFragment.newInstance())
                    .commit();
        }
    }


}
