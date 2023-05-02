package sv.edu.ues.fia.eisi.cafetinesues;
// ComboProducto posee id_ComboProducto, id_Combo, id_Producto
public class ComboProducto {
    private int id_ComboProducto;
    private int id_Combo;
    private int id_Producto;

    public ComboProducto() {
    }

    public ComboProducto(int id_ComboProducto, int id_Combo, int id_Producto) {
        this.id_ComboProducto = id_ComboProducto;
        this.id_Combo = id_Combo;
        this.id_Producto = id_Producto;
    }

    public int getId_ComboProducto() {
        return id_ComboProducto;
    }

    public void setId_ComboProducto(int id_ComboProducto) {
        this.id_ComboProducto = id_ComboProducto;
    }

    public int getId_Combo() {
        return id_Combo;
    }

    public void setId_Combo(int id_Combo) {
        this.id_Combo = id_Combo;
    }

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

}
