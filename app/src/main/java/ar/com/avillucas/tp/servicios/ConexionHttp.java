package ar.com.avillucas.tp.servicios;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import ar.com.avillucas.tp.entidades.SetDatos;
import ar.com.avillucas.tp.errors.ConexionError;

/**
 * https://apis.datos.gob.ar/series/api/series?ids=363.3_PRODUCCIONOIL__20:avg:percent_change&header=titles&collapse=month&collapse_aggregation=avg&representation_mode=change&start_date=2020-01-01&end_date=2022-12-31&limit=50&start=0&sort=asc&format=json
 */
public class ConexionHttp {


    protected HttpURLConnection conn;

    private HttpURLConnection conectar(String enpointUrl) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(enpointUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return urlConnection;
    }

    public boolean isReady() {
        return conn != null;
    }


    protected byte[] readFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        inputStream.close();
        return baos.toByteArray();
    }

    public byte[] getBytesDataByGET(String endpointUrl) throws IOException {
        if (!this.isReady()) {
            conn = this.conectar(endpointUrl);
        }
        conn.setRequestMethod("GET");
        conn.connect();
        int status = conn.getResponseCode();
        if (status == 200) {
            InputStream is = conn.getInputStream();
            return readFully(is);
        } else {
            throw new ConexionError("Server error response status", status);
        }
    }

    public List<SetDatos> getSetDatosJSONByGET(String endpointUrl) {
        List<SetDatos> datos = new ArrayList<SetDatos>();
        try {
            byte[] bytes = new byte[0];
            bytes = getBytesDataByGET(endpointUrl);
            String jsonResponse = new String(bytes, "UTF-8");
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray jsonDatos = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonDatos.length(); i++) {
                JSONArray dato = jsonDatos.getJSONArray(i);
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

                Log.d(dato.getString(0), dato.getString(1));

                Date fecha = formater.parse(dato.getString(0));
                Double valor = Double.parseDouble(dato.getString(1));
                SetDatos set = new SetDatos(fecha, valor);
                datos.add(set);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return datos;
    }


}
