package sv.edu.ues.fia.eisi.cafetinesues;

public class Facultad {


    private int id_Faculdad;
    private String nombre_Facultad;

    public Facultad() {
    }

    public Facultad(int id_Faculdad, String nombre_Facultad) {
        this.id_Faculdad = id_Faculdad;
        this.nombre_Facultad = nombre_Facultad;
    }

    public int getId_Faculdad() {
        return id_Faculdad;
    }

    public void setId_Faculdad(int id_Faculdad) {
        this.id_Faculdad = id_Faculdad;

    }

    public String getNombre_Facultad() {
        return nombre_Facultad;
    }

    public void setNombre_Facultad(String nombre_Facultad) {
        this.nombre_Facultad = nombre_Facultad;
    }
}
