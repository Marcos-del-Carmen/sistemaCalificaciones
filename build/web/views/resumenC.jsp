<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Calificaciones"%>
<%@ page import="dao.CalificacionesDAO"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Tabla de Calificaciones</title>
        <jsp:include page="../components/estilos.jsp" />
        <style>
            /* Estilos para los enlaces con la clase "boton-enlace" */
            .boton-enlace {
                display: inline-block;
                padding: 10px 20px;
                margin: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                text-decoration: none;
                cursor: pointer;
                transition: background-color 0.3s ease-in-out;
            }

            .boton-enlace:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../components/menu.jsp" />
        <div class="contenido">
            <a href="SvMenu?accion=resumen" class="boton-enlace">Resumen de calificaciones</a>
            <a href="SvMenu?accion=boleta" class="boton-enlace">Boleta por alumno</a>
        </div>
        <jsp:include page="../components/scripts.jsp" />
    </body>
</html>
