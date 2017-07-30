package com.techclutch.finassist.merchants;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.techclutch.finassist.R;
import com.techclutch.finassist.banktypes.BankTypeAdapter;
import com.techclutch.finassist.banktypes.BankTypeItem;
import com.techclutch.finassist.callbacks.OnBankTypeCallback;
import com.techclutch.finassist.callbacks.OnMerchantTypeCallback;
import com.techclutch.finassist.uploaddocuments.UploadDocumentActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FurnitureLoansActivity extends AppCompatActivity implements OnMerchantTypeCallback {

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture_loans);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MerchantTypeAdapter(this, getMerchantList(), this));
    }

    private List<MerchantTypeItem> getMerchantList() {
        String[] merchantName = getResources().getStringArray(R.array.merchant_list);
        String[] interestRate = getResources().getStringArray(R.array.merchant_list_interest_rates);

        int[] images = getResources().getIntArray(R.array.furniture_list_images);
        TypedArray icons = getResources().obtainTypedArray(R.array.furniture_list_images);
        List<MerchantTypeItem> items = new ArrayList<>();

        //todo this is mock data
        List<String> eligibleLoan = Arrays.asList("RM 7000", "RM 7300");
        List<String> totalRepayment = Arrays.asList("RM 5500", "RM 5500");
        List<String> eligibleDiscounts = Arrays.asList("10%", "6.5%");

        for(int i = 0; i < images.length; ++i) {
            items.add(new MerchantTypeItem(merchantName[i], eligibleLoan.get(i), totalRepayment.get(i), interestRate[i],  eligibleDiscounts.get(i), icons.getResourceId(i, 0)));
        }
        icons.recycle();
        return items;
    }

    @Override
    public void onListFragmentInteraction(MerchantTypeItem item) {
        //go to forms
        Intent intent = new Intent(this, UploadDocumentActivity.class);
        startActivity(intent);
    }
}


