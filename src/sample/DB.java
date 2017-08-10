package sample;

import java.sql.*;
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

    public ArrayList<Remitente> getRemitentes(){
        try{
            ArrayList<Remitente> remitentes = new ArrayList<>();
            String query = "SELECT * FROM Remitentes";
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            while(con.registro.next()){
                Remitente remitente = new Remitente();
                remitente.setId(Integer.parseInt(con.registro.getString("res_id")));
                remitente.setNombre(con.registro.getString("res_despartamento"));
                remitente.setResponsable(con.registro.getString("res_responsable"));
                remitentes.add(remitente);
            }
            return remitentes;
        }catch(SQLException e){
            System.out.println("Ocurrió el error: " + e);
            ArrayList<Remitente> remitente = new ArrayList<>();
            return remitente;
        }
    }

    public String setOficio(Oficio oficio){
        try{
            String query = "INSERT INTO Oficios VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setDate(1, Date.valueOf(oficio.getFechaOficio()));
            sentencia.setDate(2, Date.valueOf(oficio.getFechaRegistro()));
            sentencia.setString(3, "");
            sentencia.setString(4, oficio.getDescripcion());
            sentencia.setString(5, oficio.getFolio());
            sentencia.setString(6, oficio.getObservaciones());
            sentencia.setString(7, "");
            sentencia.setInt(8, oficio.getDepartamento().getId());
            sentencia.setInt(9, oficio.getRemitente().getId());
            int columnasInsertadas = sentencia.executeUpdate();
            if(columnasInsertadas > 0){
                return "1";
            }else{
                return "Se insertaron " + columnasInsertadas + " registros";
            }
        }catch(SQLException e){
            return e.toString();
        }
    }

}
