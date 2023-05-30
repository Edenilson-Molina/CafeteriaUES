package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoEliminarActivity extends Activity {
    ControlDB helper;
    EditText codigo_Producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_eliminar);
        helper = new ControlDB(this);
        codigo_Producto = (EditText) findViewById(R.id.codigo_Producto);
    }

    public void eliminarProducto(View v)
    {
        Producto producto = new Producto();
        try{
            producto.setCodigo_Producto(Integer.parseInt(codigo_Producto.getText().toString()));
            String registro;
            helper.abrir();
            registro = helper.eliminar(producto);
            helper.cerrar();
            Toast.makeText(this,registro, Toast.LENGTH_SHORT).show();
        } catch(NumberFormatException ex){ // handle your exception
            Toast.makeText(this,"huy"+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}