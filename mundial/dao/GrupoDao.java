package dao;

import bean.Grupo;
import bean.TablaPosicion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class GrupoDao {

    private ArrayList<Grupo> lstGrupos;

    private PaisDao paisDao = new PaisDao();

    private Gson gson = new Gson();
    private File file = new File("GRUPOS.json");

    public GrupoDao() {
        lstGrupos = leer();
    }

    //Busca la posición de un grupo
    public int buscarPos(char codigo) {
        for (int i = 0; i < lstGrupos.size(); i++) {
            if (lstGrupos.get(i).getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    //Busca un grupo por su código
    public Grupo buscarObj(char codigo) {
        int pos = buscarPos(codigo);
        if (pos != -1) {
            return lstGrupos.get(pos);
        }
        return null;
    }

    //Registra un grupo
    public boolean registrar(Grupo grupo) throws Exception {
        if (buscarPos(grupo.getCodigo()) == -1) {
            lstGrupos.add(grupo);
            guardar(lstGrupos);
            return true;
        }
        return false;
    }

    //Actualiza un grupo
    public boolean actualizar(Grupo grupo) throws Exception {
        int pos = buscarPos(grupo.getCodigo());
        if (pos != -1) {
            lstGrupos.set(pos, grupo);
            guardar(lstGrupos);
            return true;
        }
        return false;
    }

    //Elimina un grupo
    public boolean eliminar(char codigo) throws Exception {
        int pos = buscarPos(codigo);
        if (pos != -1) {
            lstGrupos.remove(pos);
            guardar(lstGrupos);
            return true;
        }
        return false;
    }

    //Muestra todos los grupos
    public ArrayList<Grupo> mostrar() {
        return lstGrupos;
    }
    
    //Ordena los países registrados según el Ranking FIFA
    public void ordenarPaisesPorRanking() {
        ArrayList<Pais> paises = new ArrayList<>(paisDao.mostrar());
        for (int i = 0; i < paises.size() - 1; i++) {
            for (int j = i + 1; j < paises.size(); j++) {
                if (paises.get(j).getRankingFifa() < paises.get(i).getRankingFifa()) {
                    Pais aux = paises.get(i);
                    paises.set(i, paises.get(j));
                    paises.set(j, aux);
                }
            }
        }
        paisDao.setLstPaises(paises);
    }

    //Genera los cuatro bombos según el Ranking FIFA
    private ArrayList<ArrayList<Pais>> generarBombos() {
        ordenarPaisesPorRanking();
        ArrayList<Pais> paises = new ArrayList<>(paisDao.mostrar());
        ArrayList<ArrayList<Pais>> bombos = new ArrayList<>();

        int indice = 0;

        for (int i = 0; i < 4; i++) {
            ArrayList<Pais> bombo = new ArrayList<>();
            for (int j = 0; j < 12; j++) {
                bombo.add(paises.get(indice));
                indice++;
            }
            bombos.add(bombo);
        }

        return bombos;
    }

    //Limpia la información de todos los grupos
    public void limpiarGrupos() throws Exception {
        for (int i = 0; i < lstGrupos.size(); i++) {
            lstGrupos.get(i).getGrupoPaises().clear();
            lstGrupos.get(i).getTabla().clear();
            lstGrupos.get(i).getEncuentros().clear();
        }
        guardar(lstGrupos);
    }

    //Realiza el sorteo aleatorio de los grupos
    public void sortearGrupos() throws Exception {
        limpiarGrupos();
        ArrayList<ArrayList<Pais>> bombos = generarBombos();
        for (int i = 0; i < bombos.size(); i++) {
            ArrayList<Pais> bombo = bombos.get(i);
            for (int j = 0; j < lstGrupos.size(); j++) {
                int aleatorio = (int) (Math.random() * bombo.size());
                Pais pais = bombo.remove(aleatorio);
                lstGrupos.get(j).getGrupoPaises().add(pais);
                pais.setGrupo(lstGrupos.get(j));
            }
        }
        guardar(lstGrupos);
    }
        
    //Genera los encuentros de todos los grupos
    public void generarEncuentros() throws Exception {
        for (int i = 0; i < lstGrupos.size(); i++) {

            Grupo grupo = lstGrupos.get(i);
            grupo.getEncuentros().clear();

            ArrayList<Pais> paises = grupo.getGrupoPaises();

            int numeroPartido = 1;

            for (int j = 0; j < paises.size() - 1; j++) {
                for (int k = j + 1; k < paises.size(); k++) {

                    Partido partido = new Partido();
                    partido.setId("P" + grupo.getCodigo() + numeroPartido);

                    Alineacion local = new Alineacion(partido, paises.get(j));
                    Alineacion visitante = new Alineacion(partido, paises.get(k));

                    ArrayList<Alineacion> alineaciones = new ArrayList<>();
                    alineaciones.add(local);
                    alineaciones.add(visitante);

                    partido.setAlineaciones(alineaciones);

                    grupo.getEncuentros().add(partido);

                    numeroPartido++;
                }
            }
        }

        guardar(lstGrupos);
    }

    //Muestra los encuentros de un grupo
    public ArrayList<Partido> mostrarEncuentros(char codigoGrupo) {
        Grupo grupo = buscarObj(codigoGrupo);
        if (grupo != null) {
            return new ArrayList<>((ArrayList<Partido>) grupo.getEncuentros());
        }
        return new ArrayList<>();
    }

    //Muestra todos los encuentros del torneo
    public ArrayList<Partido> mostrarEncuentrosTotales() {
        ArrayList<Partido> partidos = new ArrayList<>();

        for (int i = 0; i < lstGrupos.size(); i++) {
            partidos.addAll(lstGrupos.get(i).getEncuentros());
        }

        return partidos;
    }

    //Crea las tablas de posiciones de todos los grupos
    public void crearTablasPosiciones() throws Exception {
        for (int i = 0; i < lstGrupos.size(); i++) {
            TablaPosicionDao.crearTablaPosiciones(lstGrupos.get(i));
        }
        guardar(lstGrupos);
    }    
        
    //Lee los grupos almacenados
    public ArrayList<Grupo> leer() {
        try {
            ArrayList<Grupo> grupos;

            if (!file.exists()) {
                return new ArrayList<>();
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                grupos = gson.fromJson(br, new TypeToken<ArrayList<Grupo>>() {
                }.getType());
            }

            if (grupos == null) {
                grupos = new ArrayList<>();
            }

            return grupos;

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    //Guarda los grupos
    public void guardar(ArrayList<Grupo> lista) throws Exception {
        try (FileWriter fw = new FileWriter(file)) {
            gson.toJson(lista, fw);
        } catch (Exception e) {
            throw e;
        }
    }

    //Obtiene la cantidad de grupos registrados
    public int cantidadGrupos() {
        return lstGrupos.size();
    }

    //Verifica si un grupo existe
    public boolean existeGrupo(char codigo) {
        return buscarPos(codigo) != -1;
    }

    //Elimina todos los grupos
    public void limpiarRegistroGrupos() throws Exception {
        lstGrupos.clear();
        guardar(lstGrupos);
    }
}