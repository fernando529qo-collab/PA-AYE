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
public class Pais {
    
    private String id;
    private String nombre;
    private int rankingFifa;
    private int mundialesGanados;
    private boolean esAnfitrion;
    private int ataque;
    private int defensa;
    private int factorSuerte;
   
    private Grupo grupo;
    private List<Jugador> paisJugadores;
    private Confederacion confederacion;
    private EstiloJuego estiloJuego;

    public Pais(String id, String nombre, int rankingFifa, int mundialesGanados, boolean esAnfitrion, int ataque, int defensa, int factorSuerte, Grupo grupo, List<Jugador> paisJugadores, Confederacion confederacion, EstiloJuego estiloJuego) {
        this.id = id;
        this.nombre = nombre;
        this.rankingFifa = rankingFifa;
        this.mundialesGanados = mundialesGanados;
        this.esAnfitrion = esAnfitrion;
        this.ataque = ataque;
        this.defensa = defensa;
        this.factorSuerte = factorSuerte;
        this.grupo = grupo;
        this.paisJugadores = paisJugadores;
        this.confederacion = confederacion;
        this.estiloJuego = estiloJuego;
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

    public int getRankingFifa() {
        return rankingFifa;
    }

    public void setRankingFifa(int rankingFifa) {
        this.rankingFifa = rankingFifa;
    }

    public int getMundialesGanados() {
        return mundialesGanados;
    }

    public void setMundialesGanados(int mundialesGanados) {
        this.mundialesGanados = mundialesGanados;
    }

    public boolean isEsAnfitrion() {
        return esAnfitrion;
    }

    public void setEsAnfitrion(boolean esAnfitrion) {
        this.esAnfitrion = esAnfitrion;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getFactorSuerte() {
        return factorSuerte;
    }

    public void setFactorSuerte(int factorSuerte) {
        this.factorSuerte = factorSuerte;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Jugador> getPaisJugadores() {
        return paisJugadores;
    }

    public void setPaisJugadores(List<Jugador> paisJugadores) {
        this.paisJugadores = paisJugadores;
    }

    public Confederacion getConfederacion() {
        return confederacion;
    }

    public void setConfederacion(Confederacion confederacion) {
        this.confederacion = confederacion;
    }

    public EstiloJuego getEstiloJuego() {
        return estiloJuego;
    }

    public void setEstiloJuego(EstiloJuego estiloJuego) {
        this.estiloJuego = estiloJuego;
    }

    @Override
    public String toString() {
        return "Pais{" + "id=" + id + ", nombre=" + nombre + ", rankingFifa=" + rankingFifa + ", mundialesGanados=" + mundialesGanados + ", esAnfitrion=" + esAnfitrion + ", ataque=" + ataque + ", defensa=" + defensa + ", factorSuerte=" + factorSuerte + ", grupo=" + grupo + ", paisJugadores=" + paisJugadores + ", confederacion=" + confederacion + ", estiloJuego=" + estiloJuego + '}';
    }
}
