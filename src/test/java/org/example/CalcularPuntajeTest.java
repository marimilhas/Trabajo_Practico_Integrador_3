package org.example;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CalcularPuntajeTest{
   @Test
   void calcular_puntaje(){
        //Creo los puntos que ingresaría el usuario
       int puntos = 1;

       ///////////////////////////////////////////////////RONDA 1///////////////////////////////////////////////////
        //Creo el hashMaps correspondiente de Partido
       HashMap<Integer, Partido> partidos = new HashMap<>();

       //Creo 4 equipos, 2 vs 2
       Equipo Argentina = new Equipo("Argentina", "desc");
       Equipo Mexico = new Equipo("Mexico", "desc");

       Equipo Polonia = new Equipo("Polonia", "desc");
       Equipo Francia = new Equipo("Francia", "desc");

       //Los agrego 2 partidos, 2 vs 2
       partidos.put(1, new Partido(Argentina, Mexico, 2, 1));
       partidos.put(2, new Partido(Polonia, Francia, 2, 1));


       //Creo el hashMaps correspondiente de pronostico
       HashMap<String, List<Pronostico>> pronosticos = new HashMap<>();

       //Creo el pronóstico de Pedro y de Juan
       List<Pronostico> pronostico_pedro = new ArrayList<>();
       pronostico_pedro.add(new Pronostico(1, ResultadoEnum.GANA_EQUIPO1));
       pronostico_pedro.add(new Pronostico(2, ResultadoEnum.GANA_EQUIPO1));

       List<Pronostico> pronostico_juan = new ArrayList<>();
       pronostico_juan.add(new Pronostico(1, ResultadoEnum.GANA_EQUIPO2));
       pronostico_juan.add(new Pronostico(2, ResultadoEnum.GANA_EQUIPO2));

       //Guardo en el HashMap de pronóstico pronósticos de Pedro y Juan
       pronosticos.put("Pedro", pronostico_pedro);
       pronosticos.put("Juan", pronostico_juan);

       //Creo la ronda, los puntajes por Ronda y los puntajes totales

       List<Integer> puntajes_ronda = new ArrayList<>();
       List<Integer> puntajes_totales = new ArrayList<>();
       puntajes_totales.add(0);
       puntajes_totales.add(0);

       //Calculo el pronóstico con la Ronda 1. Le paso por parámetro 1 para calcular ronda 1
       Ronda ronda = new Ronda("1", partidos);
       puntajes_ronda = ronda.calcular_puntaje_ronda(pronosticos, puntos);
       puntajes_totales = Funciones.sumar_puntajes_totales(puntajes_totales, puntajes_ronda);

       ///////////////////////////////////////////////////RONDA 2///////////////////////////////////////////////////
       //Creo el hashMaps correspondiente de Partido
       partidos.clear(); //Vacío el HashMap para ponerle otros partidos correspondientes a la segunda ronda

       //Los agrego 2 partidos, 2 vs 2
       partidos.put(3, new Partido(Polonia, Mexico, 1, 2));
       partidos.put(4, new Partido(Argentina, Francia, 1, 2));

       //Creo el pronóstico de Pedro  y de Juan
       pronostico_pedro.clear();
       pronostico_pedro.add(new Pronostico(3, ResultadoEnum.GANA_EQUIPO2));
       pronostico_pedro.add(new Pronostico(4, ResultadoEnum.GANA_EQUIPO2));

       pronostico_juan.clear();
       pronostico_juan.add(new Pronostico(3, ResultadoEnum.GANA_EQUIPO2));
       pronostico_juan.add(new Pronostico(4, ResultadoEnum.GANA_EQUIPO2));

       //Guardo en el HashMap de pronóstico pronósticos de Pedro y Juan
       pronosticos.put("Pedro", pronostico_pedro);
       pronosticos.put("Juan", pronostico_juan);

       //Calculo el pronóstico con la Ronda 1. Le paso por parámetro 1 para calcular ronda 1
       Ronda ronda2 = new Ronda("2", partidos);
       puntajes_ronda = ronda2.calcular_puntaje_ronda(pronosticos, puntos);
       puntajes_totales = Funciones.sumar_puntajes_totales(puntajes_totales, puntajes_ronda);

       //Calculo si el resultado es igual a lo esperado
       List<String> participantes = List.of(new String[]{"Pedro", "Juan"});
       Funciones.obtener_ganadores_rondas(participantes, puntajes_totales, puntos, 4);

       //Pedro acertó todos los partidos de todas las rondas se espera 9
       assertEquals(9, puntajes_totales.get(0));

       //Juan acertó solo los 2 partidos de la ronda 2 se espera 3
       assertEquals(3, puntajes_totales.get(1));
   }
}