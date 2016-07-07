package com.example.javiermoreno.medicamentos.BaseDeDatos;

/**
 * Created by Javier Moreno on 6 jul 2016.
 */
public class Alarmas {

    private int id;
    private String medicamento;
    private String cantidad;
    private String hora;

    public Alarmas(){}

    public Alarmas(int id, String medicamento, String cantidad, String hora){
        this.id = id;
        this.medicamento = medicamento;
        this.cantidad = cantidad;
        this.hora = hora;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setMedicamento(String medicamento){
        this.medicamento = medicamento;
    }
    public void setCantidad(String cantidad){
        this.cantidad = cantidad;
    }
    public void setHora(String hora){
        this.hora = hora;
    }

    public int getId(){return id;}
    public String getMedicamento(){return medicamento;}
    public String getCantidad(){return cantidad;}
    public String getHora(){return hora;}

}