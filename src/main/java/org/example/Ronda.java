package org.example;
import java.util.List;

public class Ronda {
    //private String nro;
    private Partido[] partidos;

    public Ronda(Partido[] partidos){
        this.partidos = partidos;
    }
    public int[] calcular_puntaje_ronda(Pronostico[][] pronosticos, List<String> jugadores){
        int[] puntajes = new int[jugadores.size()];
        int puntaje;

        for (int i = 0; i < jugadores.size(); i++){
            puntaje = 0;

            Funciones.pausar(1500);
            System.out.print("\nJUGADOR -> " + jugadores.get(i));
            System.out.println("\n");
            Funciones.pausar(1500);

            for (int j = 0; j < partidos.length; j++){
                ResultadoEnum resultado_pronostico = pronosticos[j][i].resultado;
                ResultadoEnum resultado_partido = Funciones.calcular_resultado_partido(partidos[j]);

                System.out.println("Partido Nº" + (j + 1));

                if (resultado_pronostico.equals(resultado_partido)){
                    puntaje += 1;
                    Funciones.mostrar_mensaje(resultado_pronostico, partidos[j]);
                } else{
                    System.out.println("No has podido acertar...");
                }
            }

            puntajes[i] = puntaje;
            Funciones.pausar(1500);
            System.out.println("\nCANTIDAD DE PRONÓSTICOS ACERTADOS: " + puntaje);

        }
        return puntajes;
    }
}