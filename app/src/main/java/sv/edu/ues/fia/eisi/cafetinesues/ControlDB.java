package sv.edu.ues.fia.eisi.cafetinesues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlDB {
    // CAMPOS DE CADA TABLA
    private static final String[] campos_Combo = new String[]{"id_Combo", "precio_Combo"};



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
                        "CONSTRAINT FK_PRODUCTO_CATEGORIA FOREIGN KEY (ID_CATEGORIA) REFERENCES CATEGORIA(ID_CATEGORIA) ON DELETE RESTRICT);");

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

    public Combo consultar(int id_Combo) {
        String[] id = {String.valueOf(id_Combo)};
        // Verificar que exista el registro a consultar
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
    //

    // DEMAS METODOS PARA LAS DEMAS TABLAS



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
                // Verificar si ya existe ese id_Combo
                String[] id2 = {String.valueOf(dato)};
                abrir();
                Cursor cursor2 = db.query("Combo", null, "id_combo = ?", id2, null, null, null);
                if (cursor2.moveToFirst()) {
                    // Ya existe
                    return true;
                }
                return false;
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
