package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LocalConsultarActivity extends Activity {

    ControlDB helper;
    EditText codigo_Local;
    EditText codigo_Ubicacion;
    EditText codigo_EncargadoLocal;
    EditText nombre_Local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_consultar);
        helper = new ControlDB(this);
        codigo_Local = (EditText) findViewById(R.id.codigo_Local);
        codigo_Ubicacion = (EditText) findViewById(R.id.codigo_Ubicacion);
        codigo_EncargadoLocal = (EditText) findViewById(R.id.codigo_EncargadoLocal);
        nombre_Local = (EditText) findViewById(R.id.nombre_Local);
    }

    public void consultarLocal (View v){
        Local localConsulta = new Local();
        localConsulta.setId_Local(Integer.parseInt(codigo_Local.getText().toString()));
        helper.abrir();
        Local local = helper.Consultar(localConsulta);
        helper.cerrar();
        if(local == null){
            Toast.makeText(this, "Local no registrado", Toast.LENGTH_SHORT).show();
        }else{
            codigo_Ubicacion.setText(String.valueOf(local.getId_Ubicacion()));
            codigo_EncargadoLocal.setText(String.valueOf(local.getId_EncargadoLocal()));
            nombre_Local.setText(String.valueOf(local.getNombre_Local()));
        }
    }
    public void limpiarTexto(View v){
        codigo_Local.setText("");
        codigo_Ubicacion.setText("");
        codigo_EncargadoLocal.setText("");
        nombre_Local.setText("");

    }
}
