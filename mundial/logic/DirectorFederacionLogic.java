public class DirectorFederacionLogic {
    private static DirectorFederacionDao directorFederacionDao = new DirectorFederacionDao();
    
    public static boolean validarAdministrador(DirectorFederacion director) {
        if (validarInicio(director) != -1) {
            return true;
        } else {
            return false;
        }//Aqui busca y verifica, true si encontro y false si no
    }
    
    public static int validarInicio(DirectorFederacion directorFederacion) { // Bota la posicion si coincide y -1 si no
        for (int i = 0; i < directorFederacionDao.mostrar().size(); i++) {
            if (directorFederacionDao.mostrar().get(i).getNombre().equalsIgnoreCase(directorFederacion.getNombre())
                    && directorFederacionDao.mostrar().get(i).getContraseña().equalsIgnoreCase(directorFederacion.getContraseña())) {
                return i;
            }
        }
        return -1;
    }

    public static List<DirectorFederacion> mostrarDirectorFederaciones() {
        return directorFederacionDao.mostrar();
    }

    public static void setLstDirectorFederaciones(List<DirectorFederacion> lstDirectores) {
        directorFederacionDao.setLstDirectoresFederaciones(lstDirectores);
    }
    
    public static boolean crearCuentas(List<Pais> paisesCrear) throws Exception {  //Recibe a los paises registrados para 
        for (int i = 0; i < 48; i++) {                  //crear las cuentas de Director Federacion
            String id = (i + 1) + "";
            String nombre = paisesCrear.get(i).getNombre().toUpperCase(); //Converte el nombre del pais en mayuscula
            String contraseña = paisesCrear.get(i).getNombre().toLowerCase() + paisesCrear.get(i).getRankingFifa();
            //La contraseña es el nombre del pais en minuscula mas el numero de rankingFifa
            Pais pais = paisesCrear.get(i);
            boolean habilitado = true;
            DirectorFederacion director = new DirectorFederacion(id, nombre, contraseña, habilitado, pais);
            directorFederacionDao.registrar(director);
        }
        if (directorFederacionDao.mostrar().size() == 48) {
            return true;
        }
        return false;
    }
    
    public static boolean cuentasCreadas(List<Pais> paisesCrear) throws Exception {
        return crearCuentas(paisesCrear);
    }
}
