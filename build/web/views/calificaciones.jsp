<%-- 
    Document   : calificaciones
    Created on : 26 jul 2023, 18:34:46
    Author     : Marcos del Carmen
--%>
<%@page import="java.util.*"%>
<%@page import="beans.Calificaciones"%>
<%@page import="dao.CalificacionesDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calificaciones</title>
        <jsp:include page="../components/estilos.jsp" />
    </head>
    <body>
        <jsp:include page="../components/menu.jsp" />
        <div class="contenido">
            <div class="cont-tabla-alumnos">
                <h1>Resumen de Calificaciones</h1>
                <table border="1">
                    <tr>
                        <th>Matrícula</th>
                        <th>Nombre Completo</th>
                        <th>ABD</th>
                        <th>DDI</th>
                        <th>DWI</th>
                        <th>DWP</th>
                        <th>Promedio</th>
                    </tr>
                    <%
                        CalificacionesDAO calificacionesDao = new CalificacionesDAO();
                        List<Calificaciones> calificaciones = calificacionesDao.resumen();
                        for (Calificaciones calificacion : calificaciones) {
                    %>
                    <tr>
                        <td><%= calificacion.getMatricula() %></td>
                        <td><%= calificacion.getNombreCompleto() %></td>
                        <td><%= calificacion.getMat1() %></td>
                        <td><%= calificacion.getMat2() %></td>
                        <td><%= calificacion.getMat3() %></td>
                        <td><%= calificacion.getMat4() %></td>
                        <td><%= calificacion.getProm() %></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
        <jsp:include page="../components/scripts.jsp" />
    </body>
</html>
