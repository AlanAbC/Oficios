package sample;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DB {

    private MySQL con;

    public DB(){
        con = new MySQL();
        con.MySQLConnect();
    }

    public ArrayList<Departamento> getDepartamentos(){
        try{
            ArrayList<Departamento> departamentos = new ArrayList<>();
            String query = "SELECT * FROM Departamentos";
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            while(con.registro.next()){
                Departamento departamento = new Departamento();
                departamento.setId(Integer.parseInt(con.registro.getString("dep_id")));
                departamento.setNombre(con.registro.getString("dep_nombre"));
                departamento.setResponsable(con.registro.getString("dep_responsable"));
                departamentos.add(departamento);
            }
            return departamentos;
        }catch(SQLException e){
            System.out.println("Ocurrió el error: " + e);
            ArrayList<Departamento> departamentos = new ArrayList<>();
            return departamentos;
        }
    }



}
