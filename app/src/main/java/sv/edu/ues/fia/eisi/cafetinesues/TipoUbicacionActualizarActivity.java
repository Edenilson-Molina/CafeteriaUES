package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoUbicacionActualizarActivity extends Activity {

    ControlDB helper;
    EditText id_TipoUbicacion;
    EditText nombre_TipoUbicacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_ubicacion_actualizar);
        helper = new ControlDB(this);
        id_TipoUbicacion=(EditText) findViewById(R.id.id_TipoUbicacion);
        nombre_TipoUbicacion=(EditText) findViewById(R.id.nombre_TipoUbicacion);
    }

    public void actualizarTipoUbicacion(View v){
        TipoUbicacion tipoUbicacion = new TipoUbicacion();
        tipoUbicacion.setId_TipoUbicacion(Integer.parseInt(id_TipoUbicacion.getText().toString()));
        tipoUbicacion.setNombre_TipoUbicacion(nombre_TipoUbicacion.getText().toString());
        String registro;
        helper.abrir();
        registro=helper.actualizar(tipoUbicacion);
        helper.cerrar();
        Toast.makeText(this,registro,Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v){
        id_TipoUbicacion.setText("");
        nombre_TipoUbicacion.setText("");

    }

}