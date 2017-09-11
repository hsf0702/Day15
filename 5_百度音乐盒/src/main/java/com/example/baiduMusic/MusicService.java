package com.example.baiduMusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;

    public MusicService() {
    }

    @Override
    public void onCreate() {
        mediaPlayer =  new MediaPlayer();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    public void playMusic(){
        try {
            mediaPlayer.setDataSource("/data/xpg.mp3");
            mediaPlayer.prepare();//适用本地资源，网络资源可以mediaPlayer.prepareAsync();异步，相当于开了子线程，需要设置监听
//            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mediaPlayer) {
//                    mediaPlayer.start();//防止网络不好使，放在这里
//                }
//            });
            mediaPlayer.start();

            updateSeekBar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateSeekBar() {
        final int duration = mediaPlayer.getDuration();
        //每隔一秒获取一次当前进度---计时器timer
        final Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                int currentPosition = mediaPlayer.getCurrentPosition();
                //无法在服务里更新进度条seekbar---用handler
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("duration",duration);
                bundle.putInt("currentPosition",currentPosition);
                msg.setData(bundle);
                MainActivity.handler.sendMessage(msg);
            }
        };
        timer.schedule(task,1000,1000);
        //歌曲播放完成时种植timer
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                System.out.println("播放完成");
                timer.cancel();
            }
        });

    }

    public void pauseMusic(){
        mediaPlayer.pause();
    }

    public void continueMusic(){
        mediaPlayer.start();
    }
    public void playSeekPosition(int position){
        mediaPlayer.seekTo(position);
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

        @Override
        public void callPlaySeekPosition(int position) {
            playSeekPosition(position);
        }
    }
}
