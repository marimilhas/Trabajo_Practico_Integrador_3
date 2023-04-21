package org.example;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("PRONÓSTICOS DEPORTIVOS");
        String opcion = Funciones.validar_letra("¿Desea jugar una ronda? (S - N): ", "S", "N");
        int contador = 1;
        int puntos = 0;

        while (opcion.equals("S")){
            String nro_ronda = String.valueOf(contador);

            if (contador == 1){ //configura la cantidad de puntos que se otorgan
                puntos = Funciones.validar_numero_mayor("Cantidad de puntos a otorgar: ", 0);
            }

            System.out.println("Cargando...");
            Conexion conector = new Conexion();

            HashMap<Integer, Partido> partidos = conector.obtener_partidos(nro_ronda);
            if (partidos.size() == 0){
                System.out.println("Ya no hay más rondas disponibles, gracias por jugar!");
                System.exit(0);
            }
            HashMap<String, List<Pronostico>> pronosticos = conector.obtener_pronosticos(nro_ronda);
            List<String> participantes = Funciones.obtener_participantes(pronosticos);
            Ronda ronda = new Ronda(nro_ronda, partidos);
            List<Integer> puntajes_ronda = ronda.calcular_puntaje_ronda(pronosticos, puntos);

            Funciones.mostrar_puntajes(participantes,puntajes_ronda, nro_ronda);

            opcion = Funciones.validar_letra("¿Desea jugar otra ronda? (S - N): ", "S", "N");
            contador += 1;
        }

        if (opcion.equals("N")){ //Imprime mensaje en caso de que no quiera jugar
            System.out.println("¡Sin problema!¡Hasta la próxima!");
        }
    }
}