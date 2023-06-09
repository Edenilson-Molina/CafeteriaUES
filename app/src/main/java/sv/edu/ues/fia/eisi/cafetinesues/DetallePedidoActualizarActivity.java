package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DetallePedidoActualizarActivity extends AppCompatActivity {
    ControlDB helper;
    EditText EditId_DetallePedido;
    EditText EditId_Pedido;
    //EditText EditId_Combo;
    //EditText EditId_Producto;
    EditText EditCantidad_Producto;
    EditText EditSubtotal;


    // EXTRAS
    EditText ID_ProdOrCombo;
    RadioGroup radio_seleccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_actualizar);
        helper = new ControlDB(this);
        EditId_DetallePedido = (EditText) findViewById(R.id.id_DetallePedido);
        EditId_Pedido = (EditText) findViewById(R.id.id_Pedido);
        //EditId_Combo = (EditText) findViewById(R.id.id_Combo);
        //EditId_Producto = (EditText) findViewById(R.id.id_ProductoOrCombo);

        // EXTRAS
        ID_ProdOrCombo = (EditText) findViewById(R.id.id_ProductoOrCombo);
        radio_seleccion = (RadioGroup) findViewById(R.id.radio_group);

        EditCantidad_Producto = (EditText) findViewById(R.id.cantidad_Producto);
        EditSubtotal = (EditText) findViewById(R.id.subtotal);


        //
        //
        // PARA CADA DETALLE_PEDIDO, SOLO SE DEBE ESPECIFICAR UNO DE LOS DOS CAMPOS: Combo o Producto
        // JUNTO CON LA CANTIDAD QUE ES ESPECIFIQUE PARA UNO DE LOS DOS
        // SI SE ESPECIFICA UNO, EL OTRO DEBE QUEDAR VACÍO o CON VALOR 0
        //
        //

//        EditId_Combo.setOnFocusChangeListener((view, hasFocus) -> {
//            if (hasFocus) {
//                EditId_Producto.setText("0");
//            }
//        });
//
//        EditId_Producto.setOnFocusChangeListener((view, hasFocus) -> {
//            if (hasFocus) {
//                EditId_Combo.setText("0");
//            }
//        });
    }

//    public void actualizarDetallePedido(View v) {
//        DetallePedido detallePedido = new DetallePedido();
//        String idDetallePedido = EditId_DetallePedido.getText().toString();
//        String idPedido = EditId_Pedido.getText().toString();
//        String idCombo = EditId_Combo.getText().toString();
//        String idProducto = EditId_Producto.getText().toString();
//        String cantidadProducto = EditCantidad_Producto.getText().toString();
//        String subtotal = EditSubtotal.getText().toString();
//
//        // VERIFICAMOS SI SE INGRESÓ AL MENOS UN Combo - O UN -  Producto al DetallePedido
//        if (TextUtils.isEmpty(idCombo) && TextUtils.isEmpty(idProducto)) {
//            Toast.makeText(this, "Debe ingresar un Combo -o un- Producto", Toast.LENGTH_SHORT).show();
//        } else {
//            // EVITAMOS SETEAR EL CAMPO QUE NO SE INGRESÓ (YA SEA Combo o Producto)
//            if (TextUtils.isEmpty(idCombo)) {
//                try {
//                    detallePedido.setId_DetallePedido(Integer.parseInt(idDetallePedido));
//                    detallePedido.setId_Pedido(Integer.parseInt(idPedido));
//                    detallePedido.setCantidad_Producto(Integer.parseInt(cantidadProducto));
//                    detallePedido.setSubtotal(Float.parseFloat(subtotal));
//                    detallePedido.setId_Producto(Integer.parseInt(idProducto));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(this, "Error en los datos ingresados", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                try {
//                    detallePedido.setId_DetallePedido(Integer.parseInt(idDetallePedido));
//                    detallePedido.setId_Pedido(Integer.parseInt(idPedido));
//                    detallePedido.setCantidad_Producto(Integer.parseInt(cantidadProducto));
//                    detallePedido.setSubtotal(Float.parseFloat(subtotal));
//                    detallePedido.setId_Combo(Integer.parseInt(idCombo));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(this, "Error en los datos ingresados", Toast.LENGTH_SHORT).show();
//                }
//            }
//            helper.abrir();
//            String estado = helper.actualizar(detallePedido);
//            helper.cerrar();
//            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
//        }
//    }

    public void actualizarDetallePedido(View v) {
        DetallePedido detallePedido = new DetallePedido();
        String idDetallePedido = EditId_DetallePedido.getText().toString();
        String idPedido = EditId_Pedido.getText().toString();

        // EXTRAS
        String id_ProdOrCom = ID_ProdOrCombo.getText().toString();
        boolean isCombo = radio_seleccion.getCheckedRadioButtonId() == R.id.radio_combo;
        Log.i("isCombo", String.valueOf(isCombo));

        String cantidadProducto = EditCantidad_Producto.getText().toString();
        String subtotal = EditSubtotal.getText().toString();

        try{
            detallePedido.setId_DetallePedido(Integer.parseInt(idDetallePedido));
            detallePedido.setId_Pedido(Integer.parseInt(idPedido));
            detallePedido.setCantidad_Producto(Integer.parseInt(cantidadProducto));
            detallePedido.setSubtotal(Float.parseFloat(subtotal));
            if (isCombo) {
                detallePedido.setId_Combo(Integer.parseInt(id_ProdOrCom));
                detallePedido.setId_Producto(0);
            } else {
                detallePedido.setId_Producto(Integer.parseInt(id_ProdOrCom));
                detallePedido.setId_Combo(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error en los datos ingresados", Toast.LENGTH_SHORT).show();
        }

        helper.abrir();
        String estado = helper.actualizar(detallePedido);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoActualizarDetallePedido(View v) {
        EditId_DetallePedido.setText("");
        EditId_Pedido.setText("");
        //EditId_Combo.setText("");
        //EditId_Producto.setText("");
        ID_ProdOrCombo.setText("");
        EditCantidad_Producto.setText("");
        EditSubtotal.setText("");
    }
}