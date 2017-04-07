package com.example.kyb24.smartfarm.Activitys;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.kyb24.smartfarm.R;
import com.example.kyb24.smartfarm.util.TabPagerAdapter;

import static android.R.id.tabhost;

/**
 * Created by kyb24 on 2017-03-28.
 */

public class FarmsTabActivity extends TabActivity{

    TabHost.TabSpec spec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        final TabHost tabHost = getTabHost();


        spec = tabHost.newTabSpec("Setting");
        spec.setContent(new Intent(this, AddFarmActivity.class));
        spec.setIndicator("Setting");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("House");
        spec.setContent(new Intent(this, StatusActivity.class));
        spec.setIndicator("House");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("House");
        spec.setContent(new Intent(this, StatusActivity.class));
        spec.setIndicator("House");
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

        /*
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabHost.TabSpec spec = tabHost.newTabSpec("tab1").setIndicator("House").setContent(new Intent(this, StatusActivity.class));

                spec.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String tag) {
                        //return new View(R.layout.activity_status);
                        return new DatePicker(FarmsTabActivity.this);
                    }
                });

                tabHost.addTab(spec);
                tabHost.setCurrentTab(tabHost.getChildCount());
            }
        });
*/
    }
}
