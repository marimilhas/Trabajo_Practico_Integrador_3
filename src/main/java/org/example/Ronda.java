package org.example;
public class Ronda {
    //private String nro;
    private Partido[] partidos;

    public Ronda(Partido[] partidos){
        this.partidos = partidos;
    }
    public int calcular_puntaje_ronda(Pronostico[] pronosticos){
        int puntaje = 0;

        for (int i = 0; i < partidos.length; i++){
            ResultadoEnum resultado_pronostico = pronosticos[i].resultado;
            ResultadoEnum resultado_partido = Funciones.calcular_resultado_partido(partidos[i]);

            Funciones.pausar();
            System.out.println("Partido Nº" + (i + 1));

            if (resultado_pronostico.equals(resultado_partido)){
                puntaje += 1;
                if (resultado_pronostico.equals(ResultadoEnum.GANA_EQUIPO1)){
                    System.out.println("Ganó el equipo 1, acertaste!\n");
                } else if (resultado_pronostico.equals(ResultadoEnum.EMPATE)){
                    System.out.println("Empataron, acertaste!\n");
                } else{
                    System.out.println("Ganó el equipo 2, acertaste!\n");
                }
            } else{
                System.out.println("No has podido acertar...\n");
            }
        }
        return puntaje;
    }
}