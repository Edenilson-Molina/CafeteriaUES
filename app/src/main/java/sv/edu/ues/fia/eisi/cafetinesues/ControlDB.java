package sv.edu.ues.fia.eisi.cafetinesues;

import android.content.ContentValues;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Date;
import java.util.Objects;

public class ControlDB {
    // CAMPOS DE CADA TABLA
    private static final String[] campos_Combo = new String[]{"id_Combo", "precio_Combo"};
    private static final String[] campos_ComboProducto = new String[]{"id_ComboProducto", "id_Combo", "id_Producto"};
    private static final String[] campos_DetallePedido = new String[]{"id_DetallePedido", "id_Pedido", "id_Combo", "id_Producto", "cantidad_Producto", "subtotal"};
    private static final String[] campos_Producto = new String[]{"id_Producto","id_TipoProducto","nombre_Producto","estado_Producto","precioactual_Producto"};
    private static final String[] campos_TipoProducto = new String[] {"id_TipoProducto","nombre_TipoProducto"};

    private static final String[] campos_PrecioProducto = new String[] {"id_PrecioProducto","id_Producto","id_ListaPrecio","precio"};
    private static final String[] campos_ListaPrecio = new String[] {"id_ListaPrecio","desde","hasta"};
    private static final String[] campos_Cliente = new String[] {"id_Cliente","id_Ubicacion","nombres_Cliente","apellidos_Cliente","fecha_nacimiento",};
    private static final String[] campos_TipoPago = new String[] {"id_TipoPago","nombre_TipoPago"};

    private static final String[] campos_Facultad = new String[] {"id_Facultad", "nombre_Facultad"};

    private static final String[] Campos_Empleado = new String[] {"id_Empleado","id_Local", "nombre_Empleado", "tipo_Empleado"};

    private static final String[] Campos_EncargadoLocal = new String[]{"id_EncargadoLocal","nombre_EncargadoLocal"};

    private static final String[] Campos_Local = new String[]{"id_Local","id_Ubicacion", "id_EncargadoLocal", "nombre_Local"};

    private static final String[] Campos_Pedido = new String[]{"id_Pedido","id_Cliente","id_TipoPago","id_Local","id_EventoEspecial","tipo_Pedido","estado_Pedido"};
    
    private static final String[] campos_Ubicacion = new String[] {"id_Ubicacion", "nombre_Ubicacion","descripcion_Ubicacion", "id_Facultad", "id_TipoUbicacion"};

    private static final String[] campos_TipoUbicacion = new String[] {"id_TipoUbicacion", "nombre_TipoUbicacion"};
    

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

                //
                //
                // T R I G G E R S
                //
                //

                // PARA EVITAR ELIMINAR UN COMBO SI TIENE COMBOPRODUCTO ASOCIADOS
                db.execSQL("CREATE TRIGGER integridad_comboproducto_delete_combo " +
                        "BEFORE DELETE ON COMBO " +
                        "FOR EACH ROW " +
                        "BEGIN " +
                        "SELECT RAISE(ABORT, 'No se puede eliminar este Combo porque existen ComboProducto asociados') " +
                        "FROM COMBOPRODUCTO " +
                        "WHERE ID_COMBO = OLD.ID_COMBO; " +
                        "END;");

                // PARA EVITAR ELIMINAR UN COMBO SI TIENE DETALLEPEDIDOS ASOCIADOS
                db.execSQL("CREATE TRIGGER integridad_detallepedido_delete_combo " +
                        "BEFORE DELETE ON COMBO " +
                        "FOR EACH ROW " +
                        "BEGIN " +
                        "SELECT RAISE(ABORT, 'No se puede eliminar este Combo porque existen DetallePedido asociados') " +
                        "FROM DETALLEPEDIDO " +
                        "WHERE ID_COMBO = OLD.ID_COMBO; " +
                        "END;");

                // ACTUALIZAR EL PRECIO DEL COMBO LUEGO DE INSERTAR UN NUEVO COMBO_PRODUCTO
                db.execSQL("CREATE TRIGGER actualizar_precio_combo_insert " +
                        "AFTER INSERT ON COMBOPRODUCTO " +
                        "BEGIN " +
                        "UPDATE COMBO " +
                        "SET PRECIO_COMBO = ( " +
                        "SELECT SUM(PRODUCTO.PRECIOACTUAL_PRODUCTO) " +
                        "FROM COMBOPRODUCTO " +
                        "JOIN PRODUCTO ON COMBOPRODUCTO.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO " +
                        "WHERE COMBOPRODUCTO.ID_COMBO = COMBO.ID_COMBO) " +
                        "WHERE COMBO.ID_COMBO = NEW.ID_COMBO; " +
                        "END;");
                //
                //
                // TABLAS PARA CONTROL DE USUARIOS
                //
                //

                db.execSQL("CREATE TABLE USUARIO(" +
                        "IDUSUARIO VARCHAR(2) NOT NULL," +
                        "NOMUSUARIO VARCHAR(30) NOT NULL," +
                        "CLAVE VARCHAR(5) NOT NULL," +
                        "CONSTRAINT PK_USUARIO PRIMARY KEY (IDUSUARIO));");

                db.execSQL("CREATE TABLE OPCIONCRUD(" +
                        "IDOPCIONCRUD VARCHAR(3) NOT NULL PRIMARY KEY," +
                        "DESOPCION VARCHAR(30) NOT NULL," +
                        "NUMCRUD INTEGER NOT NULL);");

                db.execSQL("CREATE TABLE ACCESOUSUARIO(" +
                        "IDUSUARIO VARCHAR(2) NOT NULL," +
                        "IDOPCIONCRUD VARCHAR(3) NOT NULL," +
                        "PRIMARY KEY(IDUSUARIO,IDOPCIONCRUD)," +
                        "FOREIGN KEY (IDUSUARIO) REFERENCES USUARIO(IDUSUARIO) ON DELETE RESTRICT," +
                        "FOREIGN KEY (IDOPCIONCRUD) REFERENCES OPCIONCRUD(IDOPCIONCRUD) ON DELETE RESTRICT" +
                        ");");



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
            try{
                contador += db.delete("Combo", "id_Combo='" + combo.getId_Combo() + "'", null);
            } catch (android.database.sqlite.SQLiteConstraintException e){
                e.printStackTrace();
                regAfectados = "No se puede eliminar el registro ya que existe una dependencia";
            }
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
        if (verificarIntegridad(comboProducto, 4)) {
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
        if (verificarIntegridad(comboProducto.getId_ComboProducto(), 5)) {
            contador += db.delete("ComboProducto", "id_ComboProducto='" + comboProducto.getId_ComboProducto() + "'", null);
        }
        regAfectados += contador;
        return regAfectados;
    }

