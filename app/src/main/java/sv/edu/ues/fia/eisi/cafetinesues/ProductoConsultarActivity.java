package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class ProductoConsultarActivity extends Activity {
    ControlDB helper;
    EditText codigo_Producto;
    EditText codigo_TipoProducto;
    EditText nombre_Producto;
    EditText estado_Producto;
    EditText precioactual_Producto;

    EditText cantidadAparicionesEnComboProductos;

    private final String urlLocal = "http://192.168.1.21/ws_consultar_aparicionesproducto.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_consultar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlDB(this);
        codigo_Producto = (EditText) findViewById(R.id.codigo_Producto);
        codigo_TipoProducto = (EditText) findViewById(R.id.codigo_TipoProducto);
        nombre_Producto = (EditText) findViewById(R.id.nombre_Producto);
        estado_Producto = (EditText) findViewById(R.id.estado_Producto);
        precioactual_Producto = (EditText) findViewById(R.id.precioactual_Producto);
        cantidadAparicionesEnComboProductos = (EditText) findViewById(R.id.cant_comboprod_relacionados);
    }

    public void consultarProducto(View v)
    {
        Producto productoConsulta = new Producto();
        productoConsulta.setCodigo_Producto(Integer.parseInt(codigo_Producto.getText().toString()));
        helper.abrir();
        Producto producto = helper.consultarProducto(productoConsulta);
        helper.cerrar();
        if(producto == null)
            Toast.makeText(this, "Producto no registrado", Toast.LENGTH_LONG).show();
        else{
            codigo_TipoProducto.setText(String.valueOf(producto.getCodigo_TipoProducto()));
            nombre_Producto.setText(producto.getNombre_Producto());
            estado_Producto.setText(producto.getEstado_Producto());
            precioactual_Producto.setText(String.valueOf(producto.getPrecioactual_Producto()));
            ocultarTeclado(v);
        }
    }

    public void consultarAparicionesEnComboProductos(View v)
    {
        String url = null;
        url = urlLocal + "?idProducto=" + codigo_Producto.getText().toString();
        String cant_aparicionesjSON = ControladorServicio.obtenerRespuestaPeticion(url, this);
        Log.v("Respuesta", cant_aparicionesjSON);
        cantidadAparicionesEnComboProductos.setText(ControladorServicio.consultarAparicionesProducto(cant_aparicionesjSON, this));
    }

    public void limpiarTexto(View v)
    {
        codigo_Producto.setText("");
        codigo_TipoProducto.setText("");
        nombre_Producto.setText("");
        estado_Producto.setText("");
        precioactual_Producto.setText("");
    }

    public void ocultarTeclado(View v)
    {
        if(v != null)
        {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
        }
    }
}