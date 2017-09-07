package com.example.baiduMusic;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Iservice iservie;//代表中间人对象

    /*
        综合方式开启服务流程

         */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this,MusicService.class);
        startService(intent);
        //调用binderservice获取定义的中间人对象
        bindService(intent,new Myconn(),BIND_AUTO_CREATE);
    }
    public void click(View view){
        int id = view.getId();
        switch (id){
            case R.id.bt_start:
                iservie.callPlayMusic();
                break;
            case R.id.bt_pause:
                iservie.callPauseMusic();
                break;
            case R.id.bt_continue:
                iservie.callContinueMusic();
                break;
        }
    }

    class Myconn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            iservie =  (Iservice) service;


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}
