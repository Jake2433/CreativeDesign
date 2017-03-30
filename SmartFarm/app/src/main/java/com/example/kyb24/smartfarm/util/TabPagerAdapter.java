package com.example.kyb24.smartfarm.util;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kyb24.smartfarm.Activitys.AddFarmFragment;
import com.example.kyb24.smartfarm.Farms.ChickenFarm1;
import com.example.kyb24.smartfarm.Farms.ChickenFarm2;
import com.example.kyb24.smartfarm.Farms.ChickenFarm3;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                ChickenFarm1 tabFragment1 = new ChickenFarm1();
                return tabFragment1;
            case 1:
                ChickenFarm2 tabFragment2 = new ChickenFarm2();
                return tabFragment2;
            case 2:
                ChickenFarm3 tabFragment3 = new ChickenFarm3();
                return tabFragment3;
            case 3 :
                AddFarmFragment newFarm = new AddFarmFragment();
                return newFarm;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return tabCount;
    }
}