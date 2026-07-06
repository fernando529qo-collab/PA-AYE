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
public class Alineacion {
    private Partido partido;
    private Pais pais;
    private List<Jugador> jugadoresTitulares;

    public Alineacion() {
    }

    public Alineacion(Partido partido,Pais pais, List<Jugador> jugadoresTitulares) {
        this.partido = partido;
        this.pais = pais;
        this.jugadoresTitulares = jugadoresTitulares;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Jugador> getJugadoresTitulares() {
        return jugadoresTitulares;
    }

    public void setJugadoresTitulares(List<Jugador> jugadoresTitulares) {
        this.jugadoresTitulares = jugadoresTitulares;
    }

    @Override
    public String toString() {
        return "Alineacion{" + "partido=" + partido + "pais=" + pais + ", jugadoresTitulares=" + jugadoresTitulares + '}';
    }
}
