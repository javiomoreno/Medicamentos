package com.example.javiermoreno.medicamentos;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.javiermoreno.medicamentos.AlarmaPakage.AlarmaActivity;

import java.security.Provider;

/**
 * Created by Javier Moreno on 16 jul 2016.
 */
public class RingtonePlayingService extends Service {

    MediaPlayer mediaPlayer;
    boolean isRunning;
    int startId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String state = intent.getExtras().getString("extra");

        assert state != null;
        switch (state){
            case "yes":
                startId = 1;
                break;
            case "no":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }


        if (!this.isRunning && startId == 1){
            Log.i("LocalService", "Received start id " + startId + ": " + intent);
            mediaPlayer = MediaPlayer.create(this, R.raw.melendi_la_promesa);
            mediaPlayer.start();

            this.isRunning = true;
            this.startId = 0;

            NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            Intent intent_main_activity = new Intent(this.getApplicationContext(), AlarmaActivity.class);
            PendingIntent pendingIntent_main_activity = PendingIntent.getActivity(this, 0, intent_main_activity, 0);


            Notification notification_popup = new Notification.Builder(this)
                    .setContentTitle("Apagar Alarma")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText("Presione Aqui")
                    .setContentIntent(pendingIntent_main_activity)
                    .setAutoCancel(true)
                    .build();

            notificationManager.notify(0, notification_popup);
        }

        else if (this.isRunning && startId == 0){

            mediaPlayer.stop();
            mediaPlayer.reset();

            this.isRunning = false;
            this.startId = 0;
        }

        else if (!this.isRunning && startId == 0){
            this.isRunning = false;
            this.startId = 0;
        }

        else if (this.isRunning && startId == 1){
            this.isRunning = true;
            this.startId = 1;
        }

        else {

        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        this.isRunning = false;

    }
}
