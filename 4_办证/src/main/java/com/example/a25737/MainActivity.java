package com.example.a25737;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyConn myConn;
    private Iservice iservice;
//    private bzService.MyBinder mybinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, bzService.class);
        myConn =  new MyConn();
        bindService(intent,myConn,BIND_AUTO_CREATE);
    }

    public void click(View view){
//        mybinder.callBz(1);
//        mybinder.callPlay();//可以调用，但是不想让调--------使用接口方式可以解决---隐藏代码的细节
        iservice.callBz(1);
        iservice.callPlay();
    }

    class MyConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            //IBinder iBinder  获取中间人对象实例 new MyBinder();
//            mybinder =  (bzService.MyBinder) iBinder;
            iservice =  (Iservice) service;//多态

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}
