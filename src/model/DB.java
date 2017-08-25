package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DB {

    private MySQL con;
    private Connection conexion;

    public DB() {
        con = new MySQL();
        conexion = con.MySQLConnect();
    }

    public ArrayList<Departamento> getDepartamentos() {
        try {
            ArrayList<Departamento> departamentos = new ArrayList<>();
            String query = "SELECT * FROM departamentos ORDER BY dep_nombre ASC";
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Departamento departamento = new Departamento();
                departamento.setId(Integer.parseInt(rs.getString("dep_id")));
                departamento.setNombre(rs.getString("dep_nombre"));
                departamento.setResponsable(rs.getString("dep_responsable"));
                departamentos.add(departamento);
            }
            rs.close();
            stm.close();
            return departamentos;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            return null;
        }
    }

    public ArrayList<Remitente> getRemitentes() {
        try {
            ArrayList<Remitente> remitentes = new ArrayList<>();
            String query = "SELECT * FROM Remitentes ORDER BY res_despartamento ASC";
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Remitente remitente = new Remitente();
                remitente.setId(Integer.parseInt(rs.getString("res_id")));
                remitente.setNombre(rs.getString("res_despartamento"));
                remitente.setResponsable(rs.getString("res_responsable"));
                remitentes.add(remitente);
            }
            rs.close();
            stm.close();
            return remitentes;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            ArrayList<Remitente> remitente = new ArrayList<>();
            return remitente;
        }
    }

    public String setOficio(Oficio oficio) {
        try {
            String query = "INSERT INTO Oficios VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement sentencia = conexion.prepareStatement(query);
            sentencia.setDate(1, Date.valueOf(oficio.getFechaOficio()));
            sentencia.setDate(2, Date.valueOf(oficio.getFechaRegistro()));
            sentencia.setString(3, oficio.getDescripcion());
            sentencia.setString(4, oficio.getFolio());
            sentencia.setString(5, oficio.getObservaciones());
            sentencia.setInt(6, oficio.getDepartamento().getId());
            sentencia.setInt(7, oficio.getRemitente().getId());
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
            PreparedStatement sentencia = conexion.prepareStatement(query);
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
            PreparedStatement sentencia = conexion.prepareStatement(query);
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
            PreparedStatement sentencia = conexion.prepareStatement(query);
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
            PreparedStatement sentencia = conexion.prepareStatement(query);
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
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
            Departamento departamento = new Departamento();
            while (rs.next()) {
                departamento.setId(Integer.parseInt(rs.getString("dep_id")));
                departamento.setNombre(rs.getString("dep_nombre"));
                departamento.setResponsable(rs.getString("dep_responsable"));
            }
            rs.close();
            stm.close();
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
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
            Departamento departamento = new Departamento();
            while (rs.next()) {
                departamento.setId(Integer.parseInt(rs.getString("dep_id")));
                departamento.setNombre(rs.getString("dep_nombre"));
                departamento.setResponsable(rs.getString("dep_responsable"));
            }
            rs.close();
            stm.close();
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
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
            Remitente remitente = new Remitente();
            while (rs.next()) {
                remitente.setId(Integer.parseInt(rs.getString("res_id")));
                remitente.setNombre(rs.getString("res_despartamento"));
                remitente.setResponsable(rs.getString("res_responsable"));
            }
            rs.close();
            stm.close();
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
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
            Remitente remitente = new Remitente();
            while (rs.next()) {
                remitente.setId(Integer.parseInt(rs.getString("res_id")));
                remitente.setNombre(rs.getString("res_despartamento"));
                remitente.setResponsable(rs.getString("res_responsable"));
            }
            rs.close();
            stm.close();
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
            PreparedStatement sentencia = conexion.prepareStatement(query);
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
            PreparedStatement sentencia = conexion.prepareStatement(query);
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
            String query = "SELECT * FROM Oficios ORDER BY of_fechaOficio DESC";
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Oficio oficio = new Oficio();
                String fechaOfi = rs.getString("of_fechaOficio");
                String fechaReg = rs.getString("of_fechaRegistro");
                String descOfi = rs.getString("of_descripcion");
                String folioOfi = rs.getString("of_folio");
                String obsOfi = rs.getString("of_observaciones");
                int idDep = rs.getInt("dep_id");
                int idRem = rs.getInt("res_id");
                oficio.setFechaOficio(LocalDate.parse(fechaOfi));
                oficio.setFechaRegistro(LocalDate.parse(fechaReg));
                oficio.setDescripcion(descOfi);
                oficio.setFolio(folioOfi);
                oficio.setObservaciones(obsOfi);
                oficio.setDepartamento(findByIdDepartamento(idDep));
                oficio.setRemitente(findByIdRemitente(idRem));
                oficios.add(oficio);
            }
            rs.close();
            stm.close();
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
            PreparedStatement sentencia = conexion.prepareStatement(query);
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
            PreparedStatement sentencia = conexion.prepareStatement(query);
            sentencia.setDate(1, Date.valueOf(oficio.getFechaOficio()));
            sentencia.setString(2, oficio.getDescripcion());
            sentencia.setString(3, oficio.getObservaciones());
            sentencia.setInt(4, oficio.getDepartamento().getId());
            sentencia.setInt(5, oficio.getRemitente().getId());
            sentencia.setString(6, folioN);
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

    public ArrayList<Estadistica> estadisticasDepInicial() {
        try {
            ArrayList<Estadistica> estDep = new ArrayList<>();
            String query = "SELECT Departamentos.dep_nombre as departamento, COUNT(*) AS total " +
                    "FROM Oficios " +
                    "INNER JOIN Departamentos ON Oficios.dep_id = Departamentos.dep_id " +
                    "WHERE Oficios.of_fechaOficio " +
                    "GROUP BY Oficios.dep_id";
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Estadistica estadistica = new Estadistica();
                estadistica.setDepartamento(rs.getString("departamento"));
                estadistica.setNo_folios(rs.getInt("total"));
                estDep.add(estadistica);
            }
            rs.close();
            stm.close();
            return estDep;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            ArrayList<Estadistica> estDep= new ArrayList<>();
            return estDep;
        }
    }

    public ArrayList<Estadistica> estadisticasRemInicial() {
        try {
            ArrayList<Estadistica> estRem = new ArrayList<>();
            String query = "SELECT Remitentes.res_despartamento as remitente, COUNT(*) AS total " +
                    "FROM Oficios " +
                    "INNER JOIN Remitentes ON Oficios.res_id = Remitentes.res_id " +
                    "WHERE Oficios.of_fechaOficio " +
                    "GROUP BY Oficios.res_id";
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Estadistica estadistica = new Estadistica();
                estadistica.setDepartamento(rs.getString("remitente"));
                estadistica.setNo_folios(rs.getInt("total"));
                estRem.add(estadistica);
            }
            rs.close();
            stm.close();
            return estRem;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            ArrayList<Estadistica> estRem = new ArrayList<>();
            return estRem;
        }
    }

    public int est_totalInicial(){
        try {
            int total = 0;
            String query = "SELECT COUNT(*) AS total " +
                    "FROM Oficios " +
                    "WHERE Oficios.of_fechaOficio";
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                total = rs.getInt("total");
            }
            rs.close();
            stm.close();
            return total;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            return 0;
        }
    }

    public ArrayList<Estadistica> estadisticasDepFiltro(LocalDate inicio, LocalDate fin) {
        try {
            ArrayList<Estadistica> estDep = new ArrayList<>();
            String query = "SELECT Departamentos.dep_nombre as departamento, COUNT(*) AS total " +
                    "FROM Oficios " +
                    "INNER JOIN Departamentos ON Oficios.dep_id = Departamentos.dep_id " +
                    "WHERE Oficios.of_fechaOficio BETWEEN '" + inicio.toString() + "' AND '" + fin.toString() + "' " +
                    "GROUP BY Oficios.dep_id";
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Estadistica estadistica = new Estadistica();
                estadistica.setDepartamento(rs.getString("departamento"));
                estadistica.setNo_folios(rs.getInt("total"));
                estDep.add(estadistica);
            }
            rs.close();
            stm.close();
            return estDep;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            ArrayList<Estadistica> estDep= new ArrayList<>();
            return estDep;
        }
    }

    public ArrayList<Estadistica> estadisticasRemFiltro(LocalDate inicio, LocalDate fin) {
        try {
            ArrayList<Estadistica> estRem = new ArrayList<>();
            String query = "SELECT Remitentes.res_despartamento as remitente, COUNT(*) AS total " +
                    "FROM Oficios " +
                    "INNER JOIN Remitentes ON Oficios.res_id = Remitentes.res_id " +
                    "WHERE Oficios.of_fechaOficio  BETWEEN '" + inicio.toString() + "' AND '" + fin.toString() + "' " +
                    "GROUP BY Oficios.res_id";
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Estadistica estadistica = new Estadistica();
                estadistica.setDepartamento(rs.getString("remitente"));
                estadistica.setNo_folios(rs.getInt("total"));
                estRem.add(estadistica);
            }
            rs.close();
            stm.close();
            return estRem;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            ArrayList<Estadistica> estRem = new ArrayList<>();
            return estRem;
        }
    }

    public int est_totalFiltro(LocalDate inicio, LocalDate fin){
        try {
            int total = 0;
            String query = "SELECT COUNT(*) AS total " +
                    "FROM Oficios " +
                    "WHERE Oficios.of_fechaOficio BETWEEN '" + inicio.toString() + "' AND '" + fin.toString() + "'";
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                total = rs.getInt("total");
            }
            rs.close();
            stm.close();
            return total;
        } catch (SQLException e) {
            System.out.println("Ocurrió el error: " + e);
            return 0;
        }
    }
}
