package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class FacultadMenuActivity extends ListActivity {

    String[] menu = {"Insertar Facultad","Eliminar Facultad",
            "Consultar Facultad","Actualizar Facultad"};

    String[] activities = {"FacultadInsertarActivity", "FacultadEliminarActivity",
            "FacultadConsultarActivity","FacultadActualizarActivity" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);
        setListAdapter(adapter);
    }

    protected void onListItemClick(ListView l,View v, int position, long id){
        super.onListItemClick(l,v,position,id);
        String nombreValue= activities[position];

        l.getChildAt(position).setBackgroundColor(Color.rgb(128,128,255));

        try{
            Class<?> clase = Class.forName("sv.edu.ues.fia.eisi.cafetinesues."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}