package com.example.alipay;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AliPayService extends Service {
    public AliPayService() {
    }
    //
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    public boolean pay(String name,String pwd,int money){
        if("hsf".equals(name)&&"123".equals(pwd)&&money>500){
            return true;
        }
        return false;
    }
    class MyBinder extends Iservice.Stub{
        public boolean callPay(String name,String pwd,int money){
            return pay(name,pwd,money);
        }
    }
}
