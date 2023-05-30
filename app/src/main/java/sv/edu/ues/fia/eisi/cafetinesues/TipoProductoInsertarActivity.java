package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
@SuppressLint("NewApi")
public class TipoProductoInsertarActivity extends Activity {
    
    ControlDB helper;
    EditText codigo_TipoProducto;
    EditText nombre_TipoProducto;

    private final String urlLocal = "http://192.168.1.21/ws_tipoproducto_insert.php";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_producto_insertar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlDB(this);
        codigo_TipoProducto = (EditText) findViewById(R.id.codigo_TipoProducto);
        nombre_TipoProducto = (EditText) findViewById(R.id.nombre_TipoProducto);
    }

    public void insertarTipoProducto(View v)
    {

        try{
            TipoProducto tipoProducto = new TipoProducto();
            tipoProducto.setId_TipoProducto(Integer.parseInt(codigo_TipoProducto.getText().toString()));
            tipoProducto.setNombre_TipoProducto(nombre_TipoProducto.getText().toString());
            String regInsertados;
            helper.abrir();
            regInsertados = helper.insertar(tipoProducto);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v)
    {
        codigo_TipoProducto.setText("");
        nombre_TipoProducto.setText("");
    }

    public void insertarServidor(View v)
    {
        String idTP = codigo_TipoProducto.getText().toString();
        String nomTP = nombre_TipoProducto.getText().toString();
        String url = null;
        url = urlLocal+ "?idTipoProducto=" + idTP + "&nombreTipoProducto=" + nomTP;
        ControladorServicio.insertarExterno(url, this);
        //http://localhost/ws_tipoproducto_insert.php?idTipoProducto=2&nombreTipoProducto=Almuerzo
    }


}