package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class EncargadoLocalInsertarActivity extends Activity {

    ControlDB helper;
    EditText codigo_EncargadoLocal;
    EditText nombre_EncargadoLocal;
    private final String urlLocal = "http://192.168.1.45/ws_encargo_insert_.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_local_insertar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlDB(this);
        codigo_EncargadoLocal = (EditText) findViewById(R.id.codigo_EncargadoLocal);
        nombre_EncargadoLocal = (EditText) findViewById(R.id.nombre_EncargadoLocal);
    }
    public void insertarServidor(View v) {

        String codenlocal = codigo_EncargadoLocal.getText().toString();
        String nombre = nombre_EncargadoLocal.getText().toString();
        String url = null;
        JSONObject datosNota = new JSONObject();
        JSONObject nota = new JSONObject();
        url = urlLocal+ "?id_encargadolocal=" + codenlocal + "&nombre_encargadolocal="+ nombre;
        ControladorServicio.insertarExterno(url, this);
    }
    public void insertarEncargadoLocal (View v){

        try{
            String id_Encargado = codigo_EncargadoLocal.getText().toString();
            String nombre_Encargado = nombre_EncargadoLocal.getText().toString();
            String regInsertados;

            EncargadoLocal encargadoLocal = new EncargadoLocal();
            encargadoLocal.setId_EncargadoLocal(Integer.parseInt(id_Encargado));
            encargadoLocal.setNombre_EncargadoLocal(nombre_Encargado);
            helper.abrir();
            regInsertados = helper.Insertar(encargadoLocal);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v){
        codigo_EncargadoLocal.setText("");
        nombre_EncargadoLocal.setText("");
    }
}