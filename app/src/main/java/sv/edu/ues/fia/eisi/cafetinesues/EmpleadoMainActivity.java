package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EmpleadoMainActivity extends ListActivity {

    String[] menu = {"Insertar Empleado","Eliminar Empleado",
            "Consultar Empleado","Actualizar Empleado"};

    String[] activities = {"EmpleadoInsertarActivity","EmpleadoEliminarActivity",
            "EmpleadoConsultarActivity","EmpleadoActualizarActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v,int position, long id){
        super.onListItemClick(l,v,position,id);
        String nombreValue = activities[position];
        l.getChildAt(position).setBackgroundColor(Color.rgb(129,120,250));

        try {
            Class<?> clase = Class.forName("sv.edu.ues.fia.eisi.cafetinesues."+nombreValue);
            Intent inte = new Intent(this, clase);
            this.startActivity(inte);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}