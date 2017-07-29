package com.techclutch.finassist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.view.View;

import com.microblink.activity.ScanCard;
import com.microblink.recognizers.BaseRecognitionResult;
import com.microblink.recognizers.RecognitionResults;
import com.microblink.recognizers.blinkid.malaysia.MyKadRecognitionResult;
import com.microblink.recognizers.blinkid.malaysia.MyKadRecognizerSettings;
import com.microblink.recognizers.settings.RecognitionSettings;
import com.microblink.recognizers.settings.RecognizerSettings;
import com.microblink.results.date.Date;

import com.techclutch.finassist.banktypes.BankTypeActivity;
import com.techclutch.finassist.dummy.UserDataTon;
import com.techclutch.finassist.loantype.OnLoanTypePopupSaved;
import com.techclutch.finassist.dummy.DummyContent;
import com.techclutch.finassist.loanstatus.LoanStatusFragment;
import com.techclutch.finassist.loantype.LoanTypeDialog;
import com.techclutch.finassist.loantype.LoanTypeFragment;
import com.techclutch.finassist.loantype.LoanTypeItem;

public class LandingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoanStatusFragment.OnListFragmentInteractionListener,
        LoanTypeFragment.OnListFragmentInteractionListener, OnLoanTypePopupSaved {

    private static final int MY_REQUEST_CODE = 1500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent for ScanCard Activity
                Intent intent = new Intent(LandingActivity.this, ScanCard.class);

                // set your licence key
                // obtain your licence key at http://microblink.com/login or
                // contact us at http://help.microblink.com
                intent.putExtra(ScanCard.EXTRAS_LICENSE_KEY, "NJFXFTS6-VBPLGNKL-I3KE4K5R-E565OJBB-WZT4QLJR-GALPJ5ZV-BMS2JUL5-35NYGZ6L");

                RecognitionSettings settings = new RecognitionSettings();
                // setup array of recognition settings (described in chapter "Recognition
                // settings and results")
                settings.setRecognizerSettingsArray(setupSettingsArray());
                intent.putExtra(ScanCard.EXTRAS_RECOGNITION_SETTINGS, settings);

                // Starting Activity
                startActivityForResult(intent, MY_REQUEST_CODE);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode == ScanCard.RESULT_OK && data != null) {
                // perform processing of the data here

                // for example, obtain parcelable recognition result
                Bundle extras = data.getExtras();
                RecognitionResults result = data.getParcelableExtra(ScanCard.EXTRAS_RECOGNITION_RESULTS);

                // get array of recognition results
                BaseRecognitionResult[] resultArray = result.getRecognitionResults();
                for (BaseRecognitionResult baseResult : resultArray) {
                    if (baseResult instanceof MyKadRecognitionResult) {
                        MyKadRecognitionResult my_result = (MyKadRecognitionResult) baseResult;

                        // you can use getters of MyKadRecognitionResult class to
                        // obtain scanned information
                        if (my_result.isValid() && !my_result.isEmpty()) {
                            UserDataTon.Get().mFullName = my_result.getOwnerFullName();
                            UserDataTon.Get().mNRICNumber = my_result.getNRICNumber();
                            UserDataTon.Get().mBirthDate = my_result.getOwnerBirthDate();
                            UserDataTon.Get().mAddress = my_result.getOwnerAddress();
                            UserDataTon.Get().mSex = my_result.getOwnerSex();
                            UserDataTon.Get().mTitle = my_result.getTitle();


                        } else {
                            // not all relevant data was scanned, ask user
                            // to try again
                        }
                    }
                }
            }
        }
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
        getMenuInflater().inflate(R.menu.landing, menu);
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
        Fragment fragment = null;
        if (id == R.id.nav_apply) {
            fragment = new LoanTypeFragment();
        } else if (id == R.id.nav_status) {
            fragment = new LoanStatusFragment();
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fl_placeholder, fragment).commit();

//            mDrawerList.setItemChecked(position, true);
//            mDrawerList.setSelection(position);
//            setTitle(mNavigationDrawerItemTitles[position]);
//            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

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
    private RecognizerSettings[] setupSettingsArray() {
        MyKadRecognizerSettings sett = new MyKadRecognizerSettings();

        // now add sett to recognizer settings array that is used to configure
        // recognition
        return new RecognizerSettings[] { sett };
    }
}
