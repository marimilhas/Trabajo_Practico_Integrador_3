package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ronda {
    private String nro;
    private HashMap<Integer, Partido> partidos;

    public Ronda(String numero, HashMap<Integer, Partido> partidos){
        this.nro = numero;
        this.partidos = partidos;
    }
    public List<Integer> calcular_puntaje_ronda(HashMap<String, List<Pronostico>> pronosticos, int puntos){
        List<Integer> puntajes_ronda = new ArrayList<>();
        int puntaje;

        for (Map.Entry<String, List<Pronostico>> entry : pronosticos.entrySet()){ //Recorre elementos HashMap
            String participante = entry.getKey();                                 //Elemento: nombre + lista pronósticos
            List<Pronostico> pronost_participante = entry.getValue();
            puntaje = 0;

            System.out.print("\nJUGADOR -> " + participante);
            System.out.println("\n");
            Funciones.pausar(800);

            for (Pronostico p : pronost_participante){
                Partido partido = partidos.get(p.getId_partido()); //Partido correspondiente al pronóstico (p)
                ResultadoEnum resultado_pronostico = p.getResultado();
                ResultadoEnum resultado_partido = Funciones.calcular_resultado_partido(partido);

                System.out.println(partido.getEquipo1().getNombre() + " vs " + partido.getEquipo2().getNombre());

                if (resultado_pronostico.equals(resultado_partido)){
                    puntaje += puntos;
                    Funciones.mostrar_mensaje(resultado_pronostico, partido);
                } else {
                    System.out.println("No has podido acertar...");
                }
            }

            puntaje = Funciones.calcular_puntaje(puntaje, puntos, partidos.size());
            puntajes_ronda.add(puntaje);

            Funciones.pausar(800);
            System.out.println("\nPUNTAJE POR PRONÓSTICOS ACERTADOS: " + puntaje);
            Funciones.pausar(800);

        }
        return puntajes_ronda;
    }
}