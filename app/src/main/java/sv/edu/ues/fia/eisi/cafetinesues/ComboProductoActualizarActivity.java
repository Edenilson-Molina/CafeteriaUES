package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.controls.Control;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ComboProductoActualizarActivity extends AppCompatActivity {

    ControlDB helper;
    EditText editIdComboProducto;
    EditText editIdCombo;
    EditText editIdProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_producto_actualizar);
        helper = new ControlDB(this);
        editIdComboProducto = (EditText) findViewById(R.id.id_ComboProducto);
        editIdCombo = (EditText) findViewById(R.id.id_Combo);
        editIdProducto = (EditText) findViewById(R.id.id_Producto);
    }

    public void actualizarComboProducto(View v){
        ComboProducto comboProducto = new ComboProducto();
        comboProducto.setId_ComboProducto(Integer.parseInt(editIdComboProducto.getText().toString()));
        comboProducto.setId_Combo(Integer.parseInt(editIdCombo.getText().toString()));
        comboProducto.setId_Producto(Integer.parseInt(editIdProducto.getText().toString()));
        helper.abrir();
        String estado = helper.actualizar(comboProducto);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoActualizarComboProducto(View v){
        editIdComboProducto.setText("");
        editIdCombo.setText("");
        editIdProducto.setText("");
    }
}