package com.example.javiermoreno.medicamentos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * Created by Javier Moreno on 16 jul 2016.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent){

        String get_your_string  = intent.getExtras().getString("extra");

        Intent intent_ringtone = new Intent(context, RingtonePlayingService.class);

        intent_ringtone.putExtra("extra", get_your_string);

        context.startService(intent_ringtone);

    }
}