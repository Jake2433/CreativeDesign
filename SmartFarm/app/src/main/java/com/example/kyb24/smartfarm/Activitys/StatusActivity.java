package com.example.kyb24.smartfarm.Activitys;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
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
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class StatusActivity extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener {
    TextView tvCurLight, tvCurTemper, tvCurAirCondi, tvCurHumid;
    Button btnDesirLight, btnDesirTemper, btnDesirAirCondi, btnDesirHumid;
    ImageButton btnFeed, btnWater, btnCCTV, btnGraph;

    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;

    String selectedVal;
    Spinner spinner;

    int btn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        InitVariables();

        //Declare the timer
        Timer t = new Timer();
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

    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnDesirLight:
                btn = 0;
                spinner.performClick();
                break;
            case R.id.btnDesirTemper:
                btn = 1;
                spinner.performClick();
                break;
            case R.id.btnDesirAirCondi:
                btn = 2;
                spinner.performClick();
                break;
            case R.id.btnDesirHumid:
                btn = 3;
                spinner.performClick();
                break;
            case R.id.btnFeed:

                break;
            case R.id.btnWater:

                break;
            case R.id.btnCCTV:

                break;
            case R.id.btnGraph:

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

        btnDesirLight.setOnClickListener(this);
        btnDesirTemper.setOnClickListener(this);
        btnDesirAirCondi.setOnClickListener(this);
        btnDesirHumid.setOnClickListener(this);

        btnFeed.setOnClickListener(this);
        btnWater.setOnClickListener(this);
        btnCCTV.setOnClickListener(this);
        btnGraph.setOnClickListener(this);

        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.desire_values, android.R.layout.simple_spinner_item);
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
                btnDesirLight.setText(parent.getItemAtPosition(pos).toString());
                SetDesirSensorVal("light", parent.getItemAtPosition(pos).toString());
                break;
            case 1:
                btnDesirTemper.setText(parent.getItemAtPosition(pos).toString());
                SetDesirSensorVal("temperature", parent.getItemAtPosition(pos).toString());
                break;
            case 2:
                btnDesirAirCondi.setText(parent.getItemAtPosition(pos).toString());
                SetDesirSensorVal("air", parent.getItemAtPosition(pos).toString());
                break;
            case 3:
                btnDesirHumid.setText(parent.getItemAtPosition(pos).toString());
                SetDesirSensorVal("humid", parent.getItemAtPosition(pos).toString());
                break;
        }
    }
    void SetSensorValue(){
        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost(Util.serverAddress + "/sensor.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            JSONObject json = new JSONObject(response);

            tvCurLight.setText(json.getString("light"));
            tvCurTemper.setText(json.getString("temperature"));
            tvCurAirCondi.setText(json.getString("air"));
            tvCurHumid.setText(json.getString("humid"));
            //Toast.makeText(getApplicationContext(), json.getString("temperature"), Toast.LENGTH_SHORT).show();
        }
        catch(Exception e) {}
    }
}
