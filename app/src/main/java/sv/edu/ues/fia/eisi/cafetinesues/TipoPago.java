package sv.edu.ues.fia.eisi.cafetinesues;

public class TipoPago {
    private int id_TipoPago;
    private String nombre_TipoPago;

    public TipoPago(){

    }
    public TipoPago(int id_tP,String n_tP){
        this.id_TipoPago = id_tP;
        this.nombre_TipoPago = n_tP;
    }

    public int getId_TipoPago() {
        return id_TipoPago;
    }

    public void setId_TipoPago(int id_TipoPago) {
        this.id_TipoPago = id_TipoPago;
    }

    public String getNombre_TipoPago() {
        return nombre_TipoPago;
    }

    public void setNombre_TipoPago(String nombre_TipoPago) {
        this.nombre_TipoPago = nombre_TipoPago;
    }
}
