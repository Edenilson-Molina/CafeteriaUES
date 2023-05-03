package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ComboActualizarActivity extends AppCompatActivity {

    ControlDB helper;
    EditText editIdCombo;
    EditText editPrecioCombo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_actualizar);
        helper = new ControlDB(this);
        editIdCombo = (EditText) findViewById(R.id.id_Combo);
        editPrecioCombo = (EditText) findViewById(R.id.precio_Combo);
    }

    public void actualizarCombo(View v){
        Combo combo = new Combo();
        combo.setId_Combo(Integer.parseInt(editIdCombo.getText().toString()));
        combo.setPrecio_Combo(Float.parseFloat(editPrecioCombo.getText().toString()));
        helper.abrir();
        String estado = helper.actualizar(combo);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoActualizarCombo(View v){
        editIdCombo.setText("");
        editPrecioCombo.setText("");
    }
}