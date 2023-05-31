package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.annotation.SuppressLint;

@SuppressLint("NewApi")
public class FacultadInsertarActivity extends Activity {

    ControlDB helper;
    EditText codigo_Facultad;
    EditText nombre_Facultad;

    private final String urlLocal = "http://192.168.1.21/ws_Facultad_insert.php";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultad_insertar);
        helper = new ControlDB(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        codigo_Facultad= (EditText) findViewById(R.id.id_Facultad);
        nombre_Facultad = (EditText) findViewById(R.id.nombre_Facultad);

    }

    public void insertarFacultad(View v)
    {

        try{
            String id_Facultad = codigo_Facultad.getText().toString();
            String nom_Facultad = nombre_Facultad.getText().toString();
            Facultad facultad = new Facultad();

            facultad.setId_Faculdad(Integer.parseInt(id_Facultad));
            facultad.setNombre_Facultad(nom_Facultad);
            String regInsertados;

            helper.abrir();
            regInsertados=helper.Insertar(facultad);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();
        }


    }

    public void limpiarTexto(View v){

        codigo_Facultad.setText("");
        nombre_Facultad.setText("");

    }
    public void insertarServidorFacu(View v)
    {
        String codenF = codigo_Facultad.getText().toString();
        String nomenF = nombre_Facultad.getText().toString();
        String url = null;
        url = urlLocal+ "?id_Facultad=" + codenF + "&nombre_Facultad=" + nomenF;
        ControladorServicio.insertarExterno(url, this);
        //http://localhost/ws_Facultad_insert.php?id_Facultad=4&nombre_Facultad=fia
    }

}