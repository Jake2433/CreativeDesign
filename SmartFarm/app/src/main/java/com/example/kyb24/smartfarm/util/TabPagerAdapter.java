package com.example.kyb24.smartfarm.util;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kyb24.smartfarm.Activitys.AddFarmActivity;
import com.example.kyb24.smartfarm.Activitys.Graphs.TemperatureGraph;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // change
        // Returning the current tabs
        switch (position) {
/*
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
                TemperatureGraph temperatureGraph = new TemperatureGraph();
                return temperatureGraph;
                */
            default:
                return null;

        }
    }
    @Override
    public int getCount() {
        return tabCount;
    }
}