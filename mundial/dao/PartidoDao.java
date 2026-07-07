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
import mundial.bean.EstadoPartido;
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

    //Crea o simula un solo partido
    public Partido simular(List<Pais> paises, RondaEliminacion fase) throws Exception {

        int punt1;
        int punt2;

        int ranking;
        int suerte;
        int estiloJuego;

        //Valida que la lista que se le pasa tenga dos paises
        if (paises == null || paises.size() < 2) {
            return null;
        }

        //Puntaje 1 - Local
        ranking = 211 - paises.get(0).getRankingFifa();
        suerte = (int) (Math.random() * 21);

        if (paises.get(0).getEstiloJuego().name().equalsIgnoreCase("OFENSIVO")) {
            estiloJuego = 50;
        } else if (paises.get(0).getEstiloJuego().name().equalsIgnoreCase("DEFENSIVO")) {
            estiloJuego = 40;
        } else {
            estiloJuego = 30;
        }

        punt1 = ranking + suerte + estiloJuego;

        //Puntaje 2 - Visitante
        ranking = 211 - paises.get(1).getRankingFifa();
        suerte = (int) (Math.random() * 21);

        if (paises.get(1).getEstiloJuego().name().equalsIgnoreCase("OFENSIVO")) {
            estiloJuego = 50;
        } else if (paises.get(1).getEstiloJuego().name().equalsIgnoreCase("DEFENSIVO")) {
            estiloJuego = 40;
        } else {
            estiloJuego = 30;
        }

        punt2 = ranking + suerte + estiloJuego;

        //Probabilidad de victoria
        int suma = punt1 + punt2;

        int probabilidadLocal = Math.round((punt1 * 100) / suma);
        int probabilidadVisitante = 100 - probabilidadLocal;

        //Goles
        int golesLocal;
        int golesVisitante;

        int diferencia = Math.abs(punt1 - punt2);

        if (diferencia <= 10) { // Empate

            int goles = (int) (Math.random() * 4);

            golesLocal = goles;
            golesVisitante = goles;

        } else if (punt1 > punt2) { // Gana local

            if (diferencia <= 20) {
                golesLocal = 1 + (int) (Math.random() * 2); //Min 1 - Max 2
                golesVisitante = (int) (Math.random() * golesLocal);//Min  - Max 2
            } else if (diferencia <= 40) {
                golesLocal = 2 + (int) (Math.random() * 2);
                golesVisitante = (int) (Math.random() * 2);
            } else {
                golesLocal = 4 + (int) (Math.random() * 3);
                golesVisitante = (int) (Math.random() * 2);
            }

        } else { // Gana visitante

            if (diferencia <= 20) {
                golesVisitante = 1 + (int) (Math.random() * 2);
                golesLocal = (int) (Math.random() * golesVisitante);
            } else if (diferencia <= 40) {
                golesVisitante = 2 + (int) (Math.random() * 2);
                golesLocal = (int) (Math.random() * 2);
            } else {
                golesVisitante = 4 + (int) (Math.random() * 3);
                golesLocal = (int) (Math.random() * 2);
            }

        }

        //Objeto Partido
        EstadoPartido es = EstadoPartido.FINALIZADO;
        //Se ve lo que es fecha
        if (lstPartidos.size() <= 72 && lstPartidos.size() % 4 == 0) { //Fase de grupos y cambia cada 4 partidos
            dia++;
            if (dia == 30) {
                dia = 1;
                mes++;
            }
        }
        switch (fase) {
            case DIECISEISAVOS:
                dia += 3;
                if (dia == 30) {
                    dia = 1;
                    mes++;
                }
                break;
            case OCTAVOS:
                dia += 3;
                if (dia == 30) {
                    dia = 1;
                    mes++;
                }
                break;
            case CUARTOS:
                dia += 3;
                if (dia == 30) {
                    dia = 1;
                    mes++;
                }
                break;
            case SEMIFINAL:
                dia += 3;
                if (dia == 30) {
                    dia = 1;
                    mes++;
                }
                break;
            case TERCERPUESTO:
                dia += 3;
                if (dia == 30) {
                    dia = 1;
                    mes++;
                }
                break;
            case FINAL:
                dia += 3;
                if (dia == 30) {
                    dia = 1;
                    mes++;
                }
                break;
        }
        String fecha = dia + "/" + mes + "/2026";
        Partido partido = new Partido(fecha, golesLocal, golesVisitante, probabilidadLocal,
                probabilidadVisitante, null, es);

        lstPartidos.add(partido);
        guardar(lstPartidos);
        return partido;
    }
    //Se colocan fuera del metodo porque si no en cada ocasion que se llame al metodo dia iniciaria en 10 y mes en 6
    private int dia = 10;//Inicia en 10 porque en el primer partido va a pasar los filtros y el mundial inicio el 11
    private int mes = 6;

    //Crea o simula varios partidos
    public List<Partido> simularVarios(List<Pais> paises, RondaEliminacion ronda) throws Exception {

        List<Partido> partidos = new ArrayList<>();

        if (paises == null || paises.size() < 2) {
            return null;
        }

        for (int i = 0; i < paises.size(); i += 2) {
            List<Pais> partido = new ArrayList<>();
            partido.add(paises.get(i));      // Local
            partido.add(paises.get(i + 1));  // Visitante
            partidos.add(simular(partido, ronda));  //Partido simulado
        }
        return partidos;
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
