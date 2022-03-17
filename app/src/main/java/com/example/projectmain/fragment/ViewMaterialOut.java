package com.example.projectmain.fragment;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.projectmain.R;
import com.example.projectmain.adapter.AdapterTransactionIn;
import com.example.projectmain.adapter.AdapterTransactionOut;
import com.example.projectmain.model.MaterialIn;
import com.example.projectmain.model.MaterialOut;
import com.example.projectmain.response.ResponseModel;
import com.example.projectmain.response.ResponseTransaksiIn;
import com.example.projectmain.response.ResponseTransaksiOut;
import com.example.projectmain.rest.ApiRequestData;
import com.example.projectmain.rest.RetroServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewMaterialOut extends Fragment {

    private RecyclerView rvData;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager lmData;
    private ProgressBar progressBar;
    private SwipeRefreshLayout srf;
    private List<MaterialOut> model;
    private AdapterTransactionOut adapterTransactionOut;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrieveData();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listtransactionout, container, false);

        rvData = view.findViewById(R.id.rv_transaction_out);
        srf = view.findViewById(R.id.srf_layout_transaction_out);
        progressBar = view.findViewById(R.id.pb_item_transaction_out);

        srf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srf.setRefreshing(true);
                retrieveData();
                srf.setRefreshing(false);
            }
        });

        return view;

    }

    private void retrieveData() {
        String userName = "admin";
        String password = "1234";
        String base = userName +":"+ password;

        String authHeaders1 = "Basic " + Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);
       final Call<ResponseTransaksiOut> consume = RetroServer.getClient().getConsume(authHeaders1);
        consume.enqueue(new Callback<ResponseTransaksiOut>() {
            @Override
            public void onResponse(Call<ResponseTransaksiOut> call, Response<ResponseTransaksiOut> response) {
                model = response.body().getData();

                adapterTransactionOut = new AdapterTransactionOut(getContext(),model);
                LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                rvData.setAdapter(adapterTransactionOut);
                progressBar.setVisibility(View.VISIBLE);
                rvData.setLayoutManager(gridLayoutManager);
                adapterTransactionOut.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<ResponseTransaksiOut> call, Throwable t) {
                Log.e("Retrofit get",t.toString());
            }
        });
    }

}
