package mundial.bean;
public abstract class Usuario {
    //Se declaro con protected para que solo las clases hijas AdministradorFIFA y DirectorFederacion puedan tenerlas y puedan ver
    protected String id;
    protected String nombre;
    protected String contraseña;

    public Usuario(String id, String nombre, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Id:" + id + "Nombre:" + nombre + "Contraseña:" + contraseña;
    }   
}
