package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import net.glxn.qrgen.android.QRCode;

import java.util.Date;

public class QRcodewiev extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcodewiev);
        String  UID = getIntent().getExtras().getString("name");
        Bitmap myBitmap = QRCode.from(UID).bitmap();
        ImageView myImage = (ImageView) findViewById(R.id.imageView);
        myImage.setImageBitmap(myBitmap);
    }


    public void onClickuniks(View v) {
        if (v.getId() == R.id.button101) {
            // launch barcode activity.
            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent,2);
        }

    }
}
