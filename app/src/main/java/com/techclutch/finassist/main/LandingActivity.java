package com.techclutch.finassist.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.techclutch.finassist.R;
import com.techclutch.finassist.banktypes.BankTypeActivity;
import com.techclutch.finassist.loanstatus.LoanStatusItem;
import com.techclutch.finassist.loanstatus.LoanStatusFragment;
import com.techclutch.finassist.loantype.LoanTypeDialog;
import com.techclutch.finassist.loantype.LoanTypeFragment;
import com.techclutch.finassist.loantype.LoanTypeItem;
import com.techclutch.finassist.loantype.OnLoanTypePopupSaved;
import com.techclutch.finassist.merchants.FurnitureLoansActivity;
import com.techclutch.finassist.uploaddocuments.UploadDocumentActivity;

public class LandingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoanStatusFragment.OnListFragmentInteractionListener,
        LoanTypeFragment.OnListFragmentInteractionListener, OnLoanTypePopupSaved {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        initView();
    }

    private void initView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_placeholder, new LoanTypeFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_apply) {
            fragment = new LoanTypeFragment();
        } else if (id == R.id.nav_status) {
            fragment = new LoanStatusFragment();
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fl_placeholder, fragment).commit();

        } else {
            Log.e("LandingActivity", "Error in creating fragment");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(LoanStatusItem.DummyItem item) {

    }

    @Override
    public void onListFragmentInteraction(LoanTypeItem item) {
        new LoanTypeDialog().showDialog(this, this);
    }

    @Override
    public void setData(String propertyPrice, String loanAmount, String tenure, String income, String employment) {
        //call bank type activity
        Intent intent = new Intent(this, BankTypeActivity.class);
        startActivity(intent);
    }
}
