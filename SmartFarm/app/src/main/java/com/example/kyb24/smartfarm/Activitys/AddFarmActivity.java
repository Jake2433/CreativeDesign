package com.example.kyb24.smartfarm.Activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kyb24.smartfarm.R;



public class AddFarmActivity extends AppCompatActivity {

    static public Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farm);

        btnCreate = (Button)findViewById(R.id.btnCreate);

    }
}
