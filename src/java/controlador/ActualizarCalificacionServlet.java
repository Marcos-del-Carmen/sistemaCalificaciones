package controlador;

import conexion.Conexion;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;

@WebServlet("/actualizarCalificacion")
public class ActualizarCalificacionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtén los parámetros del formulario
        String matricula = request.getParameter("matricula");
        double nuevaCalificacion = Double.parseDouble(request.getParameter("nuevaCalificacion"));

        HttpSession session = request.getSession();

        // Obtén el valor de claveMateria desde la solicitud
        String nombreMateria = request.getParameter("nombreMateria");

// Definir la clave de materia como una cadena vacía inicialmente
        String claveMateria = "";

// Consulta SQL para obtener la clave de la materia basada en el nombre de la materia
        String query = "SELECT ClaveMateria FROM materias";
        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(query)) {

            
            ResultSet rs = stmt.executeQuery();

            // Si se encuentra la materia, obtén su clave
            if (rs.next()) {
                claveMateria = rs.getString("ClaveMateria");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en la actualización de calificaciones: " + e.getMessage());
            // Maneja los errores de SQL aquí
        }

        String parcialSeleccionado = (String) session.getAttribute("parcialSeleccionado");
        System.out.println("Parcial seleccionado: " + parcialSeleccionado);

// Establece la conexión a la base de datos (debes tener tu propia lógica de conexión)
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = Conexion.getConexion(); // Reemplaza TuClaseDeConexion por tu lógica de conexión a la base de datos

            // Query SQL para actualizar la calificación
            //        String sql = "UPDATE calificaciones SET " + parcialSeleccionado + " = ? WHERE MatriculaAlumno = ?";
            String sql = "UPDATE calificaciones SET " + parcialSeleccionado + " = ? WHERE MatriculaAlumno = ? AND ClaveMateria = ?";

            // Preparar la declaración SQL
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDouble(1, nuevaCalificacion);
            preparedStatement.setString(2, matricula);
            preparedStatement.setString(3, claveMateria);

            // Ejecutar la actualización
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(" actualización fue un éxito");
                // La actualización fue exitosa
                // Puedes realizar alguna acción aquí si lo deseas
            } else {
                System.out.println(" actualización fue un fracaso");
                // La actualización no afectó ninguna fila (matrícula no encontrada)
                // Puedes manejar este caso de acuerdo a tus necesidades
            }

            // Redirige al usuario de vuelta a la página de calificaciones
            request.getRequestDispatcher("views/capturarCalificaciones.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Maneja los errores de SQL aquí
        } finally {
            // Cierra la conexión y el PreparedStatement
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
