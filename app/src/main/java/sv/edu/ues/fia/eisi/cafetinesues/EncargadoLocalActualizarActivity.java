package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ChecksSdkIntAtLeast;

public class EncargadoLocalActualizarActivity extends Activity {

    ControlDB helper;
    TextView codigo_encargadoLocal;
    TextView nombre_encargadoLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_local_actualizar);
        helper = new ControlDB(this);
        codigo_encargadoLocal = (EditText) findViewById(R.id.codigo_EncargadoLocal);
        nombre_encargadoLocal = (EditText) findViewById(R.id.nombre_EncargadoLocal);
    }
    public void actualizarEncargadoLocal (View v){
        EncargadoLocal encargadoLocal= new EncargadoLocal();
        String id_encargado = codigo_encargadoLocal.getText().toString();
        String nombre_encargado = nombre_encargadoLocal.getText().toString();
        String registro;
        encargadoLocal.setId_EncargadoLocal(Integer.parseInt(id_encargado));
        encargadoLocal.setNombre_EncargadoLocal(nombre_encargado);
        helper.abrir();
        registro = helper.Actualizar(encargadoLocal);
        helper.cerrar();
        Toast.makeText(this, registro, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v){
        codigo_encargadoLocal.setText("");
        nombre_encargadoLocal.setText("");
    }
}