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
    private List<Jugador> alineacionJugadores;
    
    public Alineacion(Partido partido, Pais pais, List<Jugador> alineacionJugadores) {
        this.partido = partido;
        this.pais = pais;
        this.alineacionJugadores = alineacionJugadores;
    }

    public Partido getPartido() {
        return partido;
    }

    public Pais getPais() {
        return pais;
    }

    public List<Jugador> getAlineacionJugadores() {
        return alineacionJugadores;
    }

    @Override
    public String toString() {
        return "Alineacion{" +
                "partido=" + partido +
                ", pais=" + pais +
                ", alineacionJugadores=" + alineacionJugadores +
                '}';
    }
}
