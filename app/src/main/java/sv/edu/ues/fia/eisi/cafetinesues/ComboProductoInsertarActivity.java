package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.database.ContentObservable;
import android.os.Bundle;
import android.service.controls.Control;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ComboProductoInsertarActivity extends AppCompatActivity {

    EditText editIdComboProducto;
    EditText editIdCombo;
    EditText editIdProducto;
    ControlDB helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_producto_insertar);
        helper = new ControlDB(this);
        editIdComboProducto = findViewById(R.id.id_ComboProducto);
        editIdCombo = findViewById(R.id.id_Combo);
        editIdProducto = findViewById(R.id.id_Producto);
    }

    public void insertarComboProducto(View v){
        String regInsertados;
        ComboProducto comboProducto = new ComboProducto();
        comboProducto.setId_ComboProducto(Integer.parseInt(editIdComboProducto.getText().toString()));
        comboProducto.setId_Combo(Integer.parseInt(editIdCombo.getText().toString()));
        comboProducto.setId_Producto(Integer.parseInt(editIdProducto.getText().toString()));
        helper.abrir();
        regInsertados = helper.insertar(comboProducto);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoInsertarComboProducto(View v){
        editIdComboProducto.setText("");
        editIdCombo.setText("");
        editIdProducto.setText("");
    }
}