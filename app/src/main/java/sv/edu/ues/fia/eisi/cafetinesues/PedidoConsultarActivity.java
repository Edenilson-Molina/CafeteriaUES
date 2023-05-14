package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PedidoConsultarActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_pedido_consultar);
        helper = new ControlDB(this);
        id_Pedido = (EditText) findViewById(R.id.txtIdPedido);
        id_cliente = (EditText) findViewById(R.id.txtIdCliente);
        id_tipoPago = (EditText) findViewById(R.id.txtIdTipoPago);
        id_local = (EditText) findViewById(R.id.txtIdLocal);
        id_eventoEspecial = (EditText) findViewById(R.id.txtIdEventoEspecial);
        tipo_Pedido = (EditText) findViewById(R.id.txtIdTipoPedido);
        estado_Pedido = (EditText) findViewById(R.id.txtEstadoPedido);
        monto_Pedido = (EditText) findViewById(R.id.txtMontoPedido);
    }
    public void consultarPedido(View v){
        Pedido pedido = new Pedido();
        pedido.setId_Pedido(Integer.parseInt(id_Pedido.getText().toString()));
        helper.abrir();
        Pedido pedi =
                helper.consultar(pedido);
        helper.cerrar();
        if(pedi == null)
            Toast.makeText(this, "Pedido con ID " +
                    id_Pedido.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            id_cliente.setText(pedi.getId_cliente());
            id_tipoPago.setText(pedi.getId_tipoPago());
            id_local.setText(pedi.getId_local());
            id_eventoEspecial.setText(pedi.getId_eventoEspecial());
            tipo_Pedido.setText(pedi.getId_Pedido());
            estado_Pedido.setText(pedi.getEstado_Pedido());
            monto_Pedido.setText(String.valueOf(pedi.getMonto_Pedido()));
        }

    }
    public void limpiarTextoPedidoConsultar(View v){
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