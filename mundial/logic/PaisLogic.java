package mundial.logic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import mundial.bean.Confederacion;
import mundial.bean.Pais;
import mundial.dao.PaisDao;

public class PaisLogic {

    private static PaisDao paisDao = new PaisDao();
    
    public static boolean validarId(String id) {
        return paisDao.buscarPos(id) != -1; //Aqui busca y verifica, true si encontro y false si no
    }

    public static boolean registrarPais(Pais pais) {
        try {
            if (validarId(pais.getId())) {
                return false;
            }
            paisDao.registrar(pais);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean modificarPais(Pais pais) {
        try {
            if (!validarId(pais.getId())) {
                return false;
            }
            paisDao.actualizar(pais);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean eliminarPais(Pais pais) {
        try {
            if (!validarId(pais.getNombre())) {
                return false;
            }
            paisDao.eliminar(pais);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static Pais buscarPais(Pais pais) {
        if (!validarId(pais.getId())) {
            return null;
        }
        int posicion = paisDao.buscarPos(pais.getId());
        if (posicion == -1) {
            return null;
        }
        return paisDao.mostrar().get(posicion);
    }
    
    public static List<Pais> listar() {
        return paisDao.mostrar();
    }
    
    //Muestra todos los paises
    public static DefaultListModel getDefaultListModelTodos() {
        DefaultListModel model = new DefaultListModel();
        List<Pais> lista = new ArrayList<>(paisDao.mostrar());
        for (int i = 0; i < lista.size(); i++) {
            model.addElement(lista.get(i).toString());
        }
        return model;
    }
    
    //Muestra de acuerdo a la confederacion 
    public static DefaultListModel getDefaultListModelConfederacion(Confederacion confederacion) {
        DefaultListModel model = new DefaultListModel();
        List<Pais> lista = new ArrayList<>(paisDao.mostrarConfederacion(confederacion));
        for (int i = 0; i < lista.size(); i++) {
            model.addElement(lista.get(i).toString());
        }
        return model;
    }
    
    public static DefaultComboBoxModel getComboBoxModelConnfederacion() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        List<String> lista = new ArrayList<>();
        lista.add("UEFA");
        lista.add("CONMEBOL");
        lista.add("CONCACAF");
        lista.add("CAF");
        lista.add("AFC");
        lista.add("OFC");
        for (int i = 0; i < lista.size(); i++) {
            model.addElement(lista.get(i));
        }
        return model;
    }

    public static DefaultComboBoxModel getComboBoxModelContinente() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        List<String> lista = new ArrayList<>();
        lista.add("Asia");
        lista.add("Africa");
        lista.add("Oceania");
        lista.add("America");
        lista.add("Europa");
        for (int i = 0; i < lista.size(); i++) {
            model.addElement(lista.get(i));
        }
        return model;
    }
}
