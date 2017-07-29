package com.techclutch.finassist.uploaddocuments;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.techclutch.finassist.R;
import com.techclutch.finassist.callbacks.OnDocumentTypeCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by madzirin on 7/30/2017.
 */

public class UploadDocumentActivity extends AppCompatActivity implements OnDocumentTypeCallback {

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_document);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UploadDocumentsAdapter(this, getDocumentList(), this));
    }

    private List<DocumentItem> getDocumentList() {
        String[] bankName = getResources().getStringArray(R.array.document_list);
        TypedArray icons = getResources().obtainTypedArray(R.array.document_list_images);
        List<DocumentItem> items = new ArrayList<>();

        for(int i = 0; i < icons.length(); ++i) {
            items.add(new DocumentItem(bankName[i], icons.getResourceId(i, 0)));
        }
        icons.recycle();
        return items;
    }

    @Override
    public void onListFragmentInteraction(DocumentItem item) {
        //go to forms
    }
}
