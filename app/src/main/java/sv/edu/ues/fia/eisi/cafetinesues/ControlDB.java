package sv.edu.ues.fia.eisi.cafetinesues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ControlDB {
    // CAMPOS DE CADA TABLA
    private static final String[] campos_Combo = new String[]{"id_Combo", "precio_Combo"};
    private static final String[] campos_ComboProducto = new String[]{"id_ComboProducto", "id_Combo", "id_Producto"};
    private static final String[] campos_Producto = new String[]{"id_Producto","id_TipoProducto","nombre_Producto","estado_Producto","precioactual_Producto"};
    private static final String[] campos_TipoProducto = new String[] {"id_TipoProducto","nombre_TipoProducto"};

    private static final String[] Campos_Empleado = new String[] {"id_Empleado","id_Local", "nombre_Empleado", "tipo_Empleado"};

    private static final String[] Campos_EncargadoLocal = new String[]{"id_EncargadoLocal","nombre_EncargadoLocal"};
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlDB(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String BASE_DATOS = "bd_cafetinesUES.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE CLIENTE (" +
                        "ID_CLIENTE INTEGER NOT NULL, " +
                        "ID_UBICACION INTEGER, " +
                        "NOMBRES_CLIENTE CHAR(30) NOT NULL, " +
                        "APELLIDOS_CLIENTE CHAR(30) NOT NULL, " +
                        "FECHA_NACIMIENTO DATE NOT NULL, " +
                        "PRIMARY KEY (ID_CLIENTE), " +
                        "FOREIGN KEY (ID_UBICACION) REFERENCES UBICACION(ID_UBICACION) ON DELETE RESTRICT);");

                db.execSQL("CREATE TABLE COMBO (" +
                        "ID_COMBO INTEGER NOT NULL, " +
                        "PRECIO_COMBO FLOAT NOT NULL, " +
                        "PRIMARY KEY (ID_COMBO));");

                db.execSQL("CREATE TABLE COMBOPRODUCTO (" +
                        "ID_COMBOPRODUCTO INTEGER NOT NULL, " +
                        "ID_PRODUCTO INTEGER, " +
                        "ID_COMBO INTEGER, " +
                        "PRIMARY KEY (ID_COMBOPRODUCTO), " +
                        "FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTO(ID_PRODUCTO) ON DELETE RESTRICT, " +
                        "FOREIGN KEY (ID_COMBO) REFERENCES COMBO(ID_COMBO) ON DELETE RESTRICT);");

                db.execSQL("CREATE TABLE DETALLEPEDIDO (" +
                        "ID_DETALLEPEDIDO INTEGER NOT NULL, " +
                        "ID_PEDIDO INTEGER NOT NULL, " +
                        "ID_COMBO INTEGER, " +
                        "ID_PRODUCTO INTEGER, " +
                        "CANTIDAD_PRODUCTO INTEGER NOT NULL, " +
                        "SUBTOTAL FLOAT NOT NULL, " +
                        "PRIMARY KEY (ID_DETALLEPEDIDO), " +
                        "FOREIGN KEY (ID_PEDIDO) REFERENCES PEDIDO(ID_PEDIDO) ON DELETE RESTRICT, " +
                        "FOREIGN KEY (ID_COMBO) REFERENCES COMBO(ID_COMBO) ON DELETE RESTRICT, " +
                        "FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTO(ID_PRODUCTO) ON DELETE RESTRICT);");

                db.execSQL("CREATE TABLE EMPLEADO (" +
                        "ID_EMPLEADO INTEGER NOT NULL, " +
                        "ID_LOCAL INTEGER NOT NULL, " +
                        "NOMBRE_EMPLEADO CHAR(30) NOT NULL, " +
                        "TIPO_EMPLEADO CHAR(10) NOT NULL, " +
                        "PRIMARY KEY (ID_EMPLEADO), " +
                        "FOREIGN KEY (ID_LOCAL) REFERENCES LOCAL(ID_LOCAL) ON DELETE RESTRICT);");

                db.execSQL("CREATE TABLE ENCARGADOLOCAL (" +
                        "ID_ENCARGADOLOCAL INTEGER NOT NULL, " +
                        "NOMBRE_ENCARGADOLOCAL CHAR(30) NOT NULL, " +
                        "PRIMARY KEY (ID_ENCARGADOLOCAL));");

                db.execSQL("CREATE TABLE ENVIO (" +
                        "ID_ENVIO INTEGER NOT NULL, " +
                        "ID_EMPLEADO INTEGER NOT NULL, " +
                        "FECHA DATE, " +
                        "PRIMARY KEY (ID_ENVIO), " +
                        "FOREIGN KEY (ID_EMPLEADO) REFERENCES EMPLEADO(ID_EMPLEADO) ON DELETE RESTRICT);");

                db.execSQL("CREATE TABLE EVENTOESPECIAL (" +
                        "ID_EVENTOESPECIAL INTEGER NOT NULL," +
                        "NOMBRE_EVENTOESPECIAL CHAR(30) NOT NULL," +
                        "FECHA_EVENTOESPECIAL DATE NOT NULL," +
                        "MONTO_MINIMO FLOAT NOT NULL," +
                        "MONTO_MAXIMO FLOAT NOT NULL," +
                        "CONSTRAINT PK_EVENTOESPECIAL PRIMARY KEY (ID_EVENTOESPECIAL));");

                db.execSQL("CREATE TABLE FACULTAD (" +
                        "ID_FACULTAD INTEGER NOT NULL," +
                        "NOMBRE_FACULTAD CHAR(30) NOT NULL," +
                        "CONSTRAINT PK_FACULTAD PRIMARY KEY (ID_FACULTAD));");

                db.execSQL("CREATE TABLE LISTAPEDIDOS (" +
                        "ID_LISTAPEDIDOS INTEGER NOT NULL," +
                        "ID_ENVIO INTEGER," +
                        "ID_PEDIDO INTEGER," +
                        "CONSTRAINT PK_LISTAPEDIDOS PRIMARY KEY (ID_LISTAPEDIDOS)," +
                        "CONSTRAINT FK_LISTAPEDIDOS_ENVIO FOREIGN KEY (ID_ENVIO) REFERENCES ENVIO(ID_ENVIO) ON DELETE RESTRICT," +
                        "CONSTRAINT FK_LISTAPEDIDOS_PEDIDO FOREIGN KEY (ID_PEDIDO) REFERENCES PEDIDO(ID_PEDIDO) ON DELETE RESTRICT);");

                db.execSQL("CREATE TABLE LISTAPRECIO (" +
                        "ID_LISTAPRECIO INTEGER NOT NULL," +
                        "DESDE DATE NOT NULL," +
                        "HASTA DATE NOT NULL," +
                        "CONSTRAINT PK_LISTAPRECIO PRIMARY KEY (ID_LISTAPRECIO));");

                db.execSQL("CREATE TABLE LOCAL (" +
                        "ID_LOCAL INTEGER NOT NULL," +
                        "ID_UBICACION INTEGER NOT NULL," +
                        "ID_ENCARGADOLOCAL INTEGER," +
                        "NOMBRE_LOCAL CHAR(30) NOT NULL," +
                        "CONSTRAINT PK_LOCAL PRIMARY KEY (ID_LOCAL)," +
                        "CONSTRAINT FK_LOCAL_UBICACION FOREIGN KEY (ID_UBICACION) REFERENCES UBICACION(ID_UBICACION) ON DELETE RESTRICT," +
                        "CONSTRAINT FK_LOCAL_ENCARGADOLOCAL FOREIGN KEY (ID_ENCARGADOLOCAL) REFERENCES ENCARGADOLOCAL(ID_ENCARGADOLOCAL) ON DELETE RESTRICT);");

                db.execSQL("CREATE TABLE PEDIDO (" +
                        "ID_PEDIDO INTEGER NOT NULL," +
                        "ID_CLIENTE INTEGER," +
                        "ID_TIPOPAGO INTEGER," +
                        "ID_LOCAL INTEGER NOT NULL," +
                        "ID_EVENTOESPECIAL INTEGER," +
                        "TIPO_PEDIDO CHAR(20) NOT NULL," +
                        "ESTADO_PEDIDO CHAR(20)," +
                        "MONTO_PEDIDO FLOAT," +
                        "CONSTRAINT PK_PEDIDO PRIMARY KEY (ID_PEDIDO)," +
                        "CONSTRAINT FK_PEDIDO_CLIENTE FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE) ON DELETE RESTRICT," +
                        "CONSTRAINT FK_PEDIDO_TIPOPAGO FOREIGN KEY (ID_TIPOPAGO) REFERENCES TIPOPAGO(ID_TIPOPAGO) ON DELETE RESTRICT," +
                        "CONSTRAINT FK_PEDIDO_LOCAL FOREIGN KEY (ID_LOCAL) REFERENCES LOCAL(ID_LOCAL) ON DELETE RESTRICT," +
                        "CONSTRAINT FK_PEDIDO_EVENTOESPECIAL FOREIGN KEY (ID_EVENTOESPECIAL) REFERENCES EVENTOESPECIAL(ID_EVENTOESPECIAL) ON DELETE RESTRICT);");

                db.execSQL("CREATE TABLE PRECIOPRODUCTO (" +
                        "ID_PRECIOPRODUCTO INTEGER NOT NULL," +
                        "ID_PRODUCTO INTEGER," +
                        "ID_LISTAPRECIO INTEGER," +
                        "PRECIO FLOAT NOT NULL," +
                        "CONSTRAINT PK_PRECIOPRODUCTO PRIMARY KEY (ID_PRECIOPRODUCTO)," +
                        "CONSTRAINT FK_PRECIOPRODUCTO_PRODUCTO FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTO(ID_PRODUCTO) ON DELETE RESTRICT," +
                        "CONSTRAINT FK_PRECIOPRODUCTO_LISTAPRECIO FOREIGN KEY (ID_LISTAPRECIO) REFERENCES LISTAPRECIO(ID_LISTAPRECIO) ON DELETE RESTRICT);");

                db.execSQL("CREATE TABLE PRODUCTO (" +
                        "ID_PRODUCTO INTEGER NOT NULL," +
                        "ID_TIPOPRODUCTO INTEGER," +
                        "NOMBRE_PRODUCTO CHAR(30) NOT NULL," +
                        "ESTADO_PRODUCTO CHAR(30) NOT NULL," +
                        "PRECIOACTUAL_PRODUCTO FLOAT," +
                        "CONSTRAINT PK_PRODUCTO PRIMARY KEY (ID_PRODUCTO)," +
                        "CONSTRAINT FK_PRODUCTO_TIPOPRODUCTO FOREIGN KEY (ID_TIPOPRODUCTO) REFERENCES TIPOPRODUCTO(ID_TIPOPRODUCTO) ON DELETE RESTRICT);");

                db.execSQL("CREATE TABLE TIPOPAGO (" +
                        "ID_TIPOPAGO INTEGER NOT NULL," +
                        "NOMBRE_TIPOPAGO CHAR(30) NOT NULL," +
                        "CONSTRAINT PK_TIPOPAGO PRIMARY KEY (ID_TIPOPAGO));");

                db.execSQL("CREATE TABLE TIPOPRODUCTO (" +
                        "ID_TIPOPRODUCTO INTEGER NOT NULL," +
                        "NOMBRE_TIPOPRODUCTO CHAR(30) NOT NULL," +
                        "CONSTRAINT PK_TIPOPRODUCTO PRIMARY KEY (ID_TIPOPRODUCTO));");

                db.execSQL("CREATE TABLE TIPOUBICACION (" +
                        "ID_TIPOUBICACION INTEGER NOT NULL," +
                        "NOMBRE_TIPOUBICACION CHAR(20) NOT NULL," +
                        "CONSTRAINT PK_TIPOUBICACION PRIMARY KEY (ID_TIPOUBICACION));");

                db.execSQL("CREATE TABLE UBICACION (" +
                        "ID_UBICACION INTEGER NOT NULL," +
                        "ID_FACULTAD INTEGER," +
                        "ID_TIPOUBICACION INTEGER NOT NULL," +
                        "NOMBRE_UBICACION CHAR(20) NOT NULL," +
                        "DESCRIPCION_UBICACION CHAR(100) NOT NULL," +
                        "CONSTRAINT PK_UBICACION PRIMARY KEY (ID_UBICACION)," +
                        "CONSTRAINT FK_UBICACION_FACULTAD FOREIGN KEY (ID_FACULTAD) REFERENCES FACULTAD(ID_FACULTAD) ON DELETE RESTRICT," +
                        "CONSTRAINT FK_UBICACION_TIPOUBICACION FOREIGN KEY (ID_TIPOUBICACION) REFERENCES TIPOUBICACION(ID_TIPOUBICACION) ON DELETE RESTRICT);");

            }catch(SQLException e){

                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

    }

    public void abrir() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar() {
        DBHelper.close();
    }

    //
    //
    //
    // METODOS PARA COMBO
    //
    //
    //
    public String insertar(Combo combo) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        // Verificar si existe el registro a insertar
        if (verificarIntegridad(combo, 1)) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            ContentValues comboValues = new ContentValues();
            comboValues.put("id_Combo", combo.getId_Combo());
            //comboValues.put("nombre_Combo", combo.getNombreCombo());
            comboValues.put("precio_Combo", combo.getPrecio_Combo());
            contador = db.insert("Combo", null, comboValues);
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(Combo combo) {
        // Verificar si existe el registro a actualizar
        // En este caso nos interesa que SI exista
        if (verificarIntegridad(combo, 1)) {
            String[] id = {String.valueOf(combo.getId_Combo())};
            ContentValues cv = new ContentValues();
            //cv.put("nombre_Combo", combo.getNombreCombo());
            cv.put("precio_Combo", combo.getPrecio_Combo());
            db.update("Combo", cv, "id_Combo = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Registro con id_Combo " + combo.getId_Combo() + " no existe";
        }
    }

    public String eliminar(Combo combo) {
        String regAfectados = "filas afectadas= ";
        int contador = 0;
        //Verificar si existe el registro a eliminar
        if (verificarIntegridad(combo, 1)) {
            contador += db.delete("Combo", "id_Combo='" + combo.getId_Combo() + "'", null);
        }
        regAfectados += contador;
        return regAfectados;
    }

    public Combo consultarCombo(int id_Combo) {
        String[] id = {String.valueOf(id_Combo)};
        // Verificar que exista el registro a consultarCombo
        if (verificarIntegridad(id_Combo, 2)) {
            Cursor cursor = db.query("Combo", campos_Combo, "id_Combo = ?", id, null, null, null);
            if (cursor.moveToFirst()) {
                Combo combo = new Combo();
                combo.setId_Combo(cursor.getInt(0));
                //combo.setNombreCombo(cursor.getString(1));
                combo.setPrecio_Combo(cursor.getFloat(1));
                return combo;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    //
    //
    // METODOS PARA COMBOPRODUCTO
    //
    //
    //
    public String insertar(ComboProducto comboProducto) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        // Verificar si existe el registro a insertar
        if (verificarIntegridad(comboProducto, 3)) {
            regInsertados = "ID existente o Producto/Combo inexistente";
        } else {
            ContentValues comboProductoValues = new ContentValues();
            comboProductoValues.put("id_ComboProducto", comboProducto.getId_ComboProducto());
            comboProductoValues.put("id_Combo", comboProducto.getId_Combo());
            comboProductoValues.put("id_Producto", comboProducto.getId_Producto());
            contador = db.insert("ComboProducto", null, comboProductoValues);
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(ComboProducto comboProducto) {
        // Verificar si existe el registro a actualizar
        // En este caso nos interesa que SI exista
        if (verificarIntegridad(comboProducto, 3)) {
            String[] id = {String.valueOf(comboProducto.getId_ComboProducto())};
            ContentValues cv = new ContentValues();
            cv.put("id_Combo", comboProducto.getId_Combo());
            cv.put("id_Producto", comboProducto.getId_Producto());
            db.update("ComboProducto", cv, "id_ComboProducto = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Registro con id_ComboProducto " + comboProducto.getId_ComboProducto() + " no existe";
        }
    }

    public String eliminar(ComboProducto comboProducto) {
        String regAfectados = "filas afectadas= ";
        int contador = 0;
        //Verificar si existe el registro a eliminar
        if (verificarIntegridad(comboProducto, 3)) {
            contador += db.delete("ComboProducto", "id_ComboProducto='" + comboProducto.getId_ComboProducto() + "'", null);
        }
        regAfectados += contador;
        return regAfectados;
    }

    public ComboProducto consultarComboProducto(int id_ComboProducto) {
        String[] id = {String.valueOf(id_ComboProducto)};
        // Verificar que exista el registro a consultarCombo
        if (verificarIntegridad(id_ComboProducto, 4)) {
            Cursor cursor = db.query("ComboProducto", campos_ComboProducto, "id_ComboProducto = ?", id, null, null, null);
            if (cursor.moveToFirst()) {
                ComboProducto comboProducto = new ComboProducto();
                comboProducto.setId_ComboProducto(cursor.getInt(0));
                comboProducto.setId_Combo(cursor.getInt(1));
                comboProducto.setId_Producto(cursor.getInt(2));
                return comboProducto;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    //
    //
    // METODOS PARA PRODUCTO
    //
    //
    //

    public String insertar(Producto producto) {
        String resultado = "";
        long contador = 0;
        TipoProducto tipoProducto = new TipoProducto();
        tipoProducto.setId_TipoProducto(producto.getCodigo_TipoProducto());

        boolean existenciaProducto = verificarIntegridad(producto, 11);
        boolean existenciaTipoProducto = verificarIntegridad(tipoProducto,14);

        // Verificar si existe el registro a insertar
        if (!(existenciaProducto) && (existenciaTipoProducto)) {
            ContentValues productoValues = new ContentValues();
            productoValues.put(campos_Producto[0], producto.getCodigo_Producto());
            productoValues.put(campos_Producto[1], producto.getCodigo_TipoProducto());
            productoValues.put(campos_Producto[2], producto.getNombre_Producto());
            productoValues.put(campos_Producto[3], producto.getEstado_Producto());
            productoValues.put(campos_Producto[4], producto.getPrecioactual_Producto());
            contador = db.insert("Producto", null, productoValues);
            resultado = "Registro Insertado Nº= "  + contador;
        } else {
            if(existenciaProducto)
                resultado = "El producto ya existe";
            if(!(existenciaTipoProducto))
            {
                if(resultado.isEmpty())
                {
                    resultado = "No existe el tipo de producto";
                }else {
                    resultado +=" y\nNo existe el tipo de producto";
                }
            }

        }
        return resultado;
    }

    public String actualizar(Producto producto) {
        // Verificar si existe el registro a actualizar
        // En este caso nos interesa que SI exista
        TipoProducto tipoProducto = new TipoProducto();
        tipoProducto.setId_TipoProducto(producto.getCodigo_TipoProducto());
        String resultado = "";
        boolean existenciaProducto = verificarIntegridad(producto, 11);
        boolean existenciaTipoProducto = verificarIntegridad(tipoProducto,14);

        if ((existenciaProducto) && (existenciaTipoProducto)) {
            String[] id = {String.valueOf(producto.getCodigo_Producto())};
            ContentValues productoUpdate = new ContentValues();
            productoUpdate.put(campos_Producto[1],producto.getCodigo_TipoProducto());
            productoUpdate.put(campos_Producto[2],producto.getNombre_Producto());
            productoUpdate.put(campos_Producto[3],producto.getEstado_Producto());
            productoUpdate.put(campos_Producto[4],producto.getPrecioactual_Producto());
            db.update("Producto", productoUpdate, "id_Producto = ?", id);
            resultado = "Registro Actualizado Correctamente";
        } else {
            if(!(existenciaProducto))
                resultado = "El producto no existe";
            if(!(existenciaTipoProducto))
            {
                if(resultado.isEmpty())
                {
                    resultado = "El tipo de producto no existe";
                }
                else{
                    resultado += " y\nEl tipo de producto no existe";
                }
            }

        }
        return resultado;
    }

    public String eliminar(Producto producto) {
        String regAfectados = "filas afectadas= ";
        int contador = 0;

        //Verificar si existe el registro a eliminar
        if (verificarIntegridad(producto, 11) && !(verificarIntegridad(producto,12))) {
            contador += db.delete("Producto", "id_Producto='" + producto.getCodigo_Producto() + "'", null);
            regAfectados += contador;
        }
        else {
            regAfectados = "No existe o\nEsta asociado";
        }

        return regAfectados;
    }

    public Producto consultarProducto(Producto producto) {
        // Verificar que exista el registro a consultar de Producto
        if (verificarIntegridad(producto, 11)) {
            Cursor cursor = db.query("Producto", campos_Producto, "id_Producto = ?",
                    new String[]{String.valueOf(producto.getCodigo_Producto())}, null, null, null);
            if (cursor.moveToFirst()) {
                Producto productoConsulta = new Producto();
                productoConsulta.setCodigo_Producto(cursor.getInt(0));
                productoConsulta.setCodigo_TipoProducto(cursor.getInt(1));
                productoConsulta.setNombre_Producto(cursor.getString(2));
                productoConsulta.setEstado_Producto(cursor.getString(3));
                productoConsulta.setPrecioactual_Producto(cursor.getFloat(4));
                return productoConsulta;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    //
    //
    // METODOS PARA TIPO PRODUCTO
    //
    //
    //
    public String insertar(TipoProducto tipoProducto) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        // Verificar si existe el registro a insertar
        if (verificarIntegridad(tipoProducto, 14)) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            ContentValues tipoProductoValues = new ContentValues();
            tipoProductoValues.put(campos_TipoProducto[0], tipoProducto.getId_TipoProducto());
            tipoProductoValues.put(campos_TipoProducto[1],tipoProducto.getNombre_TipoProducto());
            contador = db.insert("TipoProducto", null, tipoProductoValues);
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(TipoProducto tipoProducto) {
        // Verificar si existe el registro a actualizar
        // En este caso nos interesa que SI exista
        if (verificarIntegridad(tipoProducto, 14)) {
            String[] id = {String.valueOf(tipoProducto.getId_TipoProducto())};
            ContentValues tipoProductoUpdate = new ContentValues();
            tipoProductoUpdate.put(campos_TipoProducto[1],tipoProducto.getNombre_TipoProducto());
            db.update("TipoProducto", tipoProductoUpdate, "id_TipoProducto = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Tipo de Producto con código " + tipoProducto.getId_TipoProducto() + " no existe";
        }
    }

    public String eliminar(TipoProducto tipoProducto) {
        String regAfectados = "";
        int contador = 0;

        //Verificar si existe el registro a eliminar
        if (verificarIntegridad(tipoProducto, 14) && !(verificarIntegridad(tipoProducto,15))) {
            contador += db.delete("TipoProducto", "id_TipoProducto='" + tipoProducto.getId_TipoProducto() + "'", null);
            regAfectados = "Filas afectadas N° = " + contador;
        }
        else {
            regAfectados = "No existe o\nEsta asociado";
        }

        return regAfectados;
    }

    public TipoProducto consultarTipoProducto(TipoProducto tipoproducto) {
        // Verificar que exista el registro a consultar de Producto
        if (verificarIntegridad(tipoproducto, 14)) {
            Cursor cursor = db.query("TipoProducto", campos_TipoProducto, "id_TipoProducto = ?",
                    new String[]{String.valueOf(tipoproducto.getId_TipoProducto())}, null, null, null);
            if (cursor.moveToFirst()) {
                TipoProducto tipoProductoConsulta = new TipoProducto();
                tipoProductoConsulta.setId_TipoProducto(cursor.getInt(0));
                tipoProductoConsulta.setNombre_TipoProducto(cursor.getString(1));
                return tipoProductoConsulta;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    //
    // METODOS PARA EMPLEADO
    //
    //
    //
    public String Insertar(Empleado empleado){
        String resultado = "";
        long contador = 0;
        Local local = new Local();
        local.setId_Local(empleado.getId_Local());
        boolean existenciaLocal = verificarIntegridad(local, 42);
        boolean existenciaEmpleado = verificarIntegridad(empleado,41);
        //verificar si existe el registro al insertar
        if(!(existenciaEmpleado)&&(existenciaLocal)){
            ContentValues empleadoValues = new ContentValues();
            empleadoValues.put(Campos_Empleado[0], empleado.getId_Empleado());
            empleadoValues.put(Campos_Empleado[1], empleado.getId_Local());
            empleadoValues.put(Campos_Empleado[2], empleado.getNombre_Empleado());
            empleadoValues.put(Campos_Empleado[3], empleado.getTipo_Empleado());
            contador = db.insert("Empleado", null, empleadoValues);
            resultado = "Registro Insertado N°=" + contador;
        }else{
            if(existenciaEmpleado){
                resultado = "El Empleado ya existe";
                if(!(existenciaEmpleado)){
                    if(resultado.isEmpty()){
                        resultado = "No eciste el Empleado";
                    }
                    else {
                        resultado += "y\nNo existe el Local";
                    }
                }
            }
        }
        return resultado;
    }
    public String Actualizar(Empleado empleado){
        String resultado = "";
        long contador = 0;
        Local local = new Local();
        local.setId_Local(empleado.getId_Local());
        boolean existenciaLocal = verificarIntegridad(empleado, 42);
        boolean existenciaEmpleado = verificarIntegridad(empleado,41);
        if((existenciaEmpleado)&&(existenciaLocal)){
            String[] id = {String.valueOf(empleado.getId_Empleado())};
            ContentValues empleadoUpdate = new ContentValues();
            empleadoUpdate.put(Campos_Empleado[1], empleado.getId_Local());
            empleadoUpdate.put(Campos_Empleado[2], empleado.getNombre_Empleado());
            empleadoUpdate.put(Campos_Empleado[3], empleado.getTipo_Empleado());
            contador = db.update("Empleado", empleadoUpdate,"id_Empleado = ?",id);
            resultado = "Registro  Actualizado Correctamente";
        }else{
            if(!(existenciaEmpleado)){
                resultado = "El Empleado no existe";
                if(!(existenciaLocal)){
                    if(resultado.isEmpty()){
                        resultado = "El tipo de producto no existe";
                    }else{
                        resultado += "y\nNo existe el Local";
                    }
                }
            }
        }
        return resultado;
    }
    public String Eliminar (Empleado empleado){
        String resultado = "";
        int contador = 0;
        if (verificarIntegridad(empleado, 41)&& !(verificarIntegridad(empleado,42))){

            contador+= db.delete("Empleado", "id_Empleado='"+empleado.getId_Empleado()+"'",null);
            resultado = "Filas afectadas N° = "+contador;
        }else{
            resultado = "No existe o\nEsta asociado";
        }
        return resultado;
    }

    public Empleado consultarEmpleado(Empleado empleado){
        if(verificarIntegridad(empleado,41)){
            Cursor cursor = db.query("Empleado", Campos_Empleado, "id_Empleado=?",
                    new String[]{String.valueOf(empleado.getId_Empleado())},null,null,null);
            if (cursor.moveToFirst()){
                Empleado empleadoConsulta = new Empleado();
                empleadoConsulta.setId_Empleado(cursor.getInt(0));
                empleadoConsulta.setId_Local(cursor.getInt(1));
                empleadoConsulta.setNombre_Empleado(cursor.getString(2));
                empleadoConsulta.setTipo_Empleado(cursor.getString(3));
                return empleadoConsulta;
            }else{
                return null;
            }

        }else {
            return null;
        }
    }
    //
    // METODOS PARA ENCARGADOLOCAL
    //
    //
    //
    public String Insertar (EncargadoLocal encargadoLocal){
        String resultado = "";
        long contador = 0;
        boolean existenciaEncargado = verificarIntegridad(encargadoLocal,43);
        if(!existenciaEncargado){
            ContentValues encargadoValues = new ContentValues();
            encargadoValues.put(Campos_EncargadoLocal[0],encargadoLocal.getId_EncargadoLocal());
            encargadoValues.put(Campos_EncargadoLocal[1],encargadoLocal.getNombre_EncargadoLocal());
            contador = db.insert("EncargadoLocal", null, encargadoValues);
            resultado = "Registro Insertado N°="+ contador;
        }else if(existenciaEncargado){
            resultado = "El encargado ya existe";

        }
        return resultado;

    }

    public String Actualizar (EncargadoLocal encargadoLocal){
        String resultado="";
        long contador =0;
        boolean existenciaEncargado = verificarIntegridad(encargadoLocal,43);
        if(existenciaEncargado){
            String[] id ={String.valueOf(encargadoLocal.getId_EncargadoLocal())};
            ContentValues encargadoUpdate = new ContentValues();
            encargadoUpdate.put(Campos_EncargadoLocal[1], encargadoLocal.getNombre_EncargadoLocal());
            contador = db.update("EncargadoLocal", encargadoUpdate,"id_EncargadoLocal=?", id);
            resultado = "Registro Actualizado Correctamente";
        }else if(!existenciaEncargado){
            resultado = "El encargado no existe";
        }
        return resultado;
    }

    public String Eliminar(EncargadoLocal encargadoLocal){
        String resultado = "";
        long contador = 0;
        boolean existenciaEncargado = verificarIntegridad(encargadoLocal,43);
        if (existenciaEncargado){
            contador = db.delete("EncargadoLocal", "id_EncargadoLocal='"+encargadoLocal.getId_EncargadoLocal()+"'", null);
            resultado = "Filas afectadas N° ="+ contador;
        }else{
            resultado = "No existe ese encargado";
        }
        return  resultado;
    }

    public EncargadoLocal consultarEncargado(EncargadoLocal encargadoLocal){
        if(verificarIntegridad(encargadoLocal,43)){
            Cursor cursor = db.query("EncargadoLocal",Campos_EncargadoLocal,"id_EncargadoLocal=?",
                    new String[]{String.valueOf(encargadoLocal.getId_EncargadoLocal())},null,null,null);
            if(cursor.moveToFirst()){
                EncargadoLocal encargadoConsulta = new EncargadoLocal();
                encargadoConsulta.setId_EncargadoLocal(cursor.getInt(0));
                encargadoConsulta.setNombre_EncargadoLocal(cursor.getString(1));
                return encargadoConsulta;
            }else {
                return null;
            }
        }
        else{
            return null;
        }
    }
    // DEMAS METODOS CRUD PARA LAS DEMAS TABLAS



    //
    //
    //
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            //
            //
            // INTEGRIDAD PARA COMBO
            //
            //
            case 1:
                // Verificar si ya existe ese COMBO
                Combo combo = (Combo) dato;
                String[] id1 = {String.valueOf(combo.getId_Combo())};
                abrir();
                Cursor cursor1 = db.query("Combo", null, "id_combo = ?", id1, null, null, null);
                if (cursor1.moveToFirst()) {
                    // Ya existe
                    return true;
                }
                return false;
            case 2:
                // Verificar si existe ese id_Combo
                String[] id2 = {String.valueOf(dato)};
                abrir();
                Cursor cursor2 = db.query("Combo", null, "id_combo = ?", id2, null, null, null);
                if (cursor2.moveToFirst()) {
                    // Ya existe
                    return true;
                }
                return false;
            //
            //
            // INTEGRIDAD PARA COMBO_PRODUCTO
            //
            //
            case 3:
                // Verificar si ya existe ese COMBO_PRODUCTO y si existen  el Combo y Producto especificado
                ComboProducto comboProducto = (ComboProducto)dato;
                String[] id1c = {String.valueOf(comboProducto.getId_ComboProducto())};
                String[] id2c = {String.valueOf(comboProducto.getId_Combo())};
                String[] id3c = {String.valueOf(comboProducto.getId_Producto())};
                abrir();
                Cursor cursor1c = db.query("ComboProducto", null, "id_ComboProducto = ?", id1c, null, null, null);
                Cursor cursor2c = db.query("Combo", null, "id_Combo = ?", id2c, null, null, null);
                Cursor cursor3c = db.query("Producto", null, "id_Producto = ?", id3c, null, null, null);
                if(cursor1c.moveToFirst() && cursor2c.moveToFirst() && cursor3c.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            case 4:
                // Verificar si existe ese id_ComboProducto
                String[] id2cc = {String.valueOf(dato)};
                abrir();
                Cursor cursor2cc = db.query("ComboProducto", null, "id_ComboProducto = ?", id2cc, null, null, null);
                if (cursor2cc.moveToFirst()) {
                    // Ya existe
                    return true;
                }
                return false;

            //
            //
            // INTEGRIDAD PARA PRODUCTO
            //
            //
            case 11:
                // Verificar que no exista el producto
                Producto producto1 = (Producto) dato;
                String[] id_Producto1 = {String.valueOf(producto1.getCodigo_Producto())};
                abrir();
                Cursor cursorIP = db.query("Producto", null, "id_Producto = ?",id_Producto1, null, null, null);
                if (cursorIP.moveToFirst()) {
                    // Ya existe
                    return true;
                }
                return false;
            case 12:
                // Verificar que existencia del producto en las relaciones
                Producto producto12 = (Producto) dato;
                String[] id_Producto12 = {String.valueOf(producto12.getCodigo_Producto())};
                abrir();
                Cursor cursorCP12 = db.query("ComboProducto", null, "id_Producto = ?",id_Producto12, null, null, null);
                Cursor cursorDP12 = db.query("DetallePedido", null, "id_Producto = ?",id_Producto12, null, null, null);
                Cursor cursorPP12 = db.query("PrecioProducto", null, "id_Producto = ?",id_Producto12, null, null, null);
                if (cursorCP12.moveToFirst() || cursorDP12.moveToFirst() || cursorPP12.moveToFirst()) {
                    // Ya existe
                    return true;
                }
                return false;
            //
            //
            // INTEGRIDAD PARA TIPO PRODUCTO
            //
            //
            case 14:
                // Verificar la existencia del Tipo Producto
                TipoProducto tipoProducto1 = (TipoProducto) dato;
                String[] id_TipoProducto1 = {String.valueOf(tipoProducto1.getId_TipoProducto())};
                abrir();
                Cursor cursorITP = db.query("TipoProducto", null, "id_TipoProducto = ?",id_TipoProducto1, null, null, null);
                if (cursorITP.moveToFirst()) {
                    // Ya existe
                    return true;
                }
                return false;
            case 15:
                // Verificar la existencia del Tipo Producto en Producto
                TipoProducto tipoProducto15 = (TipoProducto) dato;
                String[] id_TipoProducto15 = {String.valueOf(tipoProducto15.getId_TipoProducto())};
                abrir();
                Cursor cursorP15 = db.query("Producto", null, "id_TipoProducto = ?",id_TipoProducto15, null, null, null);
                if (cursorP15.moveToFirst()) {
                    // Ya existe
                    return true;
                }
                return false;
            //
            //
            // INTEGRIDAD PARA EMPLEADO
            //
            //
            case 41:
                //verificar la integridad de existencia del empleado
                Empleado empleado = (Empleado) dato;
                String[] id_Empleado = {String.valueOf(empleado.getId_Empleado())};
                abrir();
                Cursor cursor41 = db.query("Empleado",null, "id_Empleado=?", id_Empleado,null,null,null);
                if(cursor41.moveToFirst()){
                    return true;
                }
                return false;
            case 42:
                //verificar la integridad de existencia d eempleado en relaciones
                Empleado empleado1 = (Empleado) dato;
                String []  id_Empleado1 = {String.valueOf(empleado1.getId_Empleado())};
                abrir();
                Cursor cursorE1 = db.query("Envio",null,"id_Empleado=?" ,id_Empleado1,null, null,null);
                Cursor cursorL1 = db.query("Local",null, "id_Empleado=?",id_Empleado1, null,null,null);
                if (cursorE1.moveToFirst() || cursorL1.moveToFirst()){
                    return true;
                }
                return false;
            //
            //
            // INTEGRIDAD PARA EMPLEADO
            //
            //
            case 43:
                EncargadoLocal encargadoLocal = (EncargadoLocal) dato;
                String[] id_EncargadoLocal = {String.valueOf(encargadoLocal.getId_EncargadoLocal())};
                abrir();
                Cursor cursor43 = db.query("EncargadoLocal", null, "id_EncargadoLocal=?", id_EncargadoLocal,null,null,null);
                if(cursor43.moveToFirst()){
                    return true;
                }
                return true;

            default:
                return false;
            //
            //
            // Demas casos para las demas tablas
            //

        }
    }

    //
    //
    // PARA LLENAR LA BD CON DATOS INICIALES
    //
    //
    public String llenarBD() {
        // DATOS PARA COMBO
        final String[] VECcombo = {"1", "2", "3", "4", "5"};
        final String[] VECprecio = {"1.00", "2.00", "3.00", "4.00", "5.00"};


        // ABRIR BD
        abrir();

        // ELIMINAR REGISTROS
        db.execSQL("DELETE FROM Combo");




        // LLENAR TABLA COMBO
        Combo combo = new Combo();
        for (int i = 0; i < 5; i++) {
            combo.setId_Combo(Integer.parseInt(VECcombo[i]));
            combo.setPrecio_Combo(Float.parseFloat(VECprecio[i]));
            insertar(combo);
        }



        // CERRAR BD
        cerrar();
        return "Datos cargados correctamente";
    }



}
