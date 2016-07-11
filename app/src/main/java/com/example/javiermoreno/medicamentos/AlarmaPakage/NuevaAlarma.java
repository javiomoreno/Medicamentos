package com.example.javiermoreno.medicamentos.AlarmaPakage;

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

import com.example.javiermoreno.medicamentos.BaseDeDatos.Alarmas;
import com.example.javiermoreno.medicamentos.BaseDeDatos.DBHandler;
import com.example.javiermoreno.medicamentos.MainActivity;
import com.example.javiermoreno.medicamentos.R;

public class NuevaAlarma extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText medicamento, cantidad, hora;
    Alarmas alarma;
    DBHandler dataBase;
    String opcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_alarma);

        Toolbar toolbarNA = (Toolbar) findViewById(R.id.toolbarNA);
        setSupportActionBar(toolbarNA);
        getSupportActionBar().setTitle(R.string.tituloNuevaAlarma);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        medicamento = (AutoCompleteTextView) findViewById(R.id.editTextMedicamento);
        cantidad = (AutoCompleteTextView) findViewById(R.id.editTextCantidad);
        hora = (AutoCompleteTextView) findViewById(R.id.editTextHora);

        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lista_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

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
            dataBase.addAlarma(new Alarmas(1, medicamento.getText().toString(), cantidad.getText().toString(), hora.getText().toString()+" "+opcion));
            Intent intent_homeIntens = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent_homeIntens);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        opcion = parent.getItemAtPosition(pos).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
