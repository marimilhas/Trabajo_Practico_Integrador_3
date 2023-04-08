package org.example;
public class Pronostico {
    private Partido partido;
    public String resultado;

    public Pronostico(Partido partido, String resultado){
        this.partido = partido;
        this.resultado = resultado;
    }
}
