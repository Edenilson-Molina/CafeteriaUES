package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmpleadoEliminarActivity extends Activity {

    ControlDB helper;
    EditText codigo_Empleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_eliminar);
        helper = new ControlDB(this);
        codigo_Empleado = (EditText) findViewById(R.id.codigo_Empleado);
    }

    public void eliminarEmpleado(View v){

        try{
            Empleado empleado = new Empleado();
            empleado.setId_Empleado(Integer.parseInt(codigo_Empleado.getText().toString()));
            String registro;
            helper.abrir();
            registro = helper.Eliminar(empleado);
            helper.cerrar();
            Toast.makeText(this, registro, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();
        }

    }
}