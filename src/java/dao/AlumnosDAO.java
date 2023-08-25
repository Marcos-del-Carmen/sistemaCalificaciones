package dao;

import beans.Alumnos;
import conexion.Conexion;
import java.util.*;
import java.sql.*;

public class AlumnosDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private Alumnos alumnos;
    
    public List<Alumnos> mostrar() throws ClassNotFoundException 
    {
        String sql = "SELECT * FROM alumnos";
        List<Alumnos> alumnos = new ArrayList();
        
        try {
            this.conn = Conexion.getConexion();
            this.ps = this.conn.prepareStatement(sql);
            this.rs = this.ps.executeQuery();
            
            while(this.rs.next()){
                String matricula    = this.rs.getString("Matricula");
                String nombre       = this.rs.getString("Nombre");
                String paterno      = this.rs.getString("Paterno");
                String materno      = this.rs.getString("Materno");
                String sexo         = this.rs.getString("Sexo");
                String fechaNac     = this.rs.getString("FechaNac");
                String telefono     = this.rs.getString("Telefono");
                String direccion    = this.rs.getString("Direccion");
                String correo       = this.rs.getString("Correo");
                
                this.alumnos = new Alumnos(matricula, nombre, paterno, materno, sexo, fechaNac, telefono, direccion, correo);
                // this.alumnos = new Alumnos(matricula, nombre, paterno, materno, sexo, fechaNac, telefono, direccion, correo);
                alumnos.add(this.alumnos);
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
        return alumnos;
    }
    
//    public boolean agregar (Alumnos alumno) throws ClassNotFoundException  
//    {
//        String sql = "INSERT INTO alumnos "
//                + "VALUES"+ 
//                "('"  + alumno.getMatricula()   + "',"  + 
//                "'"   + alumno.getNombre()      + "',"  +
//                "'"   + alumno.getPaterno()     + "',"  +
//                "'"   + alumno.getMaterno()     + "',"  +
//                "'"   + alumno.getSexo()        + "',"  +
//                "'"   + alumno.getFechaNac()    + "',"  +
//                "'"   + alumno.getTelefono()    + "',"  +
//                "'"   + alumno.getDireccion()   + "',"  +
//                "'"   + alumno.getCorreo()  + "')";
//        
//        // System.out.println("Antes de ejecutar la consulta");    
//        try
//        {
//            conn=Conexion.getConexion();
//            ps=conn.prepareStatement(sql);
//            ps.executeUpdate();
//            // System.out.println("Consulta ejecutada correctamente");
//            ps.close();
//            conn.close();
//        } catch (SQLException ex) { 
//            ex.printStackTrace(System.out);
//        } 
//   
//        return true;          
//    }
//    
    
    public boolean agregar(Alumnos alumno) throws ClassNotFoundException {
    String sql = "INSERT INTO alumnos "
            + "VALUES"
            + "('" + alumno.getMatricula() + "',"
            + "'" + alumno.getNombre() + "',"
            + "'" + alumno.getPaterno() + "',"
            + "'" + alumno.getMaterno() + "',"
            + "'" + alumno.getSexo() + "',"
            + "'" + alumno.getFechaNac() + "',"
            + "'" + alumno.getTelefono() + "',"
            + "'" + alumno.getDireccion() + "',"
            + "'" + alumno.getCorreo() + "')";

    // Agregar el código para insertar el alumno en la tabla "alumnos" como lo estás haciendo actualmente

    try {
        conn = Conexion.getConexion();
        ps = conn.prepareStatement(sql);
        ps.executeUpdate();

        // Después de insertar al alumno en la tabla "alumnos", inserta automáticamente todas las materias en "calificaciones" con calificaciones nulas para el nuevo alumno
        String insertMateriasSQL = "INSERT INTO calificaciones (MatriculaAlumno, ClaveMateria, Parcial1, Parcial2, Parcial3, Extraordinario) " +
                           "SELECT ?, ClaveMateria, 0.0, 0.0, 0.0, 0.0 FROM materias";

        PreparedStatement psInsertMaterias = conn.prepareStatement(insertMateriasSQL);
        psInsertMaterias.setString(1, alumno.getMatricula()); // Establece la matrícula del nuevo alumno
        psInsertMaterias.executeUpdate();
        
        psInsertMaterias.close();
        ps.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    }

    return true;
}

    public boolean editar(Alumnos alumno, String old) throws ClassNotFoundException
    {
        String sql = "UPDATE alumnos SET " +
            " Matricula             = '" + alumno.getMatricula()            + "'," +
            " Nombre                = '" + alumno.getNombre()               + "'," +
            " Paterno               = '" + alumno.getPaterno()              + "'," +
            " Materno               = '" + alumno.getMaterno()              + "'," +
            " Sexo                  = '" + alumno.getSexo()                 + "'," +
            " FechaNac              = '" + alumno.getFechaNac()             + "'," +
            " Telefono              = '" + alumno.getTelefono()             + "'," +
            " Direccion             = '" + alumno.getDireccion()            + "'," +
            " Correo                = '" + alumno.getCorreo()               + "'" +
            " WHERE Matricula = '" + old + "'";
        
        System.out.println("Clave matricula a editar: " + old); // Imprimir la clave de materia
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
     
    public boolean eliminar(String matricula) throws ClassNotFoundException
    {
        String sql= "DELETE FROM alumnos WHERE Matricula ='" + matricula + "'";
        
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
    
    public Alumnos buscar(String matricula) throws ClassNotFoundException
    {
        String sql="SELECT * FROM Alumnos WHERE Matricula = '" + matricula + "'";
        try {
            conn=Conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) 
            {
                alumnos = new Alumnos();
                alumnos.setMatricula(rs.getString("Matricula"));
                alumnos.setNombre(rs.getString("Nombre"));
                alumnos.setPaterno(rs.getString("Paterno"));
                alumnos.setMaterno(rs.getString("Materno"));
                alumnos.setSexo(rs.getString("Sexo"));
                alumnos.setFechaNac(rs.getString("FechaNac"));
                alumnos.setTelefono(rs.getString("Telefono"));
                alumnos.setDireccion(rs.getString("Direccion"));
                alumnos.setCorreo(rs.getString("Correo"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return alumnos;
    }
}
