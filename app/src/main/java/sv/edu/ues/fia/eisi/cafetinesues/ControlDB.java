package sv.edu.ues.fia.eisi.cafetinesues;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

            // EL CAMPO QUE VENGA COMO "0", SERA INSERTADO COMO NULL
            if(detallePedido.getId_Combo() == 0) {
                detallePedidoValues.put("id_Combo", (Integer) null);
            }
            if(detallePedido.getId_Producto() == 0){
                detallePedidoValues.put("id_Producto", (Integer) null);
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

            // EL CAMPO QUE VENGA COMO "0", SERA INGRESADO COMO NULL
            if(detallePedido.getId_Combo() == 0) {
                cv.put("id_Combo", (Integer) null);
            }
            if(detallePedido.getId_Producto() == 0){
                cv.put("id_Producto", (Integer) null);
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
                detallePedido.setId_Producto(cursor.getInt(3));
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
            regInsertado = "Error al Insertar el registro, Registro duplicado";
        } else {
            ContentValues precioProductoValues = new ContentValues();
            precioProductoValues.put(campos_PrecioProducto[0], precioProducto.getId_PrecioProducto());
            precioProductoValues.put(campos_PrecioProducto[1], precioProducto.getId_Producto());
            precioProductoValues.put(campos_PrecioProducto[2], precioProducto.getId_ListaPrecio());
            precioProductoValues.put(campos_PrecioProducto[3], precioProducto.getPrecio());
            contador = db.insert("PrecioProducto", null, precioProductoValues);
            regInsertado += contador;
        }
        return regInsertado;
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
                // PARA INSERTAR
                //
                // Verificar si ya existe ese COMBO_PRODUCTO y si existen  el Combo y Producto especificado
                ComboProducto comboProducto = (ComboProducto)dato;
                String[] id1c = {String.valueOf(comboProducto.getId_ComboProducto())};
                String[] id2c = {String.valueOf(comboProducto.getId_Combo())};
                String[] id3c = {String.valueOf(comboProducto.getId_Producto())};
                abrir();
                Cursor cursor1c = db.query("ComboProducto", null, "id_ComboProducto = ?", id1c, null, null, null);
                Cursor cursor2c = db.query("Combo", null, "id_Combo = ?", id2c, null, null, null);
                Cursor cursor3c = db.query("Producto", null, "id_Producto = ?", id3c, null, null, null);
                if(cursor1c.moveToFirst()){
                    // ID duplicado, no se debe insertar
                    return true;
                }
                if (cursor2c.moveToFirst() && cursor3c.moveToFirst()){
                    //Se encontraron los ID's, se puede insertar
                    return false;
                }
                return true;
            case 4:
                // PARA ACTUALIZAR
                //
                // Verificar si ya existe ese COMBO_PRODUCTO y si existen  el Combo y Producto especificado
                ComboProducto comboProducto1 = (ComboProducto)dato;
                String[] id1cQ = {String.valueOf(comboProducto1.getId_ComboProducto())};
                String[] id2cQ = {String.valueOf(comboProducto1.getId_Combo())};
                String[] id3cQ = {String.valueOf(comboProducto1.getId_Producto())};
                abrir();
                Cursor cursor1cQ = db.query("ComboProducto", null, "id_ComboProducto = ?", id1cQ, null, null, null);
                Cursor cursor2cQ = db.query("Combo", null, "id_Combo = ?", id2cQ, null, null, null);
                Cursor cursor3cQ = db.query("Producto", null, "id_Producto = ?", id3cQ, null, null, null);
                if(cursor1cQ.moveToFirst() && cursor2cQ.moveToFirst() && cursor3cQ.moveToFirst()){
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
                DetallePedido detallePedido = (DetallePedido)dato;
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
                if(detallePedido.getId_Combo() == 0){
                    String[] id4d = {String.valueOf(detallePedido.getId_Producto())};
                    Cursor cursor4d = db.query("Producto", null, "id_Producto = ?", id4d, null, null, null);
                    if (cursor2d.moveToFirst() && cursor4d.moveToFirst())   {
                        //Se encontraron datos, por lo que esta CORRECTO
                        return false;
                    }
                } else {
                    String[] id3d = {String.valueOf(detallePedido.getId_Combo())};
                    Cursor cursor3d = db.query("Combo", null, "id_Combo = ?", id3d, null, null, null);
                    if (cursor2d.moveToFirst() && cursor3d.moveToFirst())   {
                        //Se encontraron datos, por lo que esta CORRECTO
                        return false;
                    }

                }
                return true;

            //Para actualizar DetallePedido
            case 7:
                // Verificar si ya existe ese DETALLE_PEDIDO y si existen  el Pedido, Combo y Producto especificado
                DetallePedido detallePedido1 = (DetallePedido)dato;
                String[] id1d1 = {String.valueOf(detallePedido1.getId_DetallePedido())};
                String[] id2d1 = {String.valueOf(detallePedido1.getId_Pedido())};

                abrir();

                Cursor cursor1d1 = db.query("DetallePedido", null, "id_DetallePedido = ?", id1d1, null, null, null);
                Cursor cursor2d1 = db.query("Pedido", null, "id_Pedido = ?", id2d1, null, null, null);

                // AHORA VERIFICAMOS PRIMERO CUAL CAMPO ES "0"
                // Y PARA EL QUE NO ES "0", VERIFICAR SI EXISTE
                if(detallePedido1.getId_Combo() == 0){
                    String[] id4d = {String.valueOf(detallePedido1.getId_Producto())};
                    Cursor cursor4d = db.query("Producto", null, "id_Producto = ?", id4d, null, null, null);
                    if (cursor1d1.moveToFirst() && cursor2d1.moveToFirst() && cursor4d.moveToFirst())   {
                        //Se encontraron datos, por lo que esta CORRECTO
                        return true;
                    }
                } else {
                    String[] id3d = {String.valueOf(detallePedido1.getId_Combo())};
                    Cursor cursor3d = db.query("Combo", null, "id_Combo = ?", id3d, null, null, null);
                    if (cursor1d1.moveToFirst() && cursor2d1.moveToFirst() && cursor3d.moveToFirst())   {
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
            // INTEGRIDAD PARA PRECIO PRODUCTO
            //
            //
            case 16:
                // Verificar la existencia del Precio Producto
                // y tambien sus id_Producto y id_ListaPrecio
                PrecioProducto precioProducto16 = (PrecioProducto) dato;
                String[] id_PrecioProducto16 = {String.valueOf(precioProducto16.getId_PrecioProducto())};
                String[] id_ListaPrecio16 ={String.valueOf(precioProducto16.getId_ListaPrecio())};
                abrir();
                Cursor cursorPP16 = db.query("PrecioProducto", campos_PrecioProducto, "id_PrecioProducto = ?",id_PrecioProducto16, null, null, null);
                Cursor cursor2PP16 = db.query("PrecioProducto", campos_PrecioProducto, "id_ListaPrecio = ?",id_ListaPrecio16, null, null, null);
                cursor2PP16.moveToFirst();
                if (cursorPP16.moveToFirst()) {
                    // Ya existe
                    return true;
                }
                try{
                    if ((cursor2PP16.getInt(1) == precioProducto16.getId_Producto()) &&
                            (cursor2PP16.getInt(2) == precioProducto16.getId_ListaPrecio()))
                    {
                        return true;
                    }
                }catch (Exception e)
                {
                    return false;
                }

                return false;
            case 17:
                // Verificar la existencia del Precio Producto
                PrecioProducto precioProducto17 = (PrecioProducto) dato;
                String[] id_PrecioProducto17 = {String.valueOf(precioProducto17.getId_PrecioProducto())};
                abrir();
                Cursor cursorPP17 = db.query("PrecioProducto", null, "id_PrecioProducto = ?",id_PrecioProducto17, null, null, null);
                if (cursorPP17.moveToFirst()) {
                    // Ya existe
                    return true;
                }

                return false;

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
        final String[] VECDesde = {"06-05-2023","07-05-2023","08-05-2023","09-05-2023","10-05-2023"};
        final String[] VECHasta = {"07-05-2023","08-05-2023","09-05-2023","10-05-2023","11-05-2023"};



        // ABRIR BD
        abrir();

        // ELIMINAR REGISTROS
        db.execSQL("DELETE FROM Combo");
        db.execSQL("DELETE FROM Producto");
        db.execSQL("DELETE FROM TipoProducto");
        db.execSQL("DELETE FROM ComboProducto");
        db.execSQL("DELETE FROM PrecioProducto");
        db.execSQL("DELETE FROM ListaPrecio");




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


        // CERRAR BD
        cerrar();
        return "Datos cargados correctamente";
    }



}
