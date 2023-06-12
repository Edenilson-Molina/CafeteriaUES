package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class TipoUbicacionInsertarActivity extends Activity {

    ControlDB helper;
    EditText codigo_TipoUbicacion;
    EditText nombre_TipoUbicacion;
    private final String urlLocal = "http://192.168.1.45/apps/ws_tipoubicacion_insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_ubicacion_insertar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlDB(this);
        codigo_TipoUbicacion=(EditText) findViewById(R.id.id_TipoUbicacion);
        nombre_TipoUbicacion=(EditText) findViewById(R.id.nombre_TipoUbicacion);

    }

    public void insertarTipoUbicacion(View v){

        try{
            String id_TipoUbicacion = codigo_TipoUbicacion.getText().toString();
            String nom_TipoUbicacion=nombre_TipoUbicacion.getText().toString();
            TipoUbicacion tipoUbicacion = new TipoUbicacion();

            tipoUbicacion.setId_TipoUbicacion(Integer.parseInt(id_TipoUbicacion));
            tipoUbicacion.setNombre_TipoUbicacion(nom_TipoUbicacion);
            String regInsertados;

            helper.abrir();
            regInsertados=helper.insertar(tipoUbicacion);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();
        }

    }

    public void limpiarTexto(View v){
        codigo_TipoUbicacion.setText("");
        nombre_TipoUbicacion.setText("");

    }

    public void insertarServidorTU(View v)
    {
        String idTU = codigo_TipoUbicacion.getText().toString();
        String nomTU = nombre_TipoUbicacion.getText().toString();
        String url = null;
        url = urlLocal+ "?idTipoUbicacion=" + idTU + "&nombreTipoUbicacion=" + nomTU;
        ControladorServicio.insertarExterno(url, this);
        //http://192.168.1.10/ws_tipoubicacion_insert.php?idTipoUbicacion=2&nombreTipoUbicacion=FIA

    }
}