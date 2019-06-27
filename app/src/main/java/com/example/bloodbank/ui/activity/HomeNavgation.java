package com.example.bloodbank.ui.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.PageHomeNavagationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.bloodbank.data.local.SharedPreferncesManger.clean;


public class HomeNavgation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.homeNavgationTabBar)
    TabLayout homeNavgationTabBar;
    @BindView(R.id.homeNavgationViewPager)
    ViewPager homeNavgationViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        NavigationView navigationView = findViewById(R.id.nav_view);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);


        initTabBar();

    }

    private void initTabBar() {

        homeNavgationTabBar.addTab(homeNavgationTabBar.newTab().setText(getResources().getString(R.string.articles)));
        homeNavgationTabBar.addTab(homeNavgationTabBar.newTab().setText(getResources().getString(R.string.requst_donation)));

        homeNavgationTabBar.setTabGravity(TabLayout.GRAVITY_FILL);

        PageHomeNavagationAdapter pageHomeNavagationAdapter = new PageHomeNavagationAdapter(getSupportFragmentManager(),homeNavgationTabBar.getTabCount());
        homeNavgationViewPager.setAdapter(pageHomeNavagationAdapter);

        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.color_tab_bar_line_select);
        homeNavgationTabBar.setSelectedTabIndicatorColor(tabIconColor);

         homeNavgationTabBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                homeNavgationViewPager.setCurrentItem(tab.getPosition());

                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.color_tab_bar_line_select);
                homeNavgationTabBar.setSelectedTabIndicatorColor(tabIconColor);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.white);
                homeNavgationTabBar.setSelectedTabIndicatorColor(tabIconColor);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        homeNavgationViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(homeNavgationTabBar));


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_data) {
            // Handle the camera action
        } else if (id == R.id.nav_notify) {

        } else if (id == R.id.nav_my_favorite) {

        } else if (id == R.id.nav_my_home) {

        } else if (id == R.id.nav_details) {

        } else if (id == R.id.nav_call_me) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_rate) {

        } else if (id == R.id.nav_exit) {

            clean(HomeNavgation.this);

            startActivity(new Intent(HomeNavgation.this, SplashActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
