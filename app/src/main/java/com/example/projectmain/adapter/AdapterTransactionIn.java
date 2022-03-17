package com.example.projectmain.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmain.R;
import com.example.projectmain.activity.detail.DetailTransactionIn;
import com.example.projectmain.model.MaterialIn;

import java.util.ArrayList;
import java.util.List;

public class AdapterTransactionIn extends RecyclerView.Adapter<AdapterTransactionIn.MyViewHolder> implements Filterable {
    public List<MaterialIn> data,materialFilter;
    private Context ctx;
    private LinearLayout linearLayout;
    CustomFilter filter;

    public AdapterTransactionIn(Context ctx, List<MaterialIn> data) {
        this.ctx = ctx;
        this.data = data;
        this.materialFilter = data;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView no_doc,id_stock,material,quantity,uom,date,shift,nik;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            no_doc = itemView.findViewById(R.id.tv_no_doc);
            id_stock = itemView.findViewById(R.id.tv_id_stock);
            material = itemView.findViewById(R.id.tv_material);
            quantity = itemView.findViewById(R.id.tv_qty_in);
            uom = itemView.findViewById(R.id.tv_uom_in);
            date = itemView.findViewById(R.id.tv_date_in);
            shift = itemView.findViewById(R.id.tv_shift_in);
            nik = itemView.findViewById(R.id.tv_nik_in);

            linearLayout = itemView.findViewById(R.id.ll_layoutin);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaterialIn mi = data.get(getPosition());
                    Intent goDetail = new Intent(ctx, DetailTransactionIn.class);
                    goDetail.putExtra("no_doc", mi.getNo_doc());
                    goDetail.putExtra("id_stock",mi.getId_stock());
                    goDetail.putExtra("material", mi.getMaterial());
                    goDetail.putExtra("quantity", mi.getQuantity());
                    goDetail.putExtra("uom", mi.getUom());
                    goDetail.putExtra("date", mi.getDate());
                    goDetail.putExtra("shift", mi.getShift());
                    goDetail.putExtra("nik", mi.getNik());
                    goDetail.putExtra("intent_action", "in");
                    ctx.startActivity(goDetail);
                }
            });
        }
    }

    @NonNull
    @Override
    public AdapterTransactionIn.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_datain, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTransactionIn.MyViewHolder holder, int position) {
        MaterialIn mi = data.get(position);
        holder.no_doc.setText(String.valueOf(mi.getNo_doc()));
        holder.id_stock.setText(String.valueOf(mi.getId_stock()));
        holder.material.setText(mi.getMaterial());
        holder.quantity.setText(String.valueOf(mi.getQuantity()));
        holder.uom.setText(mi.getUom());
        holder.date.setText(mi.getDate());
        holder.shift.setText(mi.getShift());
        holder.nik.setText(String.valueOf(mi.getNik()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter((List<MaterialIn>) materialFilter, this);
        }
        return filter;
    }

    public void filterList(List<MaterialIn> filteredList) {
        data = filteredList;
        notifyDataSetChanged();
    }
}
