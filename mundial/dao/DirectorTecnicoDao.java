/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundial.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import mundial.bean.DirectorFederacion;

/**
 *
 * @author ferna
 */
public class DirectorTecnicoDao {
    
    private List<DirectorFederacion> lstDirectoresTecnicos;
    
    private Gson gson = new Gson();
    private File file = new File("DIRECTORES_TECNICOS.json");

    public DirectorTecnicoDao() {
        this.lstDirectoresTecnicos = leer();
    }
    
    public int buscarPos(String id) {      //Retorna la posicion (En caso de que no la encuentre retorna -1)
        for (int i = 0; i < this.lstDirectoresTecnicos.size(); i++) {
            if (this.lstDirectoresTecnicos.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
    
    public DirectorFederacion buscarObj(String id) {   //Retorna el Director Tecnico (En caso de que no la encuentre retorna null)
        for (int i = 0; i < this.lstDirectoresTecnicos.size(); i++) {
            if (this.lstDirectoresTecnicos.get(i).getId().equalsIgnoreCase(id)) {
                return this.lstDirectoresTecnicos.get(i);
            }
        }
        return null;
    }

    public List<DirectorFederacion> getLstDirectoresTecnicos() {
        return lstDirectoresTecnicos;
    }

    public void setLstDirectoresTecnicos(List<DirectorFederacion> lstDirectoresTecnicos) {
        this.lstDirectoresTecnicos = lstDirectoresTecnicos;
    }
    
    public List<DirectorFederacion> leer() {
        try {
            List<DirectorFederacion> directoresTecnicos;
            if (!file.exists()) {
                return new ArrayList<>();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                directoresTecnicos = gson.fromJson(br.readLine(), new TypeToken<ArrayList<DirectorFederacion>>() {
                }.getType());
            }
            if (directoresTecnicos == null) {
                directoresTecnicos = new ArrayList<>();
            }
            return directoresTecnicos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void guardar(List<DirectorFederacion> lista) throws Exception {
        try (FileWriter fw = new FileWriter(file)) {
            gson.toJson(lista, fw);
        } catch (Exception e) {
            throw e;
        }
    }
}
