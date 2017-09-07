package com.example.registerbroadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //开启服务
        Intent intent = new Intent(this, ScreenService.class);
        startService(intent);//只要服务不死，广播接收者就不死
    }
}
