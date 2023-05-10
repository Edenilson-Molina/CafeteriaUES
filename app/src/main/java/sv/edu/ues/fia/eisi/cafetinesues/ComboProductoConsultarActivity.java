package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class ComboProductoConsultarActivity extends AppCompatActivity {

    ControlDB helper;
    EditText EditIdComboProducto;
    EditText EditIdCombo;
    EditText EditIdProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_producto_consultar);
        helper = new ControlDB(this);
        EditIdComboProducto = (EditText) findViewById(R.id.id_ComboProductoConsulta);
        EditIdCombo = (EditText) findViewById(R.id.id_Combo);
        EditIdProducto = (EditText) findViewById(R.id.id_Producto);
    }

    public void consultarComboProducto(View v){
        helper.abrir();
        ComboProducto comboProducto = helper.consultarComboProducto(Integer.parseInt(EditIdComboProducto.getText().toString()));
        helper.cerrar();
        if(comboProducto == null)
            Toast.makeText(this, "Combo Producto no registrado", Toast.LENGTH_LONG).show();
        else{
            EditIdCombo.setText(String.valueOf(comboProducto.getId_Combo()));
            EditIdProducto.setText(String.valueOf(comboProducto.getId_Producto()));
            ocultarTeclado(v);
        }
    }

    public void limpiarTextoConsultarComboProducto(View v){
        EditIdComboProducto.setText("");
        EditIdCombo.setText("");
        EditIdProducto.setText("");
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