package sv.edu.ues.fia.eisi.cafetinesues;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class EncargadoLocalMainActivity extends ListActivity {

    String[] menu = {"Insertar Encargado", "Eliminar Encargado", "Consultar Encargado", "Actualizar Encargado"};
    String[] activities = {"EncargadoLocalInsertarActivity","EncargadoLocalEliminarActivity", "EncargadoLocalConsultarActivity","EncargadoLocalActualizarActivity"};
    final String[] OpcionCRUD = {"010","020","030","040"};
    String username;
    ControlDB helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
        helper = new ControlDB(this);
        Bundle recepcionDatos = getIntent().getExtras();
        username = recepcionDatos.getString("Username");
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);
        String nombreValue = activities[position];
        l.getChildAt(position).setBackgroundColor(Color.rgb(129,120,250));
        String acceso = OpcionCRUD[position];
        if(helper.validarEntrada(username,acceso))
        {
            try{
                Class<?> clase = Class.forName("sv.edu.ues.fia.eisi.cafetinesues."+nombreValue);
                Intent inte = new Intent(this,clase);
                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }else {
            Toast.makeText(this, "No tiene acceso", Toast.LENGTH_SHORT).show();
        }
    }
}