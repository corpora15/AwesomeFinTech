package com.techclutch.finassist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import OCR.TessOCR;

public class MainActivity extends AppCompatActivity {

    private TessOCR mTessOCR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTessOCR = new TessOCR(this, "eng");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
