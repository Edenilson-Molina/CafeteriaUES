package sv.edu.ues.fia.eisi.cafetinesues;

public class Ubicacion {
    private int id_Ubicacion;
    private int id_ubicacionFacultad;
    private int id_ubicacionTipoUbicacion;
    private String nombre_Ubicacion;
    private String descripcion_Ubicacion;

    public Ubicacion() {
    }

    public Ubicacion(int id_Ubicacion, int id_ubicacionFacultad, int id_ubicacionTipoUbicacion, String nombre_Ubicacion, String descripcion_Ubicacion) {
        this.id_Ubicacion = id_Ubicacion;
        this.id_ubicacionFacultad = id_ubicacionFacultad;
        this.id_ubicacionTipoUbicacion = id_ubicacionTipoUbicacion;
        this.nombre_Ubicacion = nombre_Ubicacion;
        this.descripcion_Ubicacion = descripcion_Ubicacion;
    }

    public int getId_Ubicacion() {
        return id_Ubicacion;
    }

    public void setId_Ubicacion(int id_Ubicacion) {
        this.id_Ubicacion = id_Ubicacion;
    }

    public int getId_ubicacionFacultad() {
        return id_ubicacionFacultad;
    }

    public void setId_ubicacionFacultad(int id_ubicacionFacultad) {
        this.id_ubicacionFacultad = id_ubicacionFacultad;
    }

    public int getId_ubicacionTipoUbicacion() {
        return id_ubicacionTipoUbicacion;
    }

    public void setId_ubicacionTipoUbicacion(int id_ubicacionTipoUbicacion) {
        this.id_ubicacionTipoUbicacion = id_ubicacionTipoUbicacion;
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
}
