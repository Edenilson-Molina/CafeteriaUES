package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LocalEliminarActivity extends Activity {

    ControlDB helper;
    EditText codigo_Local;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_eliminar);
        helper =new  ControlDB(this);
        codigo_Local = (EditText) findViewById(R.id.codigo_Local);
    }
    public void eliminarLocal(View v){
        Local local = new Local();
        local.setId_Local(Integer.parseInt(codigo_Local.getText().toString()));
        String registro;
        helper.abrir();
        registro = helper.Eliminar(local);
        helper.cerrar();
        Toast.makeText(this, registro, Toast.LENGTH_SHORT).show();
    }
}