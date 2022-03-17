package com.example.projectmain.adapter;

import android.widget.Filter;

import com.example.projectmain.model.MaterialOut;

import java.util.ArrayList;
import java.util.List;

public class FilterOfMaterialOut extends Filter {

    AdapterTransactionOut adapterTransactionOut;
    List<MaterialOut> materialOutList;

    public FilterOfMaterialOut(List<MaterialOut> materialOutList, AdapterTransactionOut adapterTransactionOut) {
       this.adapterTransactionOut=adapterTransactionOut;
       this.materialOutList=materialOutList;
    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();

        if (constraint !=null&&constraint.length()>=0){
            constraint=constraint.toString().toUpperCase();
            ArrayList<MaterialOut> filteredOut = new ArrayList<MaterialOut>();

            for (int i=0;i<materialOutList.size();i++){

                if (materialOutList.get(i).getDate().toUpperCase().contains(constraint)||
                        materialOutList.get(i).getMaterial().toUpperCase().contains(constraint)) {
                    filteredOut.add(materialOutList.get(i));
                }
                filterResults.count=filteredOut.size();
                filterResults.values=filteredOut;
            }
        }


        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterTransactionOut.data =(List<MaterialOut>) results.values;

        adapterTransactionOut.notifyDataSetChanged();

    }
}
