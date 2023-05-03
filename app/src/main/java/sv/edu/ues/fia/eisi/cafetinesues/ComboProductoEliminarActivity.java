package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ComboProductoEliminarActivity extends AppCompatActivity {

    EditText editIdComboProducto;
    EditText editIdCombo;
    EditText editIdProducto;
    ControlDB helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_producto_eliminar);
        helper = new ControlDB(this);
        editIdComboProducto = findViewById(R.id.id_ComboProducto);
        editIdCombo = findViewById(R.id.id_Combo);
        editIdProducto = findViewById(R.id.id_Producto);
    }

    public void eliminarComboProducto(View v){
        String regEliminadas;
        ComboProducto comboProducto = new ComboProducto();
        comboProducto.setId_ComboProducto(Integer.parseInt(editIdComboProducto.getText().toString()));
        comboProducto.setId_Combo(Integer.parseInt(editIdCombo.getText().toString()));
        comboProducto.setId_Producto(Integer.parseInt(editIdProducto.getText().toString()));
        helper.abrir();
        regEliminadas = helper.eliminar(comboProducto);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoEliminarComboProducto(View v){
        editIdComboProducto.setText("");
        editIdCombo.setText("");
        editIdProducto.setText("");
    }
}