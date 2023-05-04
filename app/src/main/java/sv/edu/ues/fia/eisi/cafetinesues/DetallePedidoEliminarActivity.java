package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetallePedidoEliminarActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_detalle_pedido_eliminar);
        helper = new ControlDB(this);
        EditId_DetallePedido = (EditText) findViewById(R.id.id_DetallePedido);
        EditId_Pedido = (EditText) findViewById(R.id.id_Pedido);
        EditId_Combo = (EditText) findViewById(R.id.id_Combo);
        EditId_Producto = (EditText) findViewById(R.id.id_Producto);
        EditCantidad_Producto = (EditText) findViewById(R.id.cantidad_Producto);
        EditSubtotal = (EditText) findViewById(R.id.subtotal);
    }

    public void eliminarDetallePedido(View v) {
        String regEliminadas;
        DetallePedido detallePedido = new DetallePedido();
        try {
            detallePedido.setId_DetallePedido(Integer.parseInt(EditId_DetallePedido.getText().toString()));
            //detallePedido.setId_Pedido(Integer.parseInt(EditId_Pedido.getText().toString()));
            //detallePedido.setId_Combo(Integer.parseInt(EditId_Combo.getText().toString()));
            //detallePedido.setId_Producto(Integer.parseInt(EditId_Producto.getText().toString()));
            //detallePedido.setCantidad_Producto(Integer.parseInt(EditCantidad_Producto.getText().toString()));
            //detallePedido.setSubtotal(Float.parseFloat(EditSubtotal.getText().toString()));
        } catch (Exception e) {
            Toast.makeText(this, "Error al eliminar el detalle del pedido", Toast.LENGTH_SHORT).show();
        }
        helper.abrir();
        regEliminadas = helper.eliminar(detallePedido);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoEliminarDetallePedido(View v) {
        EditId_DetallePedido.setText("");
        EditId_Pedido.setText("");
        EditId_Combo.setText("");
        EditId_Producto.setText("");
        EditCantidad_Producto.setText("");
        EditSubtotal.setText("");
    }
}