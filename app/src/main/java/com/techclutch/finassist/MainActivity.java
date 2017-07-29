package com.techclutch.finassist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.microblink.util.RecognizerCompatibility;
import com.microblink.util.RecognizerCompatibilityStatus;

import OCR.TessOCR;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv_photo)
    ImageView ivPhoto;

    private static final int CAMERA_REQUEST = 1888;
    private TessOCR mTessOCR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTessOCR = new TessOCR(this, "eng");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // check if BlinkID is supported on the device
        RecognizerCompatibilityStatus supportStatus = RecognizerCompatibility.getRecognizerCompatibilityStatus(this);
        if (supportStatus != RecognizerCompatibilityStatus.RECOGNIZER_SUPPORTED) {
            Toast.makeText(this, "BlinkID is not supported! Reason: " + supportStatus.name(), Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.fab)
    void onFabClicked() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivPhoto.setImageBitmap(photo);
            BitmapDrawable drawable = (BitmapDrawable) ivPhoto.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            String result = mTessOCR.getOCRResult(bitmap);
            Log.d("OCR from Tesseract is ", result);
        }
    }
}
