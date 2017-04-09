package com.example.kyb24.smartfarm.Activitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.DialogInterface.OnDismissListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


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
        _valueField = (Button)findViewById(R.id.BtnControlIllumination );
        Button btn = (Button)findViewById(R.id.BtnControlIllumination);
        btn.setOnClickListener(this) ;
    }

    public void BCTClick(View view)
    {
        _valueField = (Button)findViewById(R.id.BtnControlTemperature);
        Button btn = (Button)findViewById(R.id.BtnControlTemperature);
        btn.setOnClickListener(this) ;
    }

    public void BCVClick(View view)
    {
        _valueField = (Button)findViewById(R.id.BtnControlVentilator);
        Button btn = (Button)findViewById(R.id.BtnControlVentilator);
        btn.setOnClickListener(this);
    }

    public void BCHClick(View view)
    {
        _valueField = (Button)findViewById( R.id.BtnControlHumidity );
        Button btn = (Button)findViewById(R.id.BtnControlHumidity);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        CustomDialog dialog = new CustomDialog(this) ;
        dialog.setOnDismissListener(this);
        dialog.show();
    }

    @Override
    public void onDismiss(DialogInterface $dialog) {
        CustomDialog dialog = (CustomDialog) $dialog;
        String value = dialog.getValue();
        _valueField.setText(value);
    }


    public void ClickFeed(View view)
    {
        final CharSequence[] FeedingNumber = {"1번 모이통", "2번 모이통", "3번 모이통"};
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setIcon(R.drawable.feed_icon);
        alt_bld.setTitle("모이통을 선택해 주세요");

        alt_bld.setSingleChoiceItems(FeedingNumber, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // 각 리스트를 선택했을때
                    }
                }).setPositiveButton("Ok",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // OK 버튼 클릭시 , 여기서 선택한 값을 메인 Activity 로 넘기면 된다.

                    }
                }).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Cancel 버튼 클릭시
                        Toast.makeText(getApplicationContext(), "취소 하셨습니다.", Toast.LENGTH_LONG).show();
                    }
                });
        alt_bld.show();
    }

    public void ClickWater(View view)
    {
        final CharSequence[] WaterNumber = {"1번 식수통", "2번 식수통", "3번 식수통"};
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setIcon(R.drawable.water_icon);
        alt_bld.setTitle("식수통을 선택해 주세요");

        alt_bld.setSingleChoiceItems(WaterNumber, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // 각 리스트를 선택했을때
                    }
                }).setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // OK 버튼 클릭시 , 여기서 선택한 값을 메인 Activity 로 넘기면 된다.
                    }
                }).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Cancel 버튼 클릭시
                        Toast.makeText(getApplicationContext(), "취소 하셨습니다.", Toast.LENGTH_LONG).show();
                    }
                });
        alt_bld.show();
    }

    public void ClickCCTV(View view)
    {
        Intent intent = new Intent(this,CCTVActivity.class);
        startActivity(intent);
    }
}