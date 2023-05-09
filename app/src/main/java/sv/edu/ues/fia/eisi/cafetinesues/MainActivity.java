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
    String[] menu = {"Tabla Combo", "Tabla ComboProducto", "Tabla DetallePedido","Tabla TipoProducto","Tabla Producto","Tabla PrecioProducto","Tabla TipoPago", "Llenar BD"};
    String[] activities = {"ComboMenuActivity", "ComboProductoMenuActivity", "DetallePedidoMenuActivity",
                            "TipoProductoMainActivity","ProductoMainActivity","PrecioProductoMainActivity","TipoPagoMenuActivity"};

    ControlDB DBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        DBhelper=new ControlDB(this);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        if(position!=7){

            String nombreValue = activities[position];

            try{
                Class<?> clase=Class.forName("sv.edu.ues.fia.eisi.cafetinesues."+nombreValue);
                Intent inte = new Intent(this,clase);
                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }

        }else{
            DBhelper.abrir();
            String tost=DBhelper.llenarBD();
            DBhelper.cerrar();
            Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();

        }
    }


}