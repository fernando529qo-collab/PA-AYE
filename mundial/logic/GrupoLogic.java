/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundial.logic;

import mundial.dao.GrupoDao;
import mundial.dao.TablaPosicionDao;
import mundial.bean.Grupo;
import mundial.bean.Partido;
import mundial.bean.Pais;
import java.util.ArrayList;

public class GrupoLogic {
    private static GrupoDao grupoDao = new GrupoDao();
    private static TablaPosicionDao tablaDao = new TablaPosicionDao();

    // Verifica la existencia de un grupo por su código único
    public static boolean validarGrupo(char codigo) {
        return grupoDao.existeGrupo(codigo);
    }

    // Busca y obtiene un objeto Grupo específico
    public static Grupo buscarGrupo(char codigo) {
        return grupoDao.buscarObj(codigo);
    }

    // Registra un nuevo grupo si su código no está duplicado
    public static boolean registrarGrupo(Grupo grupo) {
        try {
            if (validarGrupo(grupo.getCodigo())) return false;
            return grupoDao.registrar(grupo);
        } catch (Exception ex) {
            return false;
        }
    }

    // Actualiza los datos modificados de un grupo en el archivo de persistencia
    public static boolean actualizarGrupo(Grupo grupo) {
        try {
            if (!validarGrupo(grupo.getCodigo())) return false;
            return grupoDao.actualizar(grupo);
        } catch (Exception ex) {
            return false;
        }
    }

    // Remueve un grupo del sistema mediante su código
    public static boolean eliminarGrupo(char codigo) {
        try {
            if (!validarGrupo(codigo)) return false;
            return grupoDao.eliminar(codigo);
        } catch (Exception ex) {
            return false;
        }
    }

    // Obtiene la lista completa de todos los grupos registrados
    public static ArrayList<Grupo> obtenerTodosLosGrupos() {
        return grupoDao.mostrar();
    }

    // Retorna la cantidad total de grupos almacenados
    public static int obtenerCantidadGrupos() {
        return grupoDao.cantidadGrupos();
    }

    // Ejecuta de forma secuencial el sorteo, la creación de fixtures y la inicialización de tablas[cite: 1, 2]
    public static boolean ejecutarSorteoOficial() {
        try {
            grupoDao.sortearGrupos();
            grupoDao.generarEncuentros();
            grupoDao.crearTablasPosiciones();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // Procesa el algoritmo matemático determinista del partido e impacta los resultados en la tabla[cite: 1, 2]
    public static boolean simularPartidoIndividual(Grupo grupo, Partido partido) {
        try {
            if (grupo == null || partido == null) return false;
            Pais local = partido.getAlineaciones().get(0).getPais();
            Pais visitante = partido.getAlineaciones().get(1).getPais();
            double poderLocal = (200 - local.getRankingFifa()) + local.getAtaque() + local.getDefensa() + (Math.random() * 50);
            double poderVisitante = (200 - visitante.getRankingFifa()) + visitante.getAtaque() + visitante.getDefensa() + (Math.random() * 50);
            int golesLocal = (int) Math.max(0, (poderLocal - poderVisitante) / 30 + (Math.random() * 3));
            int golesVisitante = (int) Math.max(0, (poderVisitante - poderLocal) / 30 + (Math.random() * 3));
            partido.setGolesLocal(golesLocal);
            partido.setGolesVisitante(golesVisitante);
            tablaDao.actualizarTablaPosiciones(grupo, partido);
            grupoDao.actualizar(grupo);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // Simula en lote todos los encuentros pendientes correspondientes a una jornada[cite: 1]
    public static boolean simularJornadaCompleta(Grupo grupo) {
        try {
            if (grupo == null) return false;
            ArrayList<Partido> encuentros = (ArrayList<Partido>) grupo.getEncuentros();
            for (Partido partido : encuentros) {
                if (partido.getGolesLocal() == 0 && partido.getGolesVisitante() == 0) {
                    simularPartidoIndividual(grupo, partido);
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // Obtiene la lista de encuentros programados para un grupo en específico[cite: 1]
    public static ArrayList<Partido> obtenerEncuentrosPorGrupo(char codigoGrupo) {
        return grupoDao.mostrarEncuentros(codigoGrupo);
    }

    // Obtiene el fixture absoluto con todos los partidos del torneo[cite: 1]
    public static ArrayList<Partido> obtenerEncuentrosTotales() {
        return grupoDao.mostrarEncuentrosTotales();
    }

    // Ejecuta el restablecimiento completo borrando el registro de grupos del torneo[cite: 1]
    public static boolean reiniciarRegistroTorneo() {
        try {
            grupoDao.limpiarRegistroGrupos();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}