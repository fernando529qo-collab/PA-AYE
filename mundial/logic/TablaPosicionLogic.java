/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundial.logic;

import mundial.dao.TablaPosicionDao;
import mundial.bean.Grupo;
import mundial.bean.Pais;
import mundial.bean.TablaPosicion;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class TablaPosicionLogic {
private static TablaPosicionDao tablaDao = new TablaPosicionDao();

    // Retorna la lista de posiciones actual de un grupo en específico
    public static ArrayList<TablaPosicion> obtenerTablaPorGrupo(Grupo grupo) {
        if (grupo == null) return new ArrayList<>();
        return tablaDao.mostrarTablaPosiciones(grupo);
    }

    // Sincroniza y recalcula las estadísticas de los equipos tras finalizar un encuentro[cite: 2]
    public static boolean procesarResultadoPartido(Grupo grupo, Partido partido) {
        try {
            if (grupo == null || partido == null) return false;
            tablaDao.actualizarTablaPosiciones(grupo, partido);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // Extrae los 12 terceros lugares y aplica los criterios jerárquicos para ordenar los mejores 8[cite: 1, 2]
    public static ArrayList<Pais> obtenerMejoresTerceros(ArrayList<Grupo> listaGrupos) {
        ArrayList<TablaPosicion> listaTerceros = new ArrayList<>();
        for (Grupo g : listaGrupos) {
            try {
                TablaPosicion tercero = tablaDao.obtenerTercerLugar(g);
                if (tercero != null) listaTerceros.add(tercero);
            } catch (Exception e) {}
        }
        for (int i = 0; i < listaTerceros.size() - 1; i++) {
            for (int j = i + 1; j < listaTerceros.size(); j++) {
                TablaPosicion t1 = listaTerceros.get(i);
                TablaPosicion t2 = listaTerceros.get(j);
                boolean intercambiar = false;
                if (t2.getPuntos() > t1.getPuntos()) {
                    intercambiar = true;
                } else if (t2.getPuntos() == t1.getPuntos()) {
                    if (t2.getDiferenciaGoles() > t1.getDiferenciaGoles()) {
                        intercambiar = true;
                    } else if (t2.getDiferenciaGoles() == t1.getDiferenciaGoles()) {
                        if (t2.getGolesFavor() > t1.getGolesFavor()) {
                            intercambiar = true;
                        } else if (t2.getGolesFavor() == t1.getGolesFavor()) {
                            if (t2.getGolesContra() < t1.getGolesContra()) intercambiar = true;
                        }
                    }
                }
                if (intercambiar) {
                    TablaPosicion aux = listaTerceros.get(i);
                    listaTerceros.set(i, listaTerceros.get(j));
                    listaTerceros.set(j, aux);
                }
            }
        }
        ArrayList<Pais> mejoresTercerosClasificados = new ArrayList<>();
        int limite = Math.min(8, listaTerceros.size());
        for (int i = 0; i < limite; i++) {
            mejoresTercerosClasificados.add(listaTerceros.get(i).getPais());
        }
        return mejoresTercerosClasificados;
    }

    // Consolida los 32 clasificados combinando accesos directos y mejores terceros[cite: 1, 2]
    public static ArrayList<Pais> consolidarClasificadosDieciseisavos(ArrayList<Grupo> listaGrupos) {
        ArrayList<Pais> clasificadosDirectos = new ArrayList<>();
        for (Grupo g : listaGrupos) {
            try {
                TablaPosicion primero = tablaDao.obtenerPrimerLugar(g);
                TablaPosicion segundo = tablaDao.obtenerSegundoLugar(g);
                if (primero != null) clasificadosDirectos.add(primero.getPais());
                if (segundo != null) clasificadosDirectos.add(segundo.getPais());
            } catch (Exception e) {}
        }
        clasificadosDirectos.addAll(obtenerMejoresTerceros(listaGrupos));
        return clasificadosDirectos;
    }
}