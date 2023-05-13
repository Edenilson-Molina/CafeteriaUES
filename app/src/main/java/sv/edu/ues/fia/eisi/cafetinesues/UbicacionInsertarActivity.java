package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UbicacionInsertarActivity extends Activity {

    ControlDB helper;
    EditText codigo_Ubicacion;
    EditText nombre_Ubicacion;
    EditText descripcion_Ubicacion;
    EditText codigo_Facultad;
    EditText codigo_TipoUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_insertar);

        helper = new ControlDB(this);
        codigo_Ubicacion = (EditText) findViewById(R.id.id_Ubicacion);
        nombre_Ubicacion = (EditText) findViewById(R.id.nombre_Ubicacion);
        descripcion_Ubicacion=(EditText) findViewById(R.id.descripcion_Ubicacion);
        codigo_Facultad=(EditText) findViewById(R.id.id_Facultad);
        codigo_TipoUbicacion=(EditText) findViewById(R.id.id_TipoUbicacion);

    }

    public void insertarUbicacion(View v){

        String id_Ubicacion = codigo_Ubicacion.getText().toString();
        String nom_Ubicacion = nombre_Ubicacion.getText().toString();
        String des_Ubicacion = descripcion_Ubicacion.getText().toString();
        String id_Facultad = codigo_Facultad.getText().toString();
        String id_TipoUbicacion = codigo_TipoUbicacion.getText().toString();
        Ubicacion ubicacion = new Ubicacion();

        ubicacion.setId_Ubicacion(Integer.parseInt(id_Ubicacion));
        ubicacion.setNombre_Ubicacion(nom_Ubicacion);
        ubicacion.setDescripcion_Ubicacion(des_Ubicacion);
        ubicacion.setId_Facultad(Integer.parseInt(id_Facultad));
        ubicacion.setId_TipoUbicacion(Integer.parseInt(id_TipoUbicacion));
        String regInsertados;

        helper.abrir();
        regInsertados=helper.insertar(ubicacion);
        helper.cerrar();
        Toast.makeText(this,regInsertados,Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v){
        codigo_Ubicacion.setText("");
        nombre_Ubicacion.setText("");
        descripcion_Ubicacion.setText("");
        codigo_Facultad.setText("");
        codigo_TipoUbicacion.setText("");

    }
}