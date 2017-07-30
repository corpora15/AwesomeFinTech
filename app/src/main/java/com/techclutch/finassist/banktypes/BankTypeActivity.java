package com.techclutch.finassist.banktypes;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.techclutch.finassist.R;
import com.techclutch.finassist.callbacks.OnBankTypeCallback;
import com.techclutch.finassist.uploaddocuments.UploadDocumentActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BankTypeActivity extends AppCompatActivity implements OnBankTypeCallback {

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_type);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BankTypeAdapter(this, getBankList(), this));
    }

    private List<BankTypeItem> getBankList() {
        String[] bankName = getResources().getStringArray(R.array.bank_list);
        String[] interestRate = getResources().getStringArray(R.array.bank_list_interest_rates);
        int[] images = getResources().getIntArray(R.array.bank_list_images);
        TypedArray icons = getResources().obtainTypedArray(R.array.bank_list_images);
        List<BankTypeItem> items = new ArrayList<>();

        //todo this is mock data
        List<String> monthlyPayment = Arrays.asList("RM 2320", "RM 2325", "RM 2317", "RM 2317", "RM 2335");
        List<String> totalPayment = Arrays.asList("Yes", "Yes", "No", "Yes", "Yes");

        for(int i = 0; i < images.length; ++i) {
            items.add(new BankTypeItem(bankName[i], monthlyPayment.get(i), totalPayment.get(i), interestRate[i], icons.getResourceId(i, 0)));
        }
        icons.recycle();
        return items;
    }

    @Override
    public void onListFragmentInteraction(BankTypeItem item) {
        //go to forms
        Intent intent = new Intent(this, UploadDocumentActivity.class);
        startActivity(intent);
    }
}
