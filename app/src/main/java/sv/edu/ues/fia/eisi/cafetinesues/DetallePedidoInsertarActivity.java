package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetallePedidoInsertarActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_detalle_pedido_insertar);
        helper = new ControlDB(this);
        EditId_DetallePedido = (EditText) findViewById(R.id.id_DetallePedido);
        EditId_Pedido = (EditText) findViewById(R.id.id_Pedido);
        EditId_Combo = (EditText) findViewById(R.id.id_Combo);
        EditId_Producto = (EditText) findViewById(R.id.id_Producto);
        EditCantidad_Producto = (EditText) findViewById(R.id.cantidad_Producto);
        EditSubtotal = (EditText) findViewById(R.id.subtotal);


        //
        //
        // PARA CADA DETALLE_PEDIDO, SOLO SE DEBE ESPECIFICAR UNO DE LOS DOS CAMPOS: Combo o Producto
        // JUNTO CON LA CANTIDAD QUE ES ESPECIFIQUE PARA UNO DE LOS DOS
        // SI SE ESPECIFICA UNO, EL OTRO DEBE QUEDAR VACÍO o CON VALOR 0
        //
        //

        EditId_Combo.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                EditId_Producto.setText("0");
            }
        });

        EditId_Producto.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                EditId_Combo.setText("0");
            }
        });

    }

    public void insertarDetallePedido(View v) {
        String regInsertados;
        String idDetallePedido = EditId_DetallePedido.getText().toString();
        String idPedido = EditId_Pedido.getText().toString();
        String idCombo = EditId_Combo.getText().toString();
        String idProducto = EditId_Producto.getText().toString();
        String cantidadProducto = EditCantidad_Producto.getText().toString();
        String subtotal = EditSubtotal.getText().toString();
        DetallePedido detallePedido = new DetallePedido();

        // VERIFICAMOS SI SE INGRESÓ AL MENOS UN Combo - O UN -  Producto al DetallePedido
        if (idCombo.equals("0") && idProducto.equals("0") || TextUtils.isEmpty(idCombo) && TextUtils.isEmpty(idProducto)){
            Toast.makeText(this, "Debe ingresar un Combo -o un- Producto", Toast.LENGTH_SHORT).show();
        } else {
            try {
                detallePedido.setId_DetallePedido(Integer.parseInt(idDetallePedido));
                detallePedido.setId_Pedido(Integer.parseInt(idPedido));
                detallePedido.setCantidad_Producto(Integer.parseInt(cantidadProducto));
                detallePedido.setSubtotal(Float.parseFloat(subtotal));
                detallePedido.setId_Producto(Integer.parseInt(idProducto));
                detallePedido.setId_Combo(Integer.parseInt(idCombo));
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error en los datos ingresados", Toast.LENGTH_SHORT).show();
            }
            helper.abrir();
            String estado = helper.insertar(detallePedido);
            helper.cerrar();
            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }
    }


    public void limpiarTextoInsertarDetallePedido(View v) {
        EditId_DetallePedido.getText().clear();
        EditId_Pedido.getText().clear();
        EditId_Combo.getText().clear();
        EditId_Producto.getText().clear();
        EditCantidad_Producto.getText().clear();
        EditSubtotal.getText().clear();
    }
}