<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="conexion.Conexion" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="dao.CalificacionesDAO1" %>
<%@ page errorPage="error.jsp" %>
<%@ page import="controlador.AlumnoCalificacion" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <title>Calificaciones de Alumnos</title>
        <jsp:include page="../components/estilos.jsp" />
    </head>
    <body>
        <jsp:include page="../components/menu.jsp" />
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                margin: 0;
                padding: 0;
            }

            h1 {
                background-color: #333;
                color: #fff;
                padding: 10px;
                margin: 0;
                text-align: center;
            }

            table {
                width: 80%;
                margin: 20px auto;
                border-collapse: collapse;
                background-color: #fff;
                box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
            }

            th, td {
                padding: 10px;
                text-align: justify;
            }
            #thM
            {
                padding: 10px;
                text-align: center;
                background-color: #f0f0f0;
                color: black;
                font-size: 200%;
            }

            th {
                background-color: #4CAF50;
                color: #fff;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            p {
                text-align: center;
                margin-top: 20px;
                font-size: 18px;
                color: #555;
            }
            .form
            {
                display: block;
                width: 81%;
                padding: 10px;
                margin: 0px auto;
                font-size: 150%;
            }

            .hidden-form {
                display: none;
            }
        </style>

        <%
        //    de aquí mando la vareable parcialseleccionado
        // Obtener el valor seleccionado del parcial desde la solicitud HTTP
        String parcialSeleccionado = request.getParameter("parcial");

        session.setAttribute("parcialSeleccionado", parcialSeleccionado);


        %>
        <!-- Formulario para seleccionar materia y parcial -->
        <form class="form" action="capturarCalificaciones" method="Post" ">
            <!--    <form class="form" action="capturarCalificaciones" method="get">-->
            <label for="materia">Materia:</label>
            <select name="materia" required>
                <option value="" disabled>Seleccionar materia</option>
                <% 
                try {
                    Connection conn = conexion.Conexion.getConexion();
                    Statement stmt = conn.createStatement();
                    String query = "SELECT ClaveMateria, Nombre FROM materias";
                    ResultSet rsMaterias = stmt.executeQuery(query);
                    while (rsMaterias.next()) {
                        String claveMateria = rsMaterias.getString("ClaveMateria");
                        String nombreMateria = rsMaterias.getString("Nombre");
                        String materiaSeleccionada = (String) session.getAttribute("materiaSeleccionada");
                %>

                <option value="<%= claveMateria %>" <%= (materiaSeleccionada != null && materiaSeleccionada.equals(claveMateria)) ? "selected" : "" %>><%= nombreMateria %></option>

                <%
                    }
                    rsMaterias.close();
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                %>
            </select>

            <label for="parcial">Parcial:</label>
            <select name="parcial" required>
                <option value="" disabled>Seleccionar parcial</option>
                <option value="Parcial1" <%= ("Parcial1".equals(session.getAttribute("parcialSeleccionado"))) ? "selected" : "" %>>Parcial 1</option>
                <option value="Parcial2" <%= ("Parcial2".equals(session.getAttribute("parcialSeleccionado"))) ? "selected" : "" %>>Parcial 2</option>
                <option value="Parcial3" <%= ("Parcial3".equals(session.getAttribute("parcialSeleccionado"))) ? "selected" : "" %>>Parcial 3</option>
                <option value="Extraordinario" <%= ("Extraordinario".equals(session.getAttribute("parcialSeleccionado"))) ? "selected" : "" %>>Extra ordinario </option>

            </select>
            <input type="hidden" name="parcialSeleccionado" value="${parcialSeleccionado}" >
            <input  type="submit" name="btnCalificaciones" value="Mostrar Calificaciones" class="btnProcesar">
        </form>

        <!--<input type="button" value="Mostrar Valores" onclick="mostrarValores()"><!-- este botón pertenece a JS de abajo -->           



        <table>
            <thead>
                <tr>
                    <c:if test="${not empty materiaSeleccionada}">
                        <th id="thM" colspan="4">
                            Materia Seleccionada: ${nombreMateria}
                        </th>
                    </c:if>
                </tr>

                <tr>
                    <th>Matrícula</th>
                    <th>Nombre del Alumno</th>
                    <th>${parcialSeleccionado}</th>
                    <th>accion</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="alumno" items="${alumnos}">
                    <tr>
                        <td>${alumno.matricula}</td>
                        <td>${alumno.nombre}</td>
                        <td>${alumno.calificacion}</td>
                        <td>
                            <button onclick="editar('${alumno.matricula}')" class="btnEditar">Editar</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${empty alumnos}">
            <p>No hay calificaciones disponibles para el parcial y materia seleccionados.</p>
        </c:if>

        <div id="formContainer">
            <c:forEach var="alumno" items="${alumnos}">
                <form action="actualizarCalificacion" method="Post" class="hidden-form form-${alumno.matricula}">
                    <input type="hidden" name="matricula" value="${alumno.matricula}">
                    Matrícula: ${alumno.matricula} <br>
                    Nombre: ${alumno.nombre} <br>
                    Calificación: <input type="number" name="nuevaCalificacion" value="${alumno.calificacion}" step="0.01">
                    <input type="submit" value="Guardar" onclick="ocultarModal()">
                </form>
            </c:forEach>
        </div>

        <script>
            function editar(matricula) {
                var modal = document.getElementById('formContainer');
                modal.style.display = 'block';
                // Primero, ocultamos todos los formularios.
                var forms = document.querySelectorAll('.hidden-form');
                for (var i = 0; i < forms.length; i++) {
                    forms[i].style.display = 'none';
                }

                // Luego, mostramos el formulario correspondiente a la matrícula dada.
                var formToShow = document.querySelector('.form-' + matricula);
                if (formToShow) {
                    formToShow.style.display = 'block';
                }

            }

            function ocultarModal() {
                var modal = document.getElementById('formContainer');
                modal.style.display = 'none';
            }
        </script>
        <jsp:include page="../components/scripts.jsp" />
    </body>      
</html>
