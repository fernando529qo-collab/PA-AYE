package mundial.logic;

import java.util.ArrayList;
import java.util.List;
import mundial.bean.Estadistica;
import mundial.bean.Pais;
import mundial.bean.Partido;
import mundial.dao.PartidoDao;

public class EstadisticaLogic {

    //met: recibe al partido y le agrega las estadísticas
    public static Partido simularConEstadisticas(List<Pais> paises, String fecha) {
        Partido partido = PartidoLogic.simular(paises, fecha);
        String mensajeGuardado = procesarEstadisticas(partido);
        partido.setMensaje(mensajeGuardado);
        return partido;
    }

    //met: toma un partido simulado, genera estadisticas, las guarda
    public static String procesarEstadisticas(Partido partido) {
        int golesLocal = partido.getGolesLocal();
        int golesVisitante = partido.getGolesVisitante();
        int probabilidadLocal = partido.getProbabilidadLocal();
        int probabilidadVisitante = partido.getProbabilidadVisitante();
        List<Estadistica> estadisticas = generarEstadisticas(
                golesLocal, golesVisitante, probabilidadLocal, probabilidadVisitante);

        partido.setPartidoEstadisticas(estadisticas);

        try {
            // Instancia nueva: lee el JSON ya actualizado por PartidoLogic.simular()
            PartidoDao partidoDao = new PartidoDao();
            boolean actualizado = partidoDao.actualizar(partido);
            if (actualizado) {
                return "Estadisticas generadas y guardadas con exito.";
            } else {
                return "No se encontro el partido para actualizar sus estadisticas.";
            }
        } catch (Exception e) {
            return "Error al guardar las estadisticas: " + e.getMessage();
        }
    }

    //met: genera estadisticas a partir de las de partido
    public static List<Estadistica> generarEstadisticas(int golesLocal, int golesVisitante, int probabilidadLocal, int probabilidadVisitante) {
        // La posesion del local se calcula a partir de su probabilidad de ganar
        int posesionLocal = calcularPosesion(probabilidadLocal);
        // La del visitante es el complemento, para que ambas siempre sumen 100
        int posesionVisitante = 100 - posesionLocal;
        // Diferencia de goles sirve para las faltas/tarjetas no sean puro azar
        int diferenciaLocal = golesLocal - golesVisitante;
        int diferenciaVisitante = -diferenciaLocal; // el inverso exacto del local

        List<Estadistica> estadisticas = new ArrayList<>();
        // Se genera una Estadistica completa para cada equipo
        estadisticas.add(generarEstadisticaEquipo(golesLocal, probabilidadLocal, posesionLocal, diferenciaLocal));
        estadisticas.add(generarEstadisticaEquipo(golesVisitante, probabilidadVisitante, posesionVisitante, diferenciaVisitante));
        return estadisticas;
    }

    // Calcula la posesion de un equipo a partir de su probabilidad de victoria a la probabilidad se le suma una variación aleatoria entre -5 a +5, luego se limita entre 30 y 70
    private static int calcularPosesion(int probabilidad) {
        int variacion = (int) (Math.random() * 11) - 5; // numero aleatorio entre -5 y +5
        int posesion = probabilidad + variacion;
        if (posesion < 30) {
            posesion = 30; // tope minimo
        }
        if (posesion > 70) {
            posesion = 70; // tope maximo
        }
        return posesion;
    }

    //Arma el objeto estadisticas de un equipo, llamando a cada atributo generado antes
    private static Estadistica generarEstadisticaEquipo(int goles, int probabilidad, int posesion, int diferenciaGoles) {
        int remates = calcularRemates(goles, probabilidad);
        int rematesArco = calcularRematesArco(goles, remates);
        int pases = calcularPases(posesion);
        int precisionPases = calcularPrecisionPases(posesion);
        int faltas = calcularFaltas(diferenciaGoles);
        int tarjetasAmarillas = calcularTarjetasAmarillas(faltas, diferenciaGoles);
        int tarjetasRojas = calcularTarjetasRojas(tarjetasAmarillas);
        int posicionAdelantada = calcularPosicionAdelantada(remates);
        int tirosEsquina = calcularTirosEsquina(remates);

        return new Estadistica(remates, rematesArco, posesion, pases, precisionPases, faltas, tarjetasAmarillas, tarjetasRojas, posicionAdelantada, tirosEsquina);
    }

    //Genera remates totales, a partir de la probabilidad se le suma un numero entre 0 a 4 para variar resultado
    private static int calcularRemates(int goles, int probabilidad) {
        int base = goles * 2 + (probabilidad / 10) + (int) (Math.random() * 5);
        if (base < goles) {
            base = goles + (int) (Math.random() * 3);
        }
        return base;
    }

    //Generar remates al arco, toma los goles como piso y suma un aleatorio dentro del rango hasta el total de remates
    private static int calcularRematesArco(int goles, int remates) {
        int rango = (remates - goles) + 1; // +1 para incluir el limite superior
        return goles + (int) (Math.random() * rango);
    }

    //Generar pases totales, a partir de la posesion, se le suma la posesion por 3 y se varia entre 0 a 49
    private static int calcularPases(int posesion) {
        return 250 + posesion * 3 + (int) (Math.random() * 50);
    }

    //Generar precision de pases, a mayor posesion mejor precision, con piso de 65% y tope de 95%
    private static int calcularPrecisionPases(int posesion) {
        int precision = 65 + (posesion / 5) + (int) (Math.random() * 10);
        if (precision > 95) {
            precision = 95;
        }
        return precision;
    }

    //Generar faltas, base aleatoria 10-15, sube si el equipo va perdiendo y baja si va ganando, minimo 4
    private static int calcularFaltas(int diferenciaGoles) {
        int base = 10 + (int) (Math.random() * 6);
        if (diferenciaGoles < 0) {
            base += Math.min(5, Math.abs(diferenciaGoles) * 2); // va perdiendo -> mas faltas
        } else if (diferenciaGoles > 0) {
            base -= Math.min(3, diferenciaGoles); // va ganando -> menos faltas
        }
        if (base < 4) {
            base = 4; // piso minimo
        }
        return base;
    }

    //Generar tarjetas amarillas, en base a las faltas cometidas mas un extra si el equipo va perdiendo
    private static int calcularTarjetasAmarillas(int faltas, int diferenciaGoles) {
        int amarillas = (faltas / 5) + (int) (Math.random() * 2);
        if (diferenciaGoles < -1) {
            amarillas += 1;
        }
        return amarillas;
    }

    //Generar tarjeta roja, probabilidad base de 5%, sube a 10% si el equipo ya tiene mas de 3 amarillas
    private static int calcularTarjetasRojas(int tarjetasAmarillas) {
        double probabilidadRoja = 0.05 + (tarjetasAmarillas > 3 ? 0.05 : 0);
        return Math.random() < probabilidadRoja ? 1 : 0;
    }

    //Generar posiciones adelantadas, a partir de un tercio de los remates mas variacion aleatoria de 0 a 2
    private static int calcularPosicionAdelantada(int remates) {
        int base = remates / 3;
        return base + (int) (Math.random() * 3);
    }

    //Generar tiros de esquina, a partir de la mitad de los remates mas variacion aleatoria de 0 a 2
    private static int calcularTirosEsquina(int remates) {
        return (remates / 2) + (int) (Math.random() * 3);
    }
}