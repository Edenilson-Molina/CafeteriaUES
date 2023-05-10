package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LocalActualizarActivity extends Activity {
    ControlDB helper;
    EditText codigo_Local;
    EditText codigo_Ubicacion;
    EditText codigo_EncargadoLocal;
    EditText nombre_Local;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_actualizar);
        helper = new ControlDB(this);
        codigo_Local = (EditText) findViewById(R.id.codigo_Local);
        codigo_Ubicacion = (EditText) findViewById(R.id.codigo_Ubicacion);
        codigo_EncargadoLocal = (EditText) findViewById(R.id.codigo_EncargadoLocal);
        nombre_Local = (EditText) findViewById(R.id.nombre_Local);
    }
    public void actualizarLocal(View v){
        String id_Local = codigo_Local.getText().toString();
        String id_Ubicacion = codigo_Ubicacion.getText().toString();
        String id_Encargadolocal = codigo_EncargadoLocal.getText().toString();
        String nom_Local = nombre_Local.getText().toString();
        String registro;

        Local local = new Local();

        local.setId_Local(Integer.parseInt(id_Local));
        local.setId_Ubicacion(Integer.parseInt(id_Ubicacion));
        local.setId_EncargadoLocal(Integer.parseInt(id_Encargadolocal));
        local.setNombre_Local(nom_Local);
        helper.abrir();
        registro = helper.Actualizar(local);
        helper.cerrar();
        Toast.makeText(this, registro, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v){
        codigo_Local.setText("");
        codigo_Ubicacion.setText("");
        codigo_EncargadoLocal.setText("");
        nombre_Local.setText("");

    }
}