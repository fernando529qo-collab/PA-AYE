package mundial.dao;

import java.util.ArrayList;
import java.util.List;

import mundial.bean.Bracket;
import mundial.bean.Partido;
import mundial.bean.RondaEliminacion;

public class PartidoDao {
    // CRUD estándar, más buscarPorGrupo(), buscarPorFase(), guardarResultado()
    private List<Partido> lstpartidos;

    public PartidoDao() {
        lstpartidos = new ArrayList<>();
    }

    public boolean crear(Partido partido) {
        lstpartidos.add(partido);
        return true;
    }

    public List<Partido> leer() {
        return lstpartidos;
    }

    public Partido actualizar(Partido partido) {
        int pos = 0;
        for (Partido p : lstpartidos) {
            if (p.getFecha().equalsIgnoreCase(partido.getFecha())) {
                p.setEstado(partido.getEstado());
                p.setGolesLocal(partido.getGolesLocal());
                p.setGolesVisitante(partido.getGolesVisitante());
                p.setPartidoEstadisticas(partido.getPartidoEstadisticas());
                p.setProbabilidadVictoriaLocal(partido.getProbabilidadVictoriaLocal());
                p.setProbabilidadVictoriaVisitante(partido.getProbabilidadVictoriaVisitante());
                lstpartidos.set(pos, partido);
            }
            pos++;
        }
        return lstpartidos.get(pos);
    }

    public boolean eliminar() {
        if (!lstpartidos.isEmpty()) {
            lstpartidos.removeAll(lstpartidos);
            return true;
        } else {
            return false;
        }
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
}
