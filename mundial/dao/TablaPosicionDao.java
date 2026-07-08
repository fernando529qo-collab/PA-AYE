/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundial.dao;

import mundial.bean.Grupo;
import mundial.bean.Pais;
import mundial.bean.TablaPosicion;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */

public class TablaPosicionDao {
//Busca la posición de un país dentro de la tabla
    public static int buscarPosicionPais(Grupo grupo, String id) {
        for (int i = 0; i < grupo.getTabla().size(); i++) {
            if (grupo.getTabla().get(i).getPais().getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    //Busca un país dentro de la tabla
    public static TablaPosicion buscarPaisTabla(Grupo grupo, String id) {
        int pos = buscarPosicionPais(grupo, id);
        if (pos != -1) {
            return grupo.getTabla().get(pos);
        }
        return null;
    }

    //Crea la tabla de posiciones del grupo
    public static void crearTablaPosiciones(Grupo grupo) {
        grupo.getTabla().clear();
        for (int i = 0; i < grupo.getGrupoPaises().size(); i++) {
            TablaPosicion tabla = new TablaPosicion(grupo.getGrupoPaises().get(i));
            grupo.getTabla().add(tabla);
        }
    }

    //Lista la tabla de posiciones
    public static ArrayList<TablaPosicion> mostrarTablaPosiciones(Grupo grupo) {
        return new ArrayList<>(grupo.getTabla());
    }

    //Actualiza la tabla de posiciones después de finalizar un partido
    public static void actualizarTablaPosiciones(Grupo grupo, Pais local, Pais visitante, int golesLocal, int golesVisitante) {
        TablaPosicion tablaLocal = buscarPaisTabla(grupo, local.getId());
        TablaPosicion tablaVisitante = buscarPaisTabla(grupo, visitante.getId());
        if (tablaLocal == null || tablaVisitante == null) {
            return;
        }
        tablaLocal.setPartidosJugados(tablaLocal.getPartidosJugados() + 1);
        tablaVisitante.setPartidosJugados(tablaVisitante.getPartidosJugados() + 1);
        tablaLocal.setGolesFavor(tablaLocal.getGolesFavor() + golesLocal);
        tablaLocal.setGolesContra(tablaLocal.getGolesContra() + golesVisitante);
        tablaVisitante.setGolesFavor(tablaVisitante.getGolesFavor() + golesVisitante);
        tablaVisitante.setGolesContra(tablaVisitante.getGolesContra() + golesLocal);
        if (golesLocal > golesVisitante) {
            tablaLocal.setPartidosGanados(tablaLocal.getPartidosGanados() + 1);
            tablaVisitante.setPartidosPerdidos(tablaVisitante.getPartidosPerdidos() + 1);
        } else if (golesLocal < golesVisitante) {
            tablaVisitante.setPartidosGanados(tablaVisitante.getPartidosGanados() + 1);
            tablaLocal.setPartidosPerdidos(tablaLocal.getPartidosPerdidos() + 1);
        } else {
            tablaLocal.setPartidosEmpatados(tablaLocal.getPartidosEmpatados() + 1);
            tablaVisitante.setPartidosEmpatados(tablaVisitante.getPartidosEmpatados() + 1);
        }
        calcularPuntos(tablaLocal);
        calcularPuntos(tablaVisitante);
        calcularDiferenciaGoles(tablaLocal);
        calcularDiferenciaGoles(tablaVisitante);
    }

    //Calcula los puntos de un país
    public static void calcularPuntos(TablaPosicion tabla) {
        int puntos = (tabla.getPartidosGanados() * 3) + (tabla.getPartidosEmpatados());
        tabla.setPuntos(puntos);
    }

    //Calcula la diferencia de goles
    public static void calcularDiferenciaGoles(TablaPosicion tabla) {
        int diferencia = tabla.getGolesFavor() - tabla.getGolesContra();
        tabla.setDiferenciaGoles(diferencia);
    }

    //Ordena la tabla de posiciones
    public static void ordenarTablaPosiciones(Grupo grupo) {
        for (int i = 0; i < grupo.getTabla().size() - 1; i++) {
            for (int j = i + 1; j < grupo.getTabla().size(); j++) {
                TablaPosicion equipo1 = grupo.getTabla().get(i);
                TablaPosicion equipo2 = grupo.getTabla().get(j);
                boolean cambiar = false;
                if (equipo2.getPuntos() > equipo1.getPuntos()) {
                    cambiar = true;
                } else if (equipo2.getPuntos() == equipo1.getPuntos()) {
                    if (compararDesempate(equipo1, equipo2)) {
                        cambiar = true;
                    }
                }
                if (cambiar) {
                    TablaPosicion aux = grupo.getTabla().get(i);
                    grupo.getTabla().set(i, grupo.getTabla().get(j));
                    grupo.getTabla().set(j, aux);
                }
            }
        }
    }

    //Compara dos países cuando tienen los mismos puntos
    public static boolean compararDesempate(TablaPosicion equipo1, TablaPosicion equipo2) {
        if (equipo2.getDiferenciaGoles() > equipo1.getDiferenciaGoles()) {
            return true;
        }
        if (equipo2.getDiferenciaGoles() == equipo1.getDiferenciaGoles()) {
            if (equipo2.getGolesFavor() > equipo1.getGolesFavor()) {
                return true;
            }
            if (equipo2.getGolesFavor() == equipo1.getGolesFavor()) {
                if (equipo2.getPais().getrankingFifa() < equipo1.getPais().getrankingFifa()) {
                    return true;
                }
            }
        }
        return false;
    }

    //Obtiene el primer lugar de la tabla
    public static TablaPosicion obtenerPrimerLugar(Grupo grupo) {
        ordenarTablaPosiciones(grupo);
        if (grupo.getTabla().size() > 0) {
            return grupo.getTabla().get(0);
        }
        return null;
    }

    //Obtiene el segundo lugar
    public static TablaPosicion obtenerSegundoLugar(Grupo grupo) {
        ordenarTablaPosiciones(grupo);
        if (grupo.getTabla().size() > 1) {
            return grupo.getTabla().get(1);
        }
        return null;
    }

    //Obtiene el tercer lugar
    public static TablaPosicion obtenerTercerLugar(Grupo grupo) {
        ordenarTablaPosiciones(grupo);
        if (grupo.getTabla().size() > 2) {
            return grupo.getTabla().get(2);
        }
        return null;
    }

}
