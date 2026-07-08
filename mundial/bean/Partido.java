/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundial.bean;

import java.util.List;

/**
 *
 * @author ferna
 */
public class Partido {
    private String id;
    private String fecha;
    private int golesLocal;
    private int golesVisitante;
    private int probabilidadVictoriaLocal;
    private int probabilidadVictoriaVisitante;
    
    private List<Alineacion> alineaciones; //La primera alineacion es del local
    private EstadoPartido estado;

    public Partido(String id, String fecha, int golesLocal, int golesVisitante, int probabilidadVictoriaLocal, int probabilidadVictoriaVisitante, List<Alineacion> alineaciones, EstadoPartido estado) {
        this.id = id;
        this.fecha = fecha;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.probabilidadVictoriaLocal = probabilidadVictoriaLocal;
        this.probabilidadVictoriaVisitante = probabilidadVictoriaVisitante;
        this.alineaciones = alineaciones;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public int getProbabilidadVictoriaLocal() {
        return probabilidadVictoriaLocal;
    }

    public void setProbabilidadVictoriaLocal(int probabilidadVictoriaLocal) {
        this.probabilidadVictoriaLocal = probabilidadVictoriaLocal;
    }

    public int getProbabilidadVictoriaVisitante() {
        return probabilidadVictoriaVisitante;
    }

    public void setProbabilidadVictoriaVisitante(int probabilidadVictoriaVisitante) {
        this.probabilidadVictoriaVisitante = probabilidadVictoriaVisitante;
    }

    public List<Alineacion> getAlineaciones() {
        return alineaciones;
    }

    public void setAlineaciones(List<Alineacion> alineaciones) {
        this.alineaciones = alineaciones;
    }

    public EstadoPartido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPartido estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Partido{" + "id= " + id + ", fecha=" + fecha + ", golesLocal=" + golesLocal + ", golesVisitante=" + golesVisitante + ", probabilidadVictoriaLocal=" + probabilidadVictoriaLocal + ", probabilidadVictoriaVisitante=" + probabilidadVictoriaVisitante + ", alineaciones=" + alineaciones + ", estado=" + estado + '}';
    }
}
