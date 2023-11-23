package ar.com.avillucas.tp.servicios;


import android.os.Handler;
import android.os.Message;

import java.util.List;

import ar.com.avillucas.tp.entidades.SetDatos;

public class HiloConsulta extends Thread {

    Handler manejandor;

    public HiloConsulta(Handler manejandor) {
        this.manejandor = manejandor;
    }

    @Override
    public void run() {
        SetDatosServicio servicio = new SetDatosServicio();
        List<SetDatos> datos = servicio.traerLista();
        Message message = new Message();
        message.obj = datos;
        this.manejandor.sendMessage(message);
    }
}
