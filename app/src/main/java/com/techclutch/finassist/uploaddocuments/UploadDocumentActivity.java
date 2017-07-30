package com.techclutch.finassist.uploaddocuments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.microblink.activity.ScanCard;
import com.microblink.recognizers.BaseRecognitionResult;
import com.microblink.recognizers.RecognitionResults;
import com.microblink.recognizers.blinkid.malaysia.MyKadRecognitionResult;
import com.microblink.recognizers.blinkid.malaysia.MyKadRecognizerSettings;
import com.microblink.recognizers.settings.RecognitionSettings;
import com.microblink.recognizers.settings.RecognizerSettings;
import com.techclutch.finassist.R;
import com.techclutch.finassist.callbacks.OnDocumentTypeCallback;
import com.techclutch.finassist.dummy.UserDataTron;
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

    private static final int MY_REQUEST_CODE = 1500;
    private List<DocumentItem> items;
    private UploadDocumentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_document);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Scan all documents below");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        items = getDocumentList();
        adapter = new UploadDocumentsAdapter(this, items, this);
        recyclerView.setAdapter(adapter);
    }

    private List<DocumentItem> getDocumentList() {
        String[] documentNames = getResources().getStringArray(R.array.document_list);
        List<DocumentItem> items = new ArrayList<>();

        for(int i = 0; i < documentNames.length; ++i) {
            items.add(new DocumentItem(documentNames[i]));
        }
        return items;
    }

    @Override
    public void onListFragmentInteraction(DocumentItem item) {
        //go to scanning
        Intent intent = new Intent(UploadDocumentActivity.this, ScanCard.class);

        // set your licence key
        // obtain your licence key at http://microblink.com/login or
        // contact us at http://help.microblink.com
        intent.putExtra(ScanCard.EXTRAS_LICENSE_KEY, "NJFXFTS6-VBPLGNKL-I3KE4K5R-E565OJBB-WZT4QLJR-GALPJ5ZV-BMS2JUL5-35NYGZ6L");

        RecognitionSettings settings = new RecognitionSettings();
        // setup array of recognition settings (described in chapter "Recognition
        // settings and results")
        settings.setRecognizerSettingsArray(setupSettingsArray());
        intent.putExtra(ScanCard.EXTRAS_RECOGNITION_SETTINGS, settings);

        // Starting Activity
        startActivityForResult(intent, MY_REQUEST_CODE);
    }

    @OnClick(R.id.btn_proceed)
    void onProceedClicked() {
        Intent intent = new Intent(this, HomeQuestionaireActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode == ScanCard.RESULT_OK && data != null) {
                // perform processing of the data here

                // for example, obtain parcelable recognition result
                Bundle extras = data.getExtras();
                RecognitionResults result = data.getParcelableExtra(ScanCard.EXTRAS_RECOGNITION_RESULTS);

                // get array of recognition results
                BaseRecognitionResult[] resultArray = result.getRecognitionResults();
                for (BaseRecognitionResult baseResult : resultArray) {
                    if (baseResult instanceof MyKadRecognitionResult) {
                        MyKadRecognitionResult my_result = (MyKadRecognitionResult) baseResult;

                        // you can use getters of MyKadRecognitionResult class to
                        // obtain scanned information
                        if (my_result.isValid() && !my_result.isEmpty()) {
                            UserDataTron.Get().mFullName = my_result.getOwnerFullName();
                            UserDataTron.Get().mNRICNumber = my_result.getNRICNumber();
                            UserDataTron.Get().mBirthDate = my_result.getOwnerBirthDate();
                            UserDataTron.Get().mAddress = my_result.getOwnerAddress();
                            UserDataTron.Get().mSex = my_result.getOwnerSex();
                            UserDataTron.Get().mTitle = my_result.getTitle();

                            items.get(0).setIsCompleted(true);
                            adapter.notifyDataSetChanged();
                        } else {
                            // not all relevant data was scanned, ask user
                            // to try again
                        }
                    }
                }
            }
        }
    }

    private RecognizerSettings[] setupSettingsArray() {
        MyKadRecognizerSettings sett = new MyKadRecognizerSettings();

        // now add sett to recognizer settings array that is used to configure
        // recognition
        return new RecognizerSettings[] { sett };
    }
}
