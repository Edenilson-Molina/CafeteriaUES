package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

public class PrecioProductoConsultarActivity extends AppCompatActivity {
    ControlDB helper;
    EditText id_PrecioProducto;
    EditText id_Producto;
    EditText id_ListaPrecio;
    EditText precio;
    Button limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precio_producto_consultar);
        helper = new ControlDB(this);
        id_PrecioProducto = (EditText) findViewById(R.id.id_PrecioProducto);
        id_Producto = (EditText) findViewById(R.id.id_Producto);
        id_ListaPrecio = (EditText) findViewById(R.id.id_ListaPrecio);
        precio = (EditText) findViewById(R.id.precio);
        limpiar = (Button) findViewById(R.id.btnLimpiar);
    }

    public void consultarPrecioProducto(View v)
    {
        try {
            PrecioProducto precioProductoConsulta = new PrecioProducto();
            precioProductoConsulta.setId_PrecioProducto(Integer.parseInt(id_PrecioProducto.getText().toString()));
            helper.abrir();
            PrecioProducto precioProducto = helper.consultarPrecioProducto(precioProductoConsulta);
            helper.cerrar();
            if(precioProducto == null)
                Toast.makeText(this, "PrecioProducto no registrado", Toast.LENGTH_LONG).show();
            else{
                id_Producto.setText(String.valueOf(precioProducto.getId_Producto()));
                id_ListaPrecio.setText(String.valueOf(precioProducto.getId_ListaPrecio()));
                precio.setText(String.valueOf(precioProducto.getPrecio()));
                id_PrecioProducto.clearFocus();
                ocultarTeclado(v);

            }

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

    public void ocultarTeclado(View v)
    {
        if(v != null)
        {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
        }
    }


}