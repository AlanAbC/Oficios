package model;

import java.sql.Connection;

public class PostgreSQL {

    Connection con = null;

    public Connection conexion(){
        try{
            Class.forName("org.postgresql.Driver");
            String db = "jdbc:postgresql://localhost:5432/oficios";
            String usr = "postgres";
            String pass = "";
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println(con.toString());
        return con;
    }

}

