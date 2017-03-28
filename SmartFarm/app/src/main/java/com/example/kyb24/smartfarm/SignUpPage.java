package com.example.kyb24.smartfarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyb24 on 2017-03-27.
 */

public class SignUpPage extends Activity {
    private boolean IsDuplication, IsCheckedBtnDuplication = false;

    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;

    private EditText editTextId;
    private EditText editTextPw;
    private EditText editTextPw2;
    private EditText editTextName;
    private EditText editTextBirth;

    private RadioButton RaBtnMale;
    private RadioButton RaBtnFemale;

    private EditText editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextId = (EditText)findViewById(R.id.new_id);
        editTextPw = (EditText)findViewById(R.id.new_pw);
        editTextPw2 = (EditText)findViewById(R.id.new_pwagain);

        editTextName = (EditText)findViewById(R.id.new_name);
        editTextBirth = (EditText)findViewById(R.id.new_birthday);

        RaBtnMale = (RadioButton)findViewById(R.id.new_male);
        RaBtnFemale = (RadioButton)findViewById(R.id.new_female);

        editTextPhone = (EditText)findViewById(R.id.new_phone);
    }

    public void insert(View view) {
        String Id = editTextId.getText().toString();
        String Pw = editTextPw.getText().toString();
        String Pw2 = editTextPw2.getText().toString();

        String Name = editTextName.getText().toString();
        String Birth = editTextBirth.getText().toString();

        String Sex = "Not yet";

        if(RaBtnMale.isChecked())
        {
            Sex = "Male";
        }
        else if(RaBtnFemale.isChecked())
        {
            Sex = "Female";
        }

        String Phone = editTextPhone.getText().toString();

        if(Id.getBytes().length <= 0 ||
                Pw.getBytes().length <= 0 ||
                Pw2.getBytes().length <= 0 ||
                Name.getBytes().length <= 0 ||
                Birth.getBytes().length <= 0 ||
                Sex.getBytes().length <= 0 ||
                Phone.getBytes().length <= 0)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(SignUpPage.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();     //닫기
                }
            });

            alert.setTitle("잠깐!");
            alert.setMessage("빠짐없이 모두 작성해주세요");
            alert.show();
        }

        else if(!Pw.equals(Pw2))
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(SignUpPage.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();     //닫기
                }
            });
            alert.setTitle("다시 한번 확인!");
            alert.setMessage("비밀번호 불일치");
            alert.show();
        }

        else if(!IsCheckedBtnDuplication)   //중복확인 버튼 안눌렀을 경우
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(SignUpPage.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();     //닫기
                }
            });
            alert.setTitle("아이디 체크!");
            alert.setMessage("아이디 중복확인 버튼을\n눌러 확인해주세요");
            alert.show();
        }

        else if(IsDuplication)  //중복될 경우
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(SignUpPage.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();     //닫기
                }
            });
            IsCheckedBtnDuplication = false;

            alert.setTitle("아이디 중복!");
            alert.setMessage("사용할 수 없는 ID입니다.\n중복확인 부탁드립니다.");
            alert.show();
        }

        else    //모든 조건에 만족할 경우 DB로 전송
        {
            insertoToDatabase(Id, Pw, Name, Birth, Sex, Phone);
        }
    }

    public void DuplicationCheck(View view) //중복확인 버튼 클릭했을 때
    {
        IsCheckedBtnDuplication = true; //중복확인 버튼 눌렀으므로 true
        LookingForDuplication();    //DB가서 중복검사하는 함수
    }

    private void LookingForDuplication()    //DB로가서 검색
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(SignUpPage.this);
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     //닫기
            }
        });

        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://169.254.80.80/androidtest/duplication.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("Id", editTextId.getText().toString()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);

            if (response.equalsIgnoreCase("User Found"))
            {
                IsDuplication = true;   //중복되므로 true

                alert.setTitle("아이디 체크!");
                alert.setMessage("이미 존재하는 ID입니다");
                alert.show();
            }
            else
            {
                IsDuplication = false;

                alert.setTitle("LUCKY!");
                alert.setMessage("사용 가능한 ID입니다");
                alert.show();
            }
        }
        catch(Exception e) {}
    }

    private void insertoToDatabase(String Id, String Pw, String Name,
                                   String Birth, String Sex, String Phone)
    {
        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SignUpPage.this, "잠시만 기다려 주세요.", null, true, true);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equals("success")) //db로 잘 전송 되었을 경우
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(SignUpPage.this);
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();     //닫기
                            startActivity((new Intent(SignUpPage.this, MainActivity.class)));
                        }
                    });

                    alert.setTitle("축하합니다");
                    alert.setMessage("가입하신 정보로\n로그인 부탁합니다.");
                    alert.show();
                }
                else    //db로 전송 실패할 경우 초기화
                {
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    editTextId.setText(null);
                    editTextPw.setText(null);
                    editTextPw2.setText(null);
                    editTextName.setText(null);
                    editTextBirth.setText(null);
                    editTextPhone.setText(null);
                }
            }
            @Override
            protected String doInBackground(String... params) {
                try {
                    String Id = (String) params[0];
                    String Pw = (String) params[1];
                    String Name = (String) params[2];
                    String Birth = (String) params[3];
                    String Sex = (String) params[4];
                    String Phone = (String) params[5];

                    String link = "http://169.254.80.80/androidtest/post.php";
                    String data = URLEncoder.encode("Id", "UTF-8") + "=" + URLEncoder.encode(Id, "UTF-8");
                    data += "&" + URLEncoder.encode("Pw", "UTF-8") + "=" + URLEncoder.encode(Pw, "UTF-8");
                    data += "&" + URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(Name, "UTF-8");
                    data += "&" + URLEncoder.encode("Birth", "UTF-8") + "=" + URLEncoder.encode(Birth, "UTF-8");
                    data += "&" + URLEncoder.encode("Sex", "UTF-8") + "=" + URLEncoder.encode(Sex, "UTF-8");
                    data += "&" + URLEncoder.encode("Phone", "UTF-8") + "=" + URLEncoder.encode(Phone, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch (Exception e)
                {
                    return new String("Exception: " + e.getMessage());
                }
            }
        }
        InsertData task = new InsertData();
        task.execute(Id, Pw, Name, Birth, Sex, Phone);
    }
}
