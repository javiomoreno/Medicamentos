package com.example.javiermoreno.medicamentos.AlarmaPakage;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.javiermoreno.medicamentos.BaseDeDatos.Alarmas;
import com.example.javiermoreno.medicamentos.BaseDeDatos.DBHandler;
import com.example.javiermoreno.medicamentos.MainActivity;
import com.example.javiermoreno.medicamentos.R;

public class NuevaAlarma extends AppCompatActivity {

    private Toolbar toolbar;
    EditText medicamento, cantidad, hora;

    Alarmas alarma;
    DBHandler dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_alarma);

        toolbar = (Toolbar) findViewById(R.id.toolbarNA);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nueva Alarma");

        medicamento = (EditText) findViewById(R.id.editTextMedicamento);
        cantidad = (EditText) findViewById(R.id.editTextCantidad);
        hora = (EditText) findViewById(R.id.editTextHora);

        dataBase = new DBHandler(this);

    }

    public void onClickGuardarAlarma(View v){
        int cantId = 2;
        dataBase.addAlarma(new Alarmas(cantId + 1, medicamento.getText().toString(), cantidad.getText().toString(), hora.getText().toString()));
        Intent intent_homeIntens = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent_homeIntens);
    }
}

