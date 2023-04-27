package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args){
        //DECLARACIÓN DE OPCIÓN, CONEXIÓN, HASHMAPS, LISTAS, CLASE RONDA Y VARIABLES
        Conexion conector = new Conexion();
        HashMap<Integer, Partido> partidos = null;
        HashMap<String, List<Pronostico>> pronosticos = null;
        List<String> participantes = Arrays.asList("Jugador 1", "Jugador 2");
        List<Integer> puntajes_ronda;
        List<Integer> puntajes_totales = new ArrayList<>();
        Ronda ronda;
        int partidosJugados = 0;
        int contador = 1;
        int puntos = 1;
        int cant_de_rondas = conector.obtener_cantidad_rondas();
        String opcion = "S";

        //ENCABEZADO
        System.out.println("PRONÓSTICOS DEPORTIVOS");
        if (args[0].equals("S")){ //Con interacción de usuario
            opcion = Funciones.validar_letra("¿Desea jugar una ronda? (S - N): ", "S", "N");
        }

        //DESARROLLO DEL PROGRAMA
        while (opcion.equals("S")){
            String nro_ronda = String.valueOf(contador);

            if (args[0].equals("S")){
                if (contador == 1){ //configura la cantidad de puntos que se otorgan
                    puntos = Funciones.validar_numero("Cantidad de puntos a otorgar: ", 0);
                }
            }

            if (contador <= cant_de_rondas){
                System.out.println("Cargando datos de la ronda Nº" + nro_ronda + "...");
                partidos = conector.obtener_partidos(nro_ronda);
                pronosticos = conector.obtener_pronosticos(nro_ronda);
            } else{
                partidos = new HashMap<>();
                pronosticos = new HashMap<>();
            }



            if(args[0].equals("S")){
                if(partidos.size() == 0){
                    System.out.println("Ya no hay más rondas disponibles, gracias por jugar!");
                    if (contador != 1){
                        Funciones.obtener_ganadores_rondas(participantes, puntajes_totales, puntos, partidosJugados);
                        Funciones.mostrar_puntajes_finales(puntajes_totales, participantes);
                    }
                    System.exit(0);
                }

            }

            participantes = Funciones.obtener_participantes(pronosticos); //para mostrar puntajes

            ronda = new Ronda(nro_ronda, partidos);
            puntajes_ronda = ronda.calcular_puntaje_ronda(pronosticos, puntos);
            Funciones.mostrar_puntajes_ronda(participantes, puntajes_ronda, nro_ronda); //muestra puntajes de la ronda actual

            if (contador == 1){
                for (int i = 0; i < puntajes_ronda.size(); i++){
                    puntajes_totales.add(0);
                }
            }

            puntajes_totales = Funciones.sumar_puntajes_totales(puntajes_totales, puntajes_ronda);
            partidosJugados += partidos.size();
            
            if (args[0].equals("S")){ //Con interacción de usuario
                opcion = Funciones.validar_letra("¿Desea jugar otra ronda? (S - N): ", "S", "N");
            } else if (contador == cant_de_rondas) {
                opcion = "N";
            }

            contador += 1;
        }

        if (opcion.equals("N")){ //imprime mensaje en caso de que no quiera jugar
            if (args[0].equals("S")){
                System.out.println("¡No hay problema!¡Hasta la próxima!");
            } else {
                Funciones.pausar(1000);
                System.out.println("¡Rondas completadas!¡Gracias por jugar!");
            }
            if (contador != 1){
                Funciones.obtener_ganadores_rondas(participantes, puntajes_totales, puntos, partidosJugados);
                Funciones.mostrar_puntajes_finales(puntajes_totales, participantes);
            }
        }
    }
}