package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("PRONÓSTICOS DEPORTIVOS");
        String opcion = Funciones.validar_letra("¿Desea jugar una ronda? (S - N): ", "S", "N");

        if (opcion.equals("S")){
            System.out.println("\n↓ Ingrese los siguientes datos ↓");
            Path ruta_pronosticos = Funciones.validar_archivo("Ruta del archivo con sus pronósticos: ");
            Path ruta_resultados = Funciones.validar_archivo("Ruta del archivo con los resultados: ");

            int cantidad_partidos = (Files.readAllLines(ruta_resultados)).size();

            Pronostico[] pronosticos = new Pronostico[cantidad_partidos];
            for (int i = 0; i < cantidad_partidos; i++){
                pronosticos[i] = Funciones.crear_pronostico(ruta_pronosticos, i);
            }

            Partido[] partidos = new Partido[cantidad_partidos];
            for (int i = 0; i < cantidad_partidos; i++){
                partidos[i] = Funciones.crear_partido(ruta_resultados, i);
            }

            System.out.println(" ");
            Ronda ronda = new Ronda(partidos);
            int puntaje = ronda.calcular_puntaje_ronda(pronosticos);
            System.out.println("Puntaje obtenido --> " + puntaje);
        }

        System.out.println("\nHasta luego!");
    }
}