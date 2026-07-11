/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundial.dao;

import mundial.bean.Grupo;
import mundial.bean.Pais;
import mundial.bean.Partido;
import mundial.bean.TablaPosicion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author ACER
 */
public class TablaPosicionDao {

    private ArrayList<TablaPosicion> lstTablaPosiciones;
    private Gson gson = new Gson();
    private File file = new File("TABLA_POSICIONES.json");

    public TablaPosicionDao() {
        this.lstTablaPosiciones = leer();
    }

    // Busca la posición de un país dentro de la tabla
    public int buscarPos(String idPais) {
        for (int i = 0; i < lstTablaPosiciones.size(); i++) {
            if (lstTablaPosiciones.get(i).getPais().getId().equalsIgnoreCase(idPais)) {
                return i;
            }
        }
        return -1;
    }

    // Busca un país dentro de la tabla
    public TablaPosicion buscarObj(String idPais) {
        int pos = buscarPos(idPais);
        if (pos != -1) {
            return lstTablaPosiciones.get(pos);
        }
        return null;
    }

    // Registra una posición
    public boolean registrar(TablaPosicion tabla) throws Exception {
        if (buscarPos(tabla.getPais().getId()) == -1) {
            lstTablaPosiciones.add(tabla);
            guardar(lstTablaPosiciones);
            return true;
        }
        return false;
    }

    // Actualiza una posición
    public boolean actualizar(TablaPosicion tabla) throws Exception {
        int pos = buscarPos(tabla.getPais().getId());
        if (pos != -1) {
            lstTablaPosiciones.set(pos, tabla);
            guardar(lstTablaPosiciones);
            return true;
        }
        return false;
    }

    // Elimina una posición
    public boolean eliminar(String idPais) throws Exception {
        int pos = buscarPos(idPais);
        if (pos != -1) {
            lstTablaPosiciones.remove(pos);
            guardar(lstTablaPosiciones);
            return true;
        }
        return false;
    }

    // Muestra toda la tabla
    public ArrayList<TablaPosicion> mostrar() {
        return lstTablaPosiciones;
    }

