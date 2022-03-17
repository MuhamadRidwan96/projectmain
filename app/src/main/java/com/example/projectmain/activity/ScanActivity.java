package com.example.projectmain.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.projectmain.R;
import com.example.projectmain.utils.Utils;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class ScanActivity extends AppCompatActivity {

    private CodeScanner codeScanner;
    private CodeScannerView scannerView;
    private TextView txtResultScanner;
    private String item, uom, bin,gr_date, intent_action = "";
    private int id_stock, available_stock,mm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        //initialize
        Button button = findViewById(R.id.btn_ok);
        Button cancel = findViewById(R.id.btn_cancel);
        txtResultScanner = findViewById(R.id.txt_id_stock);
        scannerView = findViewById(R.id.scanner);
        codeScanner = new CodeScanner(this, scannerView);
        Utils.blackIconStatusBar(ScanActivity.this, R.color.colorDarkBackground);

        Intent data = getIntent();
        id_stock = data.getIntExtra("id_stock", 0);
        bin = data.getStringExtra("bin");
        mm = data.getIntExtra("mm",0);
        item = data.getStringExtra("item");
        available_stock = data.getIntExtra("available_stock", 0);
        uom = data.getStringExtra("uom");
        gr_date = data.getStringExtra("gr_date");
        intent_action = data.getStringExtra("intent_action");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(txtResultScanner.getText().toString())) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ScanActivity.this);
                    dialog.setMessage("SCAN QR CODE FIRST!");
                    dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else {
                    try {
                        filterScanner();
                    } catch (NullPointerException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        getScanners();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResultScanner.setText("");
                getScanners();
            }
        });
    }


    public void filterScanner(){
        String getScan =txtResultScanner.getText().toString();
        if (getScan.equals(bin)) {
            Intent intent = new Intent(ScanActivity.this, AddMenu.class);
            intent.putExtra("id_stock", id_stock);
            intent.putExtra("bin",bin);
            intent.putExtra("mm",mm);
            intent.putExtra("item", item);
            intent.putExtra("available_stock", available_stock);
            intent.putExtra("uom", uom);
            intent.putExtra("gr_date",gr_date);
            intent.putExtra("intent_action", intent_action);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);

        } else {
            Toast.makeText(ScanActivity.this, "Wrong Location !", Toast.LENGTH_SHORT).show();
        }
    }

    private void getScanners() {
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(() -> txtResultScanner.setText(result.getText()));
            }
        });

        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeScanner.startPreview();
            }
        });
        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestForCamera();
    }

    private void requestForCamera() {

        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                codeScanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                Toast.makeText(ScanActivity.this, "Camera permission is Required", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }
}