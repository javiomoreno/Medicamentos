package com.example.javiermoreno.medicamentos.AlarmaPakage;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.javiermoreno.medicamentos.BaseDeDatos.Alarmas;
import com.example.javiermoreno.medicamentos.BaseDeDatos.DBHandler;
import com.example.javiermoreno.medicamentos.MainActivity;
import com.example.javiermoreno.medicamentos.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmasFragment extends Fragment {

    ListView lista;
    ArrayAdapter adaptador;
    View viewGlobal;

    List LISTA = new ArrayList<ListaAlarmas>();
    DBHandler dataBase;
    List<Alarmas> listaDataBase;

    public AlarmasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewGlobal = inflater.inflate(R.layout.fragment_alarmas, container, false);
        lista = (ListView)viewGlobal.findViewById(R.id.lista);

        dataBase = new DBHandler(viewGlobal.getContext());
        listaDataBase = dataBase.getAllAlarmas();

        for (Alarmas alarma : listaDataBase) {
            LISTA.add(new ListaAlarmas(alarma.getMedicamento(), alarma.getCantidad(), alarma.getHora(), R.mipmap.ic_alarm));
        }

        adaptador = new ListaAlarmasArrayAdapter<ListaAlarmas>(
                viewGlobal.getContext(),
                LISTA);

        lista.setAdapter(adaptador);

        FloatingActionButton fab = (FloatingActionButton) viewGlobal.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewGlobal.getContext(), NuevaAlarma.class);
                startActivity(intent);
            }
        });

        return viewGlobal;

    }

}