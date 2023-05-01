package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ComboInsertarActivity extends AppCompatActivity {

    ControlDB helper;
    EditText id_Combo;
    EditText precio_Combo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_insertar);
        helper = new ControlDB(this);
        id_Combo = (EditText) findViewById(R.id.id_Combo);
        precio_Combo = (EditText) findViewById(R.id.precio_Combo);
    }

    public void insertarCombo(View v) {
        String id_Combo=id_Combo.getText().toString();
        String precio_Combo=precio_Combo.getText().toString();
        String regInsertados;
        Combo combo=new Combo();
        combo.setId_Combo(id_Combo);
        combo.setPrecio_Combo(precio_Combo);
        helper.abrir();
        regInsertados=helper.insertar(combo);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoCombo(View v) {
        id_Combo.setText("");
        precio_Combo.setText("");
    }
}