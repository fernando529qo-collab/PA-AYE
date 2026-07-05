package mundial.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import mundial.bean.Partido;
import mundial.bean.RondaEliminacion;
import mundial.bean.Bracket;

public class PartidoDao {
    // CRUD estándar, más buscarPorGrupo(), buscarPorFase(), guardarResultado()
    private List<Partido> lstpartidos;
    private Gson gson = new Gson();
    private File file = new File("PARTIDOS.json");

    public PartidoDao() {
        lstpartidos = leer();
    }

    public boolean registrar(Partido partido) throws Exception {
        if (buscarPos(partido.getFecha()) == -1) {
            lstpartidos.add(partido);
            guardar(lstpartidos);
            return true;
        }
        return false;
    }

    public List<Partido> mostrar() {
        return lstpartidos;
    }

    public int buscarPos(String fecha) {
        for (int i = 0; i < lstpartidos.size(); i++) {
            if (lstpartidos.get(i).getFecha().equalsIgnoreCase(fecha)) {
                return i;
            }
        }
        return -1;
    }

    public Partido buscarObj(String fecha) {
        for (int i = 0; i < lstpartidos.size(); i++) {
            if (lstpartidos.get(i).getFecha().equalsIgnoreCase(fecha)) {
                return lstpartidos.get(i);
            }
        }
        return null;
    }

    public boolean actualizar(Partido partido) throws Exception {
        if (buscarPos(partido.getFecha()) != -1) {
            lstpartidos.set(buscarPos(partido.getFecha()), partido);
            guardar(lstpartidos);
            return true;
        }
        return false;
    }

    public boolean eliminar(Partido partido) throws Exception {
        int pos = buscarPos(partido.getFecha());
        if (pos != -1) {
            lstpartidos.remove(pos);
            guardar(lstpartidos);
            return true;
        }
        return false;
    }

    public List<Partido> buscarPorGrupo(char codigo) {
        int pos = 0;
        List<Partido> lstPartidosEncotrados = new ArrayList<>();
        for (Partido p : lstpartidos) {
            if (codigo != 0 && codigo == p.getPaisLocal().getGrupo().getCodigo()) {
                lstPartidosEncotrados.add(lstpartidos.get(pos));
            }
            pos++;
        }
        return lstPartidosEncotrados;
    }

    public List<Partido> buscarPorFase(RondaEliminacion rondaEliminacion) {
        int pos = 0;
        List<Partido> lstPartidosEscontrados = new ArrayList<>();
        Bracket fase = new Bracket();
        for (Partido p : lstPartidosEscontrados) {
            if (fase.getBracketPartidos().get(pos) == p) {
                if (fase.getRonda().compareTo(rondaEliminacion) == 1) {
                    lstPartidosEscontrados.add(p);
                }
            }
            pos++;
        }
        return lstPartidosEscontrados;
    }

    public List<Partido> leer() {
        try {
            List<Partido> partidos;
            if (!file.exists()) {
                return new ArrayList<>();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                partidos = gson.fromJson(br.readLine(), new TypeToken<ArrayList<Partido>>() {
                }.getType());
            }
            if (partidos == null) {
                partidos = new ArrayList<>();
            }
            return partidos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    public void guardar(List<Partido> lista) throws Exception {
        try (FileWriter fw = new FileWriter(file)) {
            gson.toJson(lista, fw);
        } catch (Exception e) {
            throw e;
        }
    }
}
