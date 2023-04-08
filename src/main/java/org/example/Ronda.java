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
            System.out.print("\nJUGADOR -> " + jugadores.get(i));
            for (int j = 0; j < partidos.length; j++){
                ResultadoEnum resultado_pronostico = pronosticos[j][i].resultado;
                ResultadoEnum resultado_partido = Funciones.calcular_resultado_partido(partidos[j]);

                Funciones.pausar();
                System.out.println("\nPartido Nº" + (i + 1));

                if (resultado_pronostico.equals(resultado_partido)){
                    puntaje += 1;
                    if (resultado_pronostico.equals(ResultadoEnum.GANA_EQUIPO1)){
                        System.out.println("Ganó el equipo 1, acertaste!");
                    } else if (resultado_pronostico.equals(ResultadoEnum.EMPATE)){
                        System.out.println("Empataron, acertaste!");
                    } else{
                        System.out.println("Ganó el equipo 2, acertaste!");
                    }
                } else{
                    System.out.println("No has podido acertar...");
                }
            }
            puntajes[i] = puntaje;
            System.out.println("\nPUNTAJE TOTAL: " + puntaje);
        }
        return puntajes;
    }
}