package com.example.javiermoreno.medicamentos.AlarmaPakage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.javiermoreno.medicamentos.MyBroadcastReceiver;
import com.example.javiermoreno.medicamentos.R;

public class AlarmaActivity extends AppCompatActivity {

    Button apagar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);

        apagar = (Button) findViewById(R.id.boton_apagar);
    }

    public void onClickApagarAlarma(View view){
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        intent.putExtra("extra", "no");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

        sendBroadcast(intent);

        finish();
    }
}
