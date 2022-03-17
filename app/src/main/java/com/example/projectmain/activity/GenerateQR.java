package com.example.projectmain.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectmain.R;
import com.example.projectmain.activity.AddMenu;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateQR extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText val;
    private ImageView imgQr;
    private Spinner spinners;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_generate);

        val = findViewById(R.id.et_insert);
        imgQr = findViewById(R.id.img_qr);
        spinners = findViewById(R.id.spiner3);

        Button button = findViewById(R.id.btn_generate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = val.getText().toString();
                QRGEncoder qrgEncoder = new QRGEncoder(value, null, QRGContents.Type.TEXT, 500);

                try {
                    Bitmap bitmap = qrgEncoder.encodeAsBitmap();
                    imgQr.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }


            }
        });

        setupSpinner();

    }

    private void setupSpinner() {
        ArrayAdapter locationArray = ArrayAdapter.createFromResource(this, R.array.array_index_location,
                                    android.R.layout.simple_spinner_item);
        locationArray.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinners.setAdapter(locationArray);
        spinners.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    String txt = (String) parent.getItemAtPosition(position);
    val.setText(txt);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
