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
import mundial.bean.Pais;

public class PartidoDao {
    // CRUD estándar, más buscarPorGrupo(), buscarPorFase(), guardarResultado()
    private List<Partido> lstPartidos;

    private Gson gson = new Gson();
    private File file = new File("PARTIDOS.json");

    public PartidoDao() {
        lstPartidos = leer();
    }

    public boolean registrar(Partido partido) throws Exception {
        if (buscarPos(partido.getFecha()) == -1) {
            lstPartidos.add(partido);
            guardar(lstPartidos);
            return true;
        }
        return false;
    }

    public int buscarPos(String fecha) {
        for (int i = 0; i < lstPartidos.size(); i++) {
            if (lstPartidos.get(i).getFecha().equalsIgnoreCase(fecha)) {
                return i;
            }
        }
        return -1;
    }

    public Partido buscarObj(String fecha) {
        for (int i = 0; i < lstPartidos.size(); i++) {
            if (lstPartidos.get(i).getFecha().equalsIgnoreCase(fecha)) {
                return lstPartidos.get(i);
            }
        }
        return null;
    }

    public boolean actualizar(Partido partido) throws Exception {
        if (buscarPos(partido.getFecha()) != -1) {
            lstPartidos.set(buscarPos(partido.getFecha()), partido);
            guardar(lstPartidos);
            return true;
        }
        return false;
    }

    public boolean eliminar(Partido partido) throws Exception {
        int pos = buscarPos(partido.getFecha());
        if (pos != -1) {
            lstPartidos.remove(pos);
            guardar(lstPartidos);
            return true;
        }
        return false;
    }

    /*public List<Partido> buscarPorGrupo(char codigo) {
        int pos = 0;
        List<Partido> lstPartidosEncotrados = new ArrayList<>();
        for (Partido p : lstPartidos) {
            if (codigo != 0 && codigo == p.getPaisLocal().getGrupo().getCodigo()) {
                lstPartidosEncotrados.add(lstPartidos.get(pos));
            }
            pos++;
        }
        return lstPartidosEncotrados;
    }*/

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

    public void simular(List<Pais> paises) {//Solo simula un partido
        int punt1 = 0;
        int punt2 = 0;
        for (int i = 0; i < 2; i++) {
            int ranking;
            int suerte;
            int estiloJuego = 0;
            //Puntaje 1
            ranking = 211 - paises.get(i).getRankingFifa();
            suerte = (int) (Math.random() * 21);
            if (paises.get(i).getEstiloJuego().name().equalsIgnoreCase("OFENSIVO")) {
                estiloJuego = 50;
            }
            if (paises.get(i).getEstiloJuego().name().equalsIgnoreCase("DEFENSIVO")) {
                estiloJuego = 40;
            }
            if (paises.get(i).getEstiloJuego().name().equalsIgnoreCase("EQUILIBRADO")) {
                estiloJuego = 30;
            }
            punt1 = ranking + suerte + estiloJuego;
            //Puntaje 2
            ranking = 211 - paises.get(i+1).getRankingFifa();
            suerte = (int) (Math.random() * 21);
            if (paises.get(i+1).getEstiloJuego().name().equalsIgnoreCase("OFENSIVO")) {
                estiloJuego = 50;
            }
            if (paises.get(i+1).getEstiloJuego().name().equalsIgnoreCase("DEFENSIVO")) {
                estiloJuego = 40;
            }
            if (paises.get(i+1).getEstiloJuego().name().equalsIgnoreCase("EQUILIBRADO")) {
                estiloJuego = 30;
            }
            punt2 = ranking + suerte + estiloJuego;
        }
        int suma = punt1 + punt2;
        //int probabilidadLocal = Math.round((punt1*100)/suma);
        //int probabilidadVisitante = Math.round((punt2*100)/suma);
        
        if (Math.abs(punt1-punt2)<=10) { //Si se queda aqui es empate
            
        } else if (punt1>punt2) {   //Si se queda aqui gana el local
            
        } else {                    //Gana el visitante
            
        }
    }

    public List<Partido> mostrar() {
        return lstPartidos;
    }

    public void setLstPartidos(List<Partido> lstPartidos) {
        this.lstPartidos = lstPartidos;
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
