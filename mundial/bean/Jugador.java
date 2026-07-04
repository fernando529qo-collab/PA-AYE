/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundial.bean;

/**
 *
 * @author ferna
 */
public class Jugador {
    
    private String id;
    private int dorsal;
    private String nombre;
    private boolean esTitular;
    private int goles;
    private int asistencia;
    
    private Posicion posicion;
    private Pais pais;

    public Jugador(String id, int dorsal, String nombre, boolean esTitular, int goles, int asistencia, Posicion posicion, Pais pais) {
        this.id = id;
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.esTitular = esTitular;
        this.goles = goles;
        this.asistencia = asistencia;
        this.posicion = posicion;
        this.pais = pais;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsTitular() {
        return esTitular;
    }

    public void setEsTitular(boolean esTitular) {
        this.esTitular = esTitular;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id='" + id + '\'' +
                ", dorsal=" + dorsal +
                ", nombre='" + nombre + '\'' +
                ", esTitular=" + esTitular +
                ", goles=" + goles +
                ", asistencia=" + asistencia +
                ", posicion=" + posicion +
                ", pais=" + pais +
                '}';
    }
}