    public ComboProducto consultarComboProducto(int id_ComboProducto) {
        String[] id = {String.valueOf(id_ComboProducto)};
        // Verificar que exista el registro a consultarCombo
        if (verificarIntegridad(id_ComboProducto, 5)) {
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
    //
    // METODOS PARA DETALLEPEDIDO
    //
    //
    //
    public String insertar(DetallePedido detallePedido) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        // Verificar si existe el registro a insertar
        if (verificarIntegridad(detallePedido, 6)) {
            regInsertados = "ID ya existente o Combo/Producto inexistente";
        } else {
            ContentValues detallePedidoValues = new ContentValues();
            detallePedidoValues.put("id_DetallePedido", detallePedido.getId_DetallePedido());
            detallePedidoValues.put("id_Pedido", detallePedido.getId_Pedido());

            // EL CAMPO QUE VENGA COMO "0", NO SERA INSERTADO
            if(detallePedido.getId_Combo() == 0) {
                detallePedidoValues.put("id_Producto", detallePedido.getId_Producto());
            } else {
                detallePedidoValues.put("id_Combo", detallePedido.getId_Combo());
            }

            detallePedidoValues.put("cantidad_Producto", detallePedido.getCantidad_Producto());
            detallePedidoValues.put("subtotal", detallePedido.getSubtotal());
            contador = db.insert("DetallePedido", null, detallePedidoValues);
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(DetallePedido detallePedido) {
        // Verificar si existe el registro a actualizar
        // En este caso nos interesa que SI exista
        // Al igual verificar que el Combo o Producto exista
        if (verificarIntegridad(detallePedido, 7)) {
            String[] id = {String.valueOf(detallePedido.getId_DetallePedido())};
            ContentValues cv = new ContentValues();
            cv.put("id_Pedido", detallePedido.getId_Pedido());

            // EL CAMPO QUE VENGA COMO "0", NO SERA INSERTADO
            if(detallePedido.getId_Combo() == 0) {
                cv.put("id_Producto", detallePedido.getId_Producto());
                // EL OTRO SE INSERTA COMO NULL
                cv.putNull("id_Combo");
            } else {
                cv.put("id_Combo", detallePedido.getId_Combo());
                // EL OTRO SE INSERTA COMO NULL
                cv.putNull("id_Producto");
            }

            cv.put("cantidad_Producto", detallePedido.getCantidad_Producto());
            cv.put("subtotal", detallePedido.getSubtotal());
            db.update("DetallePedido", cv, "id_DetallePedido = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Error en los datos ingresados";
        }
    }

    public String eliminar(DetallePedido detallePedido) {
        String regAfectados = "filas afectadas= ";
        int contador = 0;
        //Verificar si existe el registro a eliminar
        if (verificarIntegridad(detallePedido.getId_DetallePedido(), 8)) {
            contador += db.delete("DetallePedido", "id_DetallePedido='" + detallePedido.getId_DetallePedido() + "'", null);
        }
        regAfectados += contador;
        return regAfectados;
    }

    public DetallePedido consultarDetallePedido(int id_DetallePedido) {
        String[] id = {String.valueOf(id_DetallePedido)};
        // Verificar que exista el registro a consultarCombo
        if (verificarIntegridad(id_DetallePedido, 8)) {
            Cursor cursor = db.query("DetallePedido", campos_DetallePedido, "id_DetallePedido = ?", id, null, null, null);
            if (cursor.moveToFirst()) {
                DetallePedido detallePedido = new DetallePedido();
                detallePedido.setId_DetallePedido(cursor.getInt(0));
                detallePedido.setId_Pedido(cursor.getInt(1));

                // VERIFICAMOS CUAL VALOR VIENE COMO NULL, PARA MANEJARLO COMO "0" EN EL OBJETO
                Integer combo = cursor.getInt(2);
                if(Objects.isNull(combo)){
                    detallePedido.setId_Combo(0);
                } else {
                    detallePedido.setId_Combo(cursor.getInt(2));
                }
                Integer prod = cursor.getInt(3);
                if(Objects.isNull(prod)){
                    detallePedido.setId_Producto(0);
                } else {
                    detallePedido.setId_Producto(cursor.getInt(3));
                }

                detallePedido.setCantidad_Producto(cursor.getInt(4));
                detallePedido.setSubtotal(cursor.getFloat(5));
                return detallePedido;
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
    //
    // METODOS PARA PRECIO PRODUCTO
    //
    //
    //

    public String insertar(PrecioProducto precioProducto) {
        String regInsertado = "Registro Insertado Nº= ";
        long contador = 0;
        // Verificar existencia de un PrecioProducto con ese id y
        // que las foraneas no se repitan
        if (verificarIntegridad(precioProducto, 16)) {
            regInsertado = "Error al Insertar el registro";
        } else {
            if (verificarIntegridad(precioProducto,19))
            {
                ContentValues precioProductoValues = new ContentValues();
                precioProductoValues.put(campos_PrecioProducto[0], precioProducto.getId_PrecioProducto());
                precioProductoValues.put(campos_PrecioProducto[1], precioProducto.getId_Producto());
                precioProductoValues.put(campos_PrecioProducto[2], precioProducto.getId_ListaPrecio());
                precioProductoValues.put(campos_PrecioProducto[3], precioProducto.getPrecio());
                contador = db.insert("PrecioProducto", null, precioProductoValues);
                regInsertado += contador;
            }else{
                regInsertado = "Verifique las foraneas";

            }

        }
        return regInsertado;
    }

    public String actualizar(PrecioProducto precioProducto){
        String registro = "";
        if(verificarIntegridad(precioProducto,18) && verificarIntegridad(precioProducto,19)){
            String[] id_precioProducto = {String.valueOf(precioProducto.getId_PrecioProducto())};
            ContentValues precioProductoUpdate = new ContentValues();
            precioProductoUpdate.put(campos_PrecioProducto[1],precioProducto.getId_Producto());
            precioProductoUpdate.put(campos_PrecioProducto[2],precioProducto.getId_ListaPrecio());
            precioProductoUpdate.put(campos_PrecioProducto[3],precioProducto.getPrecio());
            db.update("PrecioProducto", precioProductoUpdate, "id_PrecioProducto = ?", id_precioProducto);
            return "ID: " + precioProducto.getId_PrecioProducto() + "\nActualizado Satisfactoriamente";
        }else {
            return "No existe o\nForaneas repetidas";
        }
    }

    public String eliminar(PrecioProducto precioProducto) {
        String regAfectados = "";
        int contador = 0;

        //Verificar si existe el registro a eliminar
        if (verificarIntegridad(precioProducto, 17)) {
            contador += db.delete("PrecioProducto", "id_PrecioProducto='" + precioProducto.getId_PrecioProducto() + "'", null);
            regAfectados = "Filas afectadas N° = " + contador;
        }
        else {
            regAfectados = "No existe";
        }

        return regAfectados;
    }

    public PrecioProducto consultarPrecioProducto(PrecioProducto precioProducto) {
        // Verificar que exista el registro a consultar de PrecioProducto
        if (verificarIntegridad(precioProducto, 17)) {
            Cursor cursor = db.query("PrecioProducto", campos_PrecioProducto, "id_PrecioProducto = ?",
                    new String[]{String.valueOf(precioProducto.getId_PrecioProducto())}, null, null, null);
            if (cursor.moveToFirst()) {
                PrecioProducto precioProductoConsulta = new PrecioProducto();
                precioProductoConsulta.setId_Producto(cursor.getInt(1));
                precioProductoConsulta.setId_ListaPrecio(cursor.getInt(2));
                precioProductoConsulta.setPrecio(cursor.getFloat(3));
                return precioProductoConsulta;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    //
    //
    // METODOS PARA CLIENTE
    //
    //
    //
    public String insertar(Cliente cliente) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        // Verificar si existe el registro a insertar
        if (verificarIntegridad(cliente, 21)) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            ContentValues clienteValues = new ContentValues();
            clienteValues.put(campos_Cliente[0],cliente.getId_cliente());
            clienteValues.put(campos_Cliente[1],cliente.getId_ubicacion());
            clienteValues.put(campos_Cliente[2],cliente.getNombres());
            clienteValues.put(campos_Cliente[3],cliente.getApellidos());
            clienteValues.put(campos_Cliente[4],cliente.getFecha_nacimiento());
            contador = db.insert("Cliente", null, clienteValues);
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }
    public String eliminar(Cliente cliente){
        String regAfectados = "";
        int contador = 0;

        //Verificar si existe el registro a eliminar
        if (verificarIntegridad(cliente, 21) ) {
            contador += db.delete("Cliente", "id_Cliente='" + cliente.getId_cliente() + "'", null);
            regAfectados = "Filas afectadas N° = " + contador;
        }
        else {
            regAfectados = "No existe o\nEsta asociado";
        }

        return regAfectados;
    }

    public String actualizar(Cliente cliente){
        // Verificar si existe el registro a actualizar
        // En este caso nos interesa que SI exista
        if (verificarIntegridad(cliente, 21)) {
            String[] id = {String.valueOf(cliente.getId_cliente())};
            ContentValues clienteUpdate = new ContentValues();
            clienteUpdate.put(campos_Cliente[1],cliente.getId_ubicacion());
            clienteUpdate.put(campos_Cliente[2],cliente.getNombres());
            clienteUpdate.put(campos_Cliente[3],cliente.getApellidos());
            clienteUpdate.put(campos_Cliente[4],cliente.getFecha_nacimiento());
            db.update("Cliente", clienteUpdate, "id_Cliente = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Tipo de Producto con código " + cliente.getId_cliente() + " no existe";
        }
    }

    public Cliente consultarCliente(Cliente cliente){
        // Verificar que exista el registro a consultar de Cliente
        if (verificarIntegridad(cliente, 21)) {
            Cursor cursor = db.query("Cliente", campos_Cliente, "id_Cliente = ?",
                    new String[]{String.valueOf(cliente.getId_cliente())}, null, null, null);
            if (cursor.moveToFirst()) {
                Cliente clienteConsulta = new Cliente();
                clienteConsulta.setId_cliente(cursor.getInt(0));
                clienteConsulta.setId_ubicacion(cursor.getInt(1));
                clienteConsulta.setNombres(cursor.getString(2));
                clienteConsulta.setApellidos(cursor.getString(3));
                clienteConsulta.setFecha_nacimiento(cursor.getString(4));
                return  clienteConsulta;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    //
    //
    // METODOS PARA TIPOPAGO
    //
    //
    //

    public String insertar(TipoPago tipoPago) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        // Verificar si existe el registro a insertar
        if (verificarIntegridad(tipoPago, 21)) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            ContentValues tipoPagoValues = new ContentValues();
            tipoPagoValues.put(campos_TipoPago[0],tipoPago.getId_TipoPago());
            tipoPagoValues.put(campos_TipoPago[1],tipoPago.getNombre_TipoPago());
            contador = db.insert("TipoPago", null, tipoPagoValues);
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }
    public String eliminar(TipoPago tipoPago){
        String regAfectados = "";
        int contador = 0;

        //Verificar si existe el registro a eliminar
        if (verificarIntegridad(tipoPago, 21)) {
            contador += db.delete("TipoPago", "id_TipoPago='" + tipoPago.getId_TipoPago() + "'", null);
            regAfectados = "Filas afectadas N° = " + contador;
        }
        else {
            regAfectados = "No existe o\nEsta asociado";
        }

        return regAfectados;
    }

    public String actualizar(TipoPago tipoPago){
        if (verificarIntegridad(tipoPago, 21)) {
            String[] id = { String.valueOf(tipoPago.getId_TipoPago())};
            ContentValues cv = new ContentValues();
            cv.put("nombre_TipoPago", tipoPago.getNombre_TipoPago());
            db.update("TipoPago", cv, "id_TipoPago = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Registro con ID " + tipoPago.getId_TipoPago() + " no existe";
        }
    }

    public TipoPago consultarTipoPago(String id_tp){
        String[] id = {id_tp};

        Cursor cursor = db.query("TipoPago", campos_TipoPago, "id_TipoPago = ?",
                id, null, null, null);
        if (cursor.moveToFirst()) {
            TipoPago tipoPago = new TipoPago();
            tipoPago.setNombre_TipoPago(cursor.getString(1));
            return tipoPago;
        } else {
            return null;
        }
    }
    //
    //
    //

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
                        resultado = "No existe el Empleado";
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
    //
    // METODOS PARA LOCAL
    //
    public String Insertar(Local local){
        String resultado = "";
        long contador;

        Ubicacion ubicacion = new Ubicacion();
        EncargadoLocal encargadoLocal = new EncargadoLocal();

        ubicacion.setId_Ubicacion(local.getId_Ubicacion());
        encargadoLocal.setId_EncargadoLocal(local.getId_EncargadoLocal());
        boolean existenciaLocal = verificarIntegridad(local,45);
        boolean existenciaRelaciones = verificarIntegridad(local, 44);
        if(!(existenciaLocal)&&(existenciaRelaciones)){
            ContentValues localValues = new ContentValues();
            localValues.put(Campos_Local[0],local.getId_Local());
            localValues.put(Campos_Local[1], local.getId_Ubicacion());
            localValues.put(Campos_Local[2], local.getId_EncargadoLocal());
            localValues.put(Campos_Local[3], local.getNombre_Local());
            contador = db.insert("Local", null, localValues);
            resultado = "Registro insertado N°= "+contador;
        }else if(existenciaLocal) {
            resultado = "El local ya existe";
            if(!existenciaRelaciones){
                if(resultado.isEmpty()){
                    resultado = "No existe el encargado o/y ubicacion";
                }
            }
        }
        return resultado;
    }
    public String Actualizar(Local local){
        String resultado = "";
        long contador;

        Ubicacion ubicacion = new Ubicacion();
        EncargadoLocal encargadoLocal = new EncargadoLocal();

        ubicacion.setId_Ubicacion(local.getId_Ubicacion());
        encargadoLocal.setId_EncargadoLocal(local.getId_EncargadoLocal());
        boolean existenciaLocal = verificarIntegridad(local,45);
        boolean existenciaRelaciones = verificarIntegridad(local, 44);

        if((existenciaLocal)&&(existenciaRelaciones)){
            String[] id = {String.valueOf(local.getId_Local())};
            ContentValues localUpdate = new ContentValues();
            localUpdate.put(Campos_Local[1], local.getId_Ubicacion());
            localUpdate.put(Campos_Local[2], local.getId_EncargadoLocal());
            localUpdate.put(Campos_Local[3], local.getNombre_Local());
            contador = db.update("Local", localUpdate,"id_local=?",id);
            resultado = "Registro actualizado correctamente";
        }else if(!existenciaLocal){
            resultado = "El local no existe";
            if(!existenciaRelaciones){
                resultado = "La ubicacion o/y el encargado no existe";
            }

        }
        return resultado;


    }
    public String Eliminar (Local local){
        String resultado = "";
        long contador;
        boolean existenciaLocal = verificarIntegridad(local,45);
        boolean existenciaRelaciones = verificarIntegridad(local, 44);
        if(existenciaLocal&& existenciaRelaciones){
            contador = db.delete("Local", "id_local='"+local.getId_Local()+"'", null);
            resultado = "Filas afectadas  N° ="+ contador;
        }else{
            resultado = "No existe el local";
        }
        return resultado;
    }
    public Local Consultar(Local local){
        if(verificarIntegridad(local,45)){
            Cursor cursor = db.query("Local",Campos_Local,"id_Local=?",new String[]{String.valueOf(local.getId_Local())}, null,null,null);
            if(cursor.moveToFirst()){
                Local localConsulta = new Local();
                localConsulta.setId_Local(cursor.getInt(0));
                localConsulta.setId_Ubicacion(cursor.getInt(1));
                localConsulta.setId_EncargadoLocal(cursor.getInt(2));
                localConsulta.setNombre_Local(cursor.getString(3));
                return localConsulta;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    //
    //

    //Metodos para FACULTAD


    public String Insertar(Facultad facultad ){
        String resultadoF="Registro Insertado N= ";
        long contador=0;
        boolean exitenciaFacultad = verificarIntegridad(facultad, 31);
        if (!exitenciaFacultad) {
            ContentValues facutadInsertar = new ContentValues();
            facutadInsertar.put(campos_Facultad[0],facultad.getId_Faculdad());
            facutadInsertar.put(campos_Facultad[1],facultad.getNombre_Facultad());

            contador=db.insert("Facultad", null, facutadInsertar);
            resultadoF = "Facultad Insertada";
        }
        else if (exitenciaFacultad){
            resultadoF = "La Falcutad ya existe";
        }
        return resultadoF;
    }

    public String Actualizar(Facultad facultad){
        String resultadoF="Registro Insertado N= ";
        long contador=0;
        boolean exitenciaFacultad = verificarIntegridad(facultad, 31);
        if (exitenciaFacultad){
            ContentValues facultadActualizar = new ContentValues();
            String[] id = {String.valueOf(facultad.getId_Faculdad())};
            facultadActualizar.put(campos_Facultad[1],facultad.getNombre_Facultad());

            contador = db.update("Facultad", facultadActualizar, "id_Facultad=?", id);
            resultadoF="Registro Actualizado Correctamente";
        } else if (!exitenciaFacultad) {
            resultadoF="Registro no existe, no se puede actualizar";
        }
        return resultadoF;
    }

    public String Eliminar(Facultad facultad){
        String resultadoF="Registro Insertado N= ";
        long contador=0;
        boolean exitenciaFacultad = verificarIntegridad(facultad, 31);
        if (exitenciaFacultad){
            contador=db.delete("Facultad", "id_Facultad='" + facultad.getId_Faculdad() + "'", null);
            resultadoF="Se elimino correctamente";
        } else if (!exitenciaFacultad) {
            resultadoF="Registro no existe, no se puede eliminar";
        }
        return resultadoF;
    }

    public Facultad consultarFacultad(Facultad facultad){
        boolean exitenciaFacultad = verificarIntegridad(facultad, 31);
        if (exitenciaFacultad){
            Cursor cursor = db.query("Facultad", campos_Facultad, "id_Facultad=?", new String[]{String.valueOf(facultad.getId_Faculdad())},null,null,null );
            if (cursor.moveToFirst()){
                Facultad facultadConsultar = new Facultad();
                facultadConsultar.setId_Faculdad(cursor.getInt(0));
                facultadConsultar.setNombre_Facultad(cursor.getString(1));
                return facultadConsultar;
            }else {return null;}
        }
        else {return null;}


    }
    //
    //
    //
    //Metodos para Pedidos
    //
    //
    //
    public String insertar(Pedido pedido){
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        // Verificar si existe el registro a insertar
        if (verificarIntegridad(pedido, 22)) {
        //if(false){
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            ContentValues pedidoValues = new ContentValues();
            pedidoValues.put(Campos_Pedido[0],pedido.getId_Pedido());
            pedidoValues.put(Campos_Pedido[1],pedido.getId_cliente());
            pedidoValues.put(Campos_Pedido[2],pedido.getId_tipoPago());
            pedidoValues.put(Campos_Pedido[3],pedido.getId_local());
            pedidoValues.put(Campos_Pedido[4],pedido.getId_eventoEspecial());
            pedidoValues.put(Campos_Pedido[5],pedido.getTipo_Pedido());
            pedidoValues.put(Campos_Pedido[6],pedido.getEstado_Pedido());
            pedidoValues.put(Campos_Pedido[7],pedido.getMonto_Pedido());
            contador = db.insert("Pedido", null, pedidoValues);
            regInsertados = regInsertados + contador;
        }
        return regInsertados;

    }
    public String actualizar(Pedido pedido){
        // Verificar si existe el registro a actualizar
        // En este caso nos interesa que SI exista
        if (verificarIntegridad(pedido, 22)) {
            String[] id = {String.valueOf(pedido.getId_Pedido())};
            ContentValues pedidoValues = new ContentValues();
            pedidoValues.put(Campos_Pedido[1],pedido.getId_cliente());
            pedidoValues.put(Campos_Pedido[2],pedido.getId_tipoPago());
            pedidoValues.put(Campos_Pedido[3],pedido.getId_local());
            pedidoValues.put(Campos_Pedido[4],pedido.getId_eventoEspecial());
            pedidoValues.put(Campos_Pedido[5],pedido.getTipo_Pedido());
            pedidoValues.put(Campos_Pedido[6],pedido.getEstado_Pedido());
            pedidoValues.put(Campos_Pedido[7],pedido.getMonto_Pedido());
            db.update("Pedido", pedidoValues, "id_Pedido = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Tipo de Producto con código " + pedido.getId_Pedido() + " no existe";
        }
    }
    public String eliminar(Pedido pedido){
        String regAfectados = "";
        int contador = 0;

        //Verificar si existe el registro a eliminar
        if (verificarIntegridad(pedido, 22) ) {
            contador += db.delete("Pedido", "id_Pedido='" + pedido.getId_Pedido() + "'", null);
            regAfectados = "Filas afectadas N° = " + contador;
        }
        else {
            regAfectados = "No existe o\nEsta asociado";
        }

        return regAfectados;
    }

    public Pedido consultar(Pedido pedido){
        // Verificar que exista el registro a consultar de Pedido
        if (verificarIntegridad(pedido, 22)) {
            Cursor cursor = db.query("Pedido",Campos_Pedido, "id_Pedido = ?",
                    new String[]{String.valueOf(pedido.getId_cliente())}, null, null, null);
            if (cursor.moveToFirst()) {
                Pedido pedidoConsulta = new Pedido();
                pedidoConsulta.setId_Pedido(cursor.getInt(0));
                pedidoConsulta.setId_cliente(cursor.getInt(1));
                pedidoConsulta.setId_tipoPago(cursor.getInt(2));
                pedidoConsulta.setId_local(cursor.getInt(3));
                pedidoConsulta.setId_eventoEspecial(cursor.getInt(4));
                pedidoConsulta.setTipo_Pedido(cursor.getString(5));
                pedidoConsulta.setEstado_Pedido(cursor.getString(7));
                pedidoConsulta.setMonto_Pedido(cursor.getFloat(6));


                return  pedidoConsulta;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    //METODOS PARA UBICACION

    public String insertar(Ubicacion ubicacion){
        String resultafoF="Registro insertado N=";
        long contador=0;
        boolean existenciaUbicacion = verificarIntegridad(ubicacion,32);
        if (!existenciaUbicacion){
            ContentValues ubicacionInsertar = new ContentValues();
            ubicacionInsertar.put(campos_Ubicacion[0],ubicacion.getId_Ubicacion());
            ubicacionInsertar.put(campos_Ubicacion[1],ubicacion.getNombre_Ubicacion());
            ubicacionInsertar.put(campos_Ubicacion[2],ubicacion.getDescripcion_Ubicacion());
            ubicacionInsertar.put(campos_Ubicacion[3],ubicacion.getId_Facultad());
            ubicacionInsertar.put(campos_Ubicacion[4],ubicacion.getId_TipoUbicacion());

            contador=db.insert("Ubicacion",null,ubicacionInsertar);
            resultafoF="Ubicacion Insertada";
        } else if (existenciaUbicacion) {
            resultafoF="La Ubicacion ya existe";
        }
        return resultafoF;
    }

    public String actualizar(Ubicacion ubicacion){
        String resultadoF="Registro Actualizado N= ";
        long contador=0;
        boolean existenciaUbicacion = verificarIntegridad(ubicacion,32);
        if (existenciaUbicacion){
            ContentValues ubicacionActualizar = new ContentValues();
            String[] id = {String.valueOf(ubicacion.getId_Ubicacion())};
            ubicacionActualizar.put(campos_Ubicacion[1],ubicacion.getNombre_Ubicacion());
            ubicacionActualizar.put(campos_Ubicacion[2],ubicacion.getDescripcion_Ubicacion());
            ubicacionActualizar.put(campos_Ubicacion[3],ubicacion.getId_Facultad());
            ubicacionActualizar.put(campos_Ubicacion[4],ubicacion.getId_TipoUbicacion());


            contador = db.update("Ubicacion", ubicacionActualizar, "id_Ubicacion=?",id);
            resultadoF="Ubicacion Actualizada Correctamente";

        } else if (!existenciaUbicacion) {
            resultadoF="Registro no existe, no se puede actualizar";
        }
        return resultadoF;

    }

    public String eliminar(Ubicacion ubicacion){
        String resultadoF = "Registro Insertado N= ";
        long contador=0;
        boolean existenciaUbicacion = verificarIntegridad(ubicacion,32);
        boolean existenciaForaneas = verificarIntegridad(ubicacion,35);
        if (existenciaUbicacion && !existenciaForaneas){
            contador= db.delete("Ubicacion", "id_Ubicacion='" + ubicacion.getId_Ubicacion() + "'", null);
            resultadoF="Se elimino correctamente";
        } else if (!existenciaUbicacion) {
            resultadoF="Registro no existe, o sepuede eliminar";
        }
        return resultadoF;

    }

    public Ubicacion consultarUbicacion(Ubicacion ubicacion){
        boolean existenciaUbicacion = verificarIntegridad(ubicacion,32);
        if (existenciaUbicacion){
            Cursor cursor = db.query("Ubicacion", campos_Ubicacion, "id_Ubicacion=?", new String[]{String.valueOf(ubicacion.getId_Ubicacion())},null,null,null);
            if (cursor.moveToFirst()){
                Ubicacion ubicacionConsultar = new Ubicacion();
                ubicacionConsultar.setId_Ubicacion(cursor.getInt(0));
                ubicacionConsultar.setNombre_Ubicacion(cursor.getString(1));
                ubicacionConsultar.setDescripcion_Ubicacion(cursor.getString(2));
                ubicacionConsultar.setId_Facultad(cursor.getInt(3));
                ubicacionConsultar.setId_TipoUbicacion(cursor.getInt(4));

                return  ubicacionConsultar;
            }else {return null;}
        }else {return null;}

    }

    //Metodos para TipoUbicacion

    public String insertar(TipoUbicacion tipoUbicacion){
        String resultadoF="Registro Insertado N= ";
        long contador=0;
        boolean existenciaTipoUbicacion = verificarIntegridad(tipoUbicacion, 34);
        if (!existenciaTipoUbicacion){
            ContentValues tipoUbicacionInsertar = new ContentValues();
            tipoUbicacionInsertar.put(campos_TipoUbicacion[0],tipoUbicacion.getId_TipoUbicacion());
            tipoUbicacionInsertar.put(campos_TipoUbicacion[1],tipoUbicacion.getNombre_TipoUbicacion());

            contador=db.insert("TipoUbicacion",null,tipoUbicacionInsertar);
            resultadoF = "Tipo de Ubicacion Insertada";
        } else if (existenciaTipoUbicacion) {
            resultadoF = "El Tipo Ubicacion ya existe";
        }
        return resultadoF;
    }

    public String actualizar(TipoUbicacion tipoUbicacion){
        String resultadoF = "Registro Insertado N= ";
        long contador=0;
        boolean existenciaTipoUbicacion = verificarIntegridad(tipoUbicacion, 34);
        if (existenciaTipoUbicacion){
            ContentValues tipoUbicacionActualizar = new ContentValues();
            String[] id = {String.valueOf(tipoUbicacion.getId_TipoUbicacion())};
            tipoUbicacionActualizar.put(campos_TipoUbicacion[1],tipoUbicacion.getNombre_TipoUbicacion());

            contador = db.update("TipoUbicacion", tipoUbicacionActualizar,"id_TipoUbicacion=?",id);
            resultadoF = "Registro Actualizado Correctamente";
        } else if (!existenciaTipoUbicacion) {
            resultadoF="Registro no existe, no se puede actualizar";
        }
        return resultadoF;
    }

    public String eliminar(TipoUbicacion tipoUbicacion){
        String resultadoF="Registro Insertado N= ";
        long contador=0;
        boolean existenciaTipoUbicacion = verificarIntegridad(tipoUbicacion,34);
        if (existenciaTipoUbicacion){
            contador=db.delete("TipoUbicacion", "id_TipoUbicacion='" + tipoUbicacion.getId_TipoUbicacion() + "'",null);
            resultadoF="Se elimino correctamente";
        } else if (!existenciaTipoUbicacion) {
            resultadoF="Registro no existe, no se puede eliminar";
        }
        return resultadoF;
    }

    public TipoUbicacion consultarTipoUbicacion(TipoUbicacion tipoUbicacion){
        boolean existenciaTipoUbicacion = verificarIntegridad(tipoUbicacion,34);
        if(existenciaTipoUbicacion){
            Cursor cursor = db.query("TipoUbicacion", campos_TipoUbicacion, "id_TipoUbicacion=?", new String[]{String.valueOf(tipoUbicacion.getId_TipoUbicacion())},null,null,null);
            if (cursor.moveToFirst()){
                TipoUbicacion tipoUbicacionConsultar = new TipoUbicacion();
                tipoUbicacionConsultar.setId_TipoUbicacion(cursor.getInt(0));
                tipoUbicacionConsultar.setNombre_TipoUbicacion(cursor.getString(1));
                return tipoUbicacionConsultar;
            }else {return null;}
        }else {return null;}
    }

    //
    //
    // VERIFICACIÓN DE INTEGRIDAD
    //
    //
    private boolean verificarIntegridad (Object dato,int relacion) throws SQLException {
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
                // PARA INSERTAR
                //
                // Verificar si ya existe ese COMBO_PRODUCTO y si existen  el Combo y Producto especificado
                ComboProducto comboProducto = (ComboProducto) dato;
                String[] id1c = {String.valueOf(comboProducto.getId_ComboProducto())};
                String[] id2c = {String.valueOf(comboProducto.getId_Combo())};
                String[] id3c = {String.valueOf(comboProducto.getId_Producto())};
                abrir();
                Cursor cursor1c = db.query("ComboProducto", null, "id_ComboProducto = ?", id1c, null, null, null);
                Cursor cursor2c = db.query("Combo", null, "id_Combo = ?", id2c, null, null, null);
                Cursor cursor3c = db.query("Producto", null, "id_Producto = ?", id3c, null, null, null);
                if (cursor1c.moveToFirst()) {
                    // ID duplicado, no se debe insertar
                    return true;
                }
                if (cursor2c.moveToFirst() && cursor3c.moveToFirst()) {
                    //Se encontraron los ID's, se puede insertar
                    return false;
                }
                return true;
            case 4:
                // PARA ACTUALIZAR
                //
                // Verificar si ya existe ese COMBO_PRODUCTO y si existen  el Combo y Producto especificado
                ComboProducto comboProducto1 = (ComboProducto) dato;
                String[] id1cQ = {String.valueOf(comboProducto1.getId_ComboProducto())};
                String[] id2cQ = {String.valueOf(comboProducto1.getId_Combo())};
                String[] id3cQ = {String.valueOf(comboProducto1.getId_Producto())};
                abrir();
                Cursor cursor1cQ = db.query("ComboProducto", null, "id_ComboProducto = ?", id1cQ, null, null, null);
                Cursor cursor2cQ = db.query("Combo", null, "id_Combo = ?", id2cQ, null, null, null);
                Cursor cursor3cQ = db.query("Producto", null, "id_Producto = ?", id3cQ, null, null, null);
                if (cursor1cQ.moveToFirst() && cursor2cQ.moveToFirst() && cursor3cQ.moveToFirst()) {
                    // LOS 3 ID'S EXISTEN, SE PUEDE ACTUALIZAR
                    return true;
                }
                return false;
            case 5:
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
            // INTEGRIDAD PARA DETALLE_PEDIDO
            //
            //
            case 6:
                // Verificar si ya existe ese DETALLE_PEDIDO y si existen  el Pedido, Combo y Producto especificado
                DetallePedido detallePedido = (DetallePedido) dato;
                String[] id1d = {String.valueOf(detallePedido.getId_DetallePedido())};
                String[] id2d = {String.valueOf(detallePedido.getId_Pedido())};

                abrir();

                Cursor cursor1d = db.query("DetallePedido", null, "id_DetallePedido = ?", id1d, null, null, null);
                Cursor cursor2d = db.query("Pedido", null, "id_Pedido = ?", id2d, null, null, null);

                // PRIMERO VERIFICAMOS SI ESE ID YA EXISTE EN LA BDD
                if (cursor1d.moveToFirst()) {
                    // Ya existe ese DetallePedido, no se puede insertar
                    return true;
                }

                // AHORA VERIFICAMOS PRIMERO CUAL CAMPO ES "0"
                // Y PARA EL QUE NO ES "0", VERIFICAR SI EXISTE
                if (detallePedido.getId_Combo() == 0) {
                    String[] id4d = {String.valueOf(detallePedido.getId_Producto())};
                    Cursor cursor4d = db.query("Producto", null, "id_Producto = ?", id4d, null, null, null);
                    if (cursor2d.moveToFirst() && cursor4d.moveToFirst()) {
                        //Se encontraron datos, por lo que esta CORRECTO
                        return false;
                    }
                } else {
                    String[] id3d = {String.valueOf(detallePedido.getId_Combo())};
                    Cursor cursor3d = db.query("Combo", null, "id_Combo = ?", id3d, null, null, null);
                    if (cursor2d.moveToFirst() && cursor3d.moveToFirst()) {
                        //Se encontraron datos, por lo que esta CORRECTO
                        return false;
                    }

                }
                return true;

            //Para actualizar DetallePedido
            case 7:
                // Verificar si ya existe ese DETALLE_PEDIDO y si existen  el Pedido, Combo y Producto especificado
                DetallePedido detallePedido1 = (DetallePedido) dato;
                String[] id1d1 = {String.valueOf(detallePedido1.getId_DetallePedido())};
                String[] id2d1 = {String.valueOf(detallePedido1.getId_Pedido())};

                abrir();

                Cursor cursor1d1 = db.query("DetallePedido", null, "id_DetallePedido = ?", id1d1, null, null, null);
                Cursor cursor2d1 = db.query("Pedido", null, "id_Pedido = ?", id2d1, null, null, null);

                // AHORA VERIFICAMOS PRIMERO CUAL CAMPO ES "0"
                // Y PARA EL QUE NO ES "0", VERIFICAR SI EXISTE
                if (detallePedido1.getId_Combo() == 0) {
                    String[] id4d = {String.valueOf(detallePedido1.getId_Producto())};
                    Cursor cursor4d = db.query("Producto", null, "id_Producto = ?", id4d, null, null, null);
                    if (cursor1d1.moveToFirst() && cursor2d1.moveToFirst() && cursor4d.moveToFirst()) {
                        //Se encontraron datos, por lo que esta CORRECTO
                        return true;
                    }
                } else {
                    String[] id3d = {String.valueOf(detallePedido1.getId_Combo())};
                    Cursor cursor3d = db.query("Combo", null, "id_Combo = ?", id3d, null, null, null);
                    if (cursor1d1.moveToFirst() && cursor2d1.moveToFirst() && cursor3d.moveToFirst()) {
                        //Se encontraron datos, por lo que esta CORRECTO
                        return true;
                    }

                }
                return false;
            //Para eliminar DetallePedido
            case 8:
                // Verificar si existe ese id_DetallePedido
                String[] iddp = {String.valueOf(dato)};
                abrir();
                Cursor cursordp = db.query("DetallePedido", null, "id_DetallePedido = ?", iddp, null, null, null);
                if (cursordp.moveToFirst()) {
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
                Cursor cursorIP = db.query("Producto", null, "id_Producto = ?", id_Producto1, null, null, null);
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
                Cursor cursorCP12 = db.query("ComboProducto", null, "id_Producto = ?", id_Producto12, null, null, null);
                Cursor cursorDP12 = db.query("DetallePedido", null, "id_Producto = ?", id_Producto12, null, null, null);
                Cursor cursorPP12 = db.query("PrecioProducto", null, "id_Producto = ?", id_Producto12, null, null, null);
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
                Cursor cursorITP = db.query("TipoProducto", null, "id_TipoProducto = ?", id_TipoProducto1, null, null, null);
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
                Cursor cursorP15 = db.query("Producto", null, "id_TipoProducto = ?", id_TipoProducto15, null, null, null);
                if (cursorP15.moveToFirst()) {
                    // Ya existe
                    return true;
                }
                return false;
            case 16:
                // Verificar la existencia del Precio Producto
                // y tambien sus id_Producto y id_ListaPrecio
                PrecioProducto precioProducto16 = (PrecioProducto) dato;
                String[] id_PrecioProducto16 = {String.valueOf(precioProducto16.getId_PrecioProducto())};
                String[] integridad16 = {String.valueOf(precioProducto16.getId_ListaPrecio()), String.valueOf(precioProducto16.getId_Producto())};
                abrir();
                Cursor cursorPP16 = db.query("PrecioProducto", campos_PrecioProducto, "id_PrecioProducto = ?", id_PrecioProducto16, null, null, null);
                Cursor cursor2PP16 = db.query("PrecioProducto", campos_PrecioProducto, "id_ListaPrecio = ? AND id_Producto = ?", integridad16, null, null, null);

                if (cursorPP16.moveToFirst() || cursor2PP16.moveToFirst()) {
                    // Ya existe
                    return true;
                }

                return false;
            case 17:
                // Verificar la existencia del Precio Producto
                PrecioProducto precioProducto17 = (PrecioProducto) dato;
                String[] id_PrecioProducto17 = {String.valueOf(precioProducto17.getId_PrecioProducto())};
                abrir();
                Cursor cursorPP17 = db.query("PrecioProducto", null, "id_PrecioProducto = ?", id_PrecioProducto17, null, null, null);
                if (cursorPP17.moveToFirst()) {
                    // Ya existe
                    return true;
                }
            case 18:
                // Verificar la existencia del Precio Producto
                // y tambien sus id_Producto y id_ListaPrecio
                PrecioProducto precioProducto18 = (PrecioProducto) dato;
                String[] id_PrecioProducto18 = {String.valueOf(precioProducto18.getId_PrecioProducto())};
                String[] integridad18 = {String.valueOf(precioProducto18.getId_ListaPrecio()), String.valueOf(precioProducto18.getId_Producto())};
                abrir();
                Cursor cursorPP18 = db.query("PrecioProducto", campos_PrecioProducto, "id_PrecioProducto = ?", id_PrecioProducto18, null, null, null);
                Cursor cursor2PP18 = db.query("PrecioProducto", campos_PrecioProducto, "id_ListaPrecio = ? AND id_Producto = ?", integridad18, null, null, null);

                if (cursorPP18.moveToFirst()) {

                    if (!(cursor2PP18.moveToFirst()) || (cursor2PP18.getInt(0) == cursorPP18.getInt(0))) {
                        return true;
                    } else {
                        return false;
                    }

                } else {
                    return false;
                }
            case 19:
                // Verificar la existencia de producto y listaProducto
                PrecioProducto precioProducto19 = (PrecioProducto) dato;
                String[] producto19 = {String.valueOf(precioProducto19.getId_Producto())};
                String[] listaPrecio19 = {String.valueOf(precioProducto19.getId_ListaPrecio())};
                abrir();
                Cursor cursorP19 = db.query("Producto", null, "id_Producto = ?", producto19, null, null, null);
                Cursor cursorLP19 = db.query("ListaPrecio", null, "id_ListaPrecio = ?", listaPrecio19, null, null, null);
                if (cursorP19.moveToFirst() && cursorLP19.moveToFirst()) {
                    // Ya existe
                    return true;
                }
                return false;
            case 21:
                Cliente cliente = (Cliente) dato;
                String[] id_clien = {String.valueOf(cliente.getId_cliente())};
                abrir();
                Cursor cursorIP21 = db.query("Cliente", null, "id_Cliente = ?", id_clien, null, null, null);
                if (cursorIP21.moveToFirst()) {
                    // Ya existe
                    return true;
                }
                return false;
            case 22:
                Pedido pedido = (Pedido) dato;
                String[] id_pedi = {String.valueOf(pedido.getId_Pedido())};
                abrir();
                Cursor cursorIP22 = db.query("Pedido", null, "id_Pedido = ?", id_pedi, null, null, null);
                if (cursorIP22.moveToFirst()) {
                    // Ya existe
                    return true;
                }
                return false;


            //Para eliminar DetallePedido

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
                Cursor cursor41 = db.query("Empleado", null, "id_Empleado=?", id_Empleado, null, null, null);
                if (cursor41.moveToFirst()) {
                    return true;
                }
                return false;
            case 42:
                //verificar la integridad de existencia d eempleado en relaciones
                Empleado empleado1 = (Empleado) dato;
                String[] id_Empleado1 = {String.valueOf(empleado1.getId_Empleado())};
                abrir();
                Cursor cursorE1 = db.query("Empleado", null, "id_Empleado=?", id_Empleado1, null, null, null);
                Cursor cursorL1 = db.query("Local", null, "id_Empleado=?", id_Empleado1, null, null, null);
                if (cursorE1.moveToFirst() && cursorL1.moveToFirst()) {
                    return true;
                }
                return false;
            //
            //
            // INTEGRIDAD PARA ENCARGADOLOCAL
            //
            //
            case 43:
                EncargadoLocal encargadoLocal = (EncargadoLocal) dato;
                String[] id_EncargadoLocal = {String.valueOf(encargadoLocal.getId_EncargadoLocal())};
                abrir();
                Cursor cursor43 = db.query("EncargadoLocal", null, "id_EncargadoLocal=?", id_EncargadoLocal, null, null, null);
                if (cursor43.moveToFirst()) {
                    return true;
                }
                return false;
            //
            //
            // INTEGRIDAD PARA LOCAL
            //
            //
            case 44:
                //existencia de local en relaciones

                Local local = (Local) dato;

                String[] id_Local = {String.valueOf(local.getId_Local())};
                String[] id_EncargadoLoc = {String.valueOf(local.getId_EncargadoLocal())};
                String[] id_Ubicacion = {String.valueOf(local.getId_Ubicacion())};

                abrir();
                Cursor cursorEN2 = db.query("EncargadoLocal", null, "id_EncargadoLocal=?", id_EncargadoLoc, null, null, null);
                Cursor cursorU1 = db.query("Ubicacion", null, "id_Ubicacion=?", id_Ubicacion, null, null, null);
                Cursor cursorL2 = db.query("Local", null, "id_Local=?", id_Local, null, null, null);
                if (cursorEN2.moveToFirst() || cursorU1.moveToFirst() || cursorL2.moveToFirst()) {
                    return true;
                }
                return false;
            case 45:
                //existencia de Local
                Local local1 = (Local) dato;
                String[] id_Local2 = {String.valueOf(local1.getId_Local())};
                abrir();
                Cursor cursorL3 = db.query("Local", null, "id_Local=?", id_Local2, null, null, null);
                if (cursorL3.moveToFirst()) {
                    return true;
                }
                return false;

            //
            //
            // INTEGRIDAD PARA FACULTAD
            //
            //

            case 31:
                //Verificar la exitencia de Facultad
                Facultad facultad = (Facultad) dato;
                String[] id_Facutad = {String.valueOf(facultad.getId_Faculdad())};
                abrir();
                Cursor cursorP31 = db.query("Facultad", null, "id_Facultad = ?", id_Facutad, null, null, null);
                if (cursorP31.moveToFirst()) {
                    //Ya existe
                    return true;
                }
                return false;

            //
            //
            // INTEGRIDAD PARA UBICACIÓN
            //
            //

            case 32:
                //Verificar la existencia de Ubicacion
                Ubicacion ubicacion = (Ubicacion) dato;
                String[] id_Ubicacion32 = {String.valueOf(ubicacion.getId_Ubicacion())};
                abrir();
                Cursor cursorP32 = db.query("Ubicacion", null, "id_Ubicacion=?", id_Ubicacion32, null, null, null);
                if (cursorP32.moveToFirst()) {
                    //Ya existe
                    return true;
                } else {
                    return false;
                }


            case 35:
                //Verificar la existencia de Ubicacion en Cliente y Local
                Ubicacion ubicacion35 = (Ubicacion) dato;
                String[] id_Ubicacion35 = {String.valueOf(ubicacion35.getId_Ubicacion())};
                abrir();
                Cursor cursorP35 = db.query("Local", null, "id_Ubicacion=?", id_Ubicacion35, null, null, null);
                Cursor cursorP352 = db.query("Cliente", null, "id_Ubicacion=?", id_Ubicacion35, null, null, null);
                if (cursorP35.moveToFirst() || cursorP352.moveToFirst()) {
                    return true;
                } else return false;

                //
                //
                // INTEGRIDAD PARA TIPOUBICACIÓN
                //
                //
            case 34:
                //Verificar la existencia de TipoUbicacion
                TipoUbicacion tipoUbicacion = (TipoUbicacion) dato;
                String[] id_TipoUbicacion = {String.valueOf(tipoUbicacion.getId_TipoUbicacion())};
                abrir();
                Cursor cursorP34 = db.query("TipoUbicacion", null, "id_TipoUbicacion=?", id_TipoUbicacion, null, null, null);
                if (cursorP34.moveToFirst()) {
                    //Ya existe
                    return true;
                }

            default:
                return false;
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

        //DATOS PARA PRODUCTO
        final String[] VECproducto = {"1", "2", "3", "4", "5"};
        final String[] VECnombre = {"Producto1", "Producto2", "Producto3", "Producto4", "Producto5"};
        final String[] VECestado_producto = {"Estado1", "Estado2", "Estado3", "Estado4", "Estado5"};
        final String[] VECprecioActualProducto = {"1.00", "2.00", "3.00", "4.00", "5.00"};
        final String[] VECidTipoProducto = {"1", "2", "3", "4", "5"};

        //DATOS PARA TIPO PRODUCTO
        final String[] VECtipoProducto = {"1", "2", "3", "4", "5"};
        final String[] VECnombreTipoProducto = {"TipoProducto1", "TipoProducto2", "TipoProducto3", "TipoProducto4", "TipoProducto5"};

        // DATOS PARA COMBO_PRODUCTO
        final String[] VECComboProducto = {"1", "2", "3", "4", "5"};
        final String[] VECidCombo = {"1", "2", "3", "4", "5"};
        final String[] VECidProducto = {"1", "2", "3", "4", "5"};

        // DATOS PARA DETALLE_PEDIDO


        // DATOS PARA LISTA_PRECIO
        final String[] VECListaPrecio = {"1","2","3","4","5"};
        final String[] VECDesde = {"06/05/2023","07/05/2023","08/05/2023","09/05/2023","10/05/2023"};
        final String[] VECHasta = {"06/05/2023","07/05/2023","08/05/2023","09/05/2023","10/05/2023"};


        // DATOS TIPO_UBICACION
        final String[] VECid_TipoUbicacion = {"1", "2"};
        final String[] VECnombre_TipoUbicacion = {"Rural","Urbana"};


        //DATOS Facultad
        final String[] VECid_Facultad = {"1", "2"};
        final String[] VECnombre_Facultad = {"Medicina","Humanidades"};

        //DATOS ENCARGADO LOCAL
        final String[] VECid_EncargadoLocal = {"1", "2"};
        final String[] VECnombre_EncargadoLocal = {"Juan","Pedro"};

        //DATOS Ubicacion
        final String[] VECid_Ubicacion = {"1", "2"};
        final String[] VECnombre_Ubicacion = {"Oficinas Seguro","Palillos chinos"};
        final String[] VECdescripcion_Ubicacion = {"Bvd.Ejercito continuo a Carcel de mujeres","Plaza soyapango"};
        final String[] VECid_ubicacionTipoUbicacion = {"1", "2"};
        final String[] VECid_ubicacionFacultad = {"1", "2"};

        //DATOS LOCAL
        final String[] VECid_Local = {"1", "2"};
        final String[] VECnombre_Local = {"Local1","Local2"};
        final String[] VECid_localEncargadoLocal = {"1", "2"};
        final String[] VECid_localUbicacion = {"1", "2"};

        // DATOS EMPLEADO
        final String[] VECid_Empleado = {"1", "2"};
        final String[] VECid_LocalEmpleado = {"1", "2"};
        final String[] VECnombre_Empleado = {"Empleado1","Empleado2"};
        final String[] VECtipoEmpleado = {"Tipo1", "Tipo2"};

        /*
        Elmer Edenilson Rosales Molina		RM20001
        Tomás Eduardo Romero Cañas		    RC18087
        Manuel Alejandro Rico Rodriguez		RR20044
        Josias Abner Rivas Fuentes		    RF20010
        David Alejandro Deras Cerros		DC19019
        */

        // DATOS Usuario
        final String[] VECid_Usuario = {"RM","RC","RR","RF","DC"};
        final String[] VECnom_Usuario = {"Edenilson","Tomas","Manuel","Josias","David"};
        final String[] VECclave = {"admin","user","RR20044","RF20010","DC19019"};

        // DATOS Envio
        final String[] VECid_Envio = {"1","2"};
        final String[] VECid_EmpleadoEnvio = {"1","2"};
        final String[] VECfecha_Envio = {"06/05/2023","07/05/2023"};
        // DATOS OpcionCRUD
        final String[] VECid_OpcionCRUD = {"010","020","030","040"};
        final String[] VECdes_OpcionCRUD = {"Insertar","Eliminar","Consultar","Actualizar"};
        final String[] VECnum_OpcionCRUD = {"1","2","3","4"};

        // DATOS Cliente
        final String[] VECid_Cliente = {"1","2"};
        final String[] VECid_UbicacionCliente = {"1","2"};
        final String[] VECnombres_Cliente = {"Cliente1","Cliente2"};
        final String[] VECapellidos_Cliente = {"Cliente1","Cliente2"};
        final String[] VECfecha_nacimiento = {"06/05/2023","07/05/2023"};


        //DATOS TipoPago
        final String[] VECid_TipoPago = {"1", "2"};
        final String[] VECnombrre_TipoPago = {"1", "2"};

        // DATOS EVENTO ESPECIAL
        final String[] VECid_EventoEspecial = {"1","2"};
        final String[] VECnombre_EventoEspecial = {"Evento1","Evento2"};
        final String[] VECfecha_EventoEspecial = {"06/05/2023","07/05/2023"};
        final String[] VECmonto_minimo = {"100", "200"};
        final String[] VECmonto_maximo = {"1000", "2000"};

        // DATOS PEDIDO
        final String[] VECid_Pedido = {"1","2"};
        final String[] VECid_ClientePedido = {"1","2"};
        final String[] VECid_TipoPagoPedido = {"1","2"};
        final String[] VECid_LocalPedido = {"1","2"};
        final String[] VECid_EventoEspecialPedido = {"1","2"};
        final String[] VECtipo_Pedido = {"Tipo1","Tipo2"};
        final String[] VECestado_Pedido = {"Estado1","Estado2"};
        final String[] VECmonto_pedido = {"1000","2000"};
        // DATOS ACCESOUSUARIO
        final String[] VECUsuario = {
                "RM","RM","RM","RM",
                "RC","RC","RC"
        };
        final String[] VECOpcion = {
                "010","020","030","040",
                "010","040","030"
        };



        // ABRIR BD
        abrir();

        // ELIMINAR REGISTROS
        db.execSQL("DELETE FROM TipoProducto");
        db.execSQL("DELETE FROM DetallePedido");
        db.execSQL("DELETE FROM ComboProducto");
        db.execSQL("DELETE FROM PrecioProducto");
        db.execSQL("DELETE FROM ListaPrecio");
        db.execSQL("DELETE FROM Combo");
        db.execSQL("DELETE FROM Producto");
        db.execSQL("DELETE FROM Facultad");
        db.execSQL("DELETE FROM TipoUbicacion");
        db.execSQL("DELETE FROM ListaPedidos");
        db.execSQL("DELETE FROM Envio");
        db.execSQL("DELETE FROM Empleado");
        db.execSQL("DELETE FROM Pedido");
        db.execSQL("DELETE FROM Cliente");
        db.execSQL("DELETE FROM Local");
        db.execSQL("DELETE FROM Ubicacion");
        db.execSQL("DELETE FROM EncargadoLocal");
        db.execSQL("DELETE FROM TipoPago");
        db.execSQL("DELETE FROM EventoEspecial");


        db.execSQL("DELETE FROM Usuario");
        db.execSQL("DELETE FROM OpcionCrud");
        db.execSQL("DELETE FROM AccesoUsuario");








        // LLENAR TABLA COMBO
        Combo combo = new Combo();
        for (int i = 0; i < 5; i++) {
            combo.setId_Combo(Integer.parseInt(VECcombo[i]));
            combo.setPrecio_Combo(Float.parseFloat(VECprecio[i]));
            insertar(combo);
        }

        // LLENAR TABLA TIPO PRODUCTO
        TipoProducto tipoProducto = new TipoProducto();
        for (int i = 0; i < 5; i++) {
            tipoProducto.setId_TipoProducto(Integer.parseInt(VECtipoProducto[i]));
            tipoProducto.setNombre_TipoProducto(VECnombreTipoProducto[i]);
            insertar(tipoProducto);
        }

        // LLENAR TABLA PRODUCTO
        Producto producto = new Producto();
        for (int i = 0; i < 5; i++) {
            producto.setCodigo_Producto(Integer.parseInt(VECproducto[i]));
            producto.setNombre_Producto(VECnombre[i]);
            producto.setEstado_Producto(VECestado_producto[i]);
            producto.setPrecioactual_Producto(Float.parseFloat(VECprecioActualProducto[i]));
            producto.setCodigo_TipoProducto(Integer.parseInt(VECidTipoProducto[i]));
            insertar(producto);
        }

        // LLENAR TABLA COMBO_PRODUCTO
        ComboProducto comboProducto = new ComboProducto();
        for (int i = 0; i < 5; i++) {
            comboProducto.setId_ComboProducto(Integer.parseInt(VECComboProducto[i]));
            comboProducto.setId_Combo(Integer.parseInt(VECidCombo[i]));
            comboProducto.setId_Producto(Integer.parseInt(VECidProducto[i]));
            insertar(comboProducto);
        }


        // LLENAR TABLA LISTA_PRECIO
        for (int i = 0; i < 5; i++){
            ContentValues listaPrecioValues = new ContentValues();
            listaPrecioValues.put(campos_ListaPrecio[0], Integer.parseInt(VECListaPrecio[i]));
            listaPrecioValues.put(campos_ListaPrecio[1], VECDesde[i]);
            listaPrecioValues.put(campos_ListaPrecio[2], VECHasta[i]);
            db.insert("ListaPrecio", null, listaPrecioValues);

       
        }
        // LLENAR TABLA USUARIO
        for (int i = 0; i < 4; i++){
            ContentValues UsuarioValues = new ContentValues();
            UsuarioValues.put("idUsuario", VECid_Usuario[i]);
            UsuarioValues.put("nomUsuario", VECnom_Usuario[i]);
            UsuarioValues.put("clave", VECclave[i]);
            db.insert("Usuario", null,UsuarioValues);
        }

        // LLENAR TABLA TIPO UBICACION
        for (int i = 0; i < 2; i++){
            ContentValues TipoUbicacionValues = new ContentValues();
            TipoUbicacionValues.put("id_TipoUbicacion", VECid_TipoUbicacion[i]);
            TipoUbicacionValues.put("nombre_TipoUbicacion", VECnombre_TipoUbicacion[i]);
            db.insert("TipoUbicacion", null,TipoUbicacionValues);
        }

        // LLENAR TABLA FACULTAD
        for (int i = 0; i < 2; i++){
            ContentValues FacultadValues = new ContentValues();
            FacultadValues.put("id_Facultad", VECid_Facultad[i]);
            FacultadValues.put("nombre_Facultad", VECnombre_Facultad[i]);
            db.insert("Facultad", null,FacultadValues);
        }

        // LLENAR TABLA UBICACION
        for (int i = 0; i < 2; i++){
            ContentValues UbicacionValues = new ContentValues();
            UbicacionValues.put("id_Ubicacion", VECid_Ubicacion[i]);
            UbicacionValues.put("nombre_Ubicacion", VECnombre_Ubicacion[i]);
            UbicacionValues.put("descripcion_Ubicacion", VECdescripcion_Ubicacion[i]);
            UbicacionValues.put("id_Facultad", VECid_ubicacionFacultad[i]);
            UbicacionValues.put("id_TipoUbicacion", VECid_ubicacionTipoUbicacion[i]);
            db.insert("Ubicacion", null,UbicacionValues);
        }

        // LLENAR ENCARGADO LOCAL
        for (int i = 0; i < 2; i++) {
            ContentValues encargadoLocal = new ContentValues();
            encargadoLocal.put("id_EncargadoLocal", VECid_EncargadoLocal[i]);
            encargadoLocal.put("nombre_EncargadoLocal", VECnombre_EncargadoLocal[i]);
            db.insert("EncargadoLocal", null, encargadoLocal);
        }

        // LLENAR LOCAL
        for (int i = 0; i < 2; i++) {
            ContentValues local = new ContentValues();
            local.put("id_Local", VECid_Local[i]);
            local.put("id_Ubicacion", VECid_localUbicacion[i]);
            local.put("id_EncargadoLocal", VECid_localEncargadoLocal[i]);
            local.put("nombre_Local", VECnombre_Local[i]);
            db.insert("Local", null, local);
        }

        // LLENAR EMPLEADO
        for (int i = 0; i < 2; i++) {
            ContentValues empleado = new ContentValues();
            empleado.put("id_Empleado", VECid_Empleado[i]);
            empleado.put("id_Local", VECid_LocalEmpleado[i]);
            empleado.put("nombre_Empleado", VECnombre_Empleado[i]);
            empleado.put("tipo_Empleado", VECtipoEmpleado[i]);

            db.insert("Empleado", null, empleado);
        }


        // LLENAR TABLA ENVIO
        for (int i = 0; i < 2; i++) {
            ContentValues envio = new ContentValues();
            envio.put("id_Envio", VECid_Envio[i]);
            envio.put("id_Empleado", VECid_EmpleadoEnvio[i]);
            envio.put("fecha", VECfecha_Envio[i]);
            db.insert("Envio", null, envio);
        }

        // LLENAR TABLA CLIENTE
        for (int i = 0; i < 2; i++) {
            ContentValues cliente = new ContentValues();
            cliente.put("id_Cliente", VECid_Cliente[i]);
            cliente.put("id_Ubicacion", VECid_UbicacionCliente[i]);
            cliente.put("nombres_Cliente", VECnombres_Cliente[i]);
            cliente.put("apellidos_Cliente", VECapellidos_Cliente[i]);
            cliente.put("fecha_nacimiento", VECfecha_nacimiento[i]);
            db.insert("Cliente", null, cliente);
        }

        // LLENAR TABLA TIPO PAGO
        for (int i = 0; i < 2; i++) {
            ContentValues tipoPago = new ContentValues();
            tipoPago.put("id_TipoPago", VECid_TipoPago[i]);
            tipoPago.put("nombre_TipoPago", VECnombrre_TipoPago[i]);
            db.insert("TipoPago", null, tipoPago);
        }

        // LLENAR TABLA EVENTO ESPECIAL
        for (int i = 0; i < 2; i++) {
            ContentValues eventoEspecial = new ContentValues();
            eventoEspecial.put("id_EventoEspecial", VECid_EventoEspecial[i]);
            eventoEspecial.put("nombre_EventoEspecial", VECnombre_EventoEspecial[i]);
            eventoEspecial.put("fecha_EventoEspecial", VECfecha_EventoEspecial[i]);
            eventoEspecial.put("monto_minimo", VECmonto_minimo[i]);
            eventoEspecial.put("monto_maximo", VECmonto_maximo[i]);
            db.insert("EventoEspecial", null, eventoEspecial);
        }

        // LLENAR TABLA PEDIDO
        for (int i = 0; i < 2; i++) {
            ContentValues pedido = new ContentValues();
            pedido.put("id_Pedido", VECid_Pedido[i]);
            pedido.put("id_Cliente", VECid_ClientePedido[i]);
            pedido.put("id_TipoPago", VECid_TipoPagoPedido[i]);
            pedido.put("id_Local", VECid_LocalPedido[i]);
            pedido.put("id_EventoEspecial", VECid_EventoEspecialPedido[i]);
            pedido.put("tipo_Pedido", VECtipo_Pedido[i]);
            pedido.put("estado_Pedido", VECestado_Pedido[i]);
            pedido.put("monto_Pedido", VECmonto_pedido[i]);
            db.insert("Pedido", null, pedido);
        }
        // LLENAR TABLA OPCIONCRUD
        for (int i = 0; i < 3; i++)
        {
            ContentValues OpcionValues = new ContentValues();
            OpcionValues.put("idOpcionCrud", VECid_OpcionCRUD[i]);
            OpcionValues.put("desOpcion", VECdes_OpcionCRUD[i]);
            OpcionValues.put("numCrud", Integer.parseInt(VECnum_OpcionCRUD[i]));
            db.insert("OpcionCrud", null,OpcionValues);
        }

        // LLENAR TABLA ACCESOUSUARIO
        for (int i = 0; i < 7;i++)
        {
            ContentValues AccesoValues = new ContentValues();
            AccesoValues.put("idUsuario", VECUsuario[i]);
            AccesoValues.put("idOpcionCrud", VECOpcion[i]);
            db.insert("AccesoUsuario", null,AccesoValues);
        }


        // CERRAR BD
        cerrar();
        return "Datos cargados correctamente";
    }

    // Validar Usuario y Contraseña
    public boolean iniciarSesion(String usuario, String clave) {
        String[] login = {usuario,clave};
        Cursor cursor = db.query("Usuario",null, "nomUsuario = ? AND clave = ?",login, null, null, null);

        if (cursor.moveToFirst())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean validarEntrada(String username, String opcion) {
        String idUsuario = "";
        abrir();
        Cursor cursor1 = db.query("Usuario", null, "nomUsuario = ?", new String[]{username}, null, null, null);
        cursor1.moveToFirst();
        idUsuario = cursor1.getString(0);
        Cursor cursor2 = db.query("AccesoUsuario", null, "idUsuario = ? AND idOpcionCrud = ?", new String[]{idUsuario,opcion}, null, null, null);

        if(cursor2.moveToFirst())
        {
            return true;
        }
        else {
            return false;
        }

    }

    public boolean verTablas(String username) {
        String idUsuario = "";
        abrir();
        Cursor cursor1 = db.query("Usuario", null, "nomUsuario = ?", new String[]{username}, null, null, null);
        cursor1.moveToFirst();
        idUsuario = cursor1.getString(0);
        Cursor cursor2 = db.query("AccesoUsuario", null, "idUsuario = ?", new String[]{idUsuario}, null, null, null);

        if(cursor2.moveToFirst())
        {
            return true;
        }
        else {
            return false;
        }


    }
}
