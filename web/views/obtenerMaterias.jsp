<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
    /* Estilo para la tabla */
    table {
        border-collapse: collapse;
        width: 70%;
    }

    /* Estilo para las filas impares */
    tr:nth-child(odd) {
        background-color: #f2f2f2;
    }

    /* Estilo para celdas */
    th, td {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    /* Resaltar fila al pasar el mouse sobre ella */
    tr:hover {
        background-color: #c2e0ff;
    }
     /* Estilo para los encabezados h3 */
    h3 {
        text-align: center;
        font-size: 1.5em; /* Cambia el tamaño del texto según tus preferencias */
        /*background-color:grey; /* Cambia el color de fondo según tus preferencias */
        color: black; /* Cambia el color del texto según tus preferencias */
        padding: 10px;
    }
</style>
<%
    try {
        conn = Conexion.getConexion();
        String sql = "SELECT DISTINCT Cuatrimestre FROM materias";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rsCuatrimestres = stmt.executeQuery();

        while (rsCuatrimestres.next()) {
            String cuatrimestre = rsCuatrimestres.getString("Cuatrimestre");

            out.println("<h3>Cuatrimestre " + cuatrimestre + "</h3>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>ClaveMat</th>");
            out.println("<th>Nombre</th>");
            out.println("</tr>");

            sql = "SELECT ClaveMateria, Nombre FROM materias WHERE Cuatrimestre = ?";
            PreparedStatement stmtMaterias = conn.prepareStatement(sql);
            stmtMaterias.setString(1, cuatrimestre);
            ResultSet rsMaterias = stmtMaterias.executeQuery();

            while (rsMaterias.next()) {
                String claveMateria = rsMaterias.getString("ClaveMateria");
                String nombre = rsMaterias.getString("Nombre");

                out.println("<tr>");
                out.println("<td>" + claveMateria + "</td>");
                out.println("<td>" + nombre + "</td>");
                out.println("</tr>");
            }

            out.println("</table><br>");

            rsMaterias.close();
            stmtMaterias.close();
        }

        rsCuatrimestres.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>




