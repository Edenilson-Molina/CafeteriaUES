package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoActualizarActivity extends Activity {
    ControlDB helper;
    EditText codigo_Producto;
    EditText codigo_TipoProducto;
    EditText nombre_Producto;
    EditText estado_Producto;
    EditText precioactual_Producto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_actualizar);
        helper = new ControlDB(this);
        codigo_Producto = (EditText) findViewById(R.id.codigo_Producto);
        codigo_TipoProducto = (EditText) findViewById(R.id.codigo_TipoProducto);
        nombre_Producto = (EditText) findViewById(R.id.nombre_Producto);
        estado_Producto = (EditText) findViewById(R.id.estado_Producto);
        precioactual_Producto = (EditText) findViewById(R.id.precioactual_Producto);
    }

    public void actualizarProducto(View v)
    {
        Producto producto = new Producto();
        producto.setCodigo_Producto(Integer.parseInt(codigo_Producto.getText().toString()));
        producto.setCodigo_TipoProducto(Integer.parseInt(codigo_TipoProducto.getText().toString()));
        producto.setNombre_Producto(nombre_Producto.getText().toString());
        producto.setEstado_Producto(estado_Producto.getText().toString());
        producto.setPrecioactual_Producto(Float.parseFloat(precioactual_Producto.getText().toString()));
        String registro;
        helper.abrir();
            registro = helper.actualizar(producto);
        helper.cerrar();
        Toast.makeText(this,registro, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v)
    {
        codigo_Producto.setText("");
        codigo_TipoProducto.setText("");
        nombre_Producto.setText("");
        estado_Producto.setText("");
        precioactual_Producto.setText("");

    }
}