package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetallePedidoConsultarActivity extends AppCompatActivity {

    ControlDB helper;
    EditText EditId_DetallePedido;
    EditText EditId_Pedido;
    EditText EditId_Combo;
    EditText EditId_Producto;
    EditText EditCantidad_Producto;
    EditText EditSubtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_consultar);
        helper = new ControlDB(this);
        EditId_DetallePedido = (EditText) findViewById(R.id.id_DetallePedido);
        EditId_Pedido = (EditText) findViewById(R.id.id_Pedido);
        EditId_Combo = (EditText) findViewById(R.id.id_Combo);
        EditId_Producto = (EditText) findViewById(R.id.id_Producto);
        EditCantidad_Producto = (EditText) findViewById(R.id.cantidad_Producto);
        EditSubtotal = (EditText) findViewById(R.id.subtotal);
    }

    public void consultarDetallePedido(View v){
        helper.abrir();
        DetallePedido detallePedido = helper.consultarDetallePedido(Integer.parseInt(EditId_DetallePedido.getText().toString()));
        helper.cerrar();
        if(detallePedido == null)
            Toast.makeText(this, "Detalle Pedido no registrado", Toast.LENGTH_LONG).show();
        else{
            EditId_Pedido.setText(String.valueOf(detallePedido.getId_Pedido()));
            EditId_Combo.setText(String.valueOf(detallePedido.getId_Combo()));
            EditId_Producto.setText(String.valueOf(detallePedido.getId_Producto()));
            EditCantidad_Producto.setText(String.valueOf(detallePedido.getCantidad_Producto()));
            EditSubtotal.setText(String.valueOf(detallePedido.getSubtotal()));
        }
    }

    public void limpiarTextoConsultarDetallePedido(View v){
        EditId_DetallePedido.setText("");
        EditId_Pedido.setText("");
        EditId_Combo.setText("");
        EditId_Producto.setText("");
        EditCantidad_Producto.setText("");
        EditSubtotal.setText("");
    }
}