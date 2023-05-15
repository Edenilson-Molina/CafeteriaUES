package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteInsertarActivity extends Activity {

    ControlDB helper;

    EditText id_cliente;
    EditText nombre_cliente;
    EditText apellidos_cliente;
    EditText fecha_nacimiento;
    EditText id_ubicacion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_insertar);
        helper = new ControlDB(this);
        id_cliente = (EditText) findViewById(R.id.txtIdCliente);
        nombre_cliente = (EditText) findViewById(R.id.textInputNombre);
        apellidos_cliente = (EditText) findViewById(R.id.textInputApellido);
        fecha_nacimiento = (EditText) findViewById(R.id.textInputFechaNacimiento);
        id_ubicacion = (EditText) findViewById(R.id.txtidubicacion);

    }

    public void insertarCliente(View v){

        try{
            String id_cli = id_cliente.getText().toString();
            String n_cli = nombre_cliente.getText().toString();
            String a_cli = apellidos_cliente.getText().toString();
            String f_cli = fecha_nacimiento.getText().toString();
            String id_ubi = id_ubicacion.getText().toString();
            String regInsertados;

            Cliente cliente = new Cliente();
            cliente.setId_cliente(Integer.parseInt(id_cli));
            cliente.setNombres(n_cli);
            cliente.setApellidos(a_cli);
            cliente.setFecha_nacimiento(f_cli);
            cliente.setId_ubicacion(Integer.parseInt(id_ubi));
            helper.abrir();
            regInsertados=helper.insertar(cliente);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();
        }

    }

    public void limpiarTextoClienteInsertar(View v)
    {
        id_cliente.setText("");
        nombre_cliente.setText("");
        apellidos_cliente.setText("");
        fecha_nacimiento.setText("");
        id_ubicacion.setText("");
    }
}