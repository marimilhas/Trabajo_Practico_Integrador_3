package org.example;
public class Pronostico {
    private int id_partido; //OPCIÓN -> guardar id en vez de partido
    private ResultadoEnum resultado;

    public Pronostico(int id_partido, ResultadoEnum resultado){
        this.id_partido = id_partido;
        this.resultado = resultado;
    }
    public int getId_partido() {
        return id_partido;
    }
    public ResultadoEnum getResultado() {
        return resultado;
    }
    //private Partido partido;
    /*public Pronostico(Partido partido, ResultadoEnum resultado){
        this.partido = partido;
        this.resultado = resultado;
    }*/
}
