package sv.edu.ues.fia.eisi.cafetinesues;

public class Producto {

    private int codigo_Producto;
    private int codigo_TipoProducto;
    private String nombre_Producto;
    private String estado_Producto;
    private float precioactual_Producto;

    public Producto(int codigo_Producto, int codigo_TipoProducto, String nombre_Producto, String estado_Producto, float precioactual_Producto) {
        this.codigo_Producto = codigo_Producto;
        this.codigo_TipoProducto = codigo_TipoProducto;
        this.nombre_Producto = nombre_Producto;
        this.estado_Producto = estado_Producto;
        this.precioactual_Producto = precioactual_Producto;
    }

    public int getCodigo_Producto() {
        return codigo_Producto;
    }

    public void setCodigo_Producto(int codigo_Producto) {
        this.codigo_Producto = codigo_Producto;
    }

    public int getCodigo_TipoProducto() {
        return codigo_TipoProducto;
    }

    public void setCodigo_TipoProducto(int codigo_TipoProducto) {
        this.codigo_TipoProducto = codigo_TipoProducto;
    }

    public String getNombre_Producto() {
        return nombre_Producto;
    }

    public void setNombre_Producto(String nombre_Producto) {
        this.nombre_Producto = nombre_Producto;
    }

    public String getEstado_Producto() {
        return estado_Producto;
    }

    public void setEstado_Producto(String estado_Producto) {
        this.estado_Producto = estado_Producto;
    }

    public float getPrecioactual_Producto() {
        return precioactual_Producto;
    }

    public void setPrecioactual_Producto(float precioactual_Producto) {
        this.precioactual_Producto = precioactual_Producto;
    }

}
