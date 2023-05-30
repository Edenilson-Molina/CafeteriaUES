package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PedidoInsertarActivity extends Activity {

    ControlDB helper;

    EditText id_Pedido;
    EditText id_cliente;
    EditText id_tipoPago;
    EditText id_local;
    EditText id_eventoEspecial;
    EditText tipo_Pedido;
    EditText estado_Pedido;
    EditText monto_Pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_insertar);
        helper = new ControlDB(this);
        id_Pedido = (EditText) findViewById(R.id.txtIdPedido);
        id_cliente = (EditText) findViewById(R.id.txtIdCliente);
        id_tipoPago = (EditText) findViewById(R.id.txtIdTipoPago);
        id_local = (EditText) findViewById(R.id.txtIdLocal);
        id_eventoEspecial = (EditText) findViewById(R.id.txtIdEventoEspecial);
        tipo_Pedido = (EditText) findViewById(R.id.txtTipoPedido);
        estado_Pedido = (EditText) findViewById(R.id.txtEstadoPedido);
        monto_Pedido = (EditText) findViewById(R.id.txtMontoPedido);
    }
    public void insertarPedido(View v){

        try{
            String regInsertados;
            String id_P = id_Pedido.getText().toString();
            String id_c = id_cliente.getText().toString();
            String id_t = id_tipoPago.getText().toString();
            String id_l = id_local.getText().toString();
            String id_e = id_eventoEspecial.getText().toString();
            String tipo_P = tipo_Pedido.getText().toString();
            String estado_P = estado_Pedido.getText().toString();
            String monto_P = monto_Pedido.getText().toString();

            Pedido pedido = new Pedido();

            pedido.setId_Pedido(Integer.parseInt(id_P));
            pedido.setId_cliente(Integer.parseInt(id_c));
            pedido.setId_tipoPago(Integer.parseInt(id_t));
            pedido.setId_local(Integer.parseInt(id_l));
            pedido.setId_eventoEspecial(Integer.parseInt(id_e));
            pedido.setTipo_Pedido(tipo_P);
            pedido.setEstado_Pedido(estado_P);
            pedido.setMonto_Pedido(Float.parseFloat(monto_P));

            helper.abrir();
            regInsertados = helper.insertar(pedido);
            helper.cerrar();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiarTextoPedidoInsertar(View v){
        id_Pedido.setText("");
        id_cliente.setText("");
        id_tipoPago.setText("");
        id_local.setText("");
        id_eventoEspecial.setText("");
        tipo_Pedido.setText("");
        estado_Pedido.setText("");
        monto_Pedido.setText("");
    }
}