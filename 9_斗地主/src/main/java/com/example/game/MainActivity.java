package com.example.game;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alipay.Iservice;

public class MainActivity extends AppCompatActivity {

    private Iservice iservice;
    private EditText et_name;
    private EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name =  (EditText) findViewById(R.id.et_name);
        et_pwd =  (EditText) findViewById(R.id.et_pwd);

        Intent intent = new Intent();
        intent.setAction("com.itheima.pay");
        bindService(intent,new MyConn(),BIND_AUTO_CREATE);
    }

    class MyConn implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            iservice =  Iservice.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }

    public void click(View view){
        try {
            String name = et_name.getText().toString().trim();
            String pwd = et_pwd.getText().toString().trim();
            boolean b = iservice.callPay(name, pwd, 888);
            if(b){
                Toast.makeText(this, "购买成功", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "购买失败", Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
