package com.github.handioq.diberapp.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.handioq.diberapp.R;

import butterknife.BindView;

public class BaseDrawerActivity extends BaseToolbarActivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ImageView userImageView;
    private TextView nameTextView;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, getToolbar(), 0, 0);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);

        //userImageView = (ImageView) headerLayout.findViewById(R.id.);
        //nameTextView = (TextView) headerLayout.findViewById(R.id.);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        int id = item.getItemId();

        /*
        if (id == R.id.orders) {

        } else if (id == R.id.account) {

        }
        */

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onStart() {
        super.onStart();

        // set fields
    }

}