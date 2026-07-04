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
import mundial.bean.Jugador;

/**
 *
 * @author ferna
 */
public class JugadorDao {
    
    private List<Jugador> lstJugadores;
    
    private Gson gson = new Gson();
    private File file = new File("JUGADORES.json");

    public JugadorDao() {
        this.lstJugadores = leer();
    }
    
    public int buscarPos(String id) {      //Retorna la posicion (En caso de que no la encuentre retorna -1)
        for (int i = 0; i < this.lstJugadores.size(); i++) {
            if (this.lstJugadores.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
    
    public Jugador buscarObj(String id) {   //Retorna el jugador (En caso de que no la encuentre retorna null)
        for (int i = 0; i < this.lstJugadores.size(); i++) {
            if (this.lstJugadores.get(i).getId().equalsIgnoreCase(id)) {
                return this.lstJugadores.get(i);
            }
        }
        return null;
    }
    
    public List<Jugador> leer() {
        try {
            List<Jugador> jugadores;
            if (!file.exists()) {
                return new ArrayList<>();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                jugadores = gson.fromJson(br.readLine(), new TypeToken<ArrayList<Jugador>>() {
                }.getType());
            }
            if (jugadores == null) {
                jugadores = new ArrayList<>();
            }
            return jugadores;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void guardar(List<Jugador> lista) throws Exception {
        try (FileWriter fw = new FileWriter(file)) {
            gson.toJson(lista, fw);
        } catch (Exception e) {
            throw e;
        }
    }
}
