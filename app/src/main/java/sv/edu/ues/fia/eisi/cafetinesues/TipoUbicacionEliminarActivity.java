package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoUbicacionEliminarActivity extends Activity {

    ControlDB helper;
    EditText id_TipoUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_ubicacion_eliminar);
        helper = new ControlDB(this);
        id_TipoUbicacion= (EditText) findViewById(R.id.id_TipoUbicacion);

    }

    public void eliminarTipoUbicacion(View v){

        try{
            TipoUbicacion tipoUbicacion = new TipoUbicacion();
            tipoUbicacion.setId_TipoUbicacion(Integer.parseInt(id_TipoUbicacion.getText().toString()));
            String registro;
            helper.abrir();
            registro = helper.eliminar(tipoUbicacion);
            helper.cerrar();
            Toast.makeText(this,registro,Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();
        }

    }

}