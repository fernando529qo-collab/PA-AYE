package mundial.logic;

import java.util.Map;

import mundial.bean.Alineacion;
import mundial.bean.Jugador;
import mundial.bean.Posicion;
import mundial.dao.AlineacionDao;

public class AlineacionLogic {
    private static AlineacionDao alineacionDao = new AlineacionDao();

    public boolean validarAlineacion(Alineacion alineacion) throws Exception {
        Map<Jugador, Posicion> jugadoresTitulares = alineacion.getJugadoresTitulares();
        if (jugadoresTitulares.size() != 11) { // Valida 11 jugadores titulares
            return false;
        }
        int cantidadPorteros = 0;
        int cantidadDefensas = 0;
        int cantidadMediocampistas = 0;
        int cantidadDelanteros = 0;
        for (Map.Entry<Jugador, Posicion> j : jugadoresTitulares.entrySet()) { // Acepta la compatibilidad de las diferentes posiciones
            Jugador jugador = j.getKey();
            Posicion posicionSeleccionada = j.getValue();
            if (!posicionValida(jugador, posicionSeleccionada)) {
                return false;
            }
            switch (posicionSeleccionada) { // Conteo de posiciones para asegurar la formación
                case PORTERO: cantidadPorteros++; break;
                case DEFENSA: cantidadDefensas++; break;
                case MEDIOCAMPISTA: cantidadMediocampistas++; break;
                case DELANTERO: cantidadDelanteros++; break;
                default:
                    break;
            }
        }
        if (cantidadPorteros != 1 || cantidadDefensas != 4 || cantidadMediocampistas != 3 || cantidadDelanteros != 3) { // Valida formación (1-4-3-3)
            return false;
        }
        return true;
    }

    public boolean posicionValida(Jugador jugador, Posicion posicionSeleccionada) {
        Posicion posicionPrincipal = jugador.getPosicion();
        if (posicionPrincipal == posicionSeleccionada) { // Posicion principal del jugador
            return true;
        }
        switch (posicionPrincipal) { // Regla del negocio para posiciones extras
            case PORTERO:
                return false;
            case DEFENSA:
                return posicionSeleccionada == Posicion.MEDIOCAMPISTA;
            case MEDIOCAMPISTA:
                return posicionSeleccionada == Posicion.DEFENSA || posicionSeleccionada == Posicion.DELANTERO;
            case DELANTERO:
                return posicionSeleccionada == Posicion.MEDIOCAMPISTA;
            default:
                return false;
        }
    }

    public static boolean crearAlineacion(Alineacion alineacion) throws Exception {
        return alineacionDao.crear(alineacion);
    }

    public static Alineacion obtenerAlineacion(String idPartido, String idPais) {
        return alineacionDao.obtenerAlineacion(idPartido, idPais);
    }

    public static boolean actualizarAlineacion(Alineacion alineacion) throws Exception {
        return alineacionDao.actualizar(alineacion);
    }

    public static boolean eliminarAlineacion(Alineacion alineacion) throws Exception {
        return alineacionDao.eliminar(alineacion);
    }
}
