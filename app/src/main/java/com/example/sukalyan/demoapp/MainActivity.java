package com.example.sukalyan.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;




public class MainActivity extends AppCompatActivity {
    private ListView noteList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Home");

        OnClickListener listnr=new OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.button1:

                        Intent i= new Intent(MainActivity.this,registration.class);
                        startActivity(i);
                        break;

                    case R.id.button:
                        Intent j= new Intent(MainActivity.this,login.class);
                        startActivity(j);
                        break;



                    default:
                        break;
                }

                }

        };
        Button btn =(Button) findViewById(R.id.button1);
        btn.setOnClickListener(listnr);
        Button btn2 =(Button) findViewById(R.id.button);
        btn2.setOnClickListener(listnr);
    }




}
