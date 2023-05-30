package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoInsertarActivity extends Activity {
    ControlDB helper;
    EditText codigo_Producto;
    EditText codigo_TipoProducto;
    EditText nombre_Producto;
    EditText estado_Producto;
    EditText precioactual_Producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_insertar);
        helper = new ControlDB(this);
        codigo_Producto = (EditText) findViewById(R.id.codigo_Producto);
        codigo_TipoProducto = (EditText) findViewById(R.id.codigo_TipoProducto);
        nombre_Producto = (EditText) findViewById(R.id.nombre_Producto);
        estado_Producto = (EditText) findViewById(R.id.estado_Producto);
        precioactual_Producto = (EditText) findViewById(R.id.precioactual_Producto);
    }
    public void insertarProducto(View v)
    {

        try{
            String id_Producto = codigo_Producto.getText().toString();
            String id_TipoProducto = codigo_TipoProducto.getText().toString();
            String nom_Producto = nombre_Producto.getText().toString();
            String statu_Producto = estado_Producto.getText().toString();
            String precioact_Producto = precioactual_Producto.getText().toString();
            String regInsertados;

            Producto producto = new Producto();
            producto.setCodigo_Producto(Integer.parseInt(id_Producto));
            producto.setCodigo_TipoProducto(Integer.parseInt(id_TipoProducto));
            producto.setNombre_Producto(nom_Producto);
            producto.setEstado_Producto(statu_Producto);
            producto.setPrecioactual_Producto(Float.parseFloat(precioact_Producto));
            helper.abrir();
            regInsertados=helper.insertar(producto);
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
        codigo_Producto.setText("");
        codigo_TipoProducto.setText("");
        nombre_Producto.setText("");
        estado_Producto.setText("");
        precioactual_Producto.setText("");
    }
}