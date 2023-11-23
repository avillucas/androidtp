package ar.com.avillucas.tp.listar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import ar.com.avillucas.tp.R;
import ar.com.avillucas.tp.entidades.SetDatos;

public class SetDatosListarVista implements RecyclerViewInterface {

    RecyclerView lista;
    Activity actividad;
    SetDatosControlador controlador;
    SetDatosAdapter adaptador;

    public SetDatosListarVista(Activity actividad) {
        this.actividad = actividad;
    }

    public void setControlador(SetDatosControlador controlador) {
        this.controlador = controlador;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void actualizarElementos(List<SetDatos> datos) {
        if(this.adaptador == null){
            this.adaptador = new SetDatosAdapter(datos, this);
            lista = actividad.findViewById(R.id.datosListarReciclerView);
            lista.setAdapter(adaptador);
            LinearLayoutManager manejador = new LinearLayoutManager(this.actividad, LinearLayoutManager.VERTICAL, false);
            lista.setLayoutManager(manejador);
        }else{
            this.adaptador.updateData(datos);
        }
    }


}