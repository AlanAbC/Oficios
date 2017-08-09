package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    Connection conexion = null;

    public Connection MySQLConnect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost:3306/oficios";
            String user = "root";
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
