package com.androidmyway.demo.capturecameraimage;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CaptureCameraImage extends Activity {

    public static int cameraID = 0;
    public static boolean isBlack = true;
    public static ImageView image;

    Button btnTest;

    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitycapturecameraimage);
        image = (ImageView) findViewById(R.id.imgView);
        cameraID = 0;
/*
        btnTest = (Button)findViewById(R.id.button);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunCapture();
            }
        });
*/


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
											  //Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                                              RunCapture();
										  }
									  });
								  }

							  },
//Set how long before to start calling the TimerTask (in milliseconds)
				0,
//Set the amount of time between each execution (in milliseconds)
				2000);
    }

    private void RunCapture() {
        new CheckValidationAndSend().execute();
    }

    void Capture(){
        Intent i = new Intent(CaptureCameraImage.this,CameraView.class);
        startActivityForResult(i, 999);
    }

    private class CheckValidationAndSend extends AsyncTask<Void ,Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                httpclient = new DefaultHttpClient();
                httppost = new HttpPost(Util.serverAddress + "/getCaptureGrant.php");
                //nameValuePairs = new ArrayList<NameValuePair>(2);
                //httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //response = httpclient.execute(httppost);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String permission = httpclient.execute(httppost, responseHandler);
                //JSONObject json = new JSONObject(response);
                if(permission.equals("OK"))
                    Capture();
                //Toast.makeText(getApplicationContext(), test, Toast.LENGTH_SHORT).show();
            }
            catch(Exception e) {
                //Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }

            return null;
        }

    }
}
