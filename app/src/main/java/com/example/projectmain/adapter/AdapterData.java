package com.example.projectmain.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmain.R;
import com.example.projectmain.activity.AddMenu;
import com.example.projectmain.activity.ScanActivity;
import com.example.projectmain.model.ModelData;
import com.example.projectmain.response.ResponseModel;
import com.example.projectmain.rest.ApiRequestData;
import com.example.projectmain.rest.RetroServer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private Context ctx;
    private List<ModelData> data;
    private LinearLayout linearLayout;



    public class HolderData extends RecyclerView.ViewHolder {
        public TextView id_s,bin,mm,item,available_stock,uom,gr_date;


        public HolderData(@NonNull View itemView) {
            super(itemView);
            id_s = itemView.findViewById(R.id.tv_id);
            bin = itemView.findViewById(R.id.tv_bin);
            mm = itemView.findViewById(R.id.tv_mm);
            item= itemView.findViewById(R.id.tv_item);
            available_stock = itemView.findViewById(R.id.tv_available_stock);
            uom = itemView.findViewById(R.id.tv_uom);
            gr_date = itemView.findViewById(R.id.tv_gr_date);

            linearLayout = itemView.findViewById(R.id.ll_layout);
            linearLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    AlertDialog.Builder dialog = new AlertDialog.Builder(view.getRootView().getContext());
                    dialog.setMessage("CHOOSE ACTION");
                    dialog.setPositiveButton("Consume", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ModelData md = data.get(getPosition());
                            dialogInterface.dismiss();
                            Intent goADD = new Intent(ctx, ScanActivity.class);
                            goADD.putExtra("id_stock",md.getId_stock());
                            goADD.putExtra("bin",md.getBin());
                            goADD.putExtra("mm", md.getMm());
                            goADD.putExtra("date", md.getGr_date());
                            goADD.putExtra("item", md.getItem());
                            goADD.putExtra("available_stock", md.getAvailable_stock());
                            goADD.putExtra("uom", md.getUom());
                            goADD.putExtra("gr_date",md.getGr_date());
                            goADD.putExtra("intent_action","consume");
                            goADD.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
                          ctx.startActivity(goADD);
                        }
                    });
                    dialog.setNegativeButton("Migo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            ModelData md = data.get(getPosition());
                            dialogInterface.dismiss();
                            Intent goADD = new Intent(ctx,ScanActivity.class);
                            goADD.putExtra("id_stock",md.getId_stock());
                            goADD.putExtra("bin",md.getBin());
                            goADD.putExtra("mm", md.getMm());
                            goADD.putExtra("date", md.getGr_date());
                            goADD.putExtra("item", md.getItem());
                            goADD.putExtra("available_stock", md.getAvailable_stock());
                            goADD.putExtra("uom", md.getUom());
                            goADD.putExtra("gr_date",md.getGr_date());
                            goADD.putExtra("intent_action","migo");
                            ctx.startActivity(goADD);
                        }
                    });

                    dialog.show();
                }
            });
            /* linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(v.getRootView().getContext());
                    dialog.setCancelable(true);
                    dialog.setMessage("DO YOU WANT DELETED THIS ITEM ?");

                    dialog.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int id_stock = Integer.parseInt(id_s.getText().toString().trim());
                            String userName = "admin";
                            String passWord = "1234";
                            String base = userName+":"+passWord;
                            String authHeader ="Basic " + Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);


                            final Call<ResponseModel> call = RetroServer.getClient().deleteStock(authHeader,id_stock);
                            call.enqueue(new Callback<ResponseModel>() {
                                @Override
                                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                    //String message = response.body().getMessage();
                                    Toast.makeText(v.getRootView().getContext(), "message : ", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<ResponseModel> call, Throwable t) {
                                    Toast.makeText(v.getRootView().getContext(), "message : " +t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                    return false;
                }
            });*/
        }
    }

    public AdapterData(Context ctx, List<ModelData> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_data, parent, false);

        return new HolderData(view);
    }

    @Override
    public int getItemCount() {
         return data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ModelData md = data.get(position);
        holder.id_s.setText(String.valueOf(md.getId_stock()));
        holder.bin.setText(md.getBin());
        holder.mm.setText(String.valueOf(md.getMm()));
        holder.item.setText(md.getItem());
        holder.available_stock.setText(String.valueOf(md.getAvailable_stock()));
        holder.uom.setText(md.getUom());
        holder.gr_date.setText(md.getGr_date());

    }


}


