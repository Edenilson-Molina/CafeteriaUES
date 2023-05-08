package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class ComboConsultarActivity extends AppCompatActivity {
    ControlDB helper;
    EditText editIdCombo;
    EditText editPrecioCombo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_consultar);
        helper = new ControlDB(this);
        editIdCombo = (EditText) findViewById(R.id.id_ComboConsulta);
        editPrecioCombo = (EditText) findViewById(R.id.precio_Combo);
    }

    public void consultarCombo(View v){
        helper.abrir();
        Combo combo = helper.consultarCombo(Integer.parseInt(editIdCombo.getText().toString()));
        helper.cerrar();
        if(combo == null)
            Toast.makeText(this, "Combo no registrado", Toast.LENGTH_LONG).show();
        else{
            editPrecioCombo.setText(String.valueOf(combo.getPrecio_Combo()));
            ocultarTeclado(v);
        }
    }

    public void limpiarTextoConsultarCombo(View v){
        editIdCombo.setText("");
        editPrecioCombo.setText("");
    }

    public void ocultarTeclado(View v)
    {
        if(v != null)
        {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
        }
    }
}