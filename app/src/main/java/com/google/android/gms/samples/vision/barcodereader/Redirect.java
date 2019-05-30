package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static android.support.constraint.Constraints.TAG;
import static com.google.android.gms.samples.vision.barcodereader.BarcodeCaptureActivity.resultat;


public class Redirect extends Activity {
    private static final int RC_BARCODE_CAPTURE = 9001;

    public  void d(){
        TextView textView = findViewById(R.id.towar);
        textView.setText(resultat);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licke_alert);
        Log.d(TAG, resultat);
        d();



    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(false);
    }

    public void onClickalert(View v){
        if (v.getId() == R.id.analogAlrt) {
            // launch barcode activity.
            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
            intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
            intent.putExtra(BarcodeCaptureActivity.UseFlash, false);

            startActivityForResult(intent, RC_BARCODE_CAPTURE);
        }

    }
}
