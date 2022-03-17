package com.example.projectmain.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectmain.R;
import com.example.projectmain.response.ResponseModel;
import com.example.projectmain.response.ResponseTransaksiIn;
import com.example.projectmain.response.ResponseTransaksiOut;
import com.example.projectmain.rest.ApiRequestData;
import com.example.projectmain.rest.RetroServer;
import com.example.projectmain.utils.SessionManager;
import com.example.projectmain.utils.Utils;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMenu extends AppCompatActivity {
    private EditText editText_date, editText_shift, editText_quantity;
    private TextView textView_user, textView_quantity, textView_uom, txtResultScanner, textView_material, tv_result;
    private String nik, item, material,  uom, gr_date, bin, intent_action = "";
    private int id_stock, quantity, result, mm, mat_doc, no_doc, IdSresult;
    private Spinner spinner;
    private TextInputLayout qty;
    private SharedPreferences.Editor editor;
    Calendar myCalendar = Calendar.getInstance();

    private ApiRequestData apiRequestData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmenu);

        //initialize content
        editText_shift = findViewById(R.id.et_shift);
        editText_date = findViewById(R.id.et_date);
        editText_quantity = findViewById(R.id.et_quantity);
        textView_user = findViewById(R.id.tv_user);
        textView_material = findViewById(R.id.tv_materials);
        textView_quantity = findViewById(R.id.tv_qty);
        textView_uom = findViewById(R.id.tv_uom);
        txtResultScanner = findViewById(R.id.tv_idStocks);
        qty = findViewById(R.id.txtqtys);
        tv_result = findViewById(R.id.tv);

        ImageView ImageIconForDate = findViewById(R.id.img_date);
        Button btn_consume = findViewById(R.id.btn_edit);
        Button btn_Migo = findViewById(R.id.btn_edit2);

        spinner = findViewById(R.id.spinners);
        setUpSpinner2();

        //set Status bar color
        Utils.blackIconStatusBar(AddMenu.this, R.color.colorDarkBackground);
        //shared pref from session manager
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> UserDetail = sessionManager.getUserDetail();

        String niks = UserDetail.get(SessionManager.KEY_NIK);

        textView_user.setText(niks);
        // icon for date picker
        ImageIconForDate.setOnClickListener(view -> new DatePickerDialog(AddMenu.this, dates, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH)
                , myCalendar.get(Calendar.DAY_OF_MONTH)).show());
        //get value from adapterData(stocks)
        Intent data = getIntent();
        id_stock = data.getIntExtra("id_stock", 0);
        bin = data.getStringExtra("bin");
        mm = data.getIntExtra("mm", 0);
        material = data.getStringExtra("item");
        quantity = data.getIntExtra("available_stock", 0);
        uom = data.getStringExtra("uom");
        gr_date = data.getStringExtra("gr_date");
        intent_action = data.getStringExtra("intent_action");
        setDataFromIntentExtra();

        if (intent_action.equals("consume")) {
            btn_consume.setVisibility(View.VISIBLE);
            btn_Migo.setVisibility(View.GONE);
        } else if (intent_action.equals("migo")) {
            btn_Migo.setVisibility(View.VISIBLE);
            btn_consume.setVisibility(View.GONE);
        }
        //Action for button take OUT material
        btn_consume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editText_quantity.getText().toString()) ||
                        TextUtils.isEmpty(textView_user.getText().toString()) ||
                        TextUtils.isEmpty(editText_shift.getText().toString()) ||
                        TextUtils.isEmpty(editText_date.getText().toString()) ||
                        TextUtils.isEmpty(txtResultScanner.getText().toString())) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddMenu.this);
                    alertDialog.setMessage("Field cannot be empty");
                    alertDialog.setCancelable(true);

                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertDialog.show();
                } else {
                    try {
                        onMinus();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //btn add material listener
        btn_Migo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(editText_quantity.getText().toString()) ||
                        TextUtils.isEmpty(textView_user.getText().toString()) ||
                        TextUtils.isEmpty(editText_shift.getText().toString()) ||
                        TextUtils.isEmpty(editText_date.getText().toString()) ||
                        TextUtils.isEmpty(txtResultScanner.getText().toString())) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddMenu.this);
                    alertDialog.setMessage("Field cannot be empty");
                    alertDialog.setCancelable(true);

                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertDialog.show();
                } else {
                    try {
                        onAdd();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //action button scan
    }
    // Date Picker Dialog
    DatePickerDialog.OnDateSetListener dates = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setDate();
        }
    };

    //set date
    private void setDate() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText_date.setText(sdf.format(myCalendar.getTime()));
    }

    //set value from scanner
    private void setDataFromIntentExtra() {
        if (id_stock != 0) {
            textView_material.setText(material);
            textView_quantity.setText(String.valueOf(quantity));
            textView_uom.setText(uom);
            txtResultScanner.setText(bin);

        }
    }

    //method for take out material
    private void onMinus() {
        int number = Integer.parseInt(editText_quantity.getText().toString().trim());
        int number2 = Integer.parseInt(textView_quantity.getText().toString().trim());
        String tgl = editText_date.getText().toString();
        //int id = Integer.parseInt(ids.getText().toString().trim());
        result = number2 - number;
        tv_result.setText(String.valueOf(result));

        if (result < 0) {
            qty.setError("Stock less than 0");
            if (tgl.isEmpty()) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddMenu.this);
                alertDialog.setMessage("Field cannot be empty");
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
            }
        }else if (result==0){
            postMaterialOut(mat_doc);
            updateData(id_stock);
            try {
                deleteStock();

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    //method for take in material
    private void onAdd() {
        int number = Integer.parseInt(editText_quantity.getText().toString().trim());
        int number2 = Integer.parseInt(textView_quantity.getText().toString().trim());
        String tgl = editText_date.getText().toString();
        result = number2 + number;
        tv_result.setText(String.valueOf(result));

        if (result > 1000) {
            qty.setError("Stock more than 1000");

            if (tgl.isEmpty()) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddMenu.this);

                alertDialog.setMessage("Field cannot be empty");
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
            }

        } else {
            try {
                postMaterialIn(no_doc);
                updateData(id_stock);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateData(final int id_stock) {

        int available_stock = Integer.parseInt(tv_result.getText().toString().trim());
        String item = textView_material.getText().toString().trim();

        String userName = "admin";
        String password = "1234";
        String base = userName + ":" + password;
        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
        final Call<ResponseModel> updateStock = RetroServer.getClient().updateStock(authHeader, id_stock, bin, mm, item, available_stock, uom, gr_date);
        updateStock.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Toast.makeText(AddMenu.this, " Transaction Successfully!" +
                        " ", Toast.LENGTH_SHORT).show();
                finishAfterTransition();
                Intent intent = new Intent(AddMenu.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AddMenu.this, "failure response: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //method for post Good Issue
    private void postMaterialIn(final int no_doc) {
        int quantity    = Integer.parseInt(editText_quantity.getText().toString().trim());
        String date     = editText_date.getText().toString().trim();
        String shift    = editText_shift.getText().toString().trim();
        String nik      = textView_user.getText().toString().trim();
        String userName = "admin";
        String password = "1234";
        String base     = userName + ":" + password;
        String authHeaders = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
        final Call<ResponseTransaksiIn> insertIn = RetroServer.getClient().postMigo(authHeaders, no_doc, id_stock, material, quantity, uom, date, shift, nik);
        insertIn.enqueue(new Callback<ResponseTransaksiIn>() {
            @Override
            public void onResponse(Call<ResponseTransaksiIn> call, Response<ResponseTransaksiIn> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddMenu.this, "done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddMenu.this, "riject", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTransaksiIn> call, Throwable t) {
                Toast.makeText(AddMenu.this, "pesan : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //method for post Consume
    private void postMaterialOut(final int mat_doc) {
        int quantity    = Integer.parseInt(editText_quantity.getText().toString().trim());
        String date     = editText_date.getText().toString().trim();
        String shift    = editText_shift.getText().toString().trim();
        String nik      = textView_user.getText().toString().trim();
        String userName = "admin";
        String password = "1234";
        String base = userName + ":" + password;
        String authHeaders1 = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
        final Call<ResponseTransaksiOut> insertOut = RetroServer.getClient().postConsumption(authHeaders1, mat_doc, id_stock, material, quantity, uom, date, shift, nik);
        insertOut.enqueue(new Callback<ResponseTransaksiOut>() {
            @Override
            public void onResponse(Call<ResponseTransaksiOut> call, Response<ResponseTransaksiOut> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(AddMenu.this, "done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddMenu.this, "reject", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTransaksiOut> call, Throwable t) {
                Toast.makeText(AddMenu.this, "message : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //setup for spinner
    private void setUpSpinner2() {
        ArrayAdapter ShiftArray = ArrayAdapter.createFromResource(this, R.array.shift_index, android.R.layout.simple_spinner_item);
        ShiftArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ShiftArray);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text2 = (String) adapterView.getItemAtPosition(i);
                editText_shift.setText(text2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void deleteStock(){

        apiRequestData = RetroServer.getApiClient().create(ApiRequestData.class);
        Call<ResponseModel> call = apiRequestData.deleted(id_stock);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Toast.makeText(AddMenu.this, "berhasil", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable throwable) {
                Toast.makeText(AddMenu.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    /*
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
                Toast.makeText(AddMenu.this, "Camera permission is Required", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }*/

}}

