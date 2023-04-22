package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Conexion {
    private void cargar_clase() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    public HashMap<Integer, Partido> obtener_partidos(String nro_ronda) {
        HashMap<Integer, Partido> partidos = new HashMap<>();
        Statement stmt = null;
        ResultSet resultado = null;

        try {
            cargar_clase();
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/dbprueba2023",
                    "userdb2023", "pass2023");
            stmt = con.createStatement();
            resultado = stmt.executeQuery("SELECT * FROM partido WHERE ronda = " + nro_ronda);

            while (resultado.next()) {
                String equipo1 = resultado.getString("equipo1");
                String equipo2 = resultado.getString("equipo2");
                int id = resultado.getInt("id_partido");
                int goles1 = resultado.getInt("cant_goles1");
                int goles2 = resultado.getInt("cant_goles2");
                partidos.put(id, Funciones.crear_partido(equipo1, equipo2, goles1, goles2));
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
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
    public HashMap<String, List<Pronostico>> obtener_pronosticos(String nro_ronda) {
        HashMap<String, List<Pronostico>> pronosticos = new HashMap<>();
        Statement stmt = null;
        ResultSet resultado = null;

        try {
            cargar_clase();
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/dbprueba2023",
                    "userdb2023", "pass2023");
            stmt = con.createStatement();
            resultado = stmt.executeQuery(
                    "SELECT * FROM pronostico INNER JOIN partido ON pronostico.id_partido = " +
                            "partido.id_partido WHERE partido.ronda = " + nro_ronda);

            while (resultado.next()) {
                String participante = resultado.getString("participante");
                int gana1 = resultado.getInt("gana1");
                int gana2 = resultado.getInt("gana2");
                int id_partido = resultado.getInt("id_partido"); //OPCIÓN -> guardar id en vez de partido

                if (!pronosticos.containsKey(participante)) {
                    pronosticos.put(participante, new ArrayList<>());
                }

                List<Pronostico> pronost_participante = pronosticos.get(participante);
                pronost_participante.add(new Pronostico(id_partido, Funciones.calcular_resultado_pronostico(gana1, gana2)));
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
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
        return pronosticos;
    }
    public int obtener_cantidad_partidos_de_ronda(String nro_ronda) throws SQLException {
        int cantidad = 0;
        Statement stmt = null;
        ResultSet partidos = null;

        try {
            cargar_clase();
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/dbprueba2023",
                    "userdb2023", "pass2023");
            stmt = con.createStatement();
            partidos = stmt.executeQuery("SELECT COUNT(*) AS cantidad FROM partido where ronda =" + nro_ronda);

            while (partidos.next()) {
                cantidad = partidos.getInt("cantidad");
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            if (partidos != null) {
                try {
                    partidos.close();
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

        return cantidad;
    }
}
