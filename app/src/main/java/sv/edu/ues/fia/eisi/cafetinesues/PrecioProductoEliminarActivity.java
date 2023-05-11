package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PrecioProductoEliminarActivity extends AppCompatActivity {

    ControlDB helper;
    EditText id_PrecioProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precio_producto_eliminar);
        helper = new ControlDB(this);
        id_PrecioProducto = (EditText) findViewById(R.id.id_PrecioProducto);
    }

    public void eliminarPrecioProducto(View v){
        try {
            PrecioProducto precioProducto = new PrecioProducto();
            precioProducto.setId_PrecioProducto(Integer.parseInt(id_PrecioProducto.getText().toString()));
            String operacion;
            helper.abrir();
            operacion = helper.eliminar(precioProducto);
            helper.cerrar();
            Toast.makeText(this, operacion, Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
        }

    }
}