package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    ControlDB helper;
    EditText idUsuario;
    EditText claveUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper = new ControlDB(this);
        helper.abrir();
        helper.llenarBD();
        helper.llenarBD();
        idUsuario = (EditText) findViewById(R.id.id_Usuario);
        claveUsuario = (EditText) findViewById(R.id.clave_Usuario);
    }


    public void iniciarSesion(View v)
    {
        String usuario = idUsuario.getText().toString();
        String clave = claveUsuario.getText().toString();
        helper.abrir();
        boolean acceso = helper.iniciarSesion(usuario, clave);
        helper.cerrar();
        if(acceso)
        {
            Bundle envioDatos = new Bundle();
            envioDatos.putString("Username",usuario);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtras(envioDatos);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiar(View v)
    {
        idUsuario.setText("");
        claveUsuario.setText("");
    }
}