package com.example.a25737.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
   /*
   startService:onCreate----onStartCommand...后退，在后台有显示----onDestroy才销毁------
   bindService:onCreate----onBind(多次点击绑定无反应，只绑定一次，解绑后才能再次绑定)----可手动解绑你也可以退出解绑，只能选其一，多次点击解绑会报错
               后台查不到进程，activity销毁，service就销毁

    为什么要有bindService
    见 4_办证
    */

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind");
        return null;
    }

    @Override
    public void onCreate() {
        System.out.println("服务创建---onCreate");
        super.onCreate();
    }

    @Override//onStart过时了
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("服务打开---onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("服务销毁---onDestroy");
        super.onDestroy();
    }
}
