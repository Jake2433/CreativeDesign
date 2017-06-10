package com.example.kyb24.smartfarm.Activitys.Graphs;

import android.app.FragmentManager;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.kyb24.smartfarm.R;
import com.example.kyb24.smartfarm.util.TabPagerAdapter;

/**
 * Created by kyb24 on 2017-03-28.
 */

public class GraphTabActivity extends TabActivity{

    TabHost.TabSpec spec;
    TabHost tabHost ;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_tab);

        //tabHost = getTabHost();
        tabHost = (TabHost)findViewById(android.R.id.tabhost);


        /*
        SetTabs();


        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if(s.equals("Temperature")){
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    tabHost.setCurrentTab(0);
                }
                else if(s.equals("Humid/Gas")){
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    tabHost.setCurrentTab(1);
                }
                else{
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    tabHost.setCurrentTab(2);
                }
            }
        });
        */
    }

    void SetTabs(){
        spec = tabHost.newTabSpec("Temperature"); // Create a new TabSpec using tab host
        spec.setIndicator("Temperature"); // set the “HOME” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent = new Intent(this, TemperatureGraph.class);
      //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       // spec.setContent();
        spec.setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs

        spec = tabHost.newTabSpec("Humid/Gas"); // Create a new TabSpec using tab host
        spec.setIndicator("Humid/Gas"); // set the “CONTACT” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, HumidGasGraph.class);
      //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // spec.setContent();
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Illumination"); // Create a new TabSpec using tab host
        spec.setIndicator("Illumination"); // set the “ABOUT” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent = new Intent(this, IlluminationGraph.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // spec.setContent();
        spec.setContent(intent);
        tabHost.addTab(spec);

        tabHost.setup();
        //set tab which one you want to open first time 0 or 1 or 2
        tabHost.setCurrentTab(1);
    }
}


//tabHost = getTabHost();


        /*
        spec = tabHost.newTabSpec("Temperature");
        spec.setContent(new Intent(this, TemperatureGraph.class));
        spec.setIndicator("Temperature");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Humid/Gas");
        spec.setContent(new Intent(this, HumidGasGraph.class));
        spec.setIndicator("Humid/Gas");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Illumination");
        spec.setContent(new Intent(this, IlluminationGraph.class));
        spec.setIndicator("Illumination");
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
        */


        /*
        spec = tabHost.newTabSpec("Setting");
        spec.setContent(new Intent(this, AddFarmActivity.class));
        spec.setIndicator("Setting");
        tabHost.addTab(spec);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "출력할 문자열", Toast.LENGTH_LONG).show();
                NewTab();
            }
        });
        */

        /*
    void NewTab(){
        spec = tabHost.newTabSpec("House");
        spec.setContent(new Intent(this, StatusActivity.class));
        spec.setIndicator("House");
        tabHost.addTab(spec);
    }
    */