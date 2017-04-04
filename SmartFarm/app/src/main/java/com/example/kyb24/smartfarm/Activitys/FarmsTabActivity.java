package com.example.kyb24.smartfarm.Activitys;

import android.app.TabActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TabHost;

import com.example.kyb24.smartfarm.R;
import com.example.kyb24.smartfarm.util.TabPagerAdapter;

/**
 * Created by kyb24 on 2017-03-28.
 */

public class FarmsTabActivity extends TabActivity {

    //private TabLayout tabLayout;
    //private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;

        spec = tabHost.newTabSpec("TAB1").setIndicator("House1").setContent(R.id.tab1);
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("TAB2").setIndicator("House2").setContent(R.id.tab2);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
        /*
        TabHost.TabSpec tabSpecTab2 = tabHost.newTabSpec("TAB2").setIndicator("House2");
        tabSpecTab2.setContent(R.id.tab2);
        tabHost.addTab(tabSpecTab2);

        TabHost.TabSpec tabSpecTab3 = tabHost.newTabSpec("TAB3").setIndicator("House3");
        tabSpecTab3.setContent(R.id.tab3);
        tabHost.addTab(tabSpecTab3);

        TabHost.TabSpec tabSpecTab4 = tabHost.newTabSpec("TAB4").setIndicator("House4");
        tabSpecTab4.setContent(R.id.tab1);
        tabHost.addTab(tabSpecTab4);
        */



        /*
        // Adding Toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar bar = getSupportActionBar();
        if(bar != null) {
            bar.hide();
        }

        // Initializing the TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("1번 하우스"));
        tabLayout.addTab(tabLayout.newTab().setText("2번 하우스"));
        tabLayout.addTab(tabLayout.newTab().setText("3번 하우스"));
        tabLayout.addTab(tabLayout.newTab().setText("+"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Initializing ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        // Creating TabPagerAdapter adapter
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

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
        });
        */
    }
}
