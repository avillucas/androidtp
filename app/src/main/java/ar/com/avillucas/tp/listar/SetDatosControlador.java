package ar.com.avillucas.tp.listar;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


import ar.com.avillucas.tp.entidades.SetDatos;
import ar.com.avillucas.tp.servicios.HiloConsulta;

public class SetDatosControlador implements Handler.Callback {
    private SetDatosListarVista vista;
    private Activity actividad;
    private List<SetDatos> datos;

    public SetDatosControlador(SetDatosListarVista vista, Activity actividad) {
        this.vista = vista; 
        this.actividad = actividad;
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        List<SetDatos> datos;
        if (msg.obj == null) {
            throw new NullPointerException();
        }
        if (!(msg.obj instanceof ArrayList)) {
            throw new RuntimeException("El objeto enviado no es un listado de datos ");
        }
        datos = (List<SetDatos>) msg.obj;
        this.vista.actualizarElementos(datos);
        return false;
    }
}
