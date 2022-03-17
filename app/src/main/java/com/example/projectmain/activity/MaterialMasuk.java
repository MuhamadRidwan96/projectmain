
package com.example.projectmain.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.projectmain.R;
import com.example.projectmain.adapter.AdapterTransactionIn;
import com.example.projectmain.model.MaterialIn;
import com.example.projectmain.response.ResponseTransaksiIn;
import com.example.projectmain.rest.ApiRequestData;
import com.example.projectmain.rest.RetroServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaterialMasuk extends AppCompatActivity {

    private RecyclerView rvData;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;
    private SwipeRefreshLayout srf;
    private List<MaterialIn> model;
    private AdapterTransactionIn adapterTransactionIn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_masuk);
        retrieveData();

        rvData = findViewById(R.id.rv_tr_in);
        srf = findViewById(R.id.srf_tr_in);
        progressBar = findViewById(R.id.pb_tr_in);

        layoutManager = new LinearLayoutManager(this);
        rvData.setLayoutManager(layoutManager);
        srf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srf.setRefreshing(true);
                retrieveData();
                srf.setRefreshing(false);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView =( SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem menuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search Dat...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterTransactionIn.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterTransactionIn.getFilter().filter(newText);
                return false;
            }
        });
        menuItem.getIcon().setVisible(false,false);
        return true;
    }

    private void retrieveData() {

        String token;
        String userName = "admin";
        String password = "1234";
        String base = userName +":"+ password;
        String authHeaders = "Basic " + Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);
        final Call<ResponseTransaksiIn> migo = RetroServer.getClient().getMigo(authHeaders);
        migo.enqueue(new Callback<ResponseTransaksiIn>() {
            @Override
            public void onResponse(Call<ResponseTransaksiIn> call, Response<ResponseTransaksiIn> response) {

                model = response.body().getData();

                adapterTransactionIn = new AdapterTransactionIn(MaterialMasuk.this, model);
                rvData.setAdapter(adapterTransactionIn);
                progressBar.setVisibility(View.VISIBLE);
                adapterTransactionIn.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onFailure(Call<ResponseTransaksiIn> call, Throwable t) {
                Log.e("Retrofit get", t.toString());
            }
        });

    }

}