package com.techclutch.finassist.loantype;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.techclutch.finassist.R;
import com.techclutch.finassist.callbacks.OnLoanTypePopupSaved;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Arman on 7/30/2017.
 */

public class LoanTypeDialog {

    OnLoanTypePopupSaved callback;
    private Dialog dialog;

    @BindView(R.id.et_property_price)
    EditText etPropertyPrices;
    @BindView(R.id.et_loan_amount)
    EditText etLoanAmount;
    @BindView(R.id.sp_tenure)
    Spinner spTenure;
    @BindView(R.id.et_income)
    EditText etIncome;
    @BindView(R.id.sp_employment)
    Spinner spEmployment;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;

    public void showDialog(Context context, OnLoanTypePopupSaved callback) {
        this.callback = callback;
        View view = View.inflate(context, R.layout.layout_mortgage_questions, null);
        ButterKnife.bind(this, view);

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @OnClick(R.id.btn_ok)
    void onOkClicked() {
        if(TextUtils.isEmpty(etPropertyPrices.getText())) {
            etPropertyPrices.setError("Please enter property price!");
            return;
        }
        if(TextUtils.isEmpty(etLoanAmount.getText())) {
            etLoanAmount.setError("Please enter loan amount!");
            return;
        }

        // assume everything is ok!
        callback.setData(etPropertyPrices.getText().toString(), etLoanAmount.getText().toString(),
                spTenure.getSelectedItem().toString(), etIncome.getText().toString(), spEmployment.getSelectedItem().toString());
    }

    @OnClick(R.id.btn_cancel)
    void onCancelClicked() {
        dialog.dismiss();
    }
}
