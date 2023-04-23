package org.example;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        //DECLARACIÓN DE HASHMAPS, LISTAS, CLASE RONDA Y VARIABLES
        HashMap<Integer, Partido> partidos;
        HashMap<String, List<Pronostico>> pronosticos;
        List<String> participantes;
        List<Integer> puntajes_ronda;
        List<Integer> puntajes_totales = new ArrayList<>();
        Ronda ronda;
        int partidosJugados = 0;
        int contador = 1;
        int puntos = 0;

        //ENCABEZADO
        System.out.println("PRONÓSTICOS DEPORTIVOS");
        String opcion = Funciones.validar_letra("¿Desea jugar una ronda? (S - N): ", "S", "N");

        //DESARROLLO DEL PROGRAMA
        while (opcion.equals("S")){
            String nro_ronda = String.valueOf(contador);

            if (contador == 1){ //configura la cantidad de puntos que se otorgan
                puntos = Funciones.validar_numero("Cantidad de puntos a otorgar: ", 0);
            }

            System.out.println("Cargando...");
            Conexion conector = new Conexion();

            partidos = conector.obtener_partidos(nro_ronda);
            if (partidos.size() == 0){ //valida que hayan más rondas para jugar en la db
                System.out.println("Ya no hay más rondas disponibles, gracias por jugar!");
                Funciones.obtener_ganadores_rondas(puntajes_totales, contador, puntos, partidosJugados); //muestra puntajes finales
                System.exit(0);
            }
            pronosticos = conector.obtener_pronosticos(nro_ronda);
            participantes = Funciones.obtener_participantes(pronosticos); //para mostrar puntajes
            ronda = new Ronda(nro_ronda, partidos);
            puntajes_ronda = ronda.calcular_puntaje_ronda(pronosticos, puntos);

            if (contador == 1){
                for (int i = 0; i < puntajes_ronda.size(); i++){
                    puntajes_totales.add(0);
                }
            }
            puntajes_totales = Funciones.sumar_puntajes_totales(puntajes_totales, puntajes_ronda);

            /*if (contador == 1){
                puntajes_totales.addAll(puntajes_ronda);
            } else{
                Funciones.sumar_puntajes_totales(puntajes_totales, puntajes_ronda);
            }*/

            partidosJugados += partidos.size();
            Funciones.mostrar_puntajes_ronda(participantes, puntajes_ronda, nro_ronda); //muestra puntajes de la ronda actual

            opcion = Funciones.validar_letra("¿Desea jugar otra ronda? (S - N): ", "S", "N");
            contador += 1;
        }

        if (opcion.equals("N")){ //Imprime mensaje en caso de que no quiera jugar
            System.out.println("¡No hay problema!¡Hasta la próxima!");
            Funciones.obtener_ganadores_rondas(puntajes_totales, contador, puntos, partidosJugados); //muestra puntos extra
        }
    }
}