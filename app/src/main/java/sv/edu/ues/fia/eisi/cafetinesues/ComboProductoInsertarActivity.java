package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class ComboProductoInsertarActivity extends AppCompatActivity {

    EditText editIdComboProducto;
    EditText editIdCombo;
    EditText editIdProducto;
    ControlDB helper;

    private final String urlLocal = "http://192.168.1.45/ws_comboproducto_insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_producto_insertar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlDB(this);
        editIdComboProducto = findViewById(R.id.id_ComboProducto);
        editIdCombo = findViewById(R.id.id_Combo);
        editIdProducto = findViewById(R.id.id_Producto);
    }

    public void insertarComboProducto(View v){

        try{
            String regInsertados;
            ComboProducto comboProducto = new ComboProducto();
            comboProducto.setId_ComboProducto(Integer.parseInt(editIdComboProducto.getText().toString()));
            comboProducto.setId_Combo(Integer.parseInt(editIdCombo.getText().toString()));
            comboProducto.setId_Producto(Integer.parseInt(editIdProducto.getText().toString()));

            helper.abrir();
            regInsertados = helper.insertar(comboProducto);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            Toast.makeText(this, "Error en los datos ingresados", Toast.LENGTH_SHORT).show();
        }

    }

    public void insertarComboProductoServicio(View v){
        String url = null;
        JSONObject datosComboProducto = new JSONObject();
        JSONObject comboProducto = new JSONObject();
        url = urlLocal+ "?idComboProducto=" + editIdComboProducto.getText().toString() + "&idCombo="
                + editIdCombo.getText().toString() + "&idProducto=" + editIdProducto.getText().toString();
        ControladorServicio.insertarExterno(url, this);
    }

    public void limpiarTextoInsertarComboProducto(View v){
        editIdComboProducto.setText("");
        editIdCombo.setText("");
        editIdProducto.setText("");
    }
}