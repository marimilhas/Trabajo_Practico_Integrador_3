package org.example;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CalcularPuntajeTest {
    @Test
    void calcularPuntajeDeMarianaTest() throws IOException {
        System.out.println("\n↓ Ingrese los siguientes datos ↓");
        Path ruta_resultados = Path.of("C:\\Users\\Franco\\Desktop\\Desarrollador Java\\MERGE\\Trabajo_Practico_Integrador_2\\src\\main\\java\\org\\example\\resultados.csv");
        Path ruta_pronosticos = Path.of("C:\\Users\\Franco\\Desktop\\Desarrollador Java\\MERGE\\Trabajo_Practico_Integrador_2\\src\\main\\java\\org\\example\\pronosticos.csv");

        int cantidad_partidos = (Files.readAllLines(ruta_resultados)).size();
        List<String> jugadores = Funciones.obtener_jugadores(ruta_pronosticos);

        Partido[] partidos = new Partido[cantidad_partidos];
        String[] linea;
        System.out.println("\nVerificando archivo de resultados...");
        Funciones.pausar(0);
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
        int esperado = 2;

        assertEquals(esperado, puntajes[0]);
    }
    @Test

    void calcularPuntajeDePedroTest() throws IOException {
        System.out.println("\n↓ Ingrese los siguientes datos ↓");
        Path ruta_resultados = Path.of("C:\\Users\\Franco\\Desktop\\Desarrollador Java\\MERGE\\Trabajo_Practico_Integrador_2\\src\\main\\java\\org\\example\\resultados.csv");
        Path ruta_pronosticos = Path.of("C:\\Users\\Franco\\Desktop\\Desarrollador Java\\MERGE\\Trabajo_Practico_Integrador_2\\src\\main\\java\\org\\example\\pronosticos.csv");

        int cantidad_partidos = (Files.readAllLines(ruta_resultados)).size();
        List<String> jugadores = Funciones.obtener_jugadores(ruta_pronosticos);

        Partido[] partidos = new Partido[cantidad_partidos];
        String[] linea;
        System.out.println("\nVerificando archivo de resultados...");
        Funciones.pausar(0);
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
        int esperado = 0;

        assertEquals(esperado, puntajes[1]);
    }

}