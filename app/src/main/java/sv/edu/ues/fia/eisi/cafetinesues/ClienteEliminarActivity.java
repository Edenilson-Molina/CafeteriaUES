package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteEliminarActivity extends AppCompatActivity {

    ControlDB helper;
    EditText id_cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_eliminar);
        helper = new ControlDB(this);
        id_cliente = (EditText) findViewById(R.id.txtIdCliente);
    }

    public void eliminarCliente(View v)
    {
        Cliente cliente = new Cliente();
        try{
            cliente.setId_cliente(Integer.parseInt(id_cliente.getText().toString()));
            String registro;
            helper.abrir();
            registro = helper.eliminar(cliente);
            helper.cerrar();
            Toast.makeText(this,registro, Toast.LENGTH_SHORT).show();
        } catch(NumberFormatException ex){ // handle your exception
            Toast.makeText(this,"huy"+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    public  void limpiarTextoEliminarCliente(View v){
        id_cliente.setText("");
    }
}