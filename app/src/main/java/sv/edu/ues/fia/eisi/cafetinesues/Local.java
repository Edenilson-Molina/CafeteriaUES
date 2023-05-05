package sv.edu.ues.fia.eisi.cafetinesues;

public class Local {

    private int id_Local;
    private String nombre_Local;

    public Local(int id_Local, String nombre_Local) {
        this.id_Local = id_Local;
        this.nombre_Local = nombre_Local;
    }

    public Local() {
    }

    public int getId_Local() {
        return id_Local;
    }

    public void setId_Local(int id_Local) {
        this.id_Local = id_Local;
    }

    public String getNombre_Local() {
        return nombre_Local;
    }

    public void setNombre_Local(String nombre_Local) {
        this.nombre_Local = nombre_Local;
    }
}
