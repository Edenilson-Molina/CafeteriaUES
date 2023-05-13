package sv.edu.ues.fia.eisi.cafetinesues;

public class Pedido {
    private int id_Pedido;
    private int id_cliente;
    private int id_tipoPago;
    private int id_local;
    private int id_eventoEspecial;
    private String tipo_Pedido;
    private String estado_Pedido;
    private float monto_Pedido;

    public Pedido() {
    }

    public Pedido(int id_Pedido, int id_cliente, int id_tipoPago, int id_local, int id_eventoEspecial, String tipo_Pedido, String estado_Pedido, float monto_Pedido) {
        this.id_Pedido = id_Pedido;
        this.id_cliente = id_cliente;
        this.id_tipoPago = id_tipoPago;
        this.id_local = id_local;
        this.id_eventoEspecial = id_eventoEspecial;
        this.tipo_Pedido = tipo_Pedido;
        this.estado_Pedido = estado_Pedido;
        this.monto_Pedido = monto_Pedido;
    }

    public int getId_Pedido() {
        return id_Pedido;
    }

    public void setId_Pedido(int id_Pedido) {
        this.id_Pedido = id_Pedido;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_tipoPago() {
        return id_tipoPago;
    }

    public void setId_tipoPago(int id_tipoPago) {
        this.id_tipoPago = id_tipoPago;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public int getId_eventoEspecial() {
        return id_eventoEspecial;
    }

    public void setId_eventoEspecial(int id_eventoEspecial) {
        this.id_eventoEspecial = id_eventoEspecial;
    }

    public String getTipo_Pedido() {
        return tipo_Pedido;
    }

    public void setTipo_Pedido(String tipo_Pedido) {
        this.tipo_Pedido = tipo_Pedido;
    }

    public String getEstado_Pedido() {
        return estado_Pedido;
    }

    public void setEstado_Pedido(String estado_Pedido) {
        this.estado_Pedido = estado_Pedido;
    }

    public float getMonto_Pedido() {
        return monto_Pedido;
    }

    public void setMonto_Pedido(float monto_Pedido) {
        this.monto_Pedido = monto_Pedido;
    }
}
