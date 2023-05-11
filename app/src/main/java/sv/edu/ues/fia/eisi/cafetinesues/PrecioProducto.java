package sv.edu.ues.fia.eisi.cafetinesues;

public class PrecioProducto
{
    private int id_PrecioProducto;
    private int id_Producto;
    private int id_ListaPrecio;
    private float precio;

    //Constructores
    public PrecioProducto() {
    }

    public PrecioProducto(int id_PrecioProducto, int id_Producto, int id_ListaPrecio, float precio) {
        this.id_PrecioProducto = id_PrecioProducto;
        this.id_Producto = id_Producto;
        this.id_ListaPrecio = id_ListaPrecio;
        this.precio = precio;
    }

    // Metodos get y set

    public int getId_PrecioProducto() {
        return id_PrecioProducto;
    }

    public void setId_PrecioProducto(int id_PrecioProducto) {
        this.id_PrecioProducto = id_PrecioProducto;
    }

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

    public int getId_ListaPrecio() {
        return id_ListaPrecio;
    }

    public void setId_ListaPrecio(int id_ListaPrecio) {
        this.id_ListaPrecio = id_ListaPrecio;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
