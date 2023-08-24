<%-- 
    Document   : obtenerMaterias2
    Created on : 16 ago. 2023, 21:57:24
    Author     : francisco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="../components/estilos.jsp" />
    </head>
    <body>
    <jsp:include page="../components/menu.jsp" />
<%@ page import="java.sql.*" %>


<%
    try {
        Class.forName("com.mysql.jdbc.Driver");
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_calificaciones?serverTimezone=UTC";
        String usuario = "root";
        String contraseña = "12345";
        
        Connection conn = DriverManager.getConnection(jdbcUrl, usuario, contraseña);

        String sql = "SELECT ClaveMateria, Nombre, Cuatrimestre  FROM materias";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rsMaterias = stmt.executeQuery();

        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ClaveMat</th>");
        out.println("<th>Nombre</th>");
        out.println("<th>Cuatrimestre</th>");
        out.println("</tr>");

        while (rsMaterias.next()) {
            String claveMateria = rsMaterias.getString("ClaveMateria");
            String nombre = rsMaterias.getString("Nombre");
            String cuatrimestre = rsMaterias.getString("Cuatrimestre");

            out.println("<tr>");
            out.println("<td>" + claveMateria + "</td>");
            out.println("<td>" + nombre + "</td>");
            out.println("<td>" + cuatrimestre + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");

        rsMaterias.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
            
    <jsp:include page="../components/scripts.jsp" />
    </body>
</html>
