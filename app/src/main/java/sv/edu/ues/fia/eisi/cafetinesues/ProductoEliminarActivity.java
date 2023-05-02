package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoEliminarActivity extends Activity {

    EditText codigo_Producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_eliminar);
        codigo_Producto = (EditText) findViewById(R.id.codigo_Producto);
    }

    public void eliminarProducto(View v)
    {
        Toast.makeText(this,codigo_Producto.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}