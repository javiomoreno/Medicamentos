package com.example.javiermoreno.medicamentos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
/**
 * Created by Javier Moreno on 6 jul 2016.
 */
public class ListaAlarmasArrayAdapter<L> extends ArrayAdapter<ListaAlarmas> {

    public ListaAlarmasArrayAdapter(Context context, List<ListaAlarmas> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Salvando la referencia del View de la fila
        View listItemView = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con image_list_view.xml
            listItemView = inflater.inflate(
                    R.layout.custom_alarmas,
                    parent,
                    false);
        }

        //Obteniendo instancias de los elementos
        TextView medicamento = (TextView)listItemView.findViewById(R.id.text1);
        TextView cantidad = (TextView)listItemView.findViewById(R.id.text2);
        TextView hora = (TextView)listItemView.findViewById(R.id.text3);
        ImageView imagen = (ImageView)listItemView.findViewById(R.id.category);


        //Obteniendo instancia de la Tarea en la posición actual
        ListaAlarmas item = getItem(position);

        medicamento.setText(item.getMedicamento());
        cantidad.setText(item.getCantidad());
        hora.setText(item.getHora());
        imagen.setImageResource(item.getImagen());

        //Devolver al ListView la fila creada
        return listItemView;

    }
}