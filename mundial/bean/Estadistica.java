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

    public void setRemates(int remates) {
        this.remates = remates;
    }

    public int getRematesArco() {
        return rematesArco;
    }

    public void setRematesArco(int rematesArco) {
        this.rematesArco = rematesArco;
    }

    public int getPosesion() {
        return posesion;
    }

    public void setPosesion(int posesion) {
        this.posesion = posesion;
    }

    public int getPases() {
        return pases;
    }

    public void setPases(int pases) {
        this.pases = pases;
    }

    public int getPresicionPases() {
        return presicionPases;
    }

    public void setPresicionPases(int presicionPases) {
        this.presicionPases = presicionPases;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public int getTarjetasAmarillas() {
        return tarjetasAmarillas;
    }

    public void setTarjetasAmarillas(int tarjetasAmarillas) {
        this.tarjetasAmarillas = tarjetasAmarillas;
    }

    public int getTarjetasRojas() {
        return tarjetasRojas;
    }

    public void setTarjetasRojas(int tarjetasRojas) {
        this.tarjetasRojas = tarjetasRojas;
    }

    public int getPosicionAdelantada() {
        return posicionAdelantada;
    }

    public void setPosicionAdelantada(int posicionAdelantada) {
        this.posicionAdelantada = posicionAdelantada;
    }

    public int getTirosEsquina() {
        return tirosEsquina;
    }

    public void setTirosEsquina(int tirosEsquina) {
        this.tirosEsquina = tirosEsquina;
    }

    @Override
    public String toString() {
        return "Estadistica{" +
                "remates=" + remates +
                ", rematesArco=" + rematesArco +
                ", posesion=" + posesion +
                ", pases=" + pases +
                ", presicionPases=" + presicionPases +
                ", faltas=" + faltas +
                ", tarjetasAmarillas=" + tarjetasAmarillas +
                ", tarjetasRojas=" + tarjetasRojas +
                ", posicionAdelantada=" + posicionAdelantada +
                ", tirosEsquina=" + tirosEsquina +
                '}';
    }
}