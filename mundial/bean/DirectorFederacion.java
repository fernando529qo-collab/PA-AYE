/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundial.bean;

/**
 *
 * @author ferna
 */
public class DirectorFederacion extends Usuario {

    private boolean usuarioHablitado;
    
    private Pais pais;

    public DirectorFederacion(String id, String nombre, String contraseña, boolean usuarioHablitado, Pais pais) {
        super(id, nombre, contraseña);
        this.usuarioHablitado = usuarioHablitado;
        this.pais = pais;
    }

    public boolean isUsuarioHablitado() {
        return usuarioHablitado;
    }

    public void setUsuarioHablitado(boolean usuarioHablitado) {
        this.usuarioHablitado = usuarioHablitado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getContraseña() {
        return contraseña;
    }

    @Override
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Director Tecnico: " + super.toString()+ "Pais:" + pais;
    } 
}
