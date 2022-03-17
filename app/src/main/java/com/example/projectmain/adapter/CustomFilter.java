package com.example.projectmain.adapter;

import android.widget.Filter;

import com.example.projectmain.model.MaterialIn;

import java.util.ArrayList;
import java.util.List;

public class CustomFilter extends Filter {

    AdapterTransactionIn adapterIn;
    List<MaterialIn> filterList;


public CustomFilter(List<MaterialIn> filterList,AdapterTransactionIn adapterIn){
    this.adapterIn=adapterIn;
    this.filterList=filterList;


}
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        //Check constraint validity
        if (constraint !=null&&constraint.length()>=0){
            //change to upper case
            constraint=constraint.toString().toUpperCase();
            //store or filtered player
            ArrayList<MaterialIn> filteredIn = new ArrayList<MaterialIn>();

            for (int i=0;i<filterList.size();i++){
                //check
                if (filterList.get(i).getDate().toUpperCase().contains(constraint)||filterList.get(i).getMaterial().toUpperCase().contains(constraint)){
                    //add layer to filtered players
                    filteredIn.add(filterList.get(i));
                }
            }
            results.count = filteredIn.size();
            results.values = filteredIn;

        } else {
                results.count = filterList.size();
                results.values = filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
    adapterIn.data= (List<MaterialIn>) results.values;
    //refresh
        adapterIn.notifyDataSetChanged();
    }
}
