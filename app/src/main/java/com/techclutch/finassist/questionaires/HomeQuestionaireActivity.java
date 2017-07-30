package com.techclutch.finassist.questionaires;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.techclutch.finassist.R;
import com.techclutch.finassist.forms.HomeFormActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeQuestionaireActivity extends AppCompatActivity {

    @BindView(R.id.sp_property_subtype)
    Spinner spPropSubType;

    @BindView(R.id.sp_property_construction_status)
    Spinner spPropConstructionStatus;

    @BindView(R.id.sp_property_standard_built)
    Spinner spStandardBuilt;

    @BindView(R.id.sp_property_applicant_occupied)
    Spinner spApplicantOccupation;

    @BindView(R.id.sp_property_applicant_first_property)
    Spinner spApplicantFirstProperty;

    @BindView(R.id.btn_ok)
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_questionaires);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Tell us more about your home!");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String[] propertySubType = getResources().getStringArray(R.array.propertysubtypes);
        spPropSubType.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, propertySubType));

        String[] constructionStatus = getResources().getStringArray(R.array.propertyconstructionstatus);
        spPropConstructionStatus.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, constructionStatus));

        String[] yesNo = getResources().getStringArray(R.array.yesno);
        spStandardBuilt.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, yesNo));

        spApplicantOccupation.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, yesNo));

        spApplicantFirstProperty.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, yesNo));

    }

    @OnClick(R.id.btn_ok)
    void onOkClicked() {
        Intent intent = new Intent(this, HomeFormActivity.class);
        startActivity(intent);
    }
}
