package com.example.projectmain.fragment;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.projectmain.R;
import com.example.projectmain.adapter.AdapterTransactionIn;
import com.example.projectmain.model.MaterialIn;
import com.example.projectmain.response.ResponseModel;
import com.example.projectmain.response.ResponseTransaksiIn;
import com.example.projectmain.rest.ApiRequestData;
import com.example.projectmain.rest.RetroServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMaterialIn extends Fragment{

    private RecyclerView rvData;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager lmData;
    private ProgressBar progressBar;
    private SwipeRefreshLayout srf;
    private List<MaterialIn> model;
    private AdapterTransactionIn adapterTransactionIn;

    public ViewMaterialIn() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     retrieveData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listtransactionin,container,false);

        rvData=view.findViewById(R.id.rv_transaction_in);
        srf = view.findViewById(R.id.srf_layout_transaction_in);
        progressBar = view.findViewById(R.id.pb_item_transaction_in);

        srf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srf.setRefreshing(true);
                retrieveData();
                srf.setRefreshing(false);
            }
        });
        return  view;
    }

    private void retrieveData() {

        String userName = "admin";
        String password = "1234";
        String base = userName +":"+ password;
        String authHeaders = "Basic " + Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);
       final Call<ResponseTransaksiIn> migo = RetroServer.getClient().getMigo(authHeaders);
        migo.enqueue(new Callback<ResponseTransaksiIn>() {
            @Override
            public void onResponse(Call<ResponseTransaksiIn> call, Response<ResponseTransaksiIn> response) {

                model = response.body().getData();
                if (model.isEmpty()){
                   try {
                       Toast.makeText(getContext(), "data kosong", Toast.LENGTH_SHORT).show();
                   }catch (NullPointerException e){
                       e.printStackTrace();
                   }

                }
                adapterTransactionIn = new AdapterTransactionIn(getContext(),model);
                lmData = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                rvData.setAdapter(adapterTransactionIn);
                progressBar.setVisibility(View.VISIBLE);
                rvData.setLayoutManager(lmData);
                adapterTransactionIn.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onFailure(Call<ResponseTransaksiIn> call, Throwable t) {
                Log.e("Retrofit get",t.toString());
            }
        });

    }

}
