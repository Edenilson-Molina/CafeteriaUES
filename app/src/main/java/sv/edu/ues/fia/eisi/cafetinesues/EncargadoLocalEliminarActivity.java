package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoLocalEliminarActivity extends Activity {

    ControlDB helper;
    EditText codigo_EncargadoLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_local_eliminar);
        helper = new ControlDB(this);
        codigo_EncargadoLocal = (EditText) findViewById(R.id.codigo_EncargadoLocal);
    }
    public void eliminarEncargadoLocal(View v){

        try{
            EncargadoLocal encargadoLocal = new EncargadoLocal();
            encargadoLocal.setId_EncargadoLocal(Integer.parseInt(codigo_EncargadoLocal.getText().toString()));
            String registro;
            helper.abrir();
            registro = helper.Eliminar(encargadoLocal);
            helper.cerrar();
            Toast.makeText(this, registro, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error en los datos", Toast.LENGTH_SHORT).show();
        }
    }
}