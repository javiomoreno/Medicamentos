package com.example.javiermoreno.medicamentos.AlarmaPakage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.javiermoreno.medicamentos.BaseDeDatos.Alarmas;
import com.example.javiermoreno.medicamentos.BaseDeDatos.DBHandler;
import com.example.javiermoreno.medicamentos.MainActivity;
import com.example.javiermoreno.medicamentos.MyBroadcastReceiver;
import com.example.javiermoreno.medicamentos.R;

import java.util.Date;

public class NuevaAlarma extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText medicamento, cantidad, hora;
    Alarmas alarma;
    DBHandler dataBase;
    String opcion, opcion2, opcion3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_alarma);

        Toolbar toolbarNA = (Toolbar) findViewById(R.id.toolbarNA);
        setSupportActionBar(toolbarNA);
        getSupportActionBar().setTitle(R.string.tituloNuevaAlarma);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        medicamento = (AutoCompleteTextView) findViewById(R.id.editTextMedicamento);
        cantidad = (EditText) findViewById(R.id.editTextCantidad);
        hora = (EditText) findViewById(R.id.editTextHora);

        Date horaActual = new Date();

        String hora = (horaActual.getYear()+1900)+" "+(horaActual.getMonth()+1)+" "+horaActual.getDate()+" "+horaActual.getHours()+" "+horaActual.getMinutes()+" "+horaActual.getSeconds();
        Log.e("hora", String.valueOf(horaActual.getTime()));
        Log.e("hora2", String.valueOf(System.currentTimeMillis()));

        Spinner spinner_horas = (Spinner) findViewById(R.id.horas_spinner);
        ArrayAdapter<CharSequence> adapter_horas = ArrayAdapter.createFromResource(this,
                R.array.horas_array, android.R.layout.simple_spinner_item);

        Spinner spinner_cantidad = (Spinner) findViewById(R.id.cantidades_spinner);
        ArrayAdapter<CharSequence> adapter_cantidad = ArrayAdapter.createFromResource(this,
                R.array.cantidades_array, android.R.layout.simple_spinner_item);

        Spinner spinner_tiempos = (Spinner) findViewById(R.id.tiempo_spinner);
        ArrayAdapter<CharSequence> adapter_tiempos = ArrayAdapter.createFromResource(this,
                R.array.tiempos_array, android.R.layout.simple_spinner_item);

        adapter_horas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_horas.setAdapter(adapter_horas);
        spinner_horas.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        adapter_cantidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cantidad.setAdapter(adapter_cantidad);
        spinner_cantidad.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        adapter_tiempos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tiempos.setAdapter(adapter_tiempos);
        spinner_tiempos.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        dataBase = new DBHandler(this);
    }


    public void onClickGuardarAlarma(View v){

        String medica = medicamento.getText().toString();
        String cant = cantidad.getText().toString();
        String hor = hora.getText().toString();
        boolean cancel = false;
        medicamento.setError(null);
        cantidad.setError(null);
        hora.setError(null);
        View focusView = null;

        if (TextUtils.isEmpty(medica)) {
            medicamento.setError(getString(R.string.error_field_required));
            focusView = medicamento;
            cancel = true;
        }
        else if (TextUtils.isEmpty(cant)){
            cantidad.setError(getString(R.string.error_field_required));
            focusView = cantidad;
        }
        else if(TextUtils.isEmpty(hor)){
            hora.setError(getString(R.string.error_field_required));
            focusView = hora;
        }
        else {
            int milisegundos = Integer.parseInt(hora.getText().toString())*60;
            for (int i = 0; i < 2; i ++) {
                startAlert(milisegundos);
                dataBase.addAlarma(new Alarmas(1, medicamento.getText().toString(), cantidad.getText().toString() + " " + opcion2, hora.getText().toString() + " " + opcion));
                Intent intent_homeIntens = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent_homeIntens);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.horas_spinner)
        {
            opcion = parent.getItemAtPosition(pos).toString();
        }
        else if(spinner.getId() == R.id.cantidades_spinner)
        {
            opcion2 = parent.getItemAtPosition(pos).toString();
        }
        else if(spinner.getId() == R.id.tiempo_spinner)
        {
            opcion3 = parent.getItemAtPosition(pos).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void startAlert(int milisegundos){
        int i = milisegundos;
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        intent.putExtra("extra", "yes");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(i*1000), pendingIntent);
        Toast.makeText(this,"Alarma puesta en "+i+" segundos", Toast.LENGTH_LONG).show();
    }

}
