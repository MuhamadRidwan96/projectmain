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
import com.example.projectmain.response.ResponseTransaksiOut;
import com.example.projectmain.rest.ApiRequestData;
import com.example.projectmain.rest.RetroServer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransactionOut extends AppCompatActivity {
    private TextView tv_Shift, tv_IdStock, tv_MatDoc, tv_Material, tv_Uom, tv_Nik, tv_Quantity, mcaption;
    private String nik, date, shift, material, uom, intent_action = "";
    private EditText et_date_out;
    private int quantity, mat_doc, id_stock;

    Calendar MyCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_data_out);


        tv_MatDoc       = findViewById(R.id.tv_detail_mat_doc);
        tv_IdStock      = findViewById(R.id.tv_detail_id_stock_out);
        tv_Material     = findViewById(R.id.tv_detail_material_out);
        tv_Quantity     = findViewById(R.id.tv_detail_quantity_out);
        tv_Nik          = findViewById(R.id.tv_detail_nik_out);
        mcaption        = findViewById(R.id.caption);
        et_date_out     = findViewById(R.id.et_detail_date_out);
        tv_Shift        = findViewById(R.id.tv_shift_out);
        tv_Uom          = findViewById(R.id.tv_detail_uom_out);
        ImageView image_date = findViewById(R.id.image_date_out);

        Intent intents = getIntent();
        mat_doc = intents.getIntExtra("mat_doc", 0);
        id_stock = intents.getIntExtra("id_stock",0);
        material = intents.getStringExtra("material");
        quantity = intents.getIntExtra("quantity", 0);
        uom = intents.getStringExtra("uom");
        date = intents.getStringExtra("date");
        shift = intents.getStringExtra("shift");
        nik = intents.getStringExtra("nik");
        intent_action = intents.getStringExtra("intent_action");

        setDataFromIntent();

        image_date.setOnClickListener(view -> new DatePickerDialog(DetailTransactionOut.this,dates
                , MyCalendar.get(Calendar.YEAR)
                , MyCalendar.get(Calendar.MONTH)
                , MyCalendar.get(Calendar.DAY_OF_MONTH)).show());
    }
    private void setDataFromIntent() {

        if (mat_doc != 0) {
            //mcaption.setText(intent_action);
            tv_MatDoc.setText(String.valueOf(mat_doc));
            tv_IdStock.setText(String.valueOf(id_stock));
            tv_Material.setText(material);
            tv_Quantity.setText(String.valueOf(quantity));
            tv_Uom.setText(uom);
            et_date_out.setText(date);
            tv_Shift.setText(shift);
            tv_Nik.setText(String.valueOf(nik));
        }
    }

    DatePickerDialog.OnDateSetListener dates = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            MyCalendar.set(Calendar.YEAR, year);
            MyCalendar.set(Calendar.MONTH, month);
            MyCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setDate();
        }
    };

    public void setDate() {

        String MyFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MyFormat, Locale.US);
        et_date_out.setText(simpleDateFormat.format(MyCalendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_add:
                Intent back = new Intent(this, MainActivity.class);
                back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(back);
                break;
            case R.id.menu_update:
                AlertDialog.Builder DialogUpdate = new AlertDialog.Builder(this);
                DialogUpdate.setMessage("Update Data ? ");
                DialogUpdate.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        update_material_out(mat_doc);
                    }
                });
                DialogUpdate.setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                DialogUpdate.show();
                break;
            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("Delete Data ? ");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete_out(mat_doc);
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
        return true;
    }

    public void update_material_out(  final int mat_doc) {
        date = et_date_out.getText().toString().trim();
        String userName = "admin";
        String password = "1234";
        String base = userName +":"+ password;
        String authHeaders = "Basic " + Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);
        final Call<ResponseTransaksiOut> updateOut = RetroServer.getClient().updateConsumption(authHeaders,mat_doc,id_stock,material,quantity,uom,date,shift,nik);

        updateOut.enqueue(new Callback<ResponseTransaksiOut>() {
            @Override
            public void onResponse(Call<ResponseTransaksiOut> call, Response<ResponseTransaksiOut> response) {
                Toast.makeText(DetailTransactionOut.this, "update successfully", Toast.LENGTH_SHORT).show();

                Intent goList = new Intent(DetailTransactionOut.this, MainActivity.class);
                goList.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(goList);
            }

            @Override
            public void onFailure(Call<ResponseTransaksiOut> call, Throwable t) {
                Toast.makeText(DetailTransactionOut.this, "failed update " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void delete_out(final int mat_doc) {
        String userName = "admin";
        String password = "1234";
        String base = userName +":"+ password;
        String authHeader ="Basic "+Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);
        final Call<ResponseTransaksiOut> deleteOut = RetroServer.getClient().deleteConsume(authHeader,mat_doc);
        deleteOut.enqueue(new Callback<ResponseTransaksiOut>() {
            @Override
            public void onResponse(Call<ResponseTransaksiOut> call, Response<ResponseTransaksiOut> response) {
                Toast.makeText(DetailTransactionOut.this, "Deleted item ! ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseTransaksiOut> call, Throwable t) {
                Toast.makeText(DetailTransactionOut.this, "failed Delete , " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