    // Lee el archivo JSON
    public ArrayList<TablaPosicion> leer() {
        try {
            if (!file.exists()) {
                return new ArrayList<>();
            }
            ArrayList<TablaPosicion> tabla;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                tabla = gson.fromJson(br, new TypeToken<ArrayList<TablaPosicion>>() {
                }.getType());
            }
            return (tabla != null) ? tabla : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // Guarda el archivo JSON
    public void guardar(ArrayList<TablaPosicion> lista) throws Exception {
        try (FileWriter fw = new FileWriter(file)) {
            gson.toJson(lista, fw);
        } catch (Exception e) {
            throw e;
        }
    }

    // Crea la tabla de posiciones de un grupo
    public void crearTablaPosiciones(Grupo grupo) throws Exception {
        grupo.getTabla().clear();
        for (int i = 0; i < grupo.getGrupoPaises().size(); i++) {
            TablaPosicion tabla = new TablaPosicion(grupo.getGrupoPaises().get(i));
            grupo.getTabla().add(tabla);
            registrar(tabla);
        }
        guardar(lstTablaPosiciones);
    }

    // Muestra la tabla de posiciones de un grupo
    public ArrayList<TablaPosicion> mostrarTablaPosiciones(Grupo grupo) {
        return grupo.getTabla();
    }

    // Actualiza la tabla de posiciones después de un partido
    public void actualizarTablaPosiciones(Grupo grupo, Partido partido) throws Exception {
        Pais local = partido.getAlineaciones().get(0).getPais();
        Pais visitante = partido.getAlineaciones().get(1).getPais();
        int golesLocal = partido.getGolesLocal();
        int golesVisitante = partido.getGolesVisitante();

        TablaPosicion tablaLocal = buscarObj(local.getId());
        TablaPosicion tablaVisitante = buscarObj(visitante.getId());
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
        } else if (golesVisitante > golesLocal) {
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

        actualizar(tablaLocal);
        actualizar(tablaVisitante);
        ordenarTablaPosiciones(grupo);
        guardar(lstTablaPosiciones);
    }

    // Calcula los puntos de un país
    public void calcularPuntos(TablaPosicion tabla) {
        tabla.setPuntos((tabla.getPartidosGanados() * 3) + tabla.getPartidosEmpatados());
    }

    // Calcula la diferencia de goles
    public void calcularDiferenciaGoles(TablaPosicion tabla) {
        tabla.setDiferenciaGoles(tabla.getGolesFavor() - tabla.getGolesContra());
    }

    // Compara dos países aplicando los criterios de desempate
    public boolean compararDesempate(Grupo grupo, TablaPosicion pais1, TablaPosicion pais2) {
        if (pais2.getDiferenciaGoles() > pais1.getDiferenciaGoles()) {
            return true;
        }
        if (pais2.getDiferenciaGoles() < pais1.getDiferenciaGoles()) {
            return false;
        }

        if (pais2.getGolesFavor() > pais1.getGolesFavor()) {
            return true;
        }
        if (pais2.getGolesFavor() < pais1.getGolesFavor()) {
            return false;
        }

        if (pais2.getGolesContra() < pais1.getGolesContra()) {
            return true;
        }
        if (pais2.getGolesContra() > pais1.getGolesContra()) {
            return false;
        }

        for (int i = 0; i < grupo.getEncuentros().size(); i++) {
            Partido partido = grupo.getEncuentros().get(i);
            Pais local = partido.getAlineaciones().get(0).getPais();
            Pais visitante = partido.getAlineaciones().get(1).getPais();

            boolean localPais1 = local.getId().equalsIgnoreCase(pais1.getPais().getId());
            boolean visitantePais2 = visitante.getId().equalsIgnoreCase(pais2.getPais().getId());
            boolean localPais2 = local.getId().equalsIgnoreCase(pais2.getPais().getId());
            boolean visitantePais1 = visitante.getId().equalsIgnoreCase(pais1.getPais().getId());

            if ((localPais1 && visitantePais2) || (localPais2 && visitantePais1)) {
                if (partido.getGolesLocal() > partido.getGolesVisitante()) {
                    return localPais2;
                }
                if (partido.getGolesVisitante() > partido.getGolesLocal()) {
                    return visitantePais2;
                }
                break;
            }
        }
        return false;
    }

    // Ordena la tabla de posiciones del grupo
    public void ordenarTablaPosiciones(Grupo grupo) throws Exception {
        for (int i = 0; i < grupo.getTabla().size() - 1; i++) {
            for (int j = i + 1; j < grupo.getTabla().size(); j++) {
                TablaPosicion pais1 = grupo.getTabla().get(i);
                TablaPosicion pais2 = grupo.getTabla().get(j);
                boolean cambiar = false;

                if (pais2.getPuntos() > pais1.getPuntos()) {
                    cambiar = true;
                }
                if (pais2.getPuntos() == pais1.getPuntos()) {
                    cambiar = compararDesempate(grupo, pais1, pais2);
                }

                if (cambiar) {
                    TablaPosicion aux = grupo.getTabla().get(i);
                    grupo.getTabla().set(i, grupo.getTabla().get(j));
                    grupo.getTabla().set(j, aux);
                }
            }
        }
        guardar(lstTablaPosiciones);
    }

    // Obtiene el primer lugar del grupo
    public TablaPosicion obtenerPrimerLugar(Grupo grupo) throws Exception {
        ordenarTablaPosiciones(grupo);
        return !grupo.getTabla().isEmpty() ? grupo.getTabla().get(0) : null;
    }

    // Obtiene el segundo lugar del grupo
    public TablaPosicion obtenerSegundoLugar(Grupo grupo) throws Exception {
        ordenarTablaPosiciones(grupo);
        return grupo.getTabla().size() > 1 ? grupo.getTabla().get(1) : null;
    }

    // Obtiene el tercer lugar del grupo
    public TablaPosicion obtenerTercerLugar(Grupo grupo) throws Exception {
        ordenarTablaPosiciones(grupo);
        return grupo.getTabla().size() > 2 ? grupo.getTabla().get(2) : null;
    }

}
