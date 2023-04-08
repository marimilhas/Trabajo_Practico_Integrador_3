package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Funciones {
    public static void pausar(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static Path validar_archivo(String mensaje){
        Scanner entrada = new Scanner(System.in);
        System.out.print(mensaje);
        String ruta = entrada.nextLine();
        Path archivo = Paths.get(ruta);

        while (!Files.exists(archivo)){
            System.out.print("ERROR! El archivo no existe, ingrese nuevamente:");
            ruta = entrada.nextLine();
            archivo = Paths.get(ruta);
        }
        return archivo;
    }
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
    public static ResultadoEnum calcular_resultado_pronostico(String[] array){
        if (array[1].equals("X")){
            return ResultadoEnum.GANA_EQUIPO1;
        } else if (array[2].equals("X")) {
            return ResultadoEnum.EMPATE;
        } else{
            return ResultadoEnum.GANA_EQUIPO2;
        }
    }
    public static ResultadoEnum calcular_resultado_partido(Partido partido){
        if (partido.golesEquipo1 > partido.golesEquipo2){
            return ResultadoEnum.GANA_EQUIPO1;
        } else if (partido.golesEquipo1 == partido.golesEquipo2){
            return ResultadoEnum.EMPATE;
        } else{
            return ResultadoEnum.GANA_EQUIPO2;
        }
    }
    public static Partido crear_partido(Path ruta, int indice) throws IOException {
        String[] array = (Files.readAllLines(ruta).get(indice)).split(";");
        Equipo equipo1 = new Equipo(array[0], "Descripción equipo 1");
        Equipo equipo2 = new Equipo(array[3], "Descripción equipo 2");
        int golesEquipo1 = Integer.parseInt(array[1]);
        int golesEquipo2 = Integer.parseInt(array[2]);
        return new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);
    }
    public static Pronostico crear_pronostico(Path ruta, int indice) throws IOException {
        String[] array = (Files.readAllLines(ruta).get(indice)).split(";");
        Equipo equipo1 = new Equipo(array[0], "Descripción equipo 1");
        Equipo equipo2 = new Equipo(array[4], "Descripción equipo 2");
        Partido partido = new Partido(equipo1, equipo2);
        return new Pronostico(partido, calcular_resultado_pronostico(array));
    }
}
