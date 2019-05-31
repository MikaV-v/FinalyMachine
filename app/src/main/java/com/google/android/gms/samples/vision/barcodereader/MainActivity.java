package com.google.android.gms.samples.vision.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity implements View.OnClickListener {

    // use a compound button so either checkbox or switch widgets work.
    private CompoundButton autoFocus;
    private CompoundButton useFlash;
    private TextView statusMessage;
    private TextView barcodeValue;

    public static long obshsumma=0;
    //private String key_bar;

    public Map<String,String> barcodetoprod = new HashMap<>();

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "BarcodeMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        barcodetoprod.put("54491472", "\"Coca-Cola\", 0,5 л P:25");
        barcodetoprod.put("5449000000286", "\"Coca-Cola\", 2 л P:100");
        barcodetoprod.put("5449000054227", "\"Coca-Cola\", 1 л P:50");
        barcodetoprod.put("5449000228970", "\"COCA-COLA\", 0,9 л P:40");
        barcodetoprod.put("5449000000439", "\"Coca-Cola\", 1,5 л P:75");
        barcodetoprod.put("5449000133335", "\"Coca-Cola\"(ZERO), 1,5 л P:75");

        statusMessage = (TextView)findViewById(R.id.status_message);
        barcodeValue = (TextView)findViewById(R.id.barcode_value);


        findViewById(R.id.read_barcode).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.read_barcode) {
            obshsumma=0;
            // launch barcode activity.
            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
            intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
            intent.putExtra(BarcodeCaptureActivity.UseFlash, false);

            startActivityForResult(intent, RC_BARCODE_CAPTURE);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    statusMessage.setText(R.string.barcode_success);
                    barcodeValue.setText(barcode.displayValue+": товар не найден");

                    for (String key_bar : barcodetoprod.keySet()) {
                        if(key_bar == barcode.displayValue){
                            barcodeValue.setText(barcodetoprod.size());

                        }
                    }
                    Log.d(TAG, "Barcode read: " + barcode.displayValue);
                } else {
                    statusMessage.setText(R.string.barcode_failure);
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                statusMessage.setText(String.format(getString(R.string.barcode_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
