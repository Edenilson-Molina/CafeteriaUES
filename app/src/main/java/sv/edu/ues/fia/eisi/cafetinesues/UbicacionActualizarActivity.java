package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UbicacionActualizarActivity extends Activity {

    ControlDB helper;
    EditText id_Ubicacion;
    EditText nombre_Ubicacion;
    EditText descripcion_Ubicacion;
    EditText id_Facultad;
    EditText id_TipoUbicacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_actualizar);
        helper=new ControlDB(this);
        id_Ubicacion=(EditText) findViewById(R.id.id_Ubicacion);
        nombre_Ubicacion=(EditText) findViewById(R.id.nombre_Ubicacion);
        descripcion_Ubicacion=(EditText) findViewById(R.id.descripcion_Ubicacion);
        id_Facultad=(EditText) findViewById(R.id.id_Facultad);
        id_TipoUbicacion=(EditText) findViewById(R.id.id_TipoUbicacion);

    }

    public void actualizarUbicacion(View v){
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setId_Ubicacion(Integer.parseInt(id_Ubicacion.getText().toString()));
        ubicacion.setNombre_Ubicacion(nombre_Ubicacion.getText().toString());
        ubicacion.setDescripcion_Ubicacion(descripcion_Ubicacion.getText().toString());
        ubicacion.setId_Facultad(Integer.parseInt(id_Facultad.getText().toString()));
        ubicacion.setId_TipoUbicacion(Integer.parseInt(id_TipoUbicacion.getText().toString()));

        String registro;
        helper.abrir();
        registro=helper.actualizar(ubicacion);
        helper.cerrar();
        Toast.makeText(this,registro,Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v){
        id_Ubicacion.setText("");
        nombre_Ubicacion.setText("");
        descripcion_Ubicacion.setText("");
        id_Facultad.setText("");
        id_TipoUbicacion.setText("");

    }
}