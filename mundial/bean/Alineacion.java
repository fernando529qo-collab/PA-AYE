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
    
    private Pais pais;
    private List<Jugador> jugadoresTitulares;

    public Alineacion() {
    }

    public Alineacion(Pais pais, List<Jugador> jugadoresTitulares) {
        this.pais = pais;
        this.jugadoresTitulares = jugadoresTitulares;
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
        return "Alineacion{" + "pais=" + pais + ", jugadoresTitulares=" + jugadoresTitulares + '}';
    }
}
