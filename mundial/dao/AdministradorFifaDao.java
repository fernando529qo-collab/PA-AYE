/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundial.dao;

import mundial.bean.AdministradorFifa;

/**
 *
 * @author ferna
 */
public class AdministradorFifaDao {
    
    private AdministradorFifa administradorFifa;

    public AdministradorFifaDao() {
        String id = "1";
        String nombre = "FIFA";
        String contraseña = "mundial2026";
        administradorFifa = new AdministradorFifa(id, nombre, contraseña);
    }

    public AdministradorFifa mostrar() {
        return administradorFifa;
    }

    public void setAdministradorFifa(AdministradorFifa administradorFifa) {
        this.administradorFifa = administradorFifa;
    }
    
}
