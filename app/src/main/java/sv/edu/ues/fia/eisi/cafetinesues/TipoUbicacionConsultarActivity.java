package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoUbicacionConsultarActivity extends Activity {

    ControlDB helper;
    EditText id_TipoUbicacion;
    EditText nombre_TipoUbicacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_ubicacion_consultar);
        helper = new ControlDB(this);
        id_TipoUbicacion=(EditText) findViewById(R.id.id_TipoUbicacion);
        nombre_TipoUbicacion=(EditText) findViewById(R.id.nombre_TipoUbicacion);
    }

    public void consultarTipoUbicacion(View v){
        TipoUbicacion tipoUbicacionConsulta = new TipoUbicacion();
        tipoUbicacionConsulta.setId_TipoUbicacion(Integer.parseInt(id_TipoUbicacion.getText().toString()));
        helper.abrir();
        TipoUbicacion tipoUbicacion = helper.consultarTipoUbicacion(tipoUbicacionConsulta);
        helper.cerrar();
        if (tipoUbicacion==null)
            Toast.makeText(this, "Tipo Ubicacion no Registrada", Toast.LENGTH_SHORT).show();
        else {
            nombre_TipoUbicacion.setText(tipoUbicacion.getNombre_TipoUbicacion());

        }

    }

    public void limpiarTexto(View v){
        id_TipoUbicacion.setText("");
        nombre_TipoUbicacion.setText("");

    }
}