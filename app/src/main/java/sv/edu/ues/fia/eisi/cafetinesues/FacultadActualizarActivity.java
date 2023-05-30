package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FacultadActualizarActivity extends Activity {

    ControlDB helper;
    EditText id_Facultad;
    EditText nombre_Facultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultad_actualizar);
        helper = new ControlDB(this);
        id_Facultad = (EditText) findViewById(R.id.id_Facultad);
        nombre_Facultad = (EditText) findViewById(R.id.nombre_Facultad);
    }

    public  void actualizarFacultad(View v){
    Facultad facultad = new Facultad();
    facultad.setId_Faculdad(Integer.parseInt(id_Facultad.getText().toString()));
    facultad.setNombre_Facultad(nombre_Facultad.getText().toString());
    String registro;
    helper.abrir();
        registro=helper.Actualizar(facultad);
    helper.cerrar();
    Toast.makeText(this,registro,Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v){
        id_Facultad.setText("");
        nombre_Facultad.setText("");
    }
}