package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Conexion {
    private void cargar_clase() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    public List<Partido> obtener_partidos(String nro_ronda){
        Statement stmt = null;
        ResultSet resultado = null;
        List<Partido> partidos = new ArrayList<>();

        try{
            cargar_clase();
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/dbprueba2023",
                    "userdb2023", "pass2023");
            stmt = con.createStatement();
            resultado = stmt.executeQuery("SELECT * FROM partido WHERE ronda = " + nro_ronda);

            if (resultado.next()){
                System.out.println("Cargando partidos...");
                while (resultado.next()){
                    String equipo1 = resultado.getString("equipo1");
                    int golesEquipo1 = resultado.getInt("cant_goles1");
                    String equipo2 = resultado.getString("equipo2");
                    int golesEquipo2 = resultado.getInt("cant_goles2");
                    partidos.add(Funciones.crear_partido(equipo1, golesEquipo1, equipo2, golesEquipo2));
                }
            } else {
                System.out.println("Ya no hay más rondas disponibles, gracias por jugar!");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (SQLException e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
        }
        return partidos;
    }
    public void obtener_pronosticos(){
        Statement stmt = null;
        ResultSet resultado = null;
        try{
            cargar_clase();
            System.out.println("Cargando partidos...");
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/dbprueba2023",
                    "userdb2023", "pass2023");
            stmt = con.createStatement();
            resultado = stmt.executeQuery("SELECT * FROM partidos");

            while (resultado.next()){
                String equipo1 = resultado.getString("equipo1");
                String equipo2 = resultado.getString("equipo2");
                int gana1 = resultado.getInt("gana1");
                int gana2 = resultado.getInt("gana2");
                Funciones.crear_pronostico(equipo1, equipo2, gana1, gana2);
                //no está terminado
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (SQLException e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
        }
    }
}
