package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmpleadoActualizarActivity extends Activity {
    ControlDB helper;
    EditText codigo_Empleado;
    EditText codigo_Local;
    EditText nombre_Empleado;
    EditText tipo_Empleado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_actualizar);
        helper = new ControlDB(this);
        codigo_Empleado = (EditText) findViewById(R.id.codigo_Empleado);
        codigo_Local = (EditText) findViewById(R.id.codigo_Local);
        nombre_Empleado = (EditText) findViewById(R.id.nombre_Empleado);
        tipo_Empleado = (EditText) findViewById(R.id.tipo_Empleado);
    }
    public void actualizarEmpleado(View v){
        Empleado empleado = new Empleado();
        String id_Empleado = codigo_Empleado.getText().toString();
        String id_Local = codigo_Local.getText().toString();
        String nom_Empleado = nombre_Empleado.getText().toString();
        String tip_Empleado = tipo_Empleado.getText().toString();
        String registro;
        empleado.setId_Empleado(Integer.parseInt(id_Empleado));
        empleado.setId_Local(Integer.parseInt(id_Local));
        empleado.setNombre_Empleado(nom_Empleado);
        empleado.setTipo_Empleado(tip_Empleado);
        helper.abrir();
        registro = helper.Actualizar(empleado);
        helper.cerrar();
        Toast.makeText(this, registro, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        codigo_Empleado.setText("");
        codigo_Local.setText("");
        nombre_Empleado.setText("");
        tipo_Empleado.setText("");
    }
}