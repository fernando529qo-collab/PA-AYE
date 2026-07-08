package mundial.logic;

import java.util.List;

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

    public static List<Partido> buscarPorFase(RondaEliminacion rondaEliminacion) {
        return partidoDao.buscarPorFase(rondaEliminacion);
    }

    public static Partido simularPartido(List<Pais> paises, RondaEliminacion fase) throws Exception {
        return partidoDao.simular(paises, fase);
    }

    public static List<Partido> simularVariosPartidos(List<Pais> paises, RondaEliminacion fase) throws Exception  {
        return partidoDao.simularVarios(paises, fase);
    }

    public static List<Partido> mostrarPartidos() {
        return partidoDao.mostrar();
    }

    public static void setLstPartidos(List<Partido> lstPartidos) {
        partidoDao.setLstPartidos(lstPartidos);
    }
}
