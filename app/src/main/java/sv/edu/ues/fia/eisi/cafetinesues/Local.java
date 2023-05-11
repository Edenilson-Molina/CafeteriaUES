package sv.edu.ues.fia.eisi.cafetinesues;

public class Local {

    private int id_Local;

    private int id_EncargadoLocal;

    private int id_Ubicacion;

    private String nombre_Local;

    public Local() {
    }

    public Local(int id_Local, int id_EncargadoLocal, int id_Ubicacion, String nombre_Local) {
        this.id_Local = id_Local;
        this.id_EncargadoLocal = id_EncargadoLocal;
        this.id_Ubicacion = id_Ubicacion;
        this.nombre_Local = nombre_Local;
    }

    public int getId_Local() {
        return id_Local;
    }

    public void setId_Local(int id_Local) {
        this.id_Local = id_Local;
    }

    public int getId_EncargadoLocal() {
        return id_EncargadoLocal;
    }

    public void setId_EncargadoLocal(int id_EncargadoLocal) {
        this.id_EncargadoLocal = id_EncargadoLocal;
    }

    public int getId_Ubicacion() {
        return id_Ubicacion;
    }

    public void setId_Ubicacion(int id_Ubicacion) {
        this.id_Ubicacion = id_Ubicacion;
    }

    public String getNombre_Local() {
        return nombre_Local;
    }

    public void setNombre_Local(String nombre_Local) {
        this.nombre_Local = nombre_Local;
    }
}
