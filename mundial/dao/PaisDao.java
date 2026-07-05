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

import mundial.bean.Confederacion;
import mundial.bean.Pais;

/**
 *
 * @author ferna
 */
public class PaisDao {   
    
    private List<Pais> lstPaises;

    private Gson gson = new Gson();
    private File file = new File("PAISES.json");
    
    public PaisDao() {
        this.lstPaises = leer();
    }
    
    public int buscarPos(String id) {      //Retorna la posicion (En caso de que no la encuentre retorna -1)
        for (int i = 0; i < this.lstPaises.size(); i++) {
            if (this.lstPaises.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
    
    public Pais buscarObj(String id) {   //Retorna el Pais (En caso de que no la encuentre retorna null)
        for (int i = 0; i < this.lstPaises.size(); i++) {
            if (this.lstPaises.get(i).getId().equalsIgnoreCase(id)) {
                return this.lstPaises.get(i);
            }
        }
        return null;
    }

    //Buscar por confederacion esta utilizando la clase Confederacion
    public int buscarConf(Confederacion confederacion) {      //Retorna la posicion (En caso de que no la encuentre retorna -1)
        for (int i = 0; i < this.lstPaises.size(); i++) {
            if (this.lstPaises.get(i).getConfederacion().name().equalsIgnoreCase(confederacion.name())) {
                return i;
            }
        }
        return -1;
    }
    
    //Buscar por confederacion esta utilizando la clase Confederacion
    public Pais buscarConfederacion(Confederacion confederacion) {  //Retorna el pais (En caso de que no la encuentre retorna -1)
        for (int i = 0; i < this.lstPaises.size(); i++) {
            if (this.lstPaises.get(i).getConfederacion().name().equalsIgnoreCase(confederacion.name())) {
                return this.lstPaises.get(i);
            }
        }
        return null;
    }

    public void registrar(Pais p) throws Exception {
        if (buscarPos(p.getId()) == -1) {
            this.lstPaises.add(p);
        }
        guardar(this.lstPaises);
    }
    
    public void actualizar(Pais p) throws Exception {
        if (buscarPos(p.getNombre()) != -1) {
            this.lstPaises.set(buscarPos(p.getId()), p);
        }
        guardar(this.lstPaises);
    }
    
    public void eliminar(Pais p) throws Exception {
        int pos = buscarPos(p.getId());
        if (pos != -1) {
            this.lstPaises.remove(pos);
        }
        guardar(this.lstPaises);
    }

    public List<Pais> mostrarConfederacion(Confederacion c) {
        List<Pais> paises = new ArrayList<>();
        for (Pais pais : this.lstPaises) {
            if (pais.getConfederacion().name().equalsIgnoreCase(c.name())) {
                paises.add(pais);
            }
        }
        return paises;
    }

    public List<Pais> mostrar() {
        return lstPaises;
    }

    public void setLstPaises(List<Pais> lstPaises) {
        this.lstPaises = lstPaises;
    }
    
    public List<Pais> leer() {
        try {
            List<Pais> paises;
            if (!file.exists()) {
                return new ArrayList<>();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                paises = gson.fromJson(br.readLine(), new TypeToken<ArrayList<Pais>>() {
                }.getType());
            }
            if (paises == null) {
                paises = new ArrayList<>();
            }
            return paises;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void guardar(List<Pais> lista) throws Exception {
        try (FileWriter fw = new FileWriter(file)) {
            gson.toJson(lista, fw);
        } catch (Exception e) {
            throw e;
        }
    }
}
