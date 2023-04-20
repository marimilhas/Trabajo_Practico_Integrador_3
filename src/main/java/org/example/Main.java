package org.example;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("PRONÓSTICOS DEPORTIVOS");
        String opcion = Funciones.validar_letra("¿Desea jugar una ronda? (S - N): ", "S", "N");
        int contador = 1;

        while (opcion.equals("S")){
            Conexion conector = new Conexion();
            String nro_ronda = String.valueOf(contador);

            List<Partido> partidos = conector.obtener_partidos(nro_ronda);
            if (partidos.size() == 0){
                System.exit(0);
            }

            Ronda ronda = new Ronda(nro_ronda, partidos);

            opcion = Funciones.validar_letra("¿Desea jugar otra ronda? (S - N): ", "S", "N");
            contador += 1;
        }

        /*if (opcion.equals("S")){
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

            int[] puntajes = ronda.calcular_puntaje_ronda(pronosticos, jugadores);
            Funciones.pausar(1000);
            System.out.println("\nPUNTAJES");
            for (int i = 0; i < puntajes.length; i++){
                System.out.println(jugadores.get(i) + ": " + puntajes[i]);
            }

        } else{
            System.out.println("Está bien, vuelve pronto!");
        }*/

    }
}