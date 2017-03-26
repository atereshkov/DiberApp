package com.github.handioq.diberapp.ui.addresses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.base.BaseDrawerActivity;

public class AddressesActivity extends BaseDrawerActivity {

    public static Intent makeIntent(Context context){
        Intent intent = new Intent(context, AddressesActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, AddressesFragment.newInstance())
                    .commit();
        }
    }


}
