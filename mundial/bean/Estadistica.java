/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundial.bean;

/**
 *
 * @author ferna
 */
public class Estadistica {
    
    private int remates;
    private int rematesArco;
    private int posesion;
    private int pases;
    private int presicionPases;
    private int faltas;
    private int tarjetasAmarillas;
    private int tarjetasRojas;
    private int posicionAdelantada;
    private int tirosEsquina;

    public Estadistica(int remates, int rematesArco, int posesion, int pases, int presicionPases, int faltas, int tarjetasAmarillas, int tarjetasRojas, int posicionAdelantada, int tirosEsquina) {
        this.remates = remates;
        this.rematesArco = rematesArco;
        this.posesion = posesion;
        this.pases = pases;
        this.presicionPases = presicionPases;
        this.faltas = faltas;
        this.tarjetasAmarillas = tarjetasAmarillas;
        this.tarjetasRojas = tarjetasRojas;
        this.posicionAdelantada = posicionAdelantada;
        this.tirosEsquina = tirosEsquina;
    }   

    public int getRemates() {
        return remates;
    }

    public int getRematesArco() {
        return rematesArco;
    }   

    public int getPosesion() {
        return posesion;
    }

    public int getPases() {
        return pases;
    }

    public int getPresicionPases() {
        return presicionPases;
    }

    public int getFaltas() {
        return faltas;
    }

    public int getTarjetasAmarillas() {
        return tarjetasAmarillas;
    }   

    public int getTarjetasRojas() {
        return tarjetasRojas;
    }

    public int getPosicionAdelantada() {
        return posicionAdelantada;
    }   

    public int getTirosEsquina() {
        return tirosEsquina;
    }   

    
    
}
