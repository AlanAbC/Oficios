package model;

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
            String query = "SELECT * FROM departamentos";
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
            String query = "INSERT INTO Oficios VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setDate(1, Date.valueOf(oficio.getFechaOficio()));
            sentencia.setDate(2, Date.valueOf(oficio.getFechaRegistro()));
            sentencia.setString(3, oficio.getDescripcion());
            sentencia.setString(4, oficio.getFolio());
            sentencia.setString(5, oficio.getObservaciones());
            sentencia.setInt(6, oficio.getDepartamento().getId());
            sentencia.setInt(7, oficio.getRemitente().getId());
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

    public String setDepartamento(Departamento departamento){
        try{
            String query = "INSERT INTO Departamentos VALUES (default, ?, ?)";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setString(1, departamento.getNombre());
            sentencia.setString(2, departamento.getResponsable());
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

    public String setRemitentes(Remitente remitente){
        try{
            String query = "INSERT INTO Remitentes VALUES (default, ?, ?)";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setString(1, remitente.getNombre());
            sentencia.setString(2, remitente.getResponsable());
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

    public String updateDepartamento(Departamento departamento){
        try{
            String query = "UPDATE Departamentos SET dep_nombre = ?, dep_responsable = ? WHERE dep_id = ?";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setString(1, departamento.getNombre());
            sentencia.setString(2, departamento.getResponsable());
            sentencia.setInt(3, departamento.getId());
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

    public String deleteDepartamento(int id){
        try{
            String query = "DELETE FROM Departamentos WHERE dep_id = ?";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setInt(1, id);
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

    public Departamento findByNameDepartamento(String nombre){
        try{
            String query = "SELECT * FROM departamentos WHERE dep_nombre = '" + nombre + "'";
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            Departamento departamento = new Departamento();
            while(con.registro.next()){
                departamento.setId(Integer.parseInt(con.registro.getString("dep_id")));
                departamento.setNombre(con.registro.getString("dep_nombre"));
                departamento.setResponsable(con.registro.getString("dep_responsable"));
            }
            return departamento;
        }catch(SQLException e){
            System.out.println("Ocurrió el error: " + e);
            Departamento departamento = new Departamento();
            return departamento;
        }
    }

    public Departamento findByIdDepartamento(int id){
        try{
            String query = "SELECT * FROM departamentos WHERE dep_id = " + id;
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            Departamento departamento = new Departamento();
            while(con.registro.next()){
                departamento.setId(Integer.parseInt(con.registro.getString("dep_id")));
                departamento.setNombre(con.registro.getString("dep_nombre"));
                departamento.setResponsable(con.registro.getString("dep_responsable"));
            }
            return departamento;
        }catch(SQLException e){
            System.out.println("Ocurrió el error: " + e);
            Departamento departamento = new Departamento();
            return departamento;
        }
    }

    public Remitente findByNameRemitente(String nombre){
        try{
            String query = "SELECT * FROM remitentes WHERE res_nombre = '" + nombre + "'";
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            Remitente remitente = new Remitente();
            while(con.registro.next()){
                remitente.setId(Integer.parseInt(con.registro.getString("res_id")));
                remitente.setNombre(con.registro.getString("res_despartamento"));
                remitente.setResponsable(con.registro.getString("res_responsable"));
            }
            return remitente;
        }catch(SQLException e){
            System.out.println("Ocurrió el error: " + e);
            Remitente remitente = new Remitente();
            return remitente;
        }
    }

    public Remitente findByIdRemitente(int id){
        try{
            String query = "SELECT * FROM remitentes WHERE res_id = " + id;
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            Remitente remitente = new Remitente();
            while(con.registro.next()){
                remitente.setId(Integer.parseInt(con.registro.getString("res_id")));
                remitente.setNombre(con.registro.getString("res_despartamento"));
                remitente.setResponsable(con.registro.getString("res_responsable"));
            }
            return remitente;
        }catch(SQLException e){
            System.out.println("Ocurrió el error: " + e);
            Remitente remitente = new Remitente();
            return remitente;
        }
    }

    public String updateRemitentes(Remitente remitente){
        try{
            String query = "UPDATE Remitentes SET res_despartamento = ?, res_responsable= ? WHERE res_id = ?";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setString(1, remitente.getNombre());
            sentencia.setString(2, remitente.getResponsable());
            sentencia.setInt(3, remitente.getId());
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

    public String deleteRemitente(int id){
        try{
            String query = "DELETE FROM Remitentes WHERE res_id = ?";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setInt(1, id);
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

    public ArrayList<Oficio> getOficios(){
        try{
            ArrayList<Oficio> oficios = new ArrayList<>();
            String query = "SELECT * FROM Oficios";
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            while(con.registro.next()){
                Oficio oficio = new Oficio();
                int idRem = con.registro.getInt("res_id");
                oficio.setFechaOficio(con.registro.getDate("of_fechaOficio").toLocalDate());
                oficio.setFechaRegistro(con.registro.getDate("of_fechaRegistro").toLocalDate());
                oficio.setDescripcion(con.registro.getString("of_descripcion"));
                oficio.setFolio(con.registro.getString("of_folio"));
                oficio.setObservaciones(con.registro.getString("of_observaciones"));
                oficio.setDepartamento(findByIdDepartamento(con.registro.getInt("dep_id")));
                oficio.setRemitente(findByIdRemitente(idRem));
                oficios.add(oficio);
            }
            return oficios;
        }catch(SQLException e){
            System.out.println("Ocurrió el error: " + e);
            ArrayList<Oficio> oficos = new ArrayList<>();
            return oficos;
        }
    }

    public String deleteOficio(String folio){
        try{
            String query = "DELETE FROM Oficios WHERE of_folio = ?";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setString(1, folio);
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

    public String updateOficio(Oficio oficio, String folioN){
        try{
            String query = "UPDATE Oficios SET of_fechaOficio = ?, of_descripcion = ?, " +
                    "of_observaciones = ?, dep_id = ?, res_id = ?, of_folio = ? WHERE of_folio = ?";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setDate(1, Date.valueOf(oficio.getFechaOficio()));
            sentencia.setString(2, oficio.getDescripcion());
            sentencia.setString(3, oficio.getObservaciones());
            sentencia.setInt(4, oficio.getDepartamento().getId());
            sentencia.setInt(5, oficio.getRemitente().getId());
            sentencia.setString(6, folioN);
            sentencia.setString(7, oficio.getFolio());
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
