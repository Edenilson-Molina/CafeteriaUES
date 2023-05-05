package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoProductoConsultarActivity extends Activity {

    ControlDB helper;
    EditText codigo_TipoProducto;
    EditText nombre_TipoProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_producto_consultar);
        helper = new ControlDB(this);
        codigo_TipoProducto = (EditText) findViewById(R.id.codigo_TipoProducto);
        nombre_TipoProducto = (EditText) findViewById(R.id.nombre_TipoProducto);
    }

    public void consultarTipoProducto(View v)
    {
        TipoProducto tipoProductoConsulta = new TipoProducto();
        tipoProductoConsulta.setId_TipoProducto(Integer.parseInt(codigo_TipoProducto.getText().toString()));
        String registro;
        helper.abrir();
        TipoProducto tipoProducto = helper.consultarTipoProducto(tipoProductoConsulta);
        helper.cerrar();
        if(tipoProducto == null)
        {
            Toast.makeText(this,"Tipo de Producto no encontrado", Toast.LENGTH_SHORT).show();
        }
        else {
            nombre_TipoProducto.setText(tipoProducto.getNombre_TipoProducto());
        }

    }

    public void limpiarTexto(View v)
    {
        codigo_TipoProducto.setText("");
        nombre_TipoProducto.setText("");
    }
}