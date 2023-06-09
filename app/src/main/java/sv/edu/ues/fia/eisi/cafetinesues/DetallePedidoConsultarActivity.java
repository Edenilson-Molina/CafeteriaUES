package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DetallePedidoConsultarActivity extends AppCompatActivity {

    ControlDB helper;
    EditText EditId_DetallePedido;
    EditText EditId_Pedido;
    EditText EditId_Combo;
    EditText EditId_Producto;
    EditText EditCantidad_Producto;
    EditText EditSubtotal;


    // EXTRAS
    EditText ID_ProdOrCombo;
    RadioButton radio_combo;
    RadioButton radio_producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_consultar);
        helper = new ControlDB(this);
        EditId_DetallePedido = (EditText) findViewById(R.id.id_DetallePedido);
        EditId_Pedido = (EditText) findViewById(R.id.id_Pedido);

        // EXTRAS
        ID_ProdOrCombo = (EditText) findViewById(R.id.id_ProductoOrCombo);
        radio_combo = (RadioButton) findViewById(R.id.radio_combo);
        radio_producto = (RadioButton) findViewById(R.id.radio_producto);

        EditCantidad_Producto = (EditText) findViewById(R.id.cantidad_Producto);
        EditSubtotal = (EditText) findViewById(R.id.subtotal);
    }

    public void consultarDetallePedido(View v){
        helper.abrir();
        DetallePedido detallePedido = null;
        try{
            detallePedido = helper.consultarDetallePedido(Integer.parseInt(EditId_DetallePedido.getText().toString()));

        } catch (Exception e){
            Toast.makeText(this, "Ingrese un ID de Detalle Pedido", Toast.LENGTH_LONG).show();
        }
        helper.cerrar();
        if(detallePedido == null)
            Toast.makeText(this, "Detalle Pedido no registrado", Toast.LENGTH_LONG).show();
        else{
            EditId_Pedido.setText(String.valueOf(detallePedido.getId_Pedido()));

            if(detallePedido.getId_Combo() != 0){
                ID_ProdOrCombo.setText(String.valueOf(detallePedido.getId_Combo()));
                radio_combo.setChecked(true);
            }else{
                ID_ProdOrCombo.setText(String.valueOf(detallePedido.getId_Producto()));
                radio_producto.setChecked(true);
            }

            EditCantidad_Producto.setText(String.valueOf(detallePedido.getCantidad_Producto()));
            EditSubtotal.setText(String.valueOf(detallePedido.getSubtotal()));
            ocultarTeclado(v);
        }
    }

    public void limpiarTextoConsultarDetallePedido(View v){
        EditId_DetallePedido.setText("");
        EditId_Pedido.setText("");
        ID_ProdOrCombo.setText("");
        EditCantidad_Producto.setText("");
        EditSubtotal.setText("");
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