package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("PRONÓSTICOS DEPORTIVOS");
        String opcion = Funciones.validar_letra("¿Desea jugar una ronda? (S - N): ", "S", "N");

        if (opcion.equals("S")){
            System.out.println("\n↓ Ingrese los siguientes datos ↓");
            Path ruta_resultados = Funciones.validar_archivo("Ruta del archivo con los resultados: ");
            Path ruta_pronosticos = Funciones.validar_archivo("Ruta del archivo con sus pronósticos: ");

            int cantidad_partidos = (Files.readAllLines(ruta_resultados)).size();
            List<String> jugadores = Funciones.obtener_jugadores(ruta_pronosticos);

            Partido[] partidos = new Partido[cantidad_partidos];
            String[] linea;
            System.out.println("\nVerificando archivo de resultados...");
            Funciones.pausar(2000);
            for (int i = 0; i < cantidad_partidos; i++){
                linea = (Files.readAllLines(ruta_resultados).get(i)).split(";");
                if (Funciones.validar_archivo_resultados(linea, i)){
                    partidos[i] = Funciones.crear_partido(linea);
                } else{
                    System.exit(0);
                }
            }
            System.out.println("El archivo ha sido verificado con éxito!");

            Pronostico[][] pronosticos = new Pronostico[cantidad_partidos][jugadores.size()];
            int indice = 0;
            for (int i = 0; i < jugadores.size(); i++){
                for (int j = 0; j < cantidad_partidos; j++){
                    pronosticos[j][i] = Funciones.crear_pronostico(ruta_pronosticos, indice);
                    indice += 1;
                }
            }

            Ronda ronda = new Ronda(partidos);
            int[] puntajes = ronda.calcular_puntaje_ronda(pronosticos, jugadores);
            Funciones.pausar(1500);
            String ganador = Funciones.obtener_ganador(puntajes, jugadores);
            System.out.println("\n╔══════════════════╗");
            System.out.println("║      GANADOR     ║");
            System.out.println("║      " + ganador + "     ║");
            System.out.println("╚══════════════════╝");

        } else{
            System.out.println("Está bien, vuelve pronto!");
        }

    }
}