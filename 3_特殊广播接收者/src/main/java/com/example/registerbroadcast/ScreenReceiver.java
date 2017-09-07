package com.example.registerbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenReceiver extends BroadcastReceiver {
    public ScreenReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if("android.intent.action.SCREEN_OFF".equals(action)){
            System.out.println("屏幕变暗");
        }else if("android.intent.action.SCREEN_ON".equals(action)){
            System.out.println("屏幕变亮");
        }
    }
}
