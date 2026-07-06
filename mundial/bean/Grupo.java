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
public class Grupo {
    
    private char codigo;
    
    private List<Pais> grupoPaises;
    // private TablaPosicion tabla;
    private List<TablaPosicion> tabla;

    public Grupo(char codigo, List<Pais> grupoPaises, List<TablaPosicion> tabla) {
        this.codigo = codigo;
        this.grupoPaises = grupoPaises;
        this.tabla = tabla;
    }

    public char getCodigo() {
        return codigo;
    }

    public void setCodigo(char codigo) {
        this.codigo = codigo;
    }

    public List<Pais> getGrupoPaises() {
        return grupoPaises;
    }

    public void setGrupoPaises(List<Pais> grupoPaises) {
        this.grupoPaises = grupoPaises;
    }

    public List<TablaPosicion> getTabla() {
        return tabla;
    }

    public void setTabla(List<TablaPosicion> tabla) {
        this.tabla = tabla;
    }

    @Override
    public String toString() {
        return "Grupo{" + "codigo=" + codigo + ", grupoPaises=" + grupoPaises + ", tabla=" + tabla + '}';
    }
}
