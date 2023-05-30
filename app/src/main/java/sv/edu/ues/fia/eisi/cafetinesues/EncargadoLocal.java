package sv.edu.ues.fia.eisi.cafetinesues;

public class EncargadoLocal {

    private int id_EncargadoLocal;
    private String nombre_EncargadoLocal;

    public EncargadoLocal() {
    }

    public EncargadoLocal(int id_EncargadoLocal, String nombre_EncargadoLocal) {
        this.id_EncargadoLocal = id_EncargadoLocal;
        this.nombre_EncargadoLocal = nombre_EncargadoLocal;
    }

    public int getId_EncargadoLocal() {
        return id_EncargadoLocal;
    }

    public void setId_EncargadoLocal(int id_EncargadoLocal) {
        this.id_EncargadoLocal = id_EncargadoLocal;
    }

    public String getNombre_EncargadoLocal() {
        return nombre_EncargadoLocal;
    }

    public void setNombre_EncargadoLocal(String nombre_EncargadoLocal) {
        this.nombre_EncargadoLocal = nombre_EncargadoLocal;
    }
}
