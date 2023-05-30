package sv.edu.ues.fia.eisi.cafetinesues;

public class Ubicacion  {
    private int id_Ubicacion;
    private String nombre_Ubicacion;
    private String descripcion_Ubicacion;
    private int id_Facultad;
    private int id_TipoUbicacion;

    public Ubicacion() {
    }

    public Ubicacion(int id_Ubicacion, String nombre_Ubicacion, String descripcion_Ubicacion, int id_Facultad, int id_TipoUbicacion) {
        this.id_Ubicacion = id_Ubicacion;
        this.nombre_Ubicacion = nombre_Ubicacion;
        this.descripcion_Ubicacion = descripcion_Ubicacion;
        this.id_Facultad = id_Facultad;
        this.id_TipoUbicacion = id_TipoUbicacion;
    }

    public int getId_Ubicacion() {
        return id_Ubicacion;
    }

    public void setId_Ubicacion(int id_Ubicacion) {
        this.id_Ubicacion = id_Ubicacion;
    }

    public String getNombre_Ubicacion() {
        return nombre_Ubicacion;
    }

    public void setNombre_Ubicacion(String nombre_Ubicacion) {
        this.nombre_Ubicacion = nombre_Ubicacion;
    }

    public String getDescripcion_Ubicacion() {
        return descripcion_Ubicacion;
    }

    public void setDescripcion_Ubicacion(String descripcion_Ubicacion) {
        this.descripcion_Ubicacion = descripcion_Ubicacion;
    }

    public int getId_Facultad() {
        return id_Facultad;
    }

    public void setId_Facultad(int id_Facultad) {
        this.id_Facultad = id_Facultad;
    }

    public int getId_TipoUbicacion() {
        return id_TipoUbicacion;
    }

    public void setId_TipoUbicacion(int id_TipoUbicacion) {
        this.id_TipoUbicacion = id_TipoUbicacion;
    }
}
