package ar.com.avillucas.tp;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import ar.com.avillucas.tp.databinding.ActivityMainBinding;
import ar.com.avillucas.tp.listar.SetDatosControlador;
import ar.com.avillucas.tp.listar.SetDatosListarVista;
import ar.com.avillucas.tp.servicios.HiloConsulta;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ar.com.avillucas.tp.databinding.ActivityMainBinding actividadSetDatosListar = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(actividadSetDatosListar.getRoot());
        SetDatosListarVista vista = new SetDatosListarVista(this);
        SetDatosControlador controlador = new SetDatosControlador( vista, this);
        vista.setControlador(controlador);
        Handler manejador = new Handler(controlador);
        HiloConsulta hilo = new HiloConsulta(manejador);
        hilo.start();
    }

}