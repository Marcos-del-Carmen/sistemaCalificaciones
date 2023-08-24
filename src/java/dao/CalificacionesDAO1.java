package dao;

import conexion.Conexion;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class CalificacionesDAO1 {
    private HttpServletRequest request;
    private ServletResponse response;

    public ResultSet obtenerCalificaciones(String materia, String parcial) throws SQLException, NamingException {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
          // Obtener la conexión a la base de datos
          conn = Conexion.getConexion();

          // Recuperar calificaciones para la materia y el parcial seleccionados
          String sql = "SELECT MatriculaAlumno, " + parcial + " FROM calificaciones WHERE ClaveMateria = ?";
          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, materia);
          rs = pstmt.executeQuery();

          return rs;
      } catch (SQLException e) {
          // Manejar excepciones si es necesario
          throw e;
      } finally {
          // Cerrar conexión y recursos


      }
    }

    public ResultSet obtenerAlumnosConParcial(String materiaSeleccionada, String parcialSeleccionado) throws SQLException, NamingException {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
          // Obtener la conexión a la base de datos
          conn = Conexion.getConexion();

          // Consulta SQL para obtener calificaciones
          String sql = "SELECT c.MatriculaAlumno, CONCAT(a.Nombre, ' ', a.Paterno, ' ', a.Materno) AS NombreAlumno, m.Nombre AS Nombre, " + parcialSeleccionado + " FROM calificaciones c INNER JOIN alumnos a ON c.MatriculaAlumno = a.Matricula INNER JOIN materias m ON c.ClaveMateria = m.ClaveMateria WHERE c.ClaveMateria = ?";


          // Preparar la consulta
          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, materiaSeleccionada);

          // Ejecutar la consulta y obtener el ResultSet
          rs = pstmt.executeQuery();

          // Devolver el ResultSet para su procesamiento
          return rs;
      } catch (SQLException e) {
          // Manejar excepciones
          e.printStackTrace();
          throw e;
      } finally {

      }
    }

    public String obtenerNombreMateria(String claveMateria) throws SQLException, NamingException {
      String nombreMateria = null;

      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
          conn = Conexion.getConexion(); // Cambia esto según cómo obtengas tu conexión a la base de datos
          String query = "SELECT Nombre FROM materias WHERE ClaveMateria = ?";
          pstmt = conn.prepareStatement(query);
          pstmt.setString(1, claveMateria);

          rs = pstmt.executeQuery();

          if (rs.next()) {
              nombreMateria = rs.getString("Nombre");
          }
      } catch (SQLException e) {
          e.printStackTrace();
          throw e;
      } finally {
          // Cierra los recursos en orden inverso a su apertura

          if (conn != null) {
              conn.close();
          }
      }

      return nombreMateria;
    }

    public void actualizarCalificacion(String matricula, String parcial, double nuevaCalificacion) throws SQLException, NamingException {
      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
          // Validar parámetros
          if (matricula == null || parcial == null || matricula.isEmpty() || parcial.isEmpty() || nuevaCalificacion < 0) {
              throw new IllegalArgumentException("Los parámetros no son válidos");
          }

          // Obtener la conexión a la base de datos
          conn = Conexion.getConexion();

          // Consulta SQL para actualizar la calificación
          //UPDATE calificaciones SET Parcial1=9  WHERE MatriculaAlumno = 57221900130;
          String sql = "UPDATE calificaciones SET " + parcial + " = ?  WHERE MatriculaAlumno = ?";
          pstmt = conn.prepareStatement(sql);
          pstmt.setDouble(1, nuevaCalificacion);
          pstmt.setString(2, matricula);

          // Ejecutar la consulta para actualizar la calificación
          pstmt.executeUpdate();
      } catch (SQLException e) {
          // Manejar excepciones si es necesario
          throw e;
      } finally {
          // Cerrar recursos
          if (pstmt != null) {
              pstmt.close();
          }
          if (conn != null) {
              conn.close();
          }
      }
    }
    
    
}






