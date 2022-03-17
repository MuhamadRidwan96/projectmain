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
import com.example.projectmain.activity.detail.DetailTransactionOut;
import com.example.projectmain.model.MaterialIn;
import com.example.projectmain.model.MaterialOut;

import java.util.ArrayList;
import java.util.List;

public class AdapterTransactionOut extends RecyclerView.Adapter<AdapterTransactionOut.MyViewHolder> implements Filterable {
    private Context ctx;
    public List<MaterialOut> data, materialFilter;
    private LinearLayout linearLayout;
    FilterOfMaterialOut filter;

    public AdapterTransactionOut(Context ctx, List<MaterialOut>data){
        this.ctx=ctx;
        this.data=data;
        this.materialFilter=data;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mat_doc,id_stock,material,quantity,uom,date,shift,nik;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mat_doc = itemView.findViewById(R.id.mat_doc);
            id_stock = itemView.findViewById(R.id.tv_idStock_out);
            material = itemView.findViewById(R.id.tv_item_out);
            quantity = itemView.findViewById(R.id.tv_qty_out);
            uom = itemView.findViewById(R.id.tv_uom_out);
            date = itemView.findViewById(R.id.tv_date_out);
            shift = itemView.findViewById(R.id.tv_shift_out);
            nik = itemView.findViewById(R.id.tv_nik_out);

            linearLayout = itemView.findViewById(R.id.ll_layout_out);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaterialOut mo = data.get(getPosition());
                    Intent goDetailout = new Intent(ctx, DetailTransactionOut.class);
                    goDetailout.putExtra("mat_doc",mo.getMat_doc());
                    goDetailout.putExtra("id_stock",mo.getId_stock());
                    goDetailout.putExtra("material",mo.getMaterial());
                    goDetailout.putExtra("quantity",mo.getQuantity());
                    goDetailout.putExtra("uom",mo.getUom());
                    goDetailout.putExtra("date",mo.getDate());
                    goDetailout.putExtra("shift",mo.getShift());
                    goDetailout.putExtra("nik",mo.getNik());

                    goDetailout.putExtra("intent_action","out");
                  ctx.startActivity(goDetailout);
                }
            });
        }
    }

    @NonNull
    @Override
    public AdapterTransactionOut.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_dataout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTransactionOut.MyViewHolder holder, int position) {
        MaterialOut mo = data.get(position);
        holder.mat_doc.setText(String.valueOf(mo.getMat_doc()));
        holder.id_stock.setText(String.valueOf(mo.getId_stock()));
        holder.material.setText(mo.getMaterial());
        holder.quantity.setText(String.valueOf(mo.getQuantity()));
        holder.uom.setText(mo.getUom());
        holder.date.setText(mo.getDate());
        holder.shift.setText(mo.getShift());
        holder.nik.setText(String.valueOf(mo.getNik()));

    }

    @Override
    public int getItemCount() { return data.size(); }

    @Override
    public Filter getFilter() {
        if (filter==null){
            filter = new FilterOfMaterialOut((List<MaterialOut>) materialFilter,this);
        }
        return filter;
    }

    public void materialOutList(List<MaterialOut> materialOutList) {
        data = materialOutList;
        notifyDataSetChanged();
    }

}
