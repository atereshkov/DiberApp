package com.github.handioq.diberapp.base;

import android.content.Intent;
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
import com.github.handioq.diberapp.ui.addresses.AddressesActivity;
import com.github.handioq.diberapp.ui.auth.login.LoginActivity;
import com.github.handioq.diberapp.ui.orders.OrdersActivity;
import com.github.handioq.diberapp.util.AuthPreferences;

import butterknife.BindView;

public class BaseDrawerActivity extends BaseToolbarActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ImageView userImageView;
    private TextView nameTextView;
    private TextView emailTextView;

    // TODO add logout button

    private AuthPreferences authPreferences;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, getToolbar(), 0, 0);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);

        userImageView = (ImageView) headerLayout.findViewById(R.id.imageView);
        nameTextView = (TextView) headerLayout.findViewById(R.id.nameTextView);
        emailTextView = (TextView) headerLayout.findViewById(R.id.emailTextView);
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

        if (id == R.id.nav_orders) {
            Intent intent = new Intent(this, OrdersActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        } else if (id == R.id.nav_addresses) {
            Intent intent = new Intent(this, AddressesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            authPreferences.logout();

            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        authPreferences = new AuthPreferences(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();

        nameTextView.setText(authPreferences.getUserFullname());
        emailTextView.setText(authPreferences.getUserEmail());
    }

}