package sv.edu.ues.fia.eisi.cafetinesues;

public class TipoProducto {
    private int id_TipoProducto;
    private String nombre_TipoProducto;

    public TipoProducto() {
    }

    public TipoProducto(int id_TipoProducto, String nombre_TipoProducto) {
        this.id_TipoProducto = id_TipoProducto;
        this.nombre_TipoProducto = nombre_TipoProducto;
    }

    public int getId_TipoProducto() {
        return id_TipoProducto;
    }

    public void setId_TipoProducto(int id_TipoProducto) {
        this.id_TipoProducto = id_TipoProducto;
    }

    public String getNombre_TipoProducto() {
        return nombre_TipoProducto;
    }

    public void setNombre_TipoProducto(String nombre_TipoProducto) {
        this.nombre_TipoProducto = nombre_TipoProducto;
    }
}
