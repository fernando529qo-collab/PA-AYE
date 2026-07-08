/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundial.logic;

import mundial.dao.TablaPosicionDao;
import mundial.bean.Grupo;
import mundial.bean.Pais;
import mundial.bean.TablaPosicion;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class TablaPosicionLogic {
// hello 
    //Crea la tabla de posiciones
    public static String crearTablaPosiciones(Grupo grupo) {
        try {
            if (grupo == null) {
                return "Debe seleccionar un grupo.";
            }
            TablaPosicionDao.crearTablaPosiciones(grupo);
            return "Tabla de posiciones creada correctamente.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //Actualiza la tabla después de un partido
    public static String actualizarTablaPosiciones(Grupo grupo, Pais local, Pais visitante, int golesLocal, int golesVisitante) {
        try {
            if (grupo == null) {
                return "Debe seleccionar un grupo.";
            }
            TablaPosicionDao.actualizarTablaPosiciones(grupo, local, visitante, golesLocal, golesVisitante);
            TablaPosicionDao.ordenarTablaPosiciones(grupo);
            return "Tabla actualizada correctamente.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //Ordena la tabla de posiciones
    public static String ordenarTablaPosiciones(Grupo grupo) {
        try {
            if (grupo == null) {
                return "Debe seleccionar un grupo.";
            }
            TablaPosicionDao.ordenarTablaPosiciones(grupo);
            return "Tabla ordenada correctamente.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //Lista la tabla de posiciones
    public static ArrayList<TablaPosicion> listarTablaPosiciones(Grupo grupo) {
        try {
            if (grupo == null) {
                return new ArrayList<>();
            }
            return TablaPosicionDao.mostrarTablaPosiciones(grupo);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    //Busca la posición de un país en la tabla
    public static int buscarPosicionPais(Grupo grupo, String id) {
        try {
            if (grupo == null) {
                return -1;
            }
            return TablaPosicionDao.buscarPosicionPais(grupo, id);
        } catch (Exception e) {
            return -1;
        }
    }

    //Busca un país dentro de la tabla
    public static TablaPosicion buscarPaisTabla(Grupo grupo, String id) {
        try {
            if (grupo == null) {
                return null;
            }
            return TablaPosicionDao.buscarPaisTabla(grupo, id);
        } catch (Exception e) {
            return null;
        }
    }

    //Obtiene el primer lugar del grupo
    public static TablaPosicion obtenerPrimerLugar(Grupo grupo) {
        try {
            if (grupo == null) {
                return null;
            }
            return TablaPosicionDao.obtenerPrimerLugar(grupo);
        } catch (Exception e) {
            return null;
        }
    }

    //Obtiene el segundo lugar del grupo
    public static TablaPosicion obtenerSegundoLugar(Grupo grupo) {
        try {
            if (grupo == null) {
                return null;
            }
            return TablaPosicionDao.obtenerSegundoLugar(grupo);
        } catch (Exception e) {
            return null;
        }
    }

    //Obtiene el tercer lugar del grupo
    public static TablaPosicion obtenerTercerLugar(Grupo grupo) {
        try {
            if (grupo == null) {
                return null;
            }
            return TablaPosicionDao.obtenerTercerLugar(grupo);
        } catch (Exception e) {
            return null;
        }
    }

}
