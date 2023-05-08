package sv.edu.ues.fia.eisi.cafetinesues;

public class Cliente {
    private int id_cliente;
    private String nombres;
    private String apellidos;
    private String fecha_nacimiento;
    private int id_ubicacion;

    public Cliente(){

    }

    public Cliente(String nombres,String apellidos,String fechaN,int id_ubicacion){
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fechaN;
        this.id_ubicacion = id_ubicacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
