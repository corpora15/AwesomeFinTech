package com.techclutch.finassist.uploaddocuments;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.techclutch.finassist.R;
import com.techclutch.finassist.callbacks.OnDocumentTypeCallback;
import com.techclutch.finassist.questionaires.HomeQuestionaireActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by madzirin on 7/30/2017.
 */

public class UploadDocumentActivity extends AppCompatActivity implements OnDocumentTypeCallback {

    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.btn_proceed)
    Button btnProceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_document);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Please scan all documents listed below");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UploadDocumentsAdapter(this, getDocumentList(), this));
    }

    private List<DocumentItem> getDocumentList() {
        String[] documentNames = getResources().getStringArray(R.array.document_list);
        TypedArray icons = getResources().obtainTypedArray(R.array.document_list_images);
        List<DocumentItem> items = new ArrayList<>();

        for(int i = 0; i < icons.length(); ++i) {
            items.add(new DocumentItem(documentNames[i], icons.getResourceId(i, 0)));
        }
        icons.recycle();
        return items;
    }

    @Override
    public void onListFragmentInteraction(DocumentItem item) {

    }

    @OnClick(R.id.btn_proceed)
    void onProceedClicked() {
        //go to forms
        Intent intent = new Intent(this, HomeQuestionaireActivity.class);
        startActivity(intent);
    }
}
