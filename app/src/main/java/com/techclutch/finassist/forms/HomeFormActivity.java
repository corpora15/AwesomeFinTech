package com.techclutch.finassist.forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.techclutch.finassist.R;
import com.techclutch.finassist.bankfeedbacks.BankTentativeFeedbackActivity;

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

    @BindView(R.id.et_dob)
    EditText etDOB;

    @BindView(R.id.btn_ok)
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_form);
        ButterKnife.bind(this);

        etName.setText(UserData.Get().GetName());
        etNRIC.setText(UserData.Get().GetNRIC());
        etAddress.setText(UserData.Get().GetAddress());
        etSex.setText(UserData.Get().GetSex());
        etTitle.setText(UserData.Get().GetTitle());

        etDOB.setText(UserData.Get().GetDateOfBirth("-"));
    }

    @OnClick(R.id.btn_ok)
    void onOkClicked() {
        Intent intent = new Intent(this, BankTentativeFeedbackActivity.class);
        startActivity(intent);
    }
}
