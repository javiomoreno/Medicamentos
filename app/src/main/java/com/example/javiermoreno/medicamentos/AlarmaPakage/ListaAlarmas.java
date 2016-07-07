package com.example.javiermoreno.medicamentos.AlarmaPakage;

/**
 * Created by Javier Moreno on 6 jul 2016.
 */
public class ListaAlarmas {

    private String medicamento;
    private String cantidad;
    private String hora;
    private int imagen;

    public ListaAlarmas(String medicamento, String cantidad, String hora, int imagen){
        this.medicamento = medicamento;
        this.cantidad = cantidad;
        this.hora = hora;
        this.imagen = imagen;
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
    public void setCategoria(int imagen){
        this.imagen=imagen;
    }

    public String getMedicamento(){return medicamento;}
    public String getCantidad(){return cantidad;}
    public String getHora(){return hora;}
    public int getImagen(){return imagen;}

}