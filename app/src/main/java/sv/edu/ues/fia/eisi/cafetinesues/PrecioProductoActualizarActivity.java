package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PrecioProductoActualizarActivity extends AppCompatActivity {

    ControlDB helper;
    EditText id_PrecioProducto;
    EditText id_Producto;
    EditText id_ListaPrecio;
    EditText precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precio_producto_actualizar);
        helper = new ControlDB(this);
        id_PrecioProducto = (EditText) findViewById(R.id.id_PrecioProducto);
        id_Producto = (EditText) findViewById(R.id.id_Producto);
        id_ListaPrecio = (EditText) findViewById(R.id.id_ListaPrecio);
        precio = (EditText) findViewById(R.id.precio);
    }
    public void actualizarPrecioProducto(View v) {
        try {
            PrecioProducto precioProducto = new PrecioProducto();
            precioProducto.setId_PrecioProducto(Integer.parseInt(id_PrecioProducto.getText().toString()));
            precioProducto.setId_Producto(Integer.parseInt(id_Producto.getText().toString()));
            precioProducto.setId_ListaPrecio(Integer.parseInt(id_ListaPrecio.getText().toString()));
            precioProducto.setPrecio(Float.parseFloat(precio.getText().toString()));
            String registro;
            helper.abrir();
            registro = helper.actualizar(precioProducto);
            helper.cerrar();
            Toast.makeText(this, registro, Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v)
    {
        id_PrecioProducto.setText("");
        id_Producto.setText("");
        id_ListaPrecio.setText("");
        precio.setText("");
    }
}