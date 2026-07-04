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
public class Bracket {
    
    private RondaEliminacion ronda;
    private List<Partido> bracketPartidos;

    public Bracket(RondaEliminacion ronda, List<Partido> bracketPartidos) {
        this.ronda = ronda;
        this.bracketPartidos = bracketPartidos;
    }

    public RondaEliminacion getRonda() {
        return ronda;
    }

    public void setRonda(RondaEliminacion ronda) {
        this.ronda = ronda;
    }

    public List<Partido> getBracketPartidos() {
        return bracketPartidos;
    }

    public void setBracketPartidos(List<Partido> bracketPartidos) {
        this.bracketPartidos = bracketPartidos;
    }

    @Override
    public String toString() {
        return "Bracket{" + "ronda=" + ronda + ", bracketPartidos=" + bracketPartidos + '}';
    }
    
    
}
