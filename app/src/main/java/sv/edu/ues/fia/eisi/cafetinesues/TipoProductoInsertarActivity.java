package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoProductoInsertarActivity extends Activity {
    
    ControlDB helper;
    EditText codigo_TipoProducto;
    EditText nombre_TipoProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_producto_insertar);
        helper = new ControlDB(this);
        codigo_TipoProducto = (EditText) findViewById(R.id.codigo_TipoProducto);
        nombre_TipoProducto = (EditText) findViewById(R.id.nombre_TipoProducto);
    }

    public void insertarTipoProducto(View v)
    {
        TipoProducto tipoProducto = new TipoProducto();
        tipoProducto.setId_TipoProducto(Integer.parseInt(codigo_TipoProducto.getText().toString()));
        tipoProducto.setNombre_TipoProducto(nombre_TipoProducto.getText().toString());
        String regInsertados;
        helper.abrir();
        regInsertados = helper.insertar(tipoProducto);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v)
    {
        codigo_TipoProducto.setText("");
        nombre_TipoProducto.setText("");
    }


}