package sv.edu.ues.fia.eisi.cafetinesues;

// Combo posee id y precio
public class Combo {
    private int id_Combo;
    private float precio_Combo;

    public Combo() {
    }

    public Combo(int id_Combo, float precio_Combo) {
        this.id_Combo = id_Combo;
        this.precio_Combo = precio_Combo;
    }

    public int getId_Combo() {
        return id_Combo;
    }

    public void setId_Combo(int id_Combo) {
        this.id_Combo = id_Combo;
    }

    public float getPrecio_Combo() {
        return precio_Combo;
    }

    public void setPrecio_Combo(float precio_Combo) {
        this.precio_Combo = precio_Combo;
    }
}
