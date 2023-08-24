package dao;

import beans.Materias;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriasDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private Materias materias;
    
    public List<Materias> mostrar() throws ClassNotFoundException 
    {
        String sql = "SELECT * FROM materias";
        List<Materias> materia = new ArrayList();
        
        try {
            this.conn = Conexion.getConexion();
            this.ps = this.conn.prepareStatement(sql);
            this.rs = this.ps.executeQuery();
            
            while(this.rs.next()){
                String claveMat     = this.rs.getString("ClaveMateria");
                String nombre       = this.rs.getString("Nombre");
                String cuatrimestre = this.rs.getString("Cuatrimestre");
                
                this.materias = new Materias(claveMat, nombre, cuatrimestre);
                materia.add(this.materias);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                this.conn.close();
                this.ps.close();
                this.rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return materia;
    }
    
    public boolean agregar (Materias materia) throws ClassNotFoundException  
    {
        String sql = "INSERT INTO materias VALUES('"  +
                      materia.getClaveMateria()  + "',"  + 
                "'" + materia.getNombre()     + "',"  +
                "'" + materia.getCuatrimestre() +"')";
        // System.out.println("Antes de ejecutar la consulta");    
        try
        {
            conn=Conexion.getConexion();
            ps=conn.prepareStatement(sql);
            ps.executeUpdate();
            // System.out.println("Consulta ejecutada correctamente");
            ps.close();
            conn.close();
        } catch (SQLException ex) { 
            ex.printStackTrace(System.out);
        } 
   
        return true;          
    }
    
    public boolean editar(Materias materia, String old) throws ClassNotFoundException
    {
        String sql = "UPDATE materias SET " +
            " ClaveMateria      = '" + materia.getClaveMateria()      + "',"  +
            " Nombre            = '" + materia.getNombre()            + "'," +
            " Cuatrimestre      = '" + materia.getCuatrimestre()      + "'" +
            " WHERE ClaveMateria = '" + old + "'";
        
        //System.out.println("Antes de la consulta");
        System.out.println("Clave de Materia en dao: " + old); // Imprimir la clave de materia
        //System.out.println("Datos a editar: " + materia.getClaveMateria()+ " " + materia.getNombre() + " " + materia.getCuatrimestre());
        try
        {
           conn=Conexion.getConexion();
           ps=conn.prepareStatement(sql);
           ps.executeUpdate();
           ps.close();
           conn.close();

        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
     return true;
    }
     
    public boolean eliminar(String claveMateria) throws ClassNotFoundException
    {
        String sql= "DELETE FROM materias WHERE ClaveMateria ='" + claveMateria + "'";
        
        // System.out.println("Antes de la consulta");
        //System.out.println("Clave de Materia a eliminar: " + claveMateria); // Imprimir la clave de materia

        try
        { 
            conn=Conexion.getConexion();
            ps=conn.prepareStatement(sql);
            ps.executeUpdate();
            
            // System.out.println("Se ejecuta la consulta para eliminar una materia");
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return true;
    }
    
    public Materias buscar(String claveMateria) throws ClassNotFoundException
    {
        String sql="SELECT * FROM materias WHERE ClaveMateria = '" + claveMateria + "'";
        try {
            conn=Conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) 
            {
                materias = new Materias();
                materias.setClaveMateria(rs.getString("ClaveMateria"));
                materias.setNombre(rs.getString("Nombre"));
                materias.setCuatrimestre(rs.getString("Cuatrimestre"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return materias;
    }
}
