package com.example.projectmain.fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.projectmain.R;
import com.example.projectmain.adapter.AdapterData;
import com.example.projectmain.rest.ApiRequestData;
import com.example.projectmain.rest.RetroServer;
import com.example.projectmain.model.ModelData;
import com.example.projectmain.response.ResponseModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewData extends Fragment {

    private RecyclerView rvData;
    private List<ModelData> model;
    private AdapterData adapterData;
    private RecyclerView.LayoutManager lmData;
    private ProgressBar progressBar;
    private SwipeRefreshLayout srf;
    private ApiRequestData apiRequestData;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transaction_list, container, false);

        progressBar = view.findViewById(R.id.pb_item);
        srf = view.findViewById(R.id.srf_layout);
        rvData = view.findViewById(R.id.rv_transaction);



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
        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);
        final Call<ResponseModel> stock = RetroServer.getClient().Stock(authHeader);
        stock.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, @NotNull Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    model = response.body().getData();
                } else {
                    Toast.makeText(getContext(), "GAGAL", Toast.LENGTH_SHORT).show();
                }

                adapterData = new AdapterData(getActivity(), model);
                RecyclerView.LayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                rvData.setAdapter(adapterData);
                progressBar.setVisibility(View.VISIBLE);
                rvData.setLayoutManager(gridLayoutManager);
                rvData.setItemAnimator(new DefaultItemAnimator());
                adapterData.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), " message : Data Already ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

                Toast.makeText(getActivity(), "Error ! Cannot Connect to Server " + t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });


    }
}
