/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundial.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ferna
 */
public class Alineacion {
    private Partido partido;
    private Pais pais;
    // private List<Jugador> jugadoresTitulares;
    // Se reemplaza por la List por qué el Map ayuda con la asociación de Jugador y Posicion, clave para el manejo del logic
    // Al usar Map sirve para logar el manejo de la vista dinamica e interacctiva al momento de escoger los jugadores (plantilla) en la simulacion
    private Map<Jugador, Posicion> jugadoresTitulares;

    public Alineacion() {
    }

    public Alineacion(Partido partido,Pais pais) {
        this.partido = partido;
        this.pais = pais;
        this.jugadoresTitulares = new HashMap<>();
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

    public Map<Jugador, Posicion> getJugadoresTitulares() {
        return jugadoresTitulares;
    }

    public void setJugadoresTitulares(Map<Jugador, Posicion> jugadoresTitulares) {
        this.jugadoresTitulares = jugadoresTitulares;
    }

    public void agregarJugador(Jugador jugador, Posicion posicionSeleccionada) {
        this.jugadoresTitulares.put(jugador, posicionSeleccionada);
    }

    @Override
    public String toString() {
        return "Alineacion{" + "partido=" + partido + "pais=" + pais + ", jugadoresTitulares=" + jugadoresTitulares + '}';
    }
}
