package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PedidoEliminarActivity extends AppCompatActivity {
    ControlDB helper;

    EditText id_Pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_eliminar);
        helper = new ControlDB(this);
        id_Pedido = (EditText) findViewById(R.id.txtIdPedido);
    }
    public void eliminarCliente(View v)
    {
        Pedido pedido = new Pedido();
        try{
            pedido.setId_Pedido(Integer.parseInt(id_Pedido.getText().toString()));
            String registro;
            helper.abrir();
            registro = helper.eliminar(pedido);
            helper.cerrar();
            Toast.makeText(this,registro, Toast.LENGTH_SHORT).show();
        } catch(NumberFormatException ex){ // handle your exception
            Toast.makeText(this,"huy"+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}