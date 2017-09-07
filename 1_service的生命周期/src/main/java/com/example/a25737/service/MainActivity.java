package com.example.a25737.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.a25737.day15.R;

public class MainActivity extends AppCompatActivity {

    private MyConn myConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View view){
        int id = view.getId();
        switch (id){
            case R.id.start:
                Intent intent = new Intent(this,MyService.class);
                startService(intent);
                break;
            case R.id.stop:
                Intent intent1 = new Intent(this, MyService.class);
                stopService(intent1);
                break;
            case R.id.bstart:
                Intent intent2 = new Intent(this,MyService.class);
                myConn =  new MyConn();
                bindService(intent2, myConn,BIND_AUTO_CREATE);
                break;
            case R.id.bstop://手动解绑---也可以点击后退解绑
                unbindService(myConn);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(myConn);
        super.onDestroy();
    }

    //定义一个类监视服务的状态
    class MyConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}
