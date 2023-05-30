package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoPagoConsultarActivity extends AppCompatActivity {
    ControlDB helper;
    EditText id_tipoPago;
    EditText nombre_tipoPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_pago_consultar);
        helper = new ControlDB(this);
        id_tipoPago = (EditText) findViewById(R.id.txtIdTipoPago);
        nombre_tipoPago = (EditText) findViewById(R.id.txtNombrePago);
    }

    public void consultarTipoPago(View v) {
        helper.abrir();
        TipoPago tipoPago =
                helper.consultarTipoPago(id_tipoPago.getText().toString());
        helper.cerrar();
        if(tipoPago == null)
            Toast.makeText(this, "Tipo de pago con ID " +
                    id_tipoPago.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            nombre_tipoPago.setText(tipoPago.getNombre_TipoPago());

        }
    }

    public void limpiarTxtConsultarTipoPago(View v){
        id_tipoPago.setText("");
        nombre_tipoPago.setText("");
    }
}