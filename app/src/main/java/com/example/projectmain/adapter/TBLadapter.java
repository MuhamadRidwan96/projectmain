package com.example.projectmain.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.projectmain.fragment.Material_Out;
import com.example.projectmain.fragment.MenuFragment;
import com.example.projectmain.fragment.Material_In;

public class TBLadapter extends FragmentPagerAdapter {
    private int numbOftTabs;
    public TBLadapter(@NonNull FragmentManager fm, int numbOfTabs) {
        super(fm);
        this.numbOftTabs = numbOfTabs;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 :
                return new MenuFragment();
            case 1:
                return new Material_In();
            case 2 :
                return new Material_Out();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numbOftTabs;
    }
}
