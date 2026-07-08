package mundial.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import mundial.bean.Estadistica;


public class EstadisticaDao {
    private List<Estadistica> lstEstadisticas;
    private Gson gson = new Gson();
    private File file = new File("ESTADISTICAS.json");

    public EstadisticaDao() {
        lstEstadisticas = leer();
    }

    
    public boolean registrar(List<Estadistica> nuevasEstadisticas) throws Exception {
        if (nuevasEstadisticas == null || nuevasEstadisticas.isEmpty()) {
            return false;
        }
        lstEstadisticas.addAll(nuevasEstadisticas);
        guardar(lstEstadisticas);
        return true;
    }

    // Devuelve todas las estadisticas guardadas en memoria
    public List<Estadistica> mostrar() {
        return lstEstadisticas;
    }

    public void setLstEstadisticas(List<Estadistica> lstEstadisticas) {
        this.lstEstadisticas = lstEstadisticas;
    }

   
    public List<Estadistica> leer() {
        try {
            List<Estadistica> estadisticas;
            if (!file.exists()) {
                return new ArrayList<>();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                estadisticas = gson.fromJson(br.readLine(), new TypeToken<ArrayList<Estadistica>>() {
                }.getType());
            }
            if (estadisticas == null) {
                estadisticas = new ArrayList<>();
            }
            return estadisticas;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // Escribe la lista completa de estadisticas en el archivo JSON
    public void guardar(List<Estadistica> lista) throws Exception {
        try (FileWriter fw = new FileWriter(file)) {
            gson.toJson(lista, fw);
        } catch (Exception e) {
            throw e;
        }
    }
}