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
    private String fecha;
    private int golesLocal;
    private int golesVisitante;
    private int probabilidadVictoriaLocal;
    private int probabilidadVictoriaVisitante;
    
    private Pais paisLocal;
    private Pais paisVisitante;
    private EstadoPartido estado;
    private List<Estadistica> partidoEstadisticas;

    public Partido(String fecha, int golesLocal, int golesVisitante, int probabilidadVictoriaLocal,
            int probabilidadVictoriaVisitante, Pais paisLocal, Pais paisVisitante, EstadoPartido estado,
            List<Estadistica> partidoEstadisticas) {
        this.fecha = fecha;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.probabilidadVictoriaLocal = probabilidadVictoriaLocal;
        this.probabilidadVictoriaVisitante = probabilidadVictoriaVisitante;
        this.paisLocal = paisLocal;
        this.paisVisitante = paisVisitante;
        this.estado = estado;
        this.partidoEstadisticas = partidoEstadisticas;
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

    public Pais getPaisLocal() {
        return paisLocal;
    }

    public void setPaisLocal(Pais paisLocal) {
        this.paisLocal = paisLocal;
    }

    public Pais getPaisVisitante() {
        return paisVisitante;
    }

    public void setPaisVisitante(Pais paisVisitante) {
        this.paisVisitante = paisVisitante;
    }

    public EstadoPartido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPartido estado) {
        this.estado = estado;
    }

    public List<Estadistica> getPartidoEstadisticas() {
        return partidoEstadisticas;
    }

    public void setPartidoEstadisticas(List<Estadistica> partidoEstadisticas) {
        this.partidoEstadisticas = partidoEstadisticas;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "fecha='" + fecha + '\'' +
                ", golesLocal=" + golesLocal +
                ", golesVisitante=" + golesVisitante +
                ", probabilidadVictoriaLocal=" + probabilidadVictoriaLocal +
                ", probabilidadVictoriaVisitante=" + probabilidadVictoriaVisitante +
                ", paisLocal=" + paisLocal +
                ", paisVisitante=" + paisVisitante +
                ", estado=" + estado +
                ", partidoEstadisticas=" + partidoEstadisticas +
                '}';
    }
}
