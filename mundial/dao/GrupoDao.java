/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundial.dao;

import mundial.bean.Alineacion;
import mundial.bean.EstadoPartido;
import mundial.bean.Grupo;
import mundial.bean.Pais;
import mundial.bean.Partido;
import mundial.bean.TablaPosicion;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class GrupoDao {
  
    //Busca la posición de un grupo según su código
    public static int buscarPosicionGrupo(ArrayList<Grupo> grupos, char codigo) {
        for (int i = 0; i < grupos.size(); i++) {
            if (grupos.get(i).getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    //Busca un grupo por su código
    public static Grupo buscarGrupo(ArrayList<Grupo> grupos, char codigo) {
        for (int i = 0; i < grupos.size(); i++) {
            if (grupos.get(i).getCodigo() == codigo) {
                return grupos.get(i);
            }
        }
        return null;
    }

    //Registra un grupo
    public static void registrarGrupo(ArrayList<Grupo> grupos, Grupo grupo) {
        if (buscarPosicionGrupo(grupos, grupo.getCodigo()) == -1) {
            grupos.add(grupo);
        }
    }

    //Actualiza un grupo
    public static void actualizarGrupo(ArrayList<Grupo> grupos, Grupo grupo) {
        int pos = buscarPosicionGrupo(grupos, grupo.getCodigo());
        if (pos != -1) {
            grupos.set(pos, grupo);
        }
    }

    //Elimina un grupo
    public static void eliminarGrupo(ArrayList<Grupo> grupos, char codigo) {
        int pos = buscarPosicionGrupo(grupos, codigo);
        if (pos != -1) {
            grupos.remove(pos);
        }
    }

    //Muestra todos los grupos registrados
    public static ArrayList<Grupo> mostrarGrupos(ArrayList<Grupo> grupos) {
        return grupos;
    }

    //Obtiene un país por su id
    public static Pais buscarPais(ArrayList<Pais> paises, String id) {
        for (int i = 0; i < paises.size(); i++) {
            if (paises.get(i).getId().equalsIgnoreCase(id)) {
                return paises.get(i);
            }
        }
        return null;
    }

    //Ordena los países por Ranking FIFA
    public static void ordenarPaisesPorRanking(ArrayList<Pais> paises) {
        for (int i = 0; i < paises.size() - 1; i++) {
            for (int j = i + 1; j < paises.size(); j++) {
                if (paises.get(i).getRankingFifa() > paises.get(j).getRankingFifa()) {
                    Pais aux = paises.get(i);
                    paises.set(i, paises.get(j));
                    paises.set(j, aux);
                }
            }
        }
    }

    //Busca al país anfitrión
    public static Pais buscarPaisAnfitrion(ArrayList<Pais> paises) {
        for (int i = 0; i < paises.size(); i++) {
            if (paises.get(i).isEsAnfitrion()) {
                return paises.get(i);
            }
        }
        return null;
    }

    //Limpia todos los grupos antes de un nuevo sorteo
    public static void limpiarGrupos(ArrayList<Grupo> grupos) {
        for (int i = 0; i < grupos.size(); i++) {
            grupos.get(i).getGrupoPaises().clear();
            grupos.get(i).getTabla().clear();
            if (grupos.get(i).getEncuentros() != null) {
                grupos.get(i).getEncuentros().clear();
            }
        }
    }
    //Crea los bombos utilizando el Ranking FIFA

    public static ArrayList<ArrayList<Pais>> crearBombos(ArrayList<Pais> paises) {
        ArrayList<ArrayList<Pais>> bombos = new ArrayList<>();
        ordenarPaisesPorRanking(paises);
        int cantidadBombos = 4;
        int paisesPorBombo = paises.size() / cantidadBombos;
        int indice = 0;
        for (int i = 0; i < cantidadBombos; i++) {
            ArrayList<Pais> bombo = new ArrayList<>();
            for (int j = 0; j < paisesPorBombo; j++) {
                bombo.add(paises.get(indice));
                indice++;
            }
            bombos.add(bombo);
        }
        return bombos;
    }

    //Mezcla los países de cada bombo
    public static void mezclarBombos(ArrayList<ArrayList<Pais>> bombos) {
        for (int i = 0; i < bombos.size(); i++) {
            ArrayList<Pais> bombo = bombos.get(i);
            for (int j = 0; j < bombo.size(); j++) {
                int aleatorio = (int) (Math.random() * bombo.size());
                Pais aux = bombo.get(j);
                bombo.set(j, bombo.get(aleatorio));
                bombo.set(aleatorio, aux);
            }
        }
    }

    //Verifica si un país puede ingresar al grupo
    public static boolean validarConfederacion(Grupo grupo, Pais pais) {
        int mismaConfederacion = 0;
        for (int i = 0; i < grupo.getGrupoPaises().size(); i++) {
            Pais p = grupo.getGrupoPaises().get(i);
            if (p.getConfederacion() == pais.getConfederacion()) {
                mismaConfederacion++;
            }
        }
        if (pais.getConfederacion().name().equalsIgnoreCase("UEFA")) {
            return mismaConfederacion < 2;
        }
        return mismaConfederacion == 0;
    }

    //Coloca automáticamente al anfitrión en el Grupo A
    public static void colocarAnfitrion(ArrayList<Grupo> grupos, ArrayList<Pais> paises) {
        Pais anfitrion = buscarPaisAnfitrion(paises);
        if (anfitrion != null) {
            Grupo grupoA = buscarGrupo(grupos, 'A');
            if (grupoA != null) {
                grupoA.getGrupoPaises().add(anfitrion);
                anfitrion.setGrupo(grupoA);
            }
        }
    }

    //Crea la tabla de posiciones de cada grupo
    public static void crearTablaGrupo(ArrayList<Grupo> grupos) {
        for (int i = 0; i < grupos.size(); i++) {
            Grupo grupo = grupos.get(i);
            grupo.getTabla().clear();
            for (int j = 0; j < grupo.getGrupoPaises().size(); j++) {
                TablaPosicion tabla = new TablaPosicion(grupo.getGrupoPaises().get(j));
                grupo.getTabla().add(tabla);
            }
        }
    }

    //Genera los encuentros de todos los grupos
    public static void generarEncuentrosGrupos(ArrayList<Grupo> grupos) {
        for (int i = 0; i < grupos.size(); i++) {
            Grupo grupo = grupos.get(i);
            grupo.getEncuentros().clear();
            ArrayList<Pais> paises = new ArrayList<>(grupo.getGrupoPaises());
            for (int j = 0; j < paises.size() - 1; j++) {
                for (int k = j + 1; k < paises.size(); k++) {
                    Pais local = paises.get(j);
                    Pais visitante = paises.get(k);
                    Partido partido = new Partido("", "", 0, 0, 0, 0, new ArrayList<>(), EstadoPartido.PENDIENTE);
                    Alineacion alineacionLocal = new Alineacion(partido, local);
                    Alineacion alineacionVisitante = new Alineacion(partido, visitante);
                    partido.getAlineaciones().add(alineacionLocal);
                    partido.getAlineaciones().add(alineacionVisitante);
                    grupo.getEncuentros().add(partido);
                }
            }
        }
    }

    //Muestra todos los encuentros de un grupo
    public static ArrayList<Partido> mostrarEncuentrosGrupo(Grupo grupo) {
        return new ArrayList<>(grupo.getEncuentros());
    }

    //Busca un encuentro por posición
    public static Partido buscarEncuentro(Grupo grupo, int posicion) {
        if (posicion >= 0 && posicion < grupo.getEncuentros().size()) {
            return grupo.getEncuentros().get(posicion);
        }
        return null;
    }

    //Muestra la tabla de posiciones de un grupo
    public static ArrayList<TablaPosicion> mostrarTablaGrupo(Grupo grupo) {
        return new ArrayList<>(grupo.getTabla());
    }

    //Obtiene el primer lugar del grupo
    public static Pais obtenerPrimerLugarGrupo(Grupo grupo) {
        TablaPosicion tabla = TablaPosicionDao.obtenerPrimerLugar(grupo);
        if (tabla != null) {
            return tabla.getPais();
        }
        return null;
    }

    //Obtiene el segundo lugar del grupo
    public static Pais obtenerSegundoLugarGrupo(Grupo grupo) {
        TablaPosicion tabla = TablaPosicionDao.obtenerSegundoLugar(grupo);
        if (tabla != null) {
            return tabla.getPais();
        }
        return null;
    }

    //Obtiene el tercer lugar del grupo
    public static Pais obtenerTercerLugarGrupo(Grupo grupo) {
        TablaPosicion tabla = TablaPosicionDao.obtenerTercerLugar(grupo);
        if (tabla != null) {
            return tabla.getPais();
        }
        return null;
    }

    //Obtiene todos los primeros lugares
    public static ArrayList<Pais> obtenerPrimerosLugares(ArrayList<Grupo> grupos) {
        ArrayList<Pais> primeros = new ArrayList<>();
        for (int i = 0; i < grupos.size(); i++) {
            Pais pais = obtenerPrimerLugarGrupo(grupos.get(i));
            if (pais != null) {
                primeros.add(pais);
            }
        }
        return primeros;
    }

    //Obtiene todos los segundos lugares
    public static ArrayList<Pais> obtenerSegundosLugares(ArrayList<Grupo> grupos) {
        ArrayList<Pais> segundos = new ArrayList<>();
        for (int i = 0; i < grupos.size(); i++) {
            Pais pais = obtenerSegundoLugarGrupo(grupos.get(i));
            if (pais != null) {
                segundos.add(pais);
            }
        }
        return segundos;
    }

    //Obtiene todos los terceros lugares
    public static ArrayList<Pais> obtenerTercerosLugares(ArrayList<Grupo> grupos) {
        ArrayList<Pais> terceros = new ArrayList<>();
        for (int i = 0; i < grupos.size(); i++) {
            Pais pais = obtenerTercerLugarGrupo(grupos.get(i));
            if (pais != null) {
                terceros.add(pais);
            }
        }
        return terceros;
    }
    //Compara los terceros lugares

    public static void compararMejoresTerceros(ArrayList<TablaPosicion> terceros) {
        for (int i = 0; i < terceros.size() - 1; i++) {
            for (int j = i + 1; j < terceros.size(); j++) {
                TablaPosicion t1 = terceros.get(i);
                TablaPosicion t2 = terceros.get(j);
                boolean cambiar = false;
                if (t2.getPuntos() > t1.getPuntos()) {
                    cambiar = true;
                } else if (t2.getPuntos() == t1.getPuntos()) {
                    if (t2.getDiferenciaGoles() > t1.getDiferenciaGoles()) {
                        cambiar = true;
                    } else if (t2.getDiferenciaGoles() == t1.getDiferenciaGoles()) {
                        if (t2.getGolesFavor() > t1.getGolesFavor()) {
                            cambiar = true;
                        }
                    }
                }
                if (cambiar) {
                    TablaPosicion aux = terceros.get(i);
                    terceros.set(i, terceros.get(j));
                    terceros.set(j, aux);
                }
            }
        }
    }

    //Obtiene los 8 mejores terceros
    public static ArrayList<Pais> obtenerMejoresTerceros(ArrayList<Grupo> grupos) {
        ArrayList<TablaPosicion> terceros = new ArrayList<>();
        for (int i = 0; i < grupos.size(); i++) {
            TablaPosicion tercero = TablaPosicionDao.obtenerTercerLugar(grupos.get(i));
            if (tercero != null) {
                terceros.add(tercero);
            }
        }

        compararMejoresTerceros(terceros);
        ArrayList<Pais> mejores = new ArrayList<>();
        for (int i = 0; i < 8 && i < terceros.size(); i++) {
            mejores.add(terceros.get(i).getPais());
        }
        return mejores;
    }

    //Obtiene los 32 clasificados
    public static ArrayList<Pais> obtenerClasificadosMundial(ArrayList<Grupo> grupos) {
        ArrayList<Pais> clasificados = new ArrayList<>();
        clasificados.addAll(obtenerPrimerosLugares(grupos));
        clasificados.addAll(obtenerSegundosLugares(grupos));
        clasificados.addAll(obtenerMejoresTerceros(grupos));
        return clasificados;
    }
    
    //Realiza el sorteo de los grupos
    public static void sortearGrupos(ArrayList<Grupo> grupos, ArrayList<Pais> paises) {
        limpiarGrupos(grupos);
        ArrayList<ArrayList<Pais>> bombos = crearBombos(paises);
        mezclarBombos(bombos);
        colocarAnfitrion(grupos, bombos);

        for (int i = 0; i < bombos.size(); i++) {
            ArrayList<Pais> bombo = bombos.get(i);
            for (int j = 0; j < grupos.size(); j++) {
                Grupo grupo = grupos.get(j);
                if (grupo.getGrupoPaises().size() > i) {
                    continue;
                }
                for (int k = 0; k < bombo.size(); k++) {
                    Pais pais = bombo.get(k);
                    if (validarConfederacion(grupo, pais)) {
                        grupo.getGrupoPaises().add(pais);
                        pais.setGrupo(grupo);
                        bombo.remove(k);
                        break;
                    }
                }
            }
        }
        crearTablaGrupo(grupos);
        generarEncuentrosGrupos(grupos);
    } 
}
