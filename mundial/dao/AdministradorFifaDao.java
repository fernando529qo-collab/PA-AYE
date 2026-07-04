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
import mundial.bean.AdministradorFifa;

/**
 *
 * @author ferna
 */
public class AdministradorFifaDao {
    
    private List<AdministradorFifa> lstAdministradores;
    
    private Gson gson = new Gson();
    private File file = new File("ADMINISTRADOR.json");

    public AdministradorFifaDao() {
        this.lstAdministradores = leer();
    }
    
    public int buscarPos(String id) {      //Retorna la posicion (En caso de que no la encuentre retorna -1)
        for (int i = 0; i < this.lstAdministradores.size(); i++) {
            if (this.lstAdministradores.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
    
    public AdministradorFifa buscarObj(String id) {   //Retorna el AdministradorFifa (En caso de que no la encuentre retorna null)
        for (int i = 0; i < this.lstAdministradores.size(); i++) {
            if (this.lstAdministradores.get(i).getId().equalsIgnoreCase(id)) {
                return this.lstAdministradores.get(i);
            }
        }
        return null;
    }

    public List<AdministradorFifa> getLstAdministradores() {
        return lstAdministradores;
    }

    public void setLstAdministradores(List<AdministradorFifa> lstAdministradores) {
        this.lstAdministradores = lstAdministradores;
    }
    
    public List<AdministradorFifa> leer() {
        try {
            List<AdministradorFifa> administradores;
            if (!file.exists()) {
                return new ArrayList<>();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                administradores = gson.fromJson(br.readLine(), new TypeToken<ArrayList<AdministradorFifa>>() {
                }.getType());
            }
            if (administradores == null) {
                administradores = new ArrayList<>();
            }
            return administradores;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void guardar(List<AdministradorFifa> lista) throws Exception {
        try (FileWriter fw = new FileWriter(file)) {
            gson.toJson(lista, fw);
        } catch (Exception e) {
            throw e;
        }
    }
    
}
