package org.example;
public class Pronostico {
    private Partido partido;
    public ResultadoEnum resultado;

    public Pronostico(Partido partido, ResultadoEnum resultado){
        this.partido = partido;
        this.resultado = resultado;
    }
}
