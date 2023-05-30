package sv.edu.ues.fia.eisi.cafetinesues;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ControladorServicio
{

    public static String obtenerRespuestaPeticion(String url, Context ctx) {
        String respuesta = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setConnectTimeout(13000);
            conn.setReadTimeout(15000);
            conn.setRequestMethod("GET");

            int codigoEstado = conn.getResponseCode();
            String esta = String.valueOf(codigoEstado);
            Toast.makeText(ctx, esta, Toast.LENGTH_LONG).show();

            if (codigoEstado == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String linea;
                while ((linea = reader.readLine()) != null) {
                    stringBuilder.append(linea);
                }
                reader.close();
                respuesta = stringBuilder.toString();
            }
        } catch (IOException e) {
            Toast.makeText(ctx, url, Toast.LENGTH_LONG).show();
            Toast.makeText(ctx, "Error en la conexión", Toast.LENGTH_LONG).show();
            Log.v("Error de Conexión", e.toString());
        }

        return respuesta;
    }


    public static String obtenerRespuestaPost(String url, JSONObject obj, Context ctx) {
        String respuesta = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = obj.toString();

            try (OutputStream outputStream = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                outputStream.write(input, 0, input.length);
            }

            int codigoEstado = conn.getResponseCode();

            if (codigoEstado == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String linea;
                while ((linea = reader.readLine()) != null) {
                    stringBuilder.append(linea);
                }
                reader.close();
                respuesta = stringBuilder.toString();
            } else {
                Log.v("respuesta", Integer.toString(codigoEstado));
            }
        } catch (IOException e) {
            Toast.makeText(ctx, "Error en la conexión", Toast.LENGTH_LONG).show();
            Log.v("Error de Conexión", e.toString());
        }

        return respuesta;
    }


    public static void insertarExterno(String peticion, Context ctx) {

        String json = obtenerRespuestaPeticion(peticion, ctx);
        try {
            JSONObject resultado = new JSONObject(json);

            Toast.makeText(ctx, "Registro ingresado"+ resultado.getJSONArray("resultado"), Toast.LENGTH_LONG).show();
            int respuesta = resultado.getInt("resultado");
            if (respuesta == 1)
                Toast.makeText(ctx, "Registro ingresado", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
  
    public static List<TipoProducto> obtenerTipoProductosExterno(String json, Context ctx) {

        List<TipoProducto> listaTipoProductos = new ArrayList<TipoProducto>();

        try {
            JSONArray tipoProductoJSON = new JSONArray(json);
            for (int i = 0; i < tipoProductoJSON.length(); i++) {
                JSONObject obj = tipoProductoJSON.getJSONObject(i);
                TipoProducto tipoProducto = new TipoProducto();
                tipoProducto.setId_TipoProducto(obj.getInt("ID_TIPOPRODUCTO"));
                tipoProducto.setNombre_TipoProducto(obj.getString("NOMBRE_TIPOPRODUCTO"));
                listaTipoProductos.add(tipoProducto);
            }
            return listaTipoProductos;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseOO de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public static String consultarAparicionesProducto(String json, Context ctx){
        try {
            Log.v("JSON", "averaquiprimeralinea");
            JSONObject objs = new JSONObject(json);
            Log.v("JSON", "aaaaa" + objs);
            if (objs.length() != 0)
                // CANTIDAD APARICIONES
                return objs.getString("cantidadComboProducto");
            else {
                Toast.makeText(ctx, "Error", Toast.LENGTH_LONG)
                        .show();
                return " ";
            }
        } catch (JSONException e) {
            Toast.makeText(ctx, "Error con la respuesta JSON",
                    Toast.LENGTH_LONG).show();
            return " ";
        }
    }
    public static List<Facultad> obtenerFacultadesExterno(String json, Context ctx) {

        List<Facultad> listaFacultad = new ArrayList<Facultad>();

        try {
            JSONArray facultadJSON = new JSONArray(json);
            for (int i = 0; i < facultadJSON.length(); i++) {
                JSONObject obj = facultadJSON.getJSONObject(i);
                Facultad facultad = new Facultad();
                facultad.setId_Faculdad(obj.getInt("ID_FACULTAD"));
                facultad.setNombre_Facultad(obj.getString("NOMBRE_FACULTAD"));
                listaFacultad.add(facultad);
            }
            return listaFacultad;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseOO de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }

}
