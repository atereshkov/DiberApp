package com.github.handioq.diberapp.base;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.github.handioq.diberapp.R;

import butterknife.BindView;

public class BaseToolbarActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Nullable
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

}