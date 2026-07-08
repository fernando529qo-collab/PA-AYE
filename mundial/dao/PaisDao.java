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
    
    private List<Pais> lstPaises; //Paises Registrados

    private List<Pais> lstPaisesTodos; //Todos los paises

    private Gson gson = new Gson();
    private File file = new File("PAISES.json");

    private Gson gson1 = new Gson();
    private File file1 = new File("TODOS_PAISES.json");

    public PaisDao() {
        this.lstPaises = leer();
        this.lstPaisesTodos = leerTodos();
    }

    //Tanto "buscarPos" como "buscarObj" utilizan el id para buscar
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

    public void registrarConfederacion(List<Pais> paises) throws Exception {
        for (Pais p : paises) {
            if (buscarPos(p.getId()) == -1) {
                this.lstPaises.add(p);
            }
        }
        guardar(this.lstPaises);
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

    public List<Pais> mostrarConfederacionRegistrados(Confederacion c) { //Muestra a los paises de una confederacion
        List<Pais> paises = new ArrayList<>();
        for (Pais pais : this.lstPaises) {
            if (pais.getConfederacion().name().equalsIgnoreCase(c.name())) {
                paises.add(pais);
            }
        }
        return paises;
    }

    public List<Pais> mostrarConfederacionTodos(Confederacion c) { //Muestra a los paises de una confederacion
        List<Pais> paises = new ArrayList<>();
        for (Pais pais : this.lstPaisesTodos) {
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

    public List<Pais> leerTodos() {
        try {
            List<Pais> paises;
            if (!file1.exists()) {
                return new ArrayList<>();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
                paises = gson1.fromJson(br.readLine(), new TypeToken<ArrayList<Pais>>() {
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

    public void guardarTodos(List<Pais> lista) throws Exception {
        try (FileWriter fw = new FileWriter(file1)) {
            gson1.toJson(lista, fw);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Pais> mostrarTodos() {
        return lstPaisesTodos;
    }
}
