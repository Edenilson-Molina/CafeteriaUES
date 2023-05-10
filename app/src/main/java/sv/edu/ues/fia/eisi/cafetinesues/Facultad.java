package sv.edu.ues.fia.eisi.cafetinesues;

public class Facultad {
    private int id_Facultad;
    private String nombre_Facultad;

    public Facultad(int id_Facultad, String nombre_Facultad) {
        this.id_Facultad = id_Facultad;
        this.nombre_Facultad = nombre_Facultad;
    }

    public Facultad() {
    }

    public int getId_Facultad() {
        return id_Facultad;
    }

    public void setId_Facultad(int id_Facultad) {
        this.id_Facultad = id_Facultad;
    }

    public String getNombre_Facultad() {
        return nombre_Facultad;
    }

    public void setNombre_Facultad(String nombre_Facultad) {
        this.nombre_Facultad = nombre_Facultad;
    }
}
