package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FacultadConsultarActivity extends Activity {
    ControlDB helper;
    EditText id_Facultad;
    EditText nombre_Facultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultad_consultar);
        helper = new ControlDB(this);
        id_Facultad = (EditText) findViewById(R.id.id_Facultad);
        nombre_Facultad = (EditText) findViewById(R.id.nombre_Facultad);
    }

    public void consultarFacultad(View v){

        Facultad facultadConsulta = new Facultad();
        facultadConsulta.setId_Faculdad(Integer.parseInt(id_Facultad.getText().toString()));
        helper.abrir();
        Facultad facultad = helper.consultarFacultad(facultadConsulta);
        helper.cerrar();
        if(facultad == null)
            Toast.makeText(this, "Facultad no registrada", Toast.LENGTH_SHORT).show();
        else {
            nombre_Facultad.setText(facultad.getNombre_Facultad());
        }
    }

    public void LimpiarTexto(View v){
        id_Facultad.setText("");
        nombre_Facultad.setText("");

    }
}