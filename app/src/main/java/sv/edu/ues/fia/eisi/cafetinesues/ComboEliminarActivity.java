package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ComboEliminarActivity extends AppCompatActivity {
    EditText editIdCombo;
    //EditText editPrecioCombo;
    ControlDB helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_eliminar);
        helper = new ControlDB(this);
        editIdCombo = (EditText) findViewById(R.id.id_Combo);
        //editPrecioCombo = (EditText) findViewById(R.id.precio_Combo);
    }

    public void eliminarCombo(View v){
        String regEliminadas;
        Combo combo = new Combo();
        try{
            combo.setId_Combo(Integer.parseInt(editIdCombo.getText().toString()));
            //combo.setPrecio_Combo(Float.parseFloat(editPrecioCombo.getText().toString()));
        }catch (Exception e){
            Toast.makeText(this, "Error en los datos ingresados", Toast.LENGTH_SHORT).show();
        }
        helper.abrir();
        regEliminadas = helper.eliminar(combo);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoEliminarCombo(View v){
        editIdCombo.setText("");
        //editPrecioCombo.setText("");
    }
}