package mundial.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import mundial.bean.Alineacion;

public class AlineacionDao {
    private List<Alineacion> lstAlineacion;
    private File file = new File("alineacion.json");
    private Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();

    public AlineacionDao() {
        lstAlineacion = leer();
    }

    public boolean crear(Alineacion alineacion) throws Exception{
        if (buscarPos(alineacion.getPartido().getId(), alineacion.getPais().getId()) == -1) {
            lstAlineacion.add(alineacion);
            guardar(lstAlineacion);
            return true;
        }
        return false;
    }

    public Alineacion obtenerAlineacion(String idPartido, String idPais) {
        for (int i = 0; i < lstAlineacion.size(); i++) {
            if (lstAlineacion.get(i).getPartido().getId().equalsIgnoreCase(idPartido) && lstAlineacion.get(i).getPais().getId().equalsIgnoreCase(idPais)) {
                return lstAlineacion.get(i);
            }
        }
        return null;
    }

    public int buscarPos(String idPartido, String idPais) {
        for (int i = 0; i < lstAlineacion.size(); i++) {
            if (lstAlineacion.get(i).getPartido().getId().equalsIgnoreCase(idPartido) && lstAlineacion.get(i).getPais().getId().equalsIgnoreCase(idPais)) {
                return i;
            }
        }
        return -1;
    }

    public boolean actualizar(Alineacion alineacion) throws Exception {
        if (buscarPos(alineacion.getPartido().getId(), alineacion.getPais().getId()) != -1) {
            lstAlineacion.set(buscarPos(alineacion.getPartido().getId(), alineacion.getPais().getId()), alineacion);
            guardar(lstAlineacion);
            return true;
        }
        return false;
    }

    public boolean eliminar(Alineacion alineacion) throws Exception {
        int pos = buscarPos(alineacion.getPartido().getId(), alineacion.getPais().getId());
        if (pos != -1) {
            lstAlineacion.remove(pos);
            guardar(lstAlineacion);
            return true;
        }
        return false;
    }

    public List<Alineacion> leer() {
        try {
            List<Alineacion> alineaciones;
            if (!file.exists()) {
                return new ArrayList<>();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                alineaciones = gson.fromJson(br, new TypeToken<ArrayList<Alineacion>>(){}.getType());
            }
            if (alineaciones == null) {
                alineaciones = new ArrayList<>();
            }
            return alineaciones;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void guardar(List<Alineacion> lista) throws Exception {
        try (FileWriter fw = new FileWriter(file)) {
            gson.toJson(lista, fw);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }
}
