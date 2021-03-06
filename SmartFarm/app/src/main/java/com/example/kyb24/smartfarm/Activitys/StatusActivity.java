package com.example.kyb24.smartfarm.Activitys;

import android.content.DialogInterface;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kyb24.smartfarm.Activitys.Graphs.HumidGraph;
import com.example.kyb24.smartfarm.Activitys.Graphs.IlluminationGasGraph;
import com.example.kyb24.smartfarm.Activitys.Graphs.TemperatureGraph;
import com.example.kyb24.smartfarm.R;
import com.example.kyb24.smartfarm.util.Util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class StatusActivity extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener {
    TextView tvCurLight, tvCurTemper, tvCurAirCondi, tvCurHumid;
    Button btnDesirLight, btnDesirTemper, btnDesirAirCondi, btnDesirHumid;
    ImageButton btnFeed, btnWater, btnCCTV, btnGraph;
    ImageButton btnLogout;

    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;

    int  graphSelection = 0;

    String selectedVal;
    Spinner temperatureSpinner,humidSpinner,airSpinner,lightSpinner;

    Timer t;

    int btn = -1;
    private TextView _valueField ;

    String feeding_state_value;
    String water_state_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        InitVariables();


        SetTimer();
    }

    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnDesirLight:
                btn = 0;
                lightSpinner.performClick();
                break;
            case R.id.btnDesirTemper:
                btn = 1;
                temperatureSpinner.performClick();
                break;
            case R.id.btnDesirAirCondi:
                btn = 2;
                airSpinner.performClick();
                break;
            case R.id.btnDesirHumid:
                btn = 3;
                humidSpinner.performClick();
                break;
            case R.id.btnFeed:
                ClickFeed();
                break;
            case R.id.btnWater:
                ClickWater();
                break;
            case R.id.btnCCTV:
                ClickCCTV();
                break;
            case R.id.btnGraph:
                ClickGraph();
                break;
            case R.id.btnLogout:
                ClickLogOut();
                break;
            case R.id.btnOK:
                break;
        }
    }

    void SetDesirSensorVal(String eventSensor, String desirVal ){
        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost(Util.serverAddress + "/setDesirSensorValue.php");
            nameValuePairs = new ArrayList<NameValuePair>(3);
            nameValuePairs.add(new BasicNameValuePair("eventSensor", eventSensor));
            nameValuePairs.add(new BasicNameValuePair("DesirVal", desirVal));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e) {}
    }


    void InitVariables() {
        tvCurLight = (TextView) findViewById(R.id.tvCurLight);
        tvCurTemper = (TextView) findViewById(R.id.tvCurTemper);
        tvCurAirCondi = (TextView) findViewById(R.id.tvCurAirCondi);
        tvCurHumid = (TextView) findViewById(R.id.tvCurHumid);

        btnDesirLight = (Button) findViewById(R.id.btnDesirLight);
        btnDesirTemper = (Button) findViewById(R.id.btnDesirTemper);
        btnDesirAirCondi = (Button) findViewById(R.id.btnDesirAirCondi);
        btnDesirHumid = (Button) findViewById(R.id.btnDesirHumid);

        btnFeed = (ImageButton) findViewById(R.id.btnFeed);
        btnWater = (ImageButton) findViewById(R.id.btnWater);
        btnCCTV = (ImageButton) findViewById(R.id.btnCCTV);
        btnGraph = (ImageButton) findViewById(R.id.btnGraph);
        btnLogout = (ImageButton)findViewById(R.id.btnLogout);

        btnDesirLight.setOnClickListener(this);
        btnDesirTemper.setOnClickListener(this);
        btnDesirAirCondi.setOnClickListener(this);
        btnDesirHumid.setOnClickListener(this);

        btnFeed.setOnClickListener(this);
        btnWater.setOnClickListener(this);
        btnCCTV.setOnClickListener(this);
        btnGraph.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

        temperatureSpinner = (Spinner) findViewById(R.id.temperatureSpinner);
        humidSpinner = (Spinner) findViewById(R.id.humidSpinner);
        airSpinner = (Spinner) findViewById(R.id.airSpinner);
        lightSpinner = (Spinner) findViewById(R.id.lightSpinner);

        SetSpinner(temperatureSpinner,20,35,1);
        SetSpinner(humidSpinner,45,85,5);
        SetSpinner(airSpinner,100,900,100);
        SetSpinner(lightSpinner,300,900,100);

    }

    void SetSpinner(Spinner spinner, int first, int last,int gap){
        List<String> list = new ArrayList<String>();
        for(int i=first; i<=last; i+=gap){
            list.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        // Create an ArrayAdapter using the string array and a default spinner layout
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.desire_values, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        //Toast.makeText(getApplicationContext(), "The planet is " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
        switch (btn){
            case 0:
                //Toast.makeText(getApplicationContext(), parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
                btnDesirLight.setText(parent.getItemAtPosition(pos).toString());
                SetDesirSensorVal("cds", parent.getItemAtPosition(pos).toString());
                break;
            case 1:
                //Toast.makeText(getApplicationContext(), parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
                btnDesirTemper.setText(parent.getItemAtPosition(pos).toString());
                SetDesirSensorVal("temperature", parent.getItemAtPosition(pos).toString());
                break;
            case 2:
                //Toast.makeText(getApplicationContext(), parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
                btnDesirAirCondi.setText(parent.getItemAtPosition(pos).toString());
                SetDesirSensorVal("gas", parent.getItemAtPosition(pos).toString());
                break;
            case 3:
                //Toast.makeText(getApplicationContext(), parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
                btnDesirHumid.setText(parent.getItemAtPosition(pos).toString());
                SetDesirSensorVal("humidity", parent.getItemAtPosition(pos).toString());
                break;
        }
    }
    void SetSensorValue(){
        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost(Util.serverAddress + "/returnRecentSensorValue.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            JSONObject json = new JSONObject(response);

            tvCurLight.setText(json.getString("cds"));
            tvCurTemper.setText(json.getString("temperature"));
            tvCurAirCondi.setText(json.getString("gas"));
            tvCurHumid.setText(json.getString("humidity"));
            //Toast.makeText(getApplicationContext(), json.getString("temperature"), Toast.LENGTH_SHORT).show();
        }
        catch(Exception e) {}
    }


    public void ClickFeed()
    {
        feeding_state_value = "100";

        final CharSequence[] WaterNumber = {"OPEN", "CLOSE"};
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setIcon(R.drawable.feed_icon);
        alt_bld.setTitle("먹이통 수동조절");

        alt_bld.setSingleChoiceItems(WaterNumber, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // 각 리스트를 선택했을때
                        if(whichButton == 0) {
                            feeding_state_value = "100";
                        }
                        else {
                            feeding_state_value = "1";
                        }
                    }
                }).setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // OK 버튼 클릭시 , 여기서 선택한 값을 메인 Activity 로 넘기면 된다.

                        try {
                            httpclient = new DefaultHttpClient();
                            httppost = new HttpPost(Util.serverAddress + "/manual.php");
                            nameValuePairs = new ArrayList<NameValuePair>(3);
                            nameValuePairs.add(new BasicNameValuePair("Feed", feeding_state_value));
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                            response = httpclient.execute(httppost);
                            ResponseHandler<String> responseHandler = new BasicResponseHandler();
                            final String response = httpclient.execute(httppost, responseHandler);
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        }
                        catch(Exception e) {}


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

    public void ClickWater()
    {
        water_state_value = "100";

        final CharSequence[] WaterNumber = {"OPEN", "CLOSE"};
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setIcon(R.drawable.water_icon);
        alt_bld.setTitle("식수통 수동조절");

        alt_bld.setSingleChoiceItems(WaterNumber, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // 각 리스트를 선택했을때
                        if(whichButton == 0) {
                            water_state_value = "100";
                        }
                        else {
                            water_state_value = "1";
                        }
                    }
                }).setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // OK 버튼 클릭시 , 여기서 선택한 값을 메인 Activity 로 넘기면 된다.

                        try {
                            httpclient = new DefaultHttpClient();
                            httppost = new HttpPost(Util.serverAddress + "/manual.php");
                            nameValuePairs = new ArrayList<NameValuePair>(3);
                            nameValuePairs.add(new BasicNameValuePair("Water", water_state_value));
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                            response = httpclient.execute(httppost);
                            ResponseHandler<String> responseHandler = new BasicResponseHandler();
                            final String response = httpclient.execute(httppost, responseHandler);
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        }
                        catch(Exception e) {}

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

    public void ClickGraph(){
        graphSelection = 0;

        final CharSequence[] FeedingNumber = {"온도", "습도", "조도/가스"};
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setIcon(R.drawable.feed_icon);
        alt_bld.setTitle("조회할 그래프를 선택해 주세요");

        alt_bld.setSingleChoiceItems(FeedingNumber, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // 각 리스트를 선택했을때
                        //Toast.makeText(getApplicationContext(), FeedingNumber[whichButton], Toast.LENGTH_SHORT).show();

                        graphSelection = whichButton;
                    }
                }).setPositiveButton("Ok",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Toast.makeText(getApplicationContext(), String.valueOf(FeedSelected), Toast.LENGTH_LONG).show();
                        // OK 버튼 클릭시 , 여기서 선택한 값을 메인 Activity 로 넘기면 된다.
                        Intent intent;
                        if(graphSelection == 0){
                            intent = new Intent(StatusActivity.this,TemperatureGraph.class);
                            startActivity(intent);
                        }
                        else if(graphSelection == 1){
                            intent = new Intent(StatusActivity.this,HumidGraph.class);
                            startActivity(intent);
                        }
                        else if(graphSelection == 2){
                            intent = new Intent(StatusActivity.this,IlluminationGasGraph.class);
                            startActivity(intent);
                        }
                    }
                }).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Cancel 버튼 클릭시
                        Toast.makeText(getApplicationContext(), "취소 하셨습니다.", Toast.LENGTH_LONG).show();
                        graphSelection = 0;
                    }
                });
        alt_bld.show();
        /*
        Intent intent = new Intent(StatusActivity.this, GraphTabActivity.class);
        startActivity(intent);
        */
    }

    public void ClickCCTV()
    {
        Intent intent = new Intent(this,CCTVActivity.class);
        startActivity(intent);
    }

    public void ClickLogOut()
    {
        Intent intent = new Intent(StatusActivity.this, MainActivity.class);
        startActivity(intent);
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = auto.edit();
        //editor.clear()는 auto에 들어있는 모든 정보를 기기에서 지웁니다.
        editor.clear();
        editor.commit();
        Toast.makeText(StatusActivity.this, "로그아웃.", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        t.cancel();
        t.purge();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SetTimer();
    }

    void SetTimer(){
        //Declare the timer
        t = new Timer();
        //Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {
                                  @Override
                                  public void run() {
                                      //Called each time when 1000 milliseconds (1 second) (the period parameter)
                                      //We must use this function in order to change the text view text
                                      runOnUiThread(new Runnable() {
                                          @Override
                                          public void run() {
                                              SetSensorValue();

                                          }
                                      });
                                  }

                              },
//Set how long before to start calling the TimerTask (in milliseconds)
                0,
//Set the amount of time between each execution (in milliseconds)
                1000);
    }
}