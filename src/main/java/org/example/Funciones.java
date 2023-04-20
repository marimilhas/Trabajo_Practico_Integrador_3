package org.example;
import java.util.Scanner;

public class Funciones {
    public static void pausar(int tiempo){
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /*public static Path validar_archivo(String mensaje){
        Scanner entrada = new Scanner(System.in);
        System.out.print(mensaje);
        String ruta = entrada.nextLine();
        Path archivo = Paths.get(ruta);

        while (!Files.exists(archivo)){
            System.out.print("ERROR! El archivo no existe, ingrese nuevamente: ");
            ruta = entrada.nextLine();
            archivo = Paths.get(ruta);
        }
        return archivo;
    }*/
    public static String validar_letra(String mensaje, String letra1, String letra2){
        Scanner entrada = new Scanner(System.in);
        System.out.print(mensaje);
        String respuesta = entrada.nextLine().toUpperCase();

        while (!respuesta.equals(letra1) && !respuesta.equals(letra2)){
            System.out.print("ERROR! El valor ingresado es incorrecto, ingrese nuevamente: ");
            respuesta = entrada.nextLine().toUpperCase();
        }
        return respuesta;
    }
    /*public static boolean validar_archivo_resultados(String[] linea, int indice) {
        boolean resultado = true;

        try {
            if (linea.length != 5){     //Verifica número correcto de campos
                throw new ExcepcionCantCampos(indice);
            }
            Integer.parseInt(linea[2]); //Verifica cantidad de goles del equipo 1
            Integer.parseInt(linea[3]); //Verifica cantidad de goles del equipo 2

        } catch (ExcepcionCantCampos excepcion1){
            System.out.print(excepcion1.getMessage());
            resultado = false;

        } catch (NumberFormatException excepcion) {
            System.out.print("¡Los goles de al menos un equipo en la línea " + (indice + 1) +
                    " no es un número entero! \nModifique su archivo y reinicie el programa.\n" );
            resultado = false;
        }

        return resultado;
    }*/
    /*public static List<String> obtener_jugadores(Path ruta) throws IOException {
        String[] linea_array;
        List<String> jugadores = new ArrayList<>();

        for (String linea_string : Files.readAllLines(ruta)) {
            linea_array = linea_string.split(";");
            if (jugadores.size() == 0) {
                jugadores.add(linea_array[0]);
            } else {
                if (!linea_array[0].equals(jugadores.get(jugadores.size() - 1))) {
                    jugadores.add(linea_array[0]);
                }
            }
        }
        return jugadores;
    }*/
    public static ResultadoEnum calcular_resultado_pronostico(int gana1, int gana2){
        if (gana1 == 1){
            return ResultadoEnum.GANA_EQUIPO1;
        } else if (gana2 == 1) {
            return ResultadoEnum.GANA_EQUIPO2;
        } else{
            return ResultadoEnum.EMPATE;
        }
    }
    /*public static ResultadoEnum calcular_resultado_partido(Partido partido){
        if (partido.golesEquipo1 > partido.golesEquipo2){
            return ResultadoEnum.GANA_EQUIPO1;
        } else if (partido.golesEquipo1 == partido.golesEquipo2){
            return ResultadoEnum.EMPATE;
        } else{
            return ResultadoEnum.GANA_EQUIPO2;
        }
    }*/
    /*public static Partido crear_partido(String e1, int goles1, String e2, int goles2){
        Equipo equipo1 = new Equipo(e1, "Descripción equipo 1");
        Equipo equipo2 = new Equipo(e2, "Descripción equipo 2");
        return new Partido(equipo1, equipo2, goles1, goles2);
    }*/
    public static Pronostico crear_pronostico(String e1, String e2, int goles1, int goles2, int gana1, int gana2){
        Equipo equipo1 = new Equipo(e1, "Descripción equipo 1");
        Equipo equipo2 = new Equipo(e2, "Descripción equipo 2");
        Partido partido = new Partido(equipo1, equipo2, goles1, goles2);
        return new Pronostico(partido, calcular_resultado_pronostico(gana1, gana2));
    }
    /*public static void mostrar_mensaje(ResultadoEnum pronostico, Partido partido){
        if (pronostico.equals(ResultadoEnum.GANA_EQUIPO1)){
            System.out.println("Ganó " + partido.getEquipo1().getNombre() +", acertaste!");
        } else if (pronostico.equals(ResultadoEnum.EMPATE)){
            System.out.println("Empataron, acertaste!");
        } else{
            System.out.println("Ganó " + partido.getEquipo2().getNombre() + ", acertaste!");
        }
    }*/
}
