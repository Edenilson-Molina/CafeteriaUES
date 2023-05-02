package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoConsultarActivity extends Activity {
    EditText codigo_Producto;
    EditText codigo_TipoProducto;
    EditText nombre_Producto;
    EditText estado_Producto;
    EditText precioactual_Producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_consultar);
        codigo_Producto = (EditText) findViewById(R.id.codigo_Producto);
        codigo_TipoProducto = (EditText) findViewById(R.id.codigo_TipoProducto);
        nombre_Producto = (EditText) findViewById(R.id.nombre_Producto);
        estado_Producto = (EditText) findViewById(R.id.estado_Producto);
        precioactual_Producto = (EditText) findViewById(R.id.precioactual_Producto);
    }

    public void consultarProducto(View v)
    {
        Toast.makeText(this,codigo_Producto.getText().toString(), Toast.LENGTH_SHORT).show();
        precioactual_Producto.setText("1.53");
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