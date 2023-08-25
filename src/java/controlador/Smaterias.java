package controlador;

import conexion.Conexion;
import java.util.logging.Level;
import java.util.logging.Logger;

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

import java.sql.SQLException;

@WebServlet(name = "Smaterias", urlPatterns = {"/Smaterias"})
public class Smaterias extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        //String matricula = request.getParameter("matricula");
        String cuatrimestre = request.getParameter("nombre");
        System.out.println("cuatrimestre" + cuatrimestre);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConexion();
            String sql = "SELECT ClaveMateria, Nombre, Cuatrimestre FROM materias WHERE Cuatrimestre = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cuatrimestre);
            rs = stmt.executeQuery();

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

// ...
// Variable para realizar un seguimiento del cuatrimestre actual
            String cuatrimestreActual = null;

            out.println("<html>");
            out.println("<head><title>Materias del Cuatrimestre</title></head>");
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

            out.println("<br>");

            out.println("<table>");

            out.println("<tr>");

            out.println("<th>Cuatrimestre</th>");

            out.println("<th>Clave Materia</th>");
            out.println("<th>Nombre Materia</th>");
            out.println("</tr>");

            while (rs.next()) {
                String cuat = rs.getString("Cuatrimestre");
                String claveMateria = rs.getString("ClaveMateria");
                String nombreMateria = rs.getString("Nombre");

                if (!cuat.equals(cuatrimestreActual)) {
                    // Mostrar el cuatrimestre solo cuando cambia
                    out.println("<tr>");
                    out.println("<td rowspan='999'>" + cuat + "</td>"); // rowspan para fusionar celdas

                    out.println("</tr>");
                    cuatrimestreActual = cuat;
                }

                out.println("<tr>");
                out.println("<td>" + claveMateria + "</td>");
                out.println("<td>" + nombreMateria + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException | SQLException e) {
            // Manejo de excepciones

        } finally {
            // Cierre de conexiones y recursos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Smaterias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Smaterias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Smaterias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Smaterias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
