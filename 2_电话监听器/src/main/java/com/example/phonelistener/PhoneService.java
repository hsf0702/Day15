package com.example.phonelistener;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneService extends Service {
    private MediaRecorder recorder;

    public PhoneService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        tm.listen(new MyPhoneStateListener(),PhoneStateListener.LISTEN_CALL_STATE);
        super.onCreate();
    }

    class MyPhoneStateListener extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            //判断电话的状态
            switch (state){
                case TelephonyManager.CALL_STATE_IDLE://空闲
                    if(recorder!=null){
                        recorder.stop();
                        recorder.reset();
                        recorder.release();
                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK://接听
                    recorder.start();
                    break;
                case TelephonyManager.CALL_STATE_RINGING://响铃
                    //音频捕捉
                    recorder =  new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);//格式
                    recorder.setOutputFile(getFilesDir().getPath()+"/"+"record.3gp");//路径
                    try {
                        recorder.prepare();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    }
}
