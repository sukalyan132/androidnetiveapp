package com.example.sukalyan.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.example.sukalyan.demoapp.NetworkHelper;
import com.squareup.okhttp.Callback;

public class login extends AppCompatActivity {
    private static EditText userName;
    private static EditText password;
    private static Button login_button;
    String URL = "http://192.168.1.5:3000/login";
    NetworkHelper networkHelper = new NetworkHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton();

       // userName        =(EditText)findViewById(R.id.editText);
        //password        =(EditText)findViewById(R.id.editText2);
        //login_button    =(Button)findViewById(R.id.login_button);

    }

    public void LoginButton(){
        userName                =(EditText)findViewById(R.id.editText);
        password                =(EditText)findViewById(R.id.editText2);
        login_button            =(Button)findViewById(R.id.login_button);
        JSONObject jsonResult   = new JSONObject();

        // login button click function

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CHECK VALIDATION//
                final String username = userName.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    userName.setError("Please enter username");
                    userName.requestFocus();
                    return;
                }
                ////////////////////////////////////
                final String Password = password.getText().toString().trim();
                if (TextUtils.isEmpty(Password)) {
                    password.setError("Enter a password");
                    password.requestFocus();
                    return;
                }
                /////////////////////////////////////////////////////////////
                //String json = "{"name": "" + nameInput.getText() + "", "password":"" + passwordInput.getText() + ""}";
                try {
                    jsonResult.put("name", userName.getText() );

                    jsonResult.put("password", password.getText());

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Log.d("DEV","jsonResult->"+jsonResult);

                //String json = "{"+"name"+":"+ userName.getText() +","+"password"+":"+password.getText()+"}";
                networkHelper.post(URL, jsonResult, new Callback() {


                    @Override
                    public void onFailure(com.squareup.okhttp.Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                        String responseStr = response.body().string();
                        try {
                            JSONObject jObject = new JSONObject(responseStr);
                            String aJsonString = jObject.getString("name");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), aJsonString, Toast.LENGTH_LONG).show();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        final String messageText = "Status code : " + response.code() +
                                "n" +
                                "Response body : " + responseStr;
                        /*runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_LONG).show();
                            }
                        });*/
                    }
                });

            }
        });


    }
}
