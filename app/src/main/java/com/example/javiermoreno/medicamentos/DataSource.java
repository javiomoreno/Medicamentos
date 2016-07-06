package com.example.javiermoreno.medicamentos;

/**
 * Created by Javier Moreno on 6 jul 2016.
 */
import java.util.ArrayList;
import java.util.List;


public class DataSource {

    static List LISTA = new ArrayList<ListaAlarmas>();

    static{

        LISTA.add(new ListaAlarmas("Acetaminofen", "1 Pastilla", "08:00 AM",R.drawable.ic_alarm_black));
        LISTA.add(new ListaAlarmas("Aspirina", "1 Pastilla", "10:00 AM",R.drawable.ic_alarm_black));
        LISTA.add(new ListaAlarmas("Atamel", "1 Pastilla", "10:30 PM",R.drawable.ic_alarm_black));
        LISTA.add(new ListaAlarmas("Paracetamol", "1 Pastilla", "3:45 PM",R.drawable.ic_alarm_black));
        LISTA.add(new ListaAlarmas("Guampole", "1 Pastilla", "6:00 PM",R.drawable.ic_alarm_black));
        LISTA.add(new ListaAlarmas("Bral", "1 Pastilla", "6:00 PM",R.drawable.ic_alarm_black));
        LISTA.add(new ListaAlarmas("Viagra", "1 Pastilla", "6:00 PM",R.drawable.ic_alarm_black));
        LISTA.add(new ListaAlarmas("Ampicilina", "1 Pastilla", "6:00 PM",R.drawable.ic_alarm_black));
        LISTA.add(new ListaAlarmas("Acitromicina", "1 Pastilla", "6:00 PM",R.drawable.ic_alarm_black));

    }

}