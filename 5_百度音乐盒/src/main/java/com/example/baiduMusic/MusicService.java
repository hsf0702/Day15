package com.example.baiduMusic;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    public void playMusic(){
        System.out.println("start");
    }

    public void pauseMusic(){
        System.out.println("pause");
    }

    public void continueMusic(){
        System.out.println("continue");
    }

    class MyBinder extends Binder implements Iservice{

        @Override
        public void callPlayMusic() {
            playMusic();
        }

        @Override
        public void callPauseMusic() {
            pauseMusic();
        }

        @Override
        public void callContinueMusic() {
            continueMusic();
        }
    }
}
