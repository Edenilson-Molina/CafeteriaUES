package sv.edu.ues.fia.eisi.cafetinesues;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@SuppressLint("NewApi")
public class FacultadConsultarActivity extends Activity {
    ControlDB helper;
    EditText id_Facultad;
    EditText nombre_Facultad;
    static List<Facultad> listaFacultad;
    static List<String> nombreFacultad;
    ListView listViewFacultad;
    //http://localhost/ws_db_facultad.php
    private final String urlLocal = "http://192.168.1.45/ws_db_facultad.php";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultad_consultar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlDB(this);
        id_Facultad = (EditText) findViewById(R.id.id_Facultad);
        nombre_Facultad = (EditText) findViewById(R.id.nombre_Facultad);
        listaFacultad = new ArrayList<Facultad>();
        nombreFacultad = new ArrayList<String>();
        listViewFacultad = (ListView) findViewById(R.id.listView1);
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

    public void limpiarTexto(View v){
        id_Facultad.setText("");
        nombre_Facultad.setText("");

    }
    public void servicioPHPF(View v) throws MalformedURLException {

        String url = "";
        url = urlLocal;
        String facultadesExternas = ControladorServicio.obtenerRespuestaPeticion(url, this);

        try {
            listaFacultad.addAll(ControladorServicio.obtenerFacultadesExterno(facultadesExternas, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void actualizarListView() {
        String dato = "";
        nombreFacultad.clear();
        for (int i = 0; i < listaFacultad.size(); i++) {
            dato = listaFacultad.get(i).getId_Faculdad() + "    "
                    + listaFacultad.get(i).getNombre_Facultad();
            nombreFacultad.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nombreFacultad);
        listViewFacultad.setAdapter(adaptador);
    }
    private void eliminarElementosDuplicados() {
        HashSet<Facultad> conjuntoFacultad = new HashSet<Facultad>();
        conjuntoFacultad.addAll(listaFacultad);
        listaFacultad.clear();
        listaFacultad.addAll(conjuntoFacultad);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreFacultad);
        nombreFacultad.clear();
        nombreFacultad.addAll(conjuntoNombre);
    }
}