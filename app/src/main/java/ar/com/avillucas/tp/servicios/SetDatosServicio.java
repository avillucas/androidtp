package ar.com.avillucas.tp.servicios;

import android.os.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ar.com.avillucas.tp.entidades.SetDatos;

public class SetDatosServicio extends ConexionHttp {

    protected String endpoint;

    //TODO tomar de una property
    public SetDatosServicio() {
        endpoint = "https://apis.datos.gob.ar/series/api/series?ids=363.3_PRODUCCIONOIL__20:avg:percent_change&header=titles&collapse=month&collapse_aggregation=avg&representation_mode=change&start_date=2020-01-01&end_date=2022-12-31&limit=50&start=0&sort=asc&format=json";
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public List<SetDatos> traerLista()  {
        return this.getSetDatosJSONByGET(this.endpoint);
    }

}
