package com.example.kyb24.smartfarm.Farms;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyb24.smartfarm.R;
/**
 * Created by kyb24 on 2017-03-28.
 */

public class ChickenFarm1 extends Fragment{

    private Button btnCreate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_chickenfarm_1, container, false);


        btnCreate = (Button)view.findViewById(R.id.btnCreate);
       // btnCreate.setOnClickListener();
    // fuck
        return view;
    }

}
