package com.techclutch.finassist.forms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.techclutch.finassist.LandingActivity;
import com.techclutch.finassist.R;
import com.techclutch.finassist.bankfeedbacks.BankTentativeFeedbackActivity;
import com.techclutch.finassist.dummy.UserDataTron;

import com.microblink.results.date.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFormActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_nric)
    EditText etNRIC;

    @BindView(R.id.et_address)
    EditText etAddress;

    @BindView(R.id.et_sex)
    EditText etSex;

    @BindView(R.id.et_title)
    EditText etTitle;

    @BindView(R.id.et_dob)
    EditText etDOB;

    @BindView(R.id.btn_ok)
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_form);
        ButterKnife.bind(this);

        etName.setText(UserDataTron.Get().GetName());
        etNRIC.setText(UserDataTron.Get().GetNRIC());
        etAddress.setText(UserDataTron.Get().GetAddress());
        etSex.setText(UserDataTron.Get().GetSex());
        etTitle.setText(UserDataTron.Get().GetTitle());

        etDOB.setText(UserDataTron.Get().GetDateOfBirth("-"));
    }

    @OnClick(R.id.btn_ok)
    void onOkClicked() {
        Intent intent = new Intent(this, BankTentativeFeedbackActivity.class);
        startActivity(intent);
    }
}
