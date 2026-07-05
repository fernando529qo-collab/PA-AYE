package mundial.logic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import mundial.bean.Jugador;
import mundial.bean.Pais;
import mundial.dao.JugadorDao;

public class JugadorLogic {

    private static JugadorDao jugadorDao = new JugadorDao();
    
    public static boolean validarId(String id) {
        if (jugadorDao.buscarPos(id) != -1) {
            return true;
        } else {
            return false;
        }//Aqui busca y verifica, true si encontro y false si no
    }

    public static boolean registrarJugador(Jugador jugador){
        try {
            if (validarId(jugador.getId())) {
                return false;
            }
            jugadorDao.registrar(jugador);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean modificarJugador(Jugador jugador) {
        try {
            if (!validarId(jugador.getId())) {
                return false;
            }
            jugadorDao.actualizar(jugador);
            return true;
        } catch (Exception ex) {
            return false;               //Retorna false por las excepciones
        }
    }

    public static boolean eliminarJugador(Jugador jugador) {
        try {
            if (!validarId(jugador.getId())) {
                return false;
            }
            jugadorDao.eliminar(jugador);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static Jugador buscarJugador(Jugador jugador) {
        if (!validarId(jugador.getId())) {
            return null;
        }
        int posicion = jugadorDao.buscarPos(jugador.getId());
        if (posicion == -1) {
            return null;
        }
        return jugadorDao.mostrar().get(posicion);
    }
    
    public static List<Jugador> listar() {
        return jugadorDao.mostrar();
    }
    
    public static DefaultListModel getDefaultListModel() {
        DefaultListModel model = new DefaultListModel();
        List<Jugador> lista = new ArrayList<>(jugadorDao.mostrar());
        for (int i = 0; i < lista.size(); i++) {
            model.addElement(lista.get(i).toString());
        }
        return model;
    }
    public static DefaultComboBoxModel getComboBoxModelPais() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        List<Pais> lista = PaisLogic.listar();
        for (int i = 0; i < lista.size(); i++) {
            model.addElement(lista.get(i));
        }
        return model;
    }
    
    public static DefaultComboBoxModel getComboBoxModelPosicion() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        List<String> lista = new ArrayList<>();
        lista.add("Delantero");
        lista.add("Mediocampista");
        lista.add("Defensa");
        lista.add("Portero");
        for (int i = 0; i < lista.size(); i++) {
            model.addElement(lista.get(i));
        }
        return model;
    }
}
