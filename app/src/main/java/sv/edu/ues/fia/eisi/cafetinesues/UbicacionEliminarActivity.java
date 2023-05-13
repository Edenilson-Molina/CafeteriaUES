package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UbicacionEliminarActivity extends Activity {


    ControlDB helper;
    EditText id_Ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_eliminar);
        helper = new ControlDB(this);
        id_Ubicacion = (EditText) findViewById(R.id.id_Ubicacion);
    }

    public void eliminarUbicacion(View v){
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setId_Ubicacion(Integer.parseInt(id_Ubicacion.getText().toString()));
        String registro;
        helper.abrir();
        registro = helper.eliminar(ubicacion);
        helper.cerrar();
        Toast.makeText(this,registro,Toast.LENGTH_SHORT).show();

    }
}