package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DB {

    private MySQL con;

    public DB() {
        con = new MySQL();
        con.MySQLConnect();
    }

    public ArrayList<Departamento> getDepartamentos() {
        try {
            ArrayList<Departamento> departamentos = new ArrayList<>();
            String query = "SELECT * FROM departamentos";
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            while (con.registro.next()) {
                Departamento departamento = new Departamento();
                departamento.setId(Integer.parseInt(con.registro.getString("dep_id")));
                departamento.setNombre(con.registro.getString("dep_nombre"));
                departamento.setResponsable(con.registro.getString("dep_responsable"));
                departamentos.add(departamento);
            }
            return departamentos;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            ArrayList<Departamento> departamentos = new ArrayList<>();
            return departamentos;
        }
    }

    public ArrayList<Remitente> getRemitentes() {
        try {
            ArrayList<Remitente> remitentes = new ArrayList<>();
            String query = "SELECT * FROM Remitentes";
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            while (con.registro.next()) {
                Remitente remitente = new Remitente();
                remitente.setId(Integer.parseInt(con.registro.getString("res_id")));
                remitente.setNombre(con.registro.getString("res_despartamento"));
                remitente.setResponsable(con.registro.getString("res_responsable"));
                remitentes.add(remitente);
            }
            return remitentes;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            ArrayList<Remitente> remitente = new ArrayList<>();
            return remitente;
        }
    }

    public String setOficio(Oficio oficio) {
        try {
            String query = "INSERT INTO Oficios VALUES (?, ?, ?, ?, ?, ?, NULL , ?, ?)";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setDate(1, Date.valueOf(oficio.getFechaOficio()));
            sentencia.setDate(2, Date.valueOf(oficio.getFechaRegistro()));
            sentencia.setString(3, oficio.getDepartamento().getNombre());
            sentencia.setString(4, oficio.getDescripcion());
            sentencia.setString(5, oficio.getFolio());
            sentencia.setString(6, oficio.getObservaciones());
            sentencia.setInt(7, oficio.getDepartamento().getId());
            sentencia.setInt(8, oficio.getRemitente().getId());
            int columnasInsertadas = sentencia.executeUpdate();
            if (columnasInsertadas > 0) {
                return "1";
            } else {
                return "Se insertaron " + columnasInsertadas + " registros";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }

    public String setDepartamento(Departamento departamento) {
        try {
            String query = "INSERT INTO Departamentos VALUES (default, ?, ?)";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setString(1, departamento.getNombre());
            sentencia.setString(2, departamento.getResponsable());
            int columnasInsertadas = sentencia.executeUpdate();
            if (columnasInsertadas > 0) {
                return "1";
            } else {
                return "Se insertaron " + columnasInsertadas + " registros";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }

    public String setRemitentes(Remitente remitente) {
        try {
            String query = "INSERT INTO Remitentes VALUES (default, ?, ?)";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setString(1, remitente.getNombre());
            sentencia.setString(2, remitente.getResponsable());
            int columnasInsertadas = sentencia.executeUpdate();
            if (columnasInsertadas > 0) {
                return "1";
            } else {
                return "Se insertaron " + columnasInsertadas + " registros";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }

    public String updateDepartamento(Departamento departamento) {
        try {
            String query = "UPDATE Departamentos SET dep_nombre = ?, dep_responsable = ? WHERE dep_id = ?";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setString(1, departamento.getNombre());
            sentencia.setString(2, departamento.getResponsable());
            sentencia.setInt(3, departamento.getId());
            int columnasInsertadas = sentencia.executeUpdate();
            if (columnasInsertadas > 0) {
                return "1";
            } else {
                return "Se insertaron " + columnasInsertadas + " registros";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }

    public String deleteDepartamento(int id) {
        try {
            String query = "DELETE FROM Departamentos WHERE dep_id = ?";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setInt(1, id);
            int columnasInsertadas = sentencia.executeUpdate();
            if (columnasInsertadas > 0) {
                return "1";
            } else {
                return "Se insertaron " + columnasInsertadas + " registros";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }

    public Departamento findByNameDepartamento(String nombre) {
        try {
            String query = "SELECT * FROM departamentos WHERE dep_nombre = '" + nombre + "'";
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            Departamento departamento = new Departamento();
            while (con.registro.next()) {
                departamento.setId(Integer.parseInt(con.registro.getString("dep_id")));
                departamento.setNombre(con.registro.getString("dep_nombre"));
                departamento.setResponsable(con.registro.getString("dep_responsable"));
            }
            return departamento;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            Departamento departamento = new Departamento();
            return departamento;
        }
    }

    public Departamento findByIdDepartamento(int id) {
        try {
            String query = "SELECT * FROM departamentos WHERE dep_id = " + id;
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            Departamento departamento = new Departamento();
            while (con.registro.next()) {
                departamento.setId(Integer.parseInt(con.registro.getString("dep_id")));
                departamento.setNombre(con.registro.getString("dep_nombre"));
                departamento.setResponsable(con.registro.getString("dep_responsable"));
            }
            return departamento;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            Departamento departamento = new Departamento();
            return departamento;
        }
    }

    public Remitente findByNameRemitente(String nombre) {
        try {
            String query = "SELECT * FROM remitentes WHERE res_nombre = '" + nombre + "'";
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            Remitente remitente = new Remitente();
            while (con.registro.next()) {
                remitente.setId(Integer.parseInt(con.registro.getString("res_id")));
                remitente.setNombre(con.registro.getString("res_despartamento"));
                remitente.setResponsable(con.registro.getString("res_responsable"));
            }
            return remitente;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            Remitente remitente = new Remitente();
            return remitente;
        }
    }

    public Remitente findByIdRemitente(int id) {
        try {
            String query = "SELECT * FROM remitentes WHERE res_id = " + id;
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            Remitente remitente = new Remitente();
            while (con.registro.next()) {
                remitente.setId(Integer.parseInt(con.registro.getString("res_id")));
                remitente.setNombre(con.registro.getString("res_despartamento"));
                remitente.setResponsable(con.registro.getString("res_responsable"));
            }
            return remitente;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            Remitente remitente = new Remitente();
            return remitente;
        }
    }

    public String updateRemitentes(Remitente remitente) {
        try {
            String query = "UPDATE Remitentes SET res_despartamento = ?, res_responsable= ? WHERE res_id = ?";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setString(1, remitente.getNombre());
            sentencia.setString(2, remitente.getResponsable());
            sentencia.setInt(3, remitente.getId());
            int columnasInsertadas = sentencia.executeUpdate();
            if (columnasInsertadas > 0) {
                return "1";
            } else {
                return "Se insertaron " + columnasInsertadas + " registros";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }

    public String deleteRemitente(int id) {
        try {
            String query = "DELETE FROM Remitentes WHERE res_id = ?";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setInt(1, id);
            int columnasInsertadas = sentencia.executeUpdate();
            if (columnasInsertadas > 0) {
                return "1";
            } else {
                return "Se insertaron " + columnasInsertadas + " registros";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }

    public ArrayList<Oficio> getOficios() {
        try {
            ArrayList<Oficio> oficios = new ArrayList<Oficio>();
            String query = "SELECT * FROM Oficios";
            con.comando.close();
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            con.registro.next();
            while(con.registro.next()){
                Oficio oficio = new Oficio();
                String fechaOfi = con.registro.getString(1);
                String fechaReg = con.registro.getString(2);
                String descOfi = con.registro.getString(3);
                String folioOfi = con.registro.getString(4);
                String obsOfi = con.registro.getString(5);
                int idRem = con.registro.getInt(6);
                int idDep = con.registro.getInt(7);
                oficio.setFechaOficio(LocalDate.parse(fechaOfi));
                oficio.setFechaRegistro(LocalDate.parse(fechaReg));
                oficio.setDescripcion(descOfi);
                oficio.setFolio(folioOfi);
                oficio.setObservaciones(obsOfi);
                oficio.setDepartamento(findByIdDepartamento(idDep));
                oficio.setRemitente(findByIdRemitente(idRem));
                oficios.add(oficio);
            }
            return oficios;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            ArrayList<Oficio> oficos = new ArrayList<>();
            return oficos;
        }
    }

    public String deleteOficio(String folio) {
        try {
            String query = "DELETE FROM Oficios WHERE of_folio = ?";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setString(1, folio);
            int columnasInsertadas = sentencia.executeUpdate();
            if (columnasInsertadas > 0) {
                return "1";
            } else {
                return "Se insertaron " + columnasInsertadas + " registros";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }

    public String updateOficio(Oficio oficio, String folioN) {
        try {
            String query = "UPDATE Oficios SET of_fechaOficio = ?, of_descripcion = ?, " +
                    "of_observaciones = ?, dep_id = ?, res_id = ?, of_folio = ? WHERE of_folio = ?";
            PreparedStatement sentencia = con.conexion.prepareStatement(query);
            sentencia.setDate(1, Date.valueOf(oficio.getFechaOficio()));
            sentencia.setString(2, oficio.getDescripcion());
            sentencia.setString(3, oficio.getObservaciones());
            sentencia.setInt(4, oficio.getDepartamento().getId());
            sentencia.setInt(5, oficio.getRemitente().getId());
            sentencia.setString(6, oficio.getFolio());
            sentencia.setString(7, oficio.getFolio());
            int columnasInsertadas = sentencia.executeUpdate();
            if (columnasInsertadas > 0) {
                return "1";
            } else {
                return "Se insertaron " + columnasInsertadas + " registros";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }

    public ArrayList<Estadistica> estadisticasDep() {
        try {
            ArrayList<Estadistica> estDep = new ArrayList<>();
            String query = "SELECT of_departamento AS DEPARTAMENTO, COUNT(*) AS '#FOLIOS' FROM oficios GROUP  BY dep_id";
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            while (con.registro.next()) {
                Estadistica estadistica = new Estadistica();
                estadistica.setDepartamento(con.registro.getString("DEPARTAMENTO"));
                estadistica.setNo_folios(con.registro.getInt("#FOLIOS"));
                estDep.add(estadistica);
            }
            return estDep;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            ArrayList<Estadistica> estDep= new ArrayList<>();
            return estDep;
        }
    }

    public ArrayList<Estadistica> estadisticasRem() {
        try {
            ArrayList<Estadistica> estRem = new ArrayList<>();
            String query = "SELECT r.res_despartamento AS REMITENTE, COUNT(*) AS '#FOLIOS' " +
                    "FROM remitentes r, oficios o WHERE o.res_id = r.res_id GROUP BY o.res_id";
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            while (con.registro.next()) {
                Estadistica estadistica = new Estadistica();
                estadistica.setDepartamento(con.registro.getString("REMITENTE"));
                estadistica.setNo_folios(con.registro.getInt("#FOLIOS"));
                estRem.add(estadistica);
            }
            return estRem;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            ArrayList<Estadistica> estRem = new ArrayList<>();
            return estRem;
        }
    }

    public int est_total(){
        try {
            int total = 0;
            String query = "SELECT COUNT(*) AS #FOLIOS FROM oficios ";
            con.comando = con.conexion.createStatement();
            con.registro = con.comando.executeQuery(query);
            while (con.registro.next()) {
                total = con.registro.getInt("#FOLIOS");
            }
            return total;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            return 0;
        }
    }
}
