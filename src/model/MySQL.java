package model;

import java.sql.*;

public class MySQL {

    Connection conexion = null;
    Statement comando = null;
    ResultSet registro;

    public Connection MySQLConnect(){
        try{
            Class.forName("org.postgresql.Driver");
            String servidor = "jdbc:postgresql://localhost:5432/oficios";
            String user = "postgres";
            String pass = "";
            conexion = DriverManager.getConnection(servidor, user, pass);
        }catch (ClassNotFoundException ex) {
            System.out.println("Error en la conexión a la base de datos: " + ex.getMessage());
            conexion = null;
        } catch (SQLException ex) {
            System.out.println("Error en la conexión a la base de datos: " + ex.getMessage());
            conexion = null;
        } catch (Exception ex) {
            System.out.println("Error en la conexión a la base de datos: " + ex.getMessage());
            conexion = null;
        } finally {
            System.out.println("Conexión Exitosa");
            return conexion;
        }
    }
}
