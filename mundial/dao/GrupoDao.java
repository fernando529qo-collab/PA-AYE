package mundial.dao;

import java.util.ArrayList;

import mundial.bean.Grupo;

public class GrupoDao {
    private static ArrayList<Grupo> listaGrupos = new ArrayList<>();

    public static boolean guardarGrupo(Grupo grupo) {
        return listaGrupos.add(grupo);
    }

    public static Grupo buscarPorIdGrupo(char codigo) {

        for (Grupo grupo : listaGrupos) {
            if (grupo.getCodigo() == codigo) {
                return grupo;
            }
        }
        return null;
    }

    public static ArrayList<Grupo> listarTodos() {
        return listaGrupos;
    }

    public static boolean actualizarGrupo(Grupo grupoActualizado) {
        for (int i = 0; i < listaGrupos.size(); i++) {
            if (listaGrupos.get(i).getCodigo() == grupoActualizado.getCodigo()) {
                listaGrupos.set(i, grupoActualizado);
                return true;
            }
        }
        return false;

    }

    public static boolean eliminarGrupo(char codigo) {
        Grupo grupo = buscarPorIdGrupo(codigo);
        if (grupo != null) {
            listaGrupos.remove(grupo);
            return true;
        }
        return false;
    }
}
