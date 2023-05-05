package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoProductoEliminarActivity extends Activity {

    ControlDB helper;
    EditText codigo_TipoProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_producto_eliminar);
        helper = new ControlDB(this);
        codigo_TipoProducto = (EditText) findViewById(R.id.codigo_TipoProducto);
    }

    public void eliminarTipoProducto(View v)
    {
        TipoProducto tipoProducto = new TipoProducto();
        tipoProducto.setId_TipoProducto(Integer.parseInt(codigo_TipoProducto.getText().toString()));
        String operacion;
        helper.abrir();
        operacion = helper.eliminar(tipoProducto);
        helper.cerrar();
        Toast.makeText(this,operacion, Toast.LENGTH_SHORT).show();
    }

}