package com.techclutch.finassist.bankfeedbacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.techclutch.finassist.R;
import com.techclutch.finassist.forms.HomeFormActivity;
import com.techclutch.finassist.merchants.FurnitureLoansActivity;
import com.techclutch.finassist.uploaddocuments.UploadDocumentActivity;
import com.techclutch.finassist.main.LandingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankTentativeFeedbackActivity extends AppCompatActivity {

    @BindView(R.id.btn_done)
    Button btnDone;

    @BindView(R.id.btn_furniture)
    Button btnFurniture;

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

    @OnClick(R.id.btn_furniture)
    void onFurnitureClicked() {
        Intent intent = new Intent(this, FurnitureLoansActivity.class);
        startActivity(intent);
    }
}
