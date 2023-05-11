package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class DetallePedidoInsertarActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_detalle_pedido_insertar);
        helper = new ControlDB(this);
        EditId_DetallePedido = (EditText) findViewById(R.id.id_DetallePedido);
        EditId_Pedido = (EditText) findViewById(R.id.id_Pedido);
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

//    public void insertarDetallePedido(View v) {
//        String regInsertados;
//        String idDetallePedido = EditId_DetallePedido.getText().toString();
//        String idPedido = EditId_Pedido.getText().toString();
//        String idCombo = EditId_Combo.getText().toString();
//        String idProducto = EditId_Producto.getText().toString();
//        String cantidadProducto = EditCantidad_Producto.getText().toString();
//        String subtotal = EditSubtotal.getText().toString();
//        DetallePedido detallePedido = new DetallePedido();
//
//        // VERIFICAMOS SI SE INGRESÓ AL MENOS UN Combo - O UN -  Producto al DetallePedido
//        if (idCombo.equals("0") && idProducto.equals("0") || TextUtils.isEmpty(idCombo) && TextUtils.isEmpty(idProducto)){
//            Toast.makeText(this, "Debe ingresar un Combo -o un- Producto", Toast.LENGTH_SHORT).show();
//        } else {
//            try {
//                detallePedido.setId_DetallePedido(Integer.parseInt(idDetallePedido));
//                detallePedido.setId_Pedido(Integer.parseInt(idPedido));
//                detallePedido.setCantidad_Producto(Integer.parseInt(cantidadProducto));
//                detallePedido.setSubtotal(Float.parseFloat(subtotal));
//                detallePedido.setId_Producto(Integer.parseInt(idProducto));
//                detallePedido.setId_Combo(Integer.parseInt(idCombo));
//            } catch (Exception e) {
//                e.printStackTrace();
//                Toast.makeText(this, "Error en los datos ingresados", Toast.LENGTH_SHORT).show();
//            }
//            helper.abrir();
//            String estado = helper.insertar(detallePedido);
//            helper.cerrar();
//            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
//        }
//    }

    public void insertarDetallePedido(View v) {
        String regInsertados;
        String idDetallePedido = EditId_DetallePedido.getText().toString();
        String idPedido = EditId_Pedido.getText().toString();
        //String idCombo = EditId_Combo.getText().toString();
        //String idProducto = EditId_Producto.getText().toString();

        String id_ProdOrCom = ID_ProdOrCombo.getText().toString();
        boolean isCombo = radio_seleccion.getCheckedRadioButtonId() == R.id.radio_combo;

        String cantidadProducto = EditCantidad_Producto.getText().toString();
        String subtotal = EditSubtotal.getText().toString();
        DetallePedido detallePedido = new DetallePedido();

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
        String estado = helper.insertar(detallePedido);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }


    public void limpiarTextoInsertarDetallePedido(View v) {
        EditId_DetallePedido.getText().clear();
        EditId_Pedido.getText().clear();
        ID_ProdOrCombo.getText().clear();
        EditCantidad_Producto.getText().clear();
        EditSubtotal.getText().clear();
    }
}