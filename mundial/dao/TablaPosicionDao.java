package mundial.dao;

import java.util.ArrayList;

import mundial.bean.Pais;
import mundial.bean.TablaPosicion;

public class TablaPosicionDao {
    private static ArrayList<TablaPosicion> listaTabla = new ArrayList<>();
    
    public static boolean guardarTabla(TablaPosicion tabla) {
        return listaTabla.add(tabla);
    }

    public static TablaPosicion buscarPorIdTabla(Pais pais) {
        for (TablaPosicion tabla : listaTabla) {
            if (tabla.getPais().equals(pais)) {
                return tabla;
            }
        }
        return null;
    }

    public static ArrayList<TablaPosicion> listarTodas() {
        return listaTabla;
    }

    public static boolean actualizarTabla(TablaPosicion tablaActualizada) {
        for (int i = 0; i < listaTabla.size(); i++) {
            if (listaTabla.get(i).getPais().equals(tablaActualizada.getPais())) {
                listaTabla.set(i, tablaActualizada);
                return true;
            }
        }
        return false;
    }

    public static boolean eliminarTabla(Pais pais) {
        TablaPosicion tabla = buscarPorIdTabla(pais);
        if (tabla != null) {
            listaTabla.remove(tabla);
            return true;
        }
        return false;
    }
}
