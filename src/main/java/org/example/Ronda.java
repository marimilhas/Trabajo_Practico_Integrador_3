package org.example;
public class Ronda {
    //private String nro;
    private Partido[] partidos;

    public Ronda(Partido[] partidos){
        this.partidos = partidos;
    }
    public int ResultadoEnum(Pronostico[] pronosticos){
        int puntaje = 0;

        for (int i = 0; i < partidos.length; i++){
            String resultado_pronostico = pronosticos[i].resultado;
            String resultado_partido = Funciones.calcular_resultado_partido(partidos[i]);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Partido Nº" + (i + 1));

            if (resultado_pronostico.equals(resultado_partido)){
                puntaje += 1;
                if (resultado_pronostico.equals("GANA_EQUIPO1\n")){
                    System.out.println("Ganó el equipo 1, acertaste!\n");
                } else if (resultado_pronostico.equals("EMPATE\n")){
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
