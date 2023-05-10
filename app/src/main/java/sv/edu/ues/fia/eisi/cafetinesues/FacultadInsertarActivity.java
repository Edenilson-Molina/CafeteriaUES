package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FacultadInsertarActivity extends Activity {

    ControlDB helper;
    EditText codigo_Facultad;
    EditText nombre_Facultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultad_insertar);

        helper = new ControlDB(this);
        codigo_Facultad= (EditText) findViewById(R.id.id_Facultad);
        nombre_Facultad = (EditText) findViewById(R.id.nombre_Facultad);

    }

    public void insertarFacultad(View v)
    {
        String id_Facultad = codigo_Facultad.getText().toString();
        String nom_Facultad = nombre_Facultad.getText().toString();
        Facultad facultad = new Facultad();

        facultad.setId_Faculdad(Integer.parseInt(id_Facultad));
        facultad.setNombre_Facultad(nom_Facultad);
        String regInsertados;

        helper.abrir();
        regInsertados=helper.Insertar(facultad);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();;

    }

    public void limpiarTexto(View v){

        codigo_Facultad.setText("");
        nombre_Facultad.setText("");

    }

}