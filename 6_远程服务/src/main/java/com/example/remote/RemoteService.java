package com.example.remote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class RemoteService extends Service {
    public RemoteService() {
    }

    //返回中间人对象
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    public void service(){
        System.out.println("我是远程服务");
    }
    class MyBinder extends Iservice.Stub{
        public void callService(){
            service();
        }
    }
}
