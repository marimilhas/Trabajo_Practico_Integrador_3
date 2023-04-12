package org.example;
public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    public int golesEquipo1;
    public int golesEquipo2;

    public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2){
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }
    public Partido(Equipo equipo1, Equipo equipo2){
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }
    public Equipo getEquipo1() {
        return equipo1;
    }
    public Equipo getEquipo2() {
        return equipo2;
    }
}
