package com.example.projectmain.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.projectmain.R;
import com.example.projectmain.fragment.Material_In;
import com.example.projectmain.fragment.Material_Out;
import com.example.projectmain.fragment.MenuFragment;
import com.example.projectmain.utils.SessionManager;
import com.example.projectmain.utils.Utils;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    ViewPager viewPager;
    // TabLayout tabs;
    //TabItem tab_menu, tab_transaction_in, getTab_transaction_out;
    //TBLadapter tab_adapter;
    NavigationView navigationView;
    Toolbar toolbar;
    SessionManager sessionManager;
    MeowBottomNavigation meowBottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbars);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);


        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.CheckLogin();


        //viewPager = findViewById(R.id.vp_menu);
        Utils.blackIconStatusBar(MainActivity.this, R.color.colorDarkBackground);

        meowBottomNavigation = findViewById(R.id.bottom_navigation);

        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_assignment));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_home));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_assignment_return));

        meowBottomNavigation.setOnShowListener(item -> {
            //iniliaze fragmnet
            Fragment fragment = null;

            switch (item.getId()) {

                case 1:

                    fragment = new Material_In();
                    break;

                case 2:
                    fragment = new MenuFragment();
                    break;

                case 3:
                    fragment = new Material_Out();
                    break;
            }
            loadFragment(fragment);

        });

        // meowBottomNavigation.setCount( 1, "10");

        meowBottomNavigation.show(2, true);

        meowBottomNavigation.setOnClickMenuListener(item -> {
            //Toast.makeText(MainActivity.this, "you clicked" + item.getId(), Toast.LENGTH_SHORT).show();
        });

        meowBottomNavigation.setOnReselectListener(item -> {
            //Toast.makeText(MainActivity.this, "you reselected" + item.getId(), Toast.LENGTH_SHORT).show();
        });
        //tabLayout
        /*
        tabs = findViewById(R.id.tabLayout);
        tab_menu = findViewById(R.id.tabItem_menu);
        tab_transaction_in = findViewById(R.id.tabItem_tr_in);


        tab_adapter = new TBLadapter(getSupportFragmentManager(), tabs.getTabCount());
        viewPager.setAdapter(tab_adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });*/

        //  setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar
                , R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_material_in:
                Intent intent = new Intent(this, MaterialMasuk.class);
                startActivity(intent);
                break;
            case R.id.item_material_out:
                Intent intent1 = new Intent(this, MaterialTerpakai.class);
                startActivity(intent1);
                break;
            case R.id.generate_location:
                Intent intent2 = new Intent(this, GenerateQR.class);
                startActivity(intent2);
                break;
            case R.id.logout:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("Do You Want Logout? ");
                dialog.setPositiveButton("Ok", (dialog1, which) -> sessionManager.LogOutUser());
                dialog.setNegativeButton("Cancel", (dialog2, which) -> dialog2.dismiss());

                dialog.show();

                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container1, fragment)
                .commit();
    }


}
