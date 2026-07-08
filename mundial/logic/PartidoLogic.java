package mundial.logic;

import java.util.ArrayList;
import java.util.List;

import mundial.bean.EstadoPartido;
import mundial.bean.Pais;
import mundial.bean.Partido;
import mundial.bean.RondaEliminacion;
import mundial.dao.PartidoDao;

public class PartidoLogic {
    private static PartidoDao partidoDao = new PartidoDao();

    public static boolean registrarPartido(Partido partido) throws Exception {
        return partidoDao.registrar(partido);
    }

    public static Partido buscarPartido(String idPartido) {
        return partidoDao.buscarObj(idPartido);
    }

    public static boolean actualizarPartido(Partido partido) throws Exception {
        return partidoDao.actualizar(partido);
    }

    public static boolean eliminarPartido(Partido partido) throws Exception {
        return partidoDao.eliminar(partido);
    }

    public static List<Partido> buscarPorFaseEliminatoria(RondaEliminacion rondaEliminacion) {
        return buscarPorFase(rondaEliminacion);
    }

    public static List<Partido> buscarPartidoPorGrupo(char codigo) {
        return buscarPorGrupo(codigo);
    }

    public static Partido simularPartido(List<Pais> paises, RondaEliminacion fase) throws Exception {
        return simular(paises, fase);
    }

    public static List<Partido> simularVariosPartidos(List<Pais> paises, RondaEliminacion fase) throws Exception  {
        return simularVarios(paises, fase);
    }

    public static List<Partido> mostrarPartidos() {
        return partidoDao.mostrar();
    }

    public static void setLstPartidos(List<Partido> lstPartidos) {
        partidoDao.setLstPartidos(lstPartidos);
    }

    public static List<Partido> buscarPorGrupo(char codigo) {
        int pos = 0;
        List<Partido> lstPartidosEncotrados = new ArrayList<>();
        for (Partido p : mostrarPartidos()) {
            if (codigo != 0 && codigo == p.getAlineaciones().get(pos).getPais().getGrupo().getCodigo()) {
                lstPartidosEncotrados.add(p);
            }
            pos++;
        }
        return lstPartidosEncotrados;
    }
    
    public static List<Partido> buscarPorFase(RondaEliminacion rondaEliminacion) {
        int pos = 0;
        List<Partido> lstPartidosEscontrados = new ArrayList<>();
        Bracket fase = new Bracket();
        for (Partido p : mostrarPartidos()) {
            if (fase.getBracketPartidos().get(pos) == p) {
                if (fase.getRonda().name().equalsIgnoreCase(rondaEliminacion.name())) {
                    lstPartidosEscontrados.add(p);
                }
            }
            pos++;
        }
        return lstPartidosEscontrados;
    }

//Crea o simula un solo partido
    public static Partido simular(List<Pais> paises, RondaEliminacion fase) throws Exception {

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
        if (mostrarPartidos().size() <= 72 && mostrarPartidos().size() % 4 == 0) { //Fase de grupos y cambia cada 4 partidos
            dia++;
            if (dia == 30) {
                dia = 1;
                mes++;
            }
        }
        switch (fase) {             //Ronda eliminatoria y cambia cada 3 dias 
            case DIECISEISAVOS:
                dia += 3;

                if (dia >= 30) {
                    dia = 1;
                    mes++;
                }
                break;
            case OCTAVOS:
                dia += 3;
                if (dia >= 30) {
                    dia = 1;
                    mes++;
                }
                break;
            case CUARTOS:
                dia += 3;
                if (dia >= 30) {
                    dia = 1;
                    mes++;
                }
                break;
            case SEMIFINAL:
                dia += 3;
                if (dia >= 30) {
                    dia = 1;
                    mes++;
                }
                break;
            case TERCERPUESTO:
                dia += 3;
                if (dia >= 30) {
                    dia = 1;
                    mes++;
                }
                break;
            case FINAL:
                dia += 3;
                if (dia >= 30) {
                    dia = 1;
                    mes++;
                }
                break;
            default:
                break;
        }
        String id = mostrarPartidos().size()+1+"";
        String fecha = dia + "/" + mes + "/2026";
        Partido partido = new Partido(id, fecha, golesLocal, golesVisitante, probabilidadLocal,
                probabilidadVisitante, null, es);

        return partido;
    }
    //Se colocan fuera del metodo porque si no en cada ocasion que se llame al metodo dia iniciaria en 10 y mes en 6
    private static int dia = 10;//Inicia en 10 porque en el primer partido va a pasar los filtros y el mundial inicio el 11
    private static int mes = 6;

    //Crea o simula varios partidos
    public static List<Partido> simularVarios(List<Pais> paises, RondaEliminacion ronda) throws Exception {

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
}