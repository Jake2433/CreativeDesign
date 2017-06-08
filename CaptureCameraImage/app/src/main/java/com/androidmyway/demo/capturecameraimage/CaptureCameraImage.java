package com.androidmyway.demo.capturecameraimage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.Timer;
import java.util.TimerTask;

public class CaptureCameraImage extends Activity {

	public static int cameraID = 0;
	public static boolean isBlack = true;
	public static ImageView image;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitycapturecameraimage);
        image = (ImageView) findViewById(R.id.imgView);

/*
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
											  cameraID = 0;
											  Intent i = new Intent(CaptureCameraImage.this,CameraView.class);
											  startActivityForResult(i, 999);
										  }
									  });
								  }

							  },
//Set how long before to start calling the TimerTask (in milliseconds)
				0,
//Set the amount of time between each execution (in milliseconds)
				5000);
				*/
		cameraID = 0;
		Intent i = new Intent(CaptureCameraImage.this,CameraView.class);
		startActivityForResult(i, 999);
    }


/*
    public void onFrontClick(View v){
    	RadioButton rdbBlack = (RadioButton) findViewById(R.id.rdb_black);
    	if(rdbBlack.isChecked()){
    		isBlack = true;
    	}else{
    		isBlack = false;
    	}
		cameraID = 1;
		Intent i = new Intent(CaptureCameraImage.this,CameraView.class);
        startActivityForResult(i, 999);
	}
    
	public void onBackClick(View v){
		RadioButton rdbBlack = (RadioButton) findViewById(R.id.rdb_black);
    	if(rdbBlack.isChecked()){
    		isBlack = true;
    	}else{
    		isBlack = false;
    	}
    	cameraID = 0;
		Intent i = new Intent(CaptureCameraImage.this,CameraView.class);
        startActivityForResult(i, 999);
	}
    */
}
