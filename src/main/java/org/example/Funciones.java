package org.example;
import java.util.*;

public class Funciones {
    public static void pausar(int tiempo){
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static int es_entero(String respuesta){
        int numero;
        try {
            numero = Integer.parseInt(respuesta);
        } catch (NumberFormatException excepcion) {
            numero = -1;
        }
        return numero;
    }
    public static int validar_numero(String mensaje, int num){
        Scanner entrada = new Scanner(System.in);
        System.out.print(mensaje);
        String respuesta = entrada.next();

        int numero = es_entero(respuesta);
        while (numero <= num){
            System.out.print("ERROR! El valor ingresado es incorrecto, ingrese nuevamente: ");
            respuesta = entrada.next();
            numero = es_entero(respuesta);
        }
        return numero;
    }
    public static String validar_letra(String mensaje, String letra1, String letra2){
        Scanner entrada = new Scanner(System.in);
        System.out.print(mensaje);
        String respuesta = entrada.next().toUpperCase();

        while (!respuesta.equals(letra1) && !respuesta.equals(letra2)){
            System.out.print("ERROR! El valor ingresado es incorrecto, ingrese nuevamente: ");
            respuesta = entrada.nextLine().toUpperCase();
        }
        return respuesta;
    }
    public static Partido crear_partido(String e1, String e2, int goles1, int goles2){
        Equipo equipo1 = new Equipo(e1, "Descripción equipo 1");
        Equipo equipo2 = new Equipo(e2, "Descripción equipo 2");
        return new Partido(equipo1, equipo2, goles1, goles2);
    }
    public static ResultadoEnum calcular_resultado_partido(Partido partido){
        if (partido.getGolesEquipo1() > partido.getGolesEquipo2()){
            return ResultadoEnum.GANA_EQUIPO1;
        } else if (partido.getGolesEquipo1() < partido.getGolesEquipo2()){
            return ResultadoEnum.GANA_EQUIPO2;
        } else{
            return ResultadoEnum.EMPATE;
        }
    }
    public static ResultadoEnum calcular_resultado_pronostico(int gana1, int gana2){
        if (gana1 == 1){
            return ResultadoEnum.GANA_EQUIPO1;
        } else if (gana2 == 1) {
            return ResultadoEnum.GANA_EQUIPO2;
        } else{
            return ResultadoEnum.EMPATE;
        }
    }
    public static void mostrar_mensaje(ResultadoEnum pronostico, Partido partido){
        if (pronostico.equals(ResultadoEnum.GANA_EQUIPO1)){
            System.out.println("Ganó " + partido.getEquipo1().getNombre() +", acertaste!");
        } else if (pronostico.equals(ResultadoEnum.EMPATE)){
            System.out.println("Empataron, acertaste!");
        } else{
            System.out.println("Ganó " + partido.getEquipo2().getNombre() + ", acertaste!");
        }
    }
    public static int calcular_puntaje(int puntaje, int puntosIndicados, int cantidadDePartidos){
        if(puntaje == puntosIndicados * cantidadDePartidos){
            System.out.println("¡Acertaste todos los partidos! Tenés 50% de puntos extra.");
            puntaje *= 1.5;
        } else{
            System.out.println("No has acertado todos los partidos esta ronda, no tienes puntos extra.");
        }
        return puntaje;
    }
    public static List<String> obtener_participantes(HashMap<String, List<Pronostico>> pronosticos){
        List<String> participantes = new ArrayList<>();
        for (Map.Entry<String, List<Pronostico>> entry : pronosticos.entrySet()){
            participantes.add(entry.getKey());
        }
        return participantes;
    }
    public static void mostrar_puntajes_ronda(List<String> participantes, List<Integer> puntajes_ronda, String nro_ronda){
        Funciones.pausar(1000);
        System.out.println("\n┌───────────────────┐");
        System.out.println("│PUNTAJES RONDA Nº" + nro_ronda + " │");
        for (int i = 0; i < puntajes_ronda.size(); i++){
            System.out.println("│" + participantes.get(i) + ": " + puntajes_ronda.get(i) + "\t\t\t│");
        }
        System.out.println("└───────────────────┘\n");
    }
    public static List<Integer> sumar_puntajes_totales(List<Integer> puntajesTotales, List<Integer> puntajesRonda) {
        int suma = 0;

        for (int i = 0; i < puntajesTotales.size(); i++){
            suma = puntajesTotales.get(i) + puntajesRonda.get(i);
            puntajesTotales.set(i, suma);
        }
        return puntajesTotales;
    }

    public static void obtener_ganadores_rondas(List<Integer> puntajesTotales, int contador, int puntos, int partidosJugados){
        try {
            System.out.println();
            String nro_ronda = String.valueOf(contador);

            if (Integer.parseInt(nro_ronda) == 1){

            }
            else {
                for (int i = 0; i < puntajesTotales.size(); i++){
                    if (puntajesTotales.get(i) == partidosJugados * puntos * 1.5){
                        System.out.println("El jugador " + (i+1) + " ha acertado todos los partidos de la fase!\n" +
                                "Tiene 50% de puntos extra!");
                        puntajesTotales.set(i, (int)(puntajesTotales.get(i) * 1.5));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    public static void mostrar_puntajes_finales(List<Float> puntajes_ronda_totales, List<String> participantes){ //probar
        Funciones.pausar(1000);
        System.out.println("\n╔═══════════════════╗");
        System.out.println("║PUNTAJES" + "\t\t\t║");
        for (int i = 0; i < puntajes_ronda_totales.size(); i++){
            System.out.println("║" + participantes.get(i) + ": " + puntajes_ronda_totales.get(i) + "\t\t\t║");
        }
        System.out.println("╚═══════════════════╝\n");
    }
}
