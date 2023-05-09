package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoLocalConsultarActivity extends Activity {

    ControlDB helper;
    EditText codigo_EncargadoLocal;
    EditText nombre_EncargadoLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_local_consultar);
        helper = new ControlDB(this);
        codigo_EncargadoLocal = (EditText) findViewById(R.id.codigo_EncargadoLocal);
        nombre_EncargadoLocal = (EditText) findViewById(R.id.nombre_EncargadoLocal);

    }

    public void ConsultarEncargadoLocal (View v){
        EncargadoLocal encargadoLocalConsulta = new EncargadoLocal();
        encargadoLocalConsulta.setId_EncargadoLocal(Integer.parseInt(codigo_EncargadoLocal.getText().toString()));
        helper.cerrar();
        EncargadoLocal encargadoLocal = helper.consultarEncargado(encargadoLocalConsulta);
        helper.cerrar();
        if(encargadoLocal == null){
            Toast.makeText(this, "Empleado no registrado", Toast.LENGTH_SHORT).show();
        }else{
            nombre_EncargadoLocal.setText(String.valueOf(encargadoLocal.getNombre_EncargadoLocal()));
        }
    }
    public void limpiarTexto(View v){
        codigo_EncargadoLocal.setText("");
        nombre_EncargadoLocal.setText("");
    }
}