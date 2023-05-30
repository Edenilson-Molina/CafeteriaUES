package sv.edu.ues.fia.eisi.cafetinesues;

public class Empleado {
    private int id_Empleado;

    private int id_Local;
    private  String nombre_Empleado;
    private String tipo_Empleado;

    public Empleado(){

    }

    public Empleado(int id_Empleado, int id_Local, String nombre_Empleado, String tipo_Empleado) {
        this.id_Empleado = id_Empleado;
        this.id_Local = id_Local;
        this.nombre_Empleado = nombre_Empleado;
        this.tipo_Empleado = tipo_Empleado;
    }

    public int getId_Local() {
        return id_Local;
    }

    public void setId_Local(int id_Local) {
        this.id_Local = id_Local;
    }

    public void setId_Empleado(int id_Empleado) {
        this.id_Empleado = id_Empleado;
    }

    public void setNombre_Empleado(String nombre_Empleado) {
        this.nombre_Empleado = nombre_Empleado;
    }

    public void setTipo_Empleado(String tipo_Empleado) {
        this.tipo_Empleado = tipo_Empleado;
    }

    public int getId_Empleado() {
        return id_Empleado;
    }

    public String getNombre_Empleado() {
        return nombre_Empleado;
    }

    public String getTipo_Empleado() {
        return tipo_Empleado;
    }
}
