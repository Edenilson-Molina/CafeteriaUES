package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoLocalInsertarActivity extends Activity {

    ControlDB helper;
    EditText codigo_EncargadoLocal;
    EditText nombre_EncargadoLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_local_insertar);
        helper = new ControlDB(this);
        codigo_EncargadoLocal = (EditText) findViewById(R.id.codigo_EncargadoLocal);
        nombre_EncargadoLocal = (EditText) findViewById(R.id.nombre_EncargadoLocal);
    }
    public void insertarEncargadoLocal (View v){
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

    public void limpiarTexto(View v){
        codigo_EncargadoLocal.setText("");
        nombre_EncargadoLocal.setText("");
    }
}