package com.example.kyb24.smartfarm.Activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

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

import java.util.ArrayList;
import java.util.List;

import static com.example.kyb24.smartfarm.R.id.user_id;
import static com.example.kyb24.smartfarm.R.id.user_pw;

public class MainActivity extends AppCompatActivity {

    ViewFlipper Vf;
    ImageView BtnSignIn, BtnSignUp;
    EditText inputID, inputPW;
    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
    TextView tv;

    String loginId, loginPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        BtnSignUp = (ImageView)findViewById(R.id.btn_signup);
        BtnSignIn = (ImageView) findViewById(R.id.btn_signin);
        inputID = (EditText)findViewById(R.id.user_id);
        inputPW = (EditText)findViewById(R.id.user_pw);
        tv = (TextView)findViewById(R.id.result_test);
        inputID = (EditText)findViewById(user_id);
        inputPW = (EditText)findViewById(user_pw);
        //tv = (TextView)findViewById(R.id.textView2);
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        loginId = auto.getString("user_id",null);
        loginPwd = auto.getString("user_pw",null);

       if(loginId == null && loginPwd == null){
            BtnSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = ProgressDialog.show(MainActivity.this, "",
                            "Validating user...", true);

                    new Thread(new Runnable() {

                        public void run() {
                            login();
                        }
                    }).start();
                }
            });
       }
       else if(loginId !=null && loginPwd != null) {
           startActivity((new Intent(MainActivity.this, FarmsTabActivity.class)));
           finish();
        }
    }
    void login() {
        try {

            httpclient = new DefaultHttpClient();
            httppost = new HttpPost(Util.serverAddress + "/login.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("username", inputID.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("password", inputPW.getText().toString()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            System.out.println("Response : " + response);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv.setText("Response from PHP : " + response);
                    dialog.dismiss();
                }
            });

                if (response.equalsIgnoreCase("User Found")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                            //auto의 loginId와 loginPwd에 값을 저장해 줍니다.
                            SharedPreferences.Editor autoLogin = auto.edit();
                            autoLogin.putString("user_id", inputID.getText().toString());
                            autoLogin.putString("user_pw", inputPW.getText().toString());
                            //꼭 commit()을 해줘야 값이 저장
                            autoLogin.commit();

                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        }
                    });

                startActivity((new Intent(MainActivity.this, FarmsTabActivity.class)));
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(MainActivity.this, FarmsTabActivity.class);
            startActivity(intent);
            finish();
        }
        catch(Exception e)
        {
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public void CliSignUp(View view)
    {
        Intent intent = new Intent(this, SignUpPage.class);
        startActivity(intent);
    }
}
