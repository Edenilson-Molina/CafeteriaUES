package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteConsultarActivity extends AppCompatActivity {
    ControlDB helper;

    EditText id_cliente;
    EditText nombre_cliente;
    EditText apellidos_cliente;
    EditText fecha_nacimiento;
    EditText id_ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_consultar);
        helper = new ControlDB(this);
        id_cliente = (EditText) findViewById(R.id.txtIdCliente);
        nombre_cliente = (EditText) findViewById(R.id.textInputNombre);
        apellidos_cliente = (EditText) findViewById(R.id.textInputApellido);
        fecha_nacimiento = (EditText) findViewById(R.id.textInputFechaNacimiento);
        id_ubicacion = (EditText) findViewById(R.id.txtidubicacion);
    }

    public void consultarCliente(View v){
        Cliente cliente = new Cliente();
        cliente.setId_cliente(Integer.parseInt(id_cliente.getText().toString()));
        helper.abrir();
        Cliente cli =
                helper.consultarCliente(cliente);
        helper.cerrar();
        if(cli == null)
            Toast.makeText(this, "Tipo de pago con ID " +
                    id_cliente.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            nombre_cliente.setText(cli.getNombres());
            apellidos_cliente.setText(cli.getApellidos());
            fecha_nacimiento.setText(cli.getFecha_nacimiento());
            id_ubicacion.setText(String.valueOf(cli.getId_ubicacion()));

        }

    }

    public void limpiarTextoConsultarCliente(View v){
        nombre_cliente.setText("");
        apellidos_cliente.setText("");
        fecha_nacimiento.setText("");
        id_ubicacion.setText("");

    }
}