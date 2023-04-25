/*package org.example;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CalcularPuntajeTest{
    @BeforeAll
    static void crear_ronda(){
        Ronda ronda = new Ronda();
    }
    @BeforeAll
    static void obtener_partidos_ronda1(){
        HashMap<Integer, Partido> partidos = new HashMap<>();
        partidos.put(1, new Partido(new Equipo("Argentina", "Descripción equipo 1"),
                new Equipo("Arabia Saudita", "Descripción equipo2"), 1, 2));
        partidos.put(2, new Partido(new Equipo("Polonia", "Descripción equipo 1"),
                new Equipo("México", "Descripción equipo2"), 0, 0));
    }
    @BeforeAll
    static void obtener_pronosticos_ronda1(){
        HashMap<String, List<Pronostico>> pronosticos = new HashMap<>();
        List<Pronostico> pronost_participante = Arrays.asList(
                new Pronostico(1, ResultadoEnum.GANA_EQUIPO2),
                new Pronostico(2, ResultadoEnum.EMPATE));
        pronosticos.put("Mariana", pronost_participante);
    }
    @BeforeAll
    static void obtener_partidos_ronda2(){
        HashMap<Integer, Partido> partidos = new HashMap<>();
        partidos.put(3, new Partido(new Equipo("Argentina", "Descripción equipo 1"),
                new Equipo("México", "Descripción equipo2"), 2, 0));
        partidos.put(4, new Partido(new Equipo("Arabia Saudita", "Descripción equipo 1"),
                new Equipo("Polonia", "Descripción equipo2"), 0, 2));
    }
    @BeforeAll
    static void obtener_pronosticos_ronda2(){
        HashMap<String, List<Pronostico>> pronosticos = new HashMap<>();
        List<Pronostico> pronost_participante = Arrays.asList(
                new Pronostico(3, ResultadoEnum.GANA_EQUIPO1),
                new Pronostico(4, ResultadoEnum.GANA_EQUIPO2));
        pronosticos.put("Mariana", pronost_participante);
    }
    @Test
    void calcular_puntos(){
        assertAll(() -> assertEquals(240, c.multiplicar(80, 3)),
                () -> assertNotEquals(605, c.multiplicar(c.restar(90, 50), 15)),
                () -> assertNotEquals(2700, c.multiplicar(c.sumar(70, 40), 25))
    }
    void simular_rondas(){
        int nro_ronda = 1;
        while (nro_ronda <= 2){
            if (nro_ronda == 1){

            }
            nro_ronda += 1;
        }
    }


}
*/
/*package org.example;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CalcularPuntajeTest {
    @Test
    void calcularPuntajeDeMarianaTest() throws IOException {
        System.out.println("\n↓ Ingrese los siguientes datos ↓");
        Path ruta_resultados = Paths.get(".\\resultados.csv");
        Path ruta_pronosticos = Paths.get(".\\pronosticos.csv");

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
        Path ruta_resultados = Paths.get(".\\resultados.csv");
        Path ruta_pronosticos = Paths.get(".\\pronosticos.csv");

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

}*/