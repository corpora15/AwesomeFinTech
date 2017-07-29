package com.techclutch.finassist.forms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.techclutch.finassist.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_form);
        ButterKnife.bind(this);


    }
}
