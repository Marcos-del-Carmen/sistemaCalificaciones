package controlador;

import conexion.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import java.sql.SQLException;

import java.util.HashMap;

@WebServlet("/Sboleta")
public class Sboleta extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matricula");
        String cuatrimestre = request.getParameter("cuatrimestre");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Configura la conexión a tu base de datos (debes ajustar esto según tu configuración)
//            String jdbcUrl = "jdbc:mysql://localhost:3306/dwi_final_marcos_francisco?serverTimezone=UTC";
//            String dbUser = "root";
//            String dbPassword = "12345";
//
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            conn = Conexion.getConexion();
            String sql = "SELECT CONCAT(alumnos.Nombre, ' ', alumnos.Paterno, ' ', alumnos.Materno) AS NombreCompleto, "
                            + "materias.Nombre AS NombreMateria, "
                            + "calificaciones.Parcial1, calificaciones.Parcial2, calificaciones.Parcial3, calificaciones.Extraordinario "
                            + "FROM calificaciones "
                            + "INNER JOIN alumnos ON calificaciones.MatriculaAlumno = alumnos.Matricula "
                            + "INNER JOIN materias ON calificaciones.ClaveMateria = materias.ClaveMateria "
                            + "WHERE calificaciones.MatriculaAlumno = ? AND materias.Cuatrimestre = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, matricula);
            stmt.setString(2, cuatrimestre);
            rs = stmt.executeQuery();

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head><title>Boleta de Calificaciones</title></head>");
            out.println("<style>");
            out.println("table {");
            out.println("    font-family: Arial, sans-serif;");
            out.println("    border-collapse: collapse;");
            out.println("    width: 100%;");
            out.println("}");
            out.println("th, td {");
            out.println("    border: 1px solid #dddddd;");
            out.println("    text-align: left;");
            out.println("    padding: 8px;");
            out.println("}");
            out.println("tr:nth-child(even) {");
            out.println("    background-color: #f2f2f2;");
            out.println("}");
            out.println("th {");
            out.println("    background-color: #4CAF50;");
            out.println("    color: white;");
            out.println("}");
            out.println("</style>");
            out.println("<body>");

            //out.println("<h2>"+ cuatrimestre + "</h2>");
            out.println("<br>");

            out.println("<table>");

            out.println("<tr>");
            out.println("<th>Nombre Alumno</th>");
            out.println("<th>Materia</th>");
            out.println("<th>Parcial 1</th>");
            out.println("<th>Parcial 2</th>");

            out.println("<th>Parcial 3</th>");

            out.println("<th>Promedio </th>"); // Agrega una columna para el promedio
            out.println("<th>Extraordinario</th>");
            out.println("</tr>");

            String nombreCompleto = null; // Variable para almacenar el nombre del alumno
            Map<String, Float> promediosMaterias = new HashMap<>();

            while (rs.next()) {
                if (nombreCompleto == null) {
                    nombreCompleto = rs.getString("NombreCompleto"); // Obtiene el nombre del alumno solo una vez
                    out.println("<tr>");
                    out.println("<td rowspan='999'>" + nombreCompleto + "</td>"); // rowspan para fusionar celdas
                }

                String nombreMateria = rs.getString("NombreMateria");
                float parcial1 = rs.getFloat("Parcial1");
                float parcial2 = rs.getFloat("Parcial2");
                float parcial3 = rs.getFloat("Parcial3");
                float extraordinario = rs.getFloat("Extraordinario");

                // Calcula el promedio para esta materia
                float promedioMateria = (parcial1 + parcial2 + parcial3) / 3;

                // Almacena el promedio de la materia en el mapa
                promediosMaterias.put(nombreMateria, promedioMateria);
                // Formatea el promedio  a un decimal
                String promedioFormateado = String.format("%.1f", promedioMateria);

                out.println("<tr>");
                out.println("<td>" + nombreMateria + "</td>");
                out.println("<td>" + parcial1 + "</td>");
                out.println("<td>" + parcial2 + "</td>");
                out.println("<td>" + parcial3 + "</td>");

                out.println("<td>" + promedioFormateado + "</td>"); // Agrega el promedio en la tabla

                out.println("<td>" + extraordinario + "</td>");
                out.println("</tr>");
            }

            // Agrega una fila para mostrar los promedios generales
            float promedioGeneral = 0;
            for (float promedio : promediosMaterias.values()) {
                promedioGeneral += promedio;
            }
            promedioGeneral /= promediosMaterias.size();

// Formatea el promedio general a un decimal
            String promedioGeneralFormateado = String.format("%.1f", promedioGeneral);

            out.println("<tr>");
            out.println("<td>Promedio General</td>");
            out.println("<td colspan='3'></td>"); // Celdas vacías para mantener el formato
            out.println("<td>" + promedioGeneralFormateado + "</td>"); // Muestra el promedio general
            out.println("</tr>");

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            // Maneja las excepciones adecuadamente

        } finally {
            // Cierra recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
    }
}