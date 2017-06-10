package com.example.kyb24.smartfarm.Activitys.Graphs;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.kyb24.smartfarm.Activitys.Graphs.HumidGasGraph;
import com.example.kyb24.smartfarm.Activitys.Graphs.IlluminationGraph;
import com.example.kyb24.smartfarm.Activitys.Graphs.TemperatureGraph;

import com.example.kyb24.smartfarm.R;

/**
 * Created by kyb24 on 2017-03-28.
 */

public class GraphTabActivity extends TabActivity{

    TabHost.TabSpec spec;
    static TabHost tabHost;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_tab);

        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        spec = tabHost.newTabSpec("home"); // Create a new TabSpec using tab host
        spec.setIndicator("HOME"); // set the “HOME” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, TemperatureGraph.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs

        spec = tabHost.newTabSpec("Contact"); // Create a new TabSpec using tab host
        spec.setIndicator("CONTACT"); // set the “CONTACT” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, HumidGasGraph.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("About"); // Create a new TabSpec using tab host
        spec.setIndicator("ABOUT"); // set the “ABOUT” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, IlluminationGraph.class);
        spec.setContent(intent);
        tabHost.addTab(spec);
        //set tab which one you want to open first time 0 or 1 or 2
        tabHost.setCurrentTab(0);


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
        tabHost.setCurrentTab(0);

    }
/*
    void NewTab(){
        spec = tabHost.newTabSpec("House");
        spec.setContent(new Intent(this, StatusActivity.class));
        spec.setIndicator("House");
        tabHost.addTab(spec);
    }
    */

}
