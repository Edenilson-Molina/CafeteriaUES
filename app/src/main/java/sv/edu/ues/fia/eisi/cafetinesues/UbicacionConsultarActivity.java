package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UbicacionConsultarActivity extends Activity {

    ControlDB helper;
    EditText id_Ubicacion;
    EditText nombre_Ubicacion;
    EditText descripcion_Ubicacion;

    EditText id_Facultad;
    EditText id_TipoUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_consultar);
        helper = new ControlDB(this);
        id_Ubicacion=(EditText) findViewById(R.id.id_Ubicacion);
        nombre_Ubicacion=(EditText) findViewById(R.id.nombre_Ubicacion);
        descripcion_Ubicacion=(EditText) findViewById(R.id.descripcion_Ubicacion);
        id_Facultad=(EditText) findViewById(R.id.id_Facultad);
        id_TipoUbicacion=(EditText) findViewById(R.id.id_TipoUbicacion);
    }

    public void consultarUbicacion(View v){
        Ubicacion ubicacionConsulta = new Ubicacion();
        ubicacionConsulta.setId_Ubicacion(Integer.parseInt(id_Ubicacion.getText().toString()));
        helper.abrir();
        Ubicacion ubicacion = helper.consultarUbicacion(ubicacionConsulta);
        helper.cerrar();
        if (ubicacion==null)
            Toast.makeText(this,"Ubicacion no registrada", Toast.LENGTH_SHORT).show();
        else {
            nombre_Ubicacion.setText(ubicacion.getNombre_Ubicacion());
            descripcion_Ubicacion.setText(ubicacion.getDescripcion_Ubicacion());
            id_Facultad.setText(String.valueOf(ubicacion.getId_Facultad()));
            id_TipoUbicacion.setText(String.valueOf(ubicacion.getId_TipoUbicacion()));
        }

    }

    public void limpiarTexto(View v){
        id_Ubicacion.setText("");
        nombre_Ubicacion.setText("");
        descripcion_Ubicacion.setText("");
        id_Facultad.setText("");
        id_TipoUbicacion.setText("");

    }
}