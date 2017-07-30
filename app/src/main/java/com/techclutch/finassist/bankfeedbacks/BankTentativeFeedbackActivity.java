package com.techclutch.finassist.bankfeedbacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.techclutch.finassist.LandingActivity;
import com.techclutch.finassist.R;
import com.techclutch.finassist.forms.HomeFormActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.widget.Button;

public class BankTentativeFeedbackActivity extends AppCompatActivity {

    @BindView(R.id.btn_done)
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_tentative_feedback);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_done)
    void onDoneClicked() {
        Intent intent = new Intent(this, LandingActivity.class);
        startActivity(intent);
    }
}
