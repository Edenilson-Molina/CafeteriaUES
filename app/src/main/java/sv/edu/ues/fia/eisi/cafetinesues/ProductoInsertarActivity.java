package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoInsertarActivity extends Activity {
    EditText codigo_Producto;
    EditText codigo_TipoProducto;
    EditText nombre_Producto;
    EditText estado_Producto;
    EditText precioactual_Producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_insertar);
        codigo_Producto = (EditText) findViewById(R.id.codigo_Producto);
        codigo_TipoProducto = (EditText) findViewById(R.id.codigo_TipoProducto);
        nombre_Producto = (EditText) findViewById(R.id.nombre_Producto);
        estado_Producto = (EditText) findViewById(R.id.estado_Producto);
        precioactual_Producto = (EditText) findViewById(R.id.precioactual_Producto);
    }
    public void insertarProducto(View v)
    {
        String consola = codigo_Producto.getText().toString();
        Toast.makeText(this, consola, Toast.LENGTH_SHORT).show();
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