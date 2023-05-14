package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    // AGREGAR DEMAS MENUS, Y SU CORRESPONDIENTE ACTIVITY, EN CADA STRING

    String[] menu = {
            "Tabla Facultad", "Tabla TipoUbicacion","Tabla Ubicacion",
            "Tabla EncargadoLocal","Tabla Local","Tabla Empleado",
            "Tabla Cliente","Tabla TipoPago","Tabla Pedido",
            "Tabla DetallePedido","Tabla Combo","Tabla ComboProducto",
            "Tabla TipoProducto","Tabla Producto","Tabla PrecioProducto",
            "Llenar BD"
    };

    String[] activities = {
            "FacultadMenuActivity", "TipoUbicacionMenuActivity","UbicacionMenuActivity",
            "EncargadoLocalMainActivity","LocalMainActivity","EmpleadoMainActivity",
            "ClienteMenuActivity","TipoPagoMenuActivity","PedidoMenuActivity",
            "DetallePedidoMenuActivity","ComboMenuActivity","ComboProductoMenuActivity",
            "TipoProductoMainActivity","ProductoMainActivity","PrecioProductoMainActivity"
    };
           
    ControlDB DBhelper;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        DBhelper=new ControlDB(this);
        Bundle recepcionDatos = getIntent().getExtras();
        username = recepcionDatos.getString("Username");
        Toast.makeText(this, "Bienvenido " + username, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);


        if(position!=15){

            String nombreValue = activities[position];
            if(DBhelper.verTablas(username))
            {

                try{
                    Bundle envioDatos = new Bundle();
                    envioDatos.putString("Username",username);
                    Class<?> clase=Class.forName("sv.edu.ues.fia.eisi.cafetinesues."+nombreValue);
                    Intent inte = new Intent(this,clase);
                    inte.putExtras(envioDatos);
                    this.startActivity(inte);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }else {
                Toast.makeText(this, "Acceso denegado", Toast.LENGTH_SHORT).show();
            }

        }else{
            DBhelper.abrir();
            String tost=DBhelper.llenarBD();
            DBhelper.cerrar();
            Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();

        }
    }


}