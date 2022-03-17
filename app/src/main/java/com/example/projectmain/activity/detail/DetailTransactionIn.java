package com.example.projectmain.activity.detail;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectmain.R;
import com.example.projectmain.activity.MainActivity;
import com.example.projectmain.response.ResponseTransaksiIn;
import com.example.projectmain.rest.ApiRequestData;
import com.example.projectmain.rest.RetroServer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransactionIn extends AppCompatActivity {
    private TextView tv_idStock, tv_NoDoc, tv_Material, tv_Quantity, tv_Uom, tv_Shift , tv_Nik , mcaption ;
    private EditText et_date;
    private String nik,date, shift, material, uom, intent_action = "";
    private int quantity, id_stock, no_doc;
    private final static String TAG = "DetailTransactionIn";
    Calendar NewCalendar = Calendar.getInstance();
    private ApiRequestData apiRequestData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_data_in);


        tv_idStock  = findViewById(R.id.tv_detail_id_stock_in);
        tv_NoDoc    = findViewById(R.id.tv_detail_no_doc);
        tv_Material = findViewById(R.id.tv_detail_material_in);
        tv_Quantity = findViewById(R.id.tv_detail_quantity_in);
        tv_Uom      = findViewById(R.id.tv_detail_uom_in);
        tv_Shift    = findViewById(R.id.tv_detail_shift_in);
        tv_Nik      = findViewById(R.id.tv_detail_nik_in);
        mcaption = findViewById(R.id.captions);
        et_date = findViewById(R.id.et_detail_date_in);

        ImageView image_date = findViewById(R.id.img_date_detail_in);

        Intent intent = getIntent();
        no_doc = intent.getIntExtra("no_doc", 0);
        id_stock = intent.getIntExtra("id_stock",0);
        material = intent.getStringExtra("material");
        quantity = intent.getIntExtra("quantity", 0);
        uom = intent.getStringExtra("uom");
        date = intent.getStringExtra("date");
        shift = intent.getStringExtra("shift");
        nik = intent.getStringExtra("nik");
        intent_action = intent.getStringExtra("intent_action");

        setDataFromIntent();

        image_date.setOnClickListener(view -> new DatePickerDialog(DetailTransactionIn.this, dates
                , NewCalendar.get(Calendar.YEAR)
                , NewCalendar.get(Calendar.MONTH)
                , NewCalendar.get(Calendar.DAY_OF_MONTH)).show());
    }

    DatePickerDialog.OnDateSetListener dates = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            NewCalendar.set(Calendar.YEAR, year);
            NewCalendar.set(Calendar.MONTH, monthOfYear);
            NewCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setBirth();
        }
    };

    private void setBirth() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_date.setText(sdf.format(NewCalendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    private void setDataFromIntent() {

        if (no_doc != 0) {
            //mcaption.setText(intent_action);
            tv_NoDoc.setText(String.valueOf(no_doc));
            tv_idStock.setText(String.valueOf(id_stock));
            tv_Material.setText(material);
            tv_Quantity.setText(String.valueOf(quantity));
            tv_Uom.setText(uom);
            et_date.setText(date);
            tv_Shift.setText(shift);
            tv_Nik.setText(nik);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_add:
                Intent intent = new Intent(DetailTransactionIn.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                break;
            case R.id.menu_update:
                AlertDialog.Builder UpdateDialog = new AlertDialog.Builder(this);
                UpdateDialog.setMessage("Update Data?");
                UpdateDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        update_material_in();
                    }
                });
                UpdateDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                UpdateDialog.show();
                break;
            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("Delete Data ? ");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete_material_in(no_doc);
                    }
                });
                dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
        }
        return false;
    }

    public void update_material_in() {

        String date = et_date.getText().toString().trim();
        String userName = "admin";
        String password = "1234";
        String base = userName +":"+ password;
        String authHeaders = "Basic " + Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);
        final Call<ResponseTransaksiIn> updateIn = RetroServer.getClient().updateMigo(authHeaders, no_doc, id_stock,material,quantity, uom, date,shift,nik);
        updateIn.enqueue(new Callback<ResponseTransaksiIn>() {
            @Override
            public void onResponse(Call<ResponseTransaksiIn> call, Response<ResponseTransaksiIn> response) {
                Toast.makeText(DetailTransactionIn.this, "update successfully", Toast.LENGTH_SHORT).show();
                Intent goList = new Intent(DetailTransactionIn.this, MainActivity.class);
                goList.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(goList);
            }

            @Override
            public void onFailure(Call<ResponseTransaksiIn> call, Throwable t) {
                Toast.makeText(DetailTransactionIn.this, "failed to update = " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void delete_material_in(final int no_doc) {


            apiRequestData = RetroServer.getApiClient().create(ApiRequestData.class);
            Call<ResponseTransaksiIn> call = apiRequestData.deleteIn(no_doc);
            call.enqueue(new Callback<ResponseTransaksiIn>() {
                @Override
                public void onResponse(Call<ResponseTransaksiIn> call, Response<ResponseTransaksiIn> response) {
                    Toast.makeText(DetailTransactionIn.this, "berhasil", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseTransaksiIn> call, Throwable throwable) {
                    Toast.makeText(DetailTransactionIn.this, "berhasil", Toast.LENGTH_SHORT).show();
                }
            });
    }

}
