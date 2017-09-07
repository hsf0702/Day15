package com.example.a25737;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class bzService extends Service {
    public bzService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void bz(int money){
        if(money>500){
            Toast.makeText(this, "办证成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "办证失败", Toast.LENGTH_SHORT).show();
        }
    }
    public void play(){
        System.out.println("play");
    }

    //定义中间人对象，ibinder类的实现类
    class MyBinder extends Binder implements  Iservice{
        public void callBz(int money){
            bz(money);
        }

        public void callPlay(){
            play();
        }

    }
}
