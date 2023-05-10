package sv.edu.ues.fia.eisi.cafetinesues;

import androidx.annotation.Nullable;

public class DetallePedido {
    private int id_DetallePedido;
    private int id_Pedido;
    private int id_Combo;
    private int id_Producto;
    private int cantidad_Producto;
    private float subtotal;

    public DetallePedido() {
    }

    public DetallePedido(int id_DetallePedido, int id_Pedido, int id_Combo, int id_Producto, int cantidad_Producto, float subtotal) {
        this.id_DetallePedido = id_DetallePedido;
        this.setId_Pedido(id_Pedido);
        this.setId_Combo(id_Combo);
        this.setId_Producto(id_Producto);
        this.setCantidad_Producto(cantidad_Producto);
        this.setSubtotal(subtotal);
    }

    public int getId_DetallePedido() {
        return id_DetallePedido;
    }

    public void setId_DetallePedido(int id_DetallePedido) {
        this.id_DetallePedido = id_DetallePedido;
    }

    public int getId_Combo() {
        return id_Combo;
    }

    public void setId_Combo(int id_Combo) {
        this.id_Combo = id_Combo;
    }

    public int getId_Pedido() {
        return id_Pedido;
    }

    public void setId_Pedido(int id_Pedido) {
        this.id_Pedido = id_Pedido;
    }

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

    public int getCantidad_Producto() {
        return cantidad_Producto;
    }

    public void setCantidad_Producto(int cantidad_Producto) {
        this.cantidad_Producto = cantidad_Producto;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
}
