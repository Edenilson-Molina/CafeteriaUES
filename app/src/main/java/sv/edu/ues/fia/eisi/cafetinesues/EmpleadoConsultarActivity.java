package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmpleadoConsultarActivity extends Activity {
    ControlDB helper;
    EditText codigo_Empleado;
    EditText codigo_Local;
    EditText nombre_Empleado;
    EditText tipo_Empleado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_consultar);
        helper = new ControlDB(this);
        codigo_Empleado = (EditText) findViewById(R.id.codigo_Empleado);
        codigo_Local = (EditText) findViewById(R.id.codigo_Local);
        nombre_Empleado = (EditText) findViewById(R.id.nombre_Empleado);
        tipo_Empleado = (EditText) findViewById(R.id.tipo_Empleado);
    }
    public void consultarEmpleado(View v){
        Empleado empleadoConsulta = new Empleado();
        empleadoConsulta.setId_Empleado(Integer.parseInt(codigo_Empleado.getText().toString()));
        helper.cerrar();
        Empleado empleado = helper.consultarEmpleado(empleadoConsulta);
        helper.cerrar();
        if(empleado == null){
            Toast.makeText(this, "Empleado no registrado", Toast.LENGTH_SHORT).show();
        }else{
            codigo_Local.setText(String.valueOf(empleado.getId_Local()));
            nombre_Empleado.setText(String.valueOf(empleado.getNombre_Empleado()));
            tipo_Empleado.setText(String.valueOf(empleado.getTipo_Empleado()));
        }
    }
    public void limpiarTexto(View v){
        codigo_Empleado.setText("");
        codigo_Local.setText("");
        nombre_Empleado.setText("");
        tipo_Empleado.setText("");
    }
}