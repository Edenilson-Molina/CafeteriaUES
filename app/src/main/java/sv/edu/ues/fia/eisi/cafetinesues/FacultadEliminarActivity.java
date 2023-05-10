package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FacultadEliminarActivity extends Activity {

    ControlDB helper;
    EditText id_Facultad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultad_eliminar);
        helper = new ControlDB(this);
        id_Facultad = (EditText) findViewById(R.id.id_Facultad);
    }

    public void eliminarFacultad(View v){
        Facultad facultad = new Facultad();
        facultad.setId_Faculdad(Integer.parseInt(id_Facultad.getText().toString()));
        String registro;
        helper.abrir();
        registro = helper.Eliminar(facultad);
        helper.cerrar();
        Toast.makeText(this,registro, Toast.LENGTH_SHORT).show();

    }

}