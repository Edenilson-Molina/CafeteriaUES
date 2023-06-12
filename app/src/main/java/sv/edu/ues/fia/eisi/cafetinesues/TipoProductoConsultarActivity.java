package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

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
public class TipoProductoConsultarActivity extends Activity {

    ControlDB helper;
    EditText codigo_TipoProducto;
    EditText nombre_TipoProducto;
    static List<TipoProducto> listaTipoProductos;
    static List<String> nombreTipoProducto;
    ListView listViewTipoProductos;
    //http://localhost/ws_db_tipoproducto.php
    private final String urlLocal = "http://192.168.1.45/ws_db_tipoproducto.php";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_producto_consultar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlDB(this);
        codigo_TipoProducto = (EditText) findViewById(R.id.codigo_TipoProducto);
        nombre_TipoProducto = (EditText) findViewById(R.id.nombre_TipoProducto);
        listaTipoProductos = new ArrayList<TipoProducto>();
        nombreTipoProducto = new ArrayList<String>();
        listViewTipoProductos = (ListView) findViewById(R.id.listView1);
    }

    public void consultarTipoProducto(View v) {
        TipoProducto tipoProductoConsulta = new TipoProducto();
        tipoProductoConsulta.setId_TipoProducto(Integer.parseInt(codigo_TipoProducto.getText().toString()));
        String registro;
        helper.abrir();
        TipoProducto tipoProducto = helper.consultarTipoProducto(tipoProductoConsulta);
        helper.cerrar();
        if (tipoProducto == null) {
            Toast.makeText(this, "Tipo de Producto no encontrado", Toast.LENGTH_SHORT).show();
        } else {
            nombre_TipoProducto.setText(tipoProducto.getNombre_TipoProducto());
            ocultarTeclado(v);
        }

    }

    public void limpiarTexto(View v) {
        codigo_TipoProducto.setText("");
        nombre_TipoProducto.setText("");
    }

    public void ocultarTeclado(View v) {
        if (v != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public void servicioPHP(View v) throws MalformedURLException {

        String url = "";
        url = urlLocal;
        String tipoProductosExternas = ControladorServicio.obtenerRespuestaPeticion(url, this);

        try {
            listaTipoProductos.addAll(ControladorServicio.obtenerTipoProductosExterno(tipoProductosExternas, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarListView() {
        String dato = "";
        nombreTipoProducto.clear();
        for (int i = 0; i < listaTipoProductos.size(); i++) {
            dato = listaTipoProductos.get(i).getId_TipoProducto() + "    "
                    + listaTipoProductos.get(i).getNombre_TipoProducto();
            nombreTipoProducto.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nombreTipoProducto);
        listViewTipoProductos.setAdapter(adaptador);
    }
    private void eliminarElementosDuplicados() {
        HashSet<TipoProducto> conjuntoTipoProducto = new HashSet<TipoProducto>();
        conjuntoTipoProducto.addAll(listaTipoProductos);
        listaTipoProductos.clear();
        listaTipoProductos.addAll(conjuntoTipoProducto);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreTipoProducto);
        nombreTipoProducto.clear();
        nombreTipoProducto.addAll(conjuntoNombre);
    }


}