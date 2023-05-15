package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LocalInsertarActivity extends Activity {

    ControlDB helper;
    EditText codigo_Local;
    EditText codigo_Ubicacion;
    EditText codigo_EncargadoLocal;
    EditText nombre_Local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_insertar);
        helper = new ControlDB(this);
        codigo_Local = (EditText) findViewById(R.id.codigo_Local);
        codigo_Ubicacion = (EditText) findViewById(R.id.codigo_Ubicacion);
        codigo_EncargadoLocal = (EditText) findViewById(R.id.codigo_EncargadoLocal);
        nombre_Local = (EditText) findViewById(R.id.nombre_Local);
    }
    public void  insertarLocal(View v){

        try{
            String id_Local = codigo_Local.getText().toString();
            String id_Ubicacion = codigo_Ubicacion.getText().toString();
            String id_Encargadolocal = codigo_EncargadoLocal.getText().toString();
            String nom_Local = nombre_Local.getText().toString();
            String regInsertados;

            Local local = new Local();
            local.setId_Local(Integer.parseInt(id_Local));
            local.setId_Ubicacion(Integer.parseInt(id_Ubicacion));
            local.setId_EncargadoLocal(Integer.parseInt(id_Encargadolocal));
            local.setNombre_Local(nom_Local);
            helper.abrir();
            regInsertados = helper.Insertar(local);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v){
        codigo_Local.setText("");
        codigo_Ubicacion.setText("");
        codigo_EncargadoLocal.setText("");
        nombre_Local.setText("");

    }

}