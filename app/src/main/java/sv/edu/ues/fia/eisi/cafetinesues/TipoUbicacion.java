package sv.edu.ues.fia.eisi.cafetinesues;

public class TipoUbicacion {
    private int id_TipoUbicacion;
    private String nombre_TipoUbicacion;

    public TipoUbicacion(int id_TipoUbicacion, String nombre_TipoUbicacion) {
        this.id_TipoUbicacion = id_TipoUbicacion;
        this.nombre_TipoUbicacion = nombre_TipoUbicacion;
    }

    public TipoUbicacion() {
    }

    public int getId_TipoUbicacion() {
        return id_TipoUbicacion;
    }

    public void setId_TipoUbicacion(int id_TipoUbicacion) {
        this.id_TipoUbicacion = id_TipoUbicacion;
    }

    public String getNombre_TipoUbicacion() {
        return nombre_TipoUbicacion;
    }

    public void setNombre_TipoUbicacion(String nombre_TipoUbicacion) {
        this.nombre_TipoUbicacion = nombre_TipoUbicacion;
    }
}
