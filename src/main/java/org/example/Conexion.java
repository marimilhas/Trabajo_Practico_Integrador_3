package org.example;
import java.sql.*;

public class Conexion {
    private void cargar_clase() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    public void obtener_pronosticos(String nro_ronda){ //Debemos crear algun tipo de variable para almacenar los pronosticos y retornarla
        Statement stmt = null;
        ResultSet resultado = null;
        boolean no_existe_ronda = true;

        try{
            cargar_clase();
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net/basededatos2023",
                    "marianamilhas", "dbcursojava2023");
            stmt = con.createStatement();
            resultado = stmt.executeQuery(
                    "SELECT * FROM pronostico INNER JOIN partido ON pronostico.id_partido = " +
                    "partido.id_partido WHERE partido.ronda = " + nro_ronda);

            while (resultado.next()){
                no_existe_ronda = false;
                String equipo1 = resultado.getString("equipo1");
                String equipo2 = resultado.getString("equipo2");
                int goles1 = resultado.getInt("cant_goles1");
                int goles2 = resultado.getInt("cant_goles2");
                int gana1 = resultado.getInt("gana1");
                int gana2 = resultado.getInt("gana2");
                Funciones.crear_pronostico(equipo1, equipo2, goles1, goles2, gana1, gana2);
            }

            if (no_existe_ronda){
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
    }
}
