package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoPagoEliminarActivity extends Activity {

    ControlDB helper;
    EditText id_tipoPago;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_pago_eliminar);
        helper = new ControlDB(this);
        id_tipoPago = (EditText) findViewById(R.id.txtIdTipoPago);
    }
    public void eliminarTipoPago(View v)
    {
        try{
            TipoPago tipopago = new TipoPago();
            tipopago.setId_TipoPago(Integer.parseInt(id_tipoPago.getText().toString()));
            String registro;
            helper.abrir();
            registro = helper.eliminar(tipopago);
            helper.cerrar();
            Toast.makeText(this,registro, Toast.LENGTH_SHORT).show();

        }catch (NumberFormatException ex){
            Toast.makeText(this,"Escriba un ID valido "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiarTextoTipoPagoEliminar(View v){
        id_tipoPago.setText("");
    }
}