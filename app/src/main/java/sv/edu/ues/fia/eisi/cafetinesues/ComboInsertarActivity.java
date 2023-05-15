package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ComboInsertarActivity extends AppCompatActivity {
    ControlDB helper;
    EditText EditId_Combo;
    EditText EditPrecio_Combo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_insertar);
        helper = new ControlDB(this);
        EditId_Combo = (EditText) findViewById(R.id.id_Combo);
        EditPrecio_Combo = (EditText) findViewById(R.id.precio_Combo);
    }


    public void insertarCombo(View v) {

            try {
                String id_Combo=EditId_Combo.getText().toString();
                String precio_Combo=EditPrecio_Combo.getText().toString();
                String regInsertados;
                Combo combo=new Combo();

                combo.setPrecio_Combo(Float.parseFloat(precio_Combo));
                combo.setId_Combo(Integer.parseInt(id_Combo));

                helper.abrir();
                regInsertados=helper.insertar(combo);
                helper.cerrar();
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Tipo de dato incorrecto", Toast.LENGTH_SHORT).show();
            }


    }

    public void limpiarTextoInsertarCombo(View v) {
        EditId_Combo.setText("");
        EditPrecio_Combo.setText("");
    }
}