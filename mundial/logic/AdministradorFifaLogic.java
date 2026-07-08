public class AdministradorFifaLogic {
    private static AdministradorFifaDao administradorFifaDao = new AdministradorFifaDao();
    
    public static boolean validarAdministrador(AdministradorFifa administrador) {
        if (validarInicio(administrador) != -1) {
            return true;
        } else {
            return false;
        }//Aqui busca y verifica, true si encontro y false si no
    }
    
    public static int validarInicio(AdministradorFifa admin) { // Bota 1 si coincide y -1 si no
        if (administradorFifaDao.mostrar().getNombre().equalsIgnoreCase(admin.getNombre()) 
                && administradorFifaDao.mostrar().getContraseña().equalsIgnoreCase(admin.getContraseña())) {
            return 1;
        }
        return -1;
    }

    public static AdministradorFifa mostrarAdministradorFifa() {
        return administradorFifaDao.mostrar();
    }
}
