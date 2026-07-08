/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundial.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ferna
 */
public class Grupo {
   
    private char codigo;
    private ArrayList<Pais> grupoPaises;
    private ArrayList<TablaPosicion> tabla;
    private List<Partido> encuentros;

    public Grupo() {
        grupoPaises = new ArrayList<>();
        tabla = new ArrayList<>();
    }

    public Grupo(char codigo) {
        this.codigo = codigo;
        this.grupoPaises = new ArrayList<>();
        this.tabla = new ArrayList<>();
        this.encuentros = new ArrayList<>();
    }

    public char getCodigo() {
        return codigo;
    }

    public List getEncuentros() {
        return encuentros;
    }

    public void setEncuentros(List encuentros) {
        this.encuentros = encuentros;
    }

    public void setCodigo(char codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Pais> getGrupoPaises() {
        return grupoPaises;
    }

    public void setGrupoPaises(ArrayList<Pais> grupoPaises) {
        this.grupoPaises = grupoPaises;
    }

    public ArrayList<TablaPosicion> getTabla() {
        return tabla;
    }

    public void setTabla(ArrayList<TablaPosicion> tabla) {
        this.tabla = tabla;
    }

    @Override
    public String toString() {
        return "Grupo{"
                + "codigo=" + codigo
                + ", grupoPaises=" + grupoPaises
                + ", tabla=" + tabla
                + '}';
    }
}
