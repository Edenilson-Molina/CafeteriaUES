package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmpleadoInsertarActivity extends Activity {
    ControlDB helper;
    EditText codigo_Empleado;
    EditText codigo_Local;
    EditText nombre_Empleado;
    EditText tipo_Empleado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_insertar);
        helper = new ControlDB(this);
        codigo_Empleado = (EditText) findViewById(R.id.codigo_Empleado);
        codigo_Local = (EditText) findViewById(R.id.codigo_Local);
        nombre_Empleado = (EditText) findViewById(R.id.nombre_Empleado);
        tipo_Empleado = (EditText) findViewById(R.id.tipo_Empleado);
    }
    public void insertarEmpleado(View v){

        try{
            String id_Empleado = codigo_Empleado.getText().toString();
            String id_Local = codigo_Local.getText().toString();
            String nom_Empleado = nombre_Empleado.getText().toString();
            String tip_Empleado = tipo_Empleado.getText().toString();
            String regInsertados;

            Empleado empleado = new Empleado();
            empleado.setId_Empleado(Integer.parseInt(id_Empleado));
            empleado.setId_Local(Integer.parseInt(id_Local));
            empleado.setNombre_Empleado(nom_Empleado);
            empleado.setTipo_Empleado(tip_Empleado);
            helper.abrir();
            regInsertados = helper.Insertar(empleado);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();}
        catch (Exception e)
        {
            Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v){
        codigo_Empleado.setText("");
        codigo_Local.setText("");
        nombre_Empleado.setText("");
        tipo_Empleado.setText("");
    }
}