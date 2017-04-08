package com.example.kyb24.smartfarm.Activitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.DialogInterface.OnDismissListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


import com.example.kyb24.smartfarm.R;

public class StatusActivity extends AppCompatActivity implements OnDismissListener, OnClickListener{

    private TextView _valueField ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
    }
    public void BCIClick(View view)
    {
        _valueField = (Button) findViewById( R.id.BtnControlIllumination ) ;
        Button btn = (Button) findViewById(R.id.BtnControlIllumination) ;
        btn.setOnClickListener( this ) ;
    }

    public void BCTClick(View view)
    {
        _valueField = (Button) findViewById( R.id.BtnControlTemperature ) ;
        Button btn = (Button) findViewById(R.id.BtnControlTemperature) ;
        btn.setOnClickListener( this ) ;
    }

    public void BCVClick(View view)
    {
        _valueField = (Button) findViewById( R.id.BtnControlVentilator ) ;
        Button btn = (Button) findViewById(R.id.BtnControlVentilator) ;
        btn.setOnClickListener( this ) ;
    }

    public void BCHClick(View view)
    {
        _valueField = (Button) findViewById( R.id.BtnControlHumidity ) ;
        Button btn = (Button) findViewById(R.id.BtnControlHumidity) ;
        btn.setOnClickListener( this ) ;
    }

    public void ClickCCTV(View view)
    {
        Intent intent = new Intent(this,CCTVActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        CustomDialog dialog = new CustomDialog( this ) ;
        dialog.setOnDismissListener( this ) ;
        dialog.show() ;
    }

    @Override
    public void onDismiss(DialogInterface $dialog) {
        CustomDialog dialog = (CustomDialog) $dialog ;
        String value = dialog.getValue() ;
        _valueField.setText( value ) ;
    }
}