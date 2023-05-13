package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class TipoUbicacionInsertarActivity extends Activity {

    ControlDB helper;
    EditText codigo_TipoUbicacion;
    EditText nombre_TipoUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_ubicacion_insertar);

        helper = new ControlDB(this);
        codigo_TipoUbicacion=(EditText) findViewById(R.id.id_TipoUbicacion);
        nombre_TipoUbicacion=(EditText) findViewById(R.id.nombre_TipoUbicacion);

    }

    public void insertarTipoUbicacion(View v){
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

    public void limpiarTexto(View v){
        codigo_TipoUbicacion.setText("");
        nombre_TipoUbicacion.setText("");

    }
}