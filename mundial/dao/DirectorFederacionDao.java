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
import mundial.bean.Pais;

/**
 *
 * @author ferna
 */
public class DirectorFederacionDao {
    
    private List<DirectorFederacion> lstDirectoresFederaciones;

    private Gson gson = new Gson();
    private File file = new File("DIRECTORES_TECNICOS.json");

    public DirectorFederacionDao() {
        this.lstDirectoresFederaciones = leer();
    }

    public int buscarPos(String id) {      //Retorna la posicion (En caso de que no la encuentre retorna -1)
        for (int i = 0; i < this.lstDirectoresFederaciones.size(); i++) {
            if (this.lstDirectoresFederaciones.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public DirectorFederacion buscarObj(String id) {   //Retorna el Director Tecnico (En caso de que no la encuentre retorna null)
        for (int i = 0; i < this.lstDirectoresFederaciones.size(); i++) {
            if (this.lstDirectoresFederaciones.get(i).getId().equalsIgnoreCase(id)) {
                return this.lstDirectoresFederaciones.get(i);
            }
        }
        return null;
    }

    public void crearCuentas(List<Pais> paisesCrear) throws Exception {  //Recibe a los paises registrados para 
        for (int i = 0; i < 48; i++) {                  //crear las cuentas de Director Federacion
            String id = (i + 1) + "";
            String nombre = paisesCrear.get(i).getNombre().toUpperCase(); //Converte el nombre del pais en mayuscula
            String contraseña = paisesCrear.get(i).getNombre().toLowerCase() + paisesCrear.get(i).getRankingFifa();
            //La contraseña es el nombre del pais en minuscula mas el numero de rankingFifa
            Pais pais = paisesCrear.get(i);
            boolean habilitado = true;
            DirectorFederacion director = new DirectorFederacion(id, nombre, contraseña, habilitado, pais);
            this.lstDirectoresFederaciones.add(director);
        }
        guardar(this.lstDirectoresFederaciones);
    }

    public int validarInicio(DirectorFederacion directorFederacion) { // Bota la posicion si coincide y -1 si no
        for (int i = 0; i < this.lstDirectoresFederaciones.size(); i++) {
            if (this.lstDirectoresFederaciones.get(i).getNombre().equalsIgnoreCase(directorFederacion.getNombre())
                    && this.lstDirectoresFederaciones.get(i).getContraseña().equalsIgnoreCase(directorFederacion.getContraseña())) {
                return i;
            }
        }
        return -1;
    }

    public List<DirectorFederacion> mostrar() {
        return this.lstDirectoresFederaciones;
    }

    public void setLstDirectoresFederaciones(List<DirectorFederacion> lstDirectoresFederaciones) {
        this.lstDirectoresFederaciones = lstDirectoresFederaciones;
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
