package com.example.local;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.remote.Iservice;

public class MainActivity extends AppCompatActivity {

    private Iservice iservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //隐式意图开启远程服务
        Intent intent = new Intent();
        intent.setAction("com.itheima.remote");
        bindService(intent,new MyConn(),BIND_AUTO_CREATE);//用于接收中间人对象
    }
    public void click(View view){
        try {
            iservice.callService();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    //见识服务的状态
    class MyConn implements ServiceConnection{

        @Override//获取中间人对象
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            //aidl :android interface defation lannguage-----------ipc 进程间通信
            iservice =  Iservice.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}
