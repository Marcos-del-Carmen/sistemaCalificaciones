<%-- 
    Document   : alumnosEditar
    Created on : 12 ago 2023, 13:07:44
    Author     : Marcos del Carmen
--%>

<%@page import="beans.Alumnos"%>
<%@page import="dao.AlumnosDAO"%>
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
        <%
            AlumnosDAO dao = new AlumnosDAO();
            String matricula = request.getAttribute("matricula").toString();
            Alumnos alumno = dao.buscar(matricula);
        %>

        <div class="contenido">
            <div class="cont-form-alumnos" style="display: block; margin: 0px 50px;">
                <form action="SvMenu" method='post' id='formulario'>
                    <div class="cont-input" style="display: none;">
                        <input type="hidden" name="tfMatriculaOld" value="<%= alumno.getMatricula() %>">
                    </div>
                    <div class="cont-input">
                        <label for='lblMatricula'>Matricula</label>
                        <input type='text' class='controls' name='tfMatricula' value = "<%= alumno.getMatricula() %>">
                    </div>

                    <div class="cont-input">
                        <label>Nombre</label>
                        <input type='text' class='controls' name='tfNombre' value = "<%= alumno.getNombre() %>">
                    </div>
                    <div class="cont-input">
                        <label>Paterno</label>
                        <input type='text' class='controls' name='tfPaterno' value = "<%= alumno.getPaterno() %>">
                    </div>
                    <div class="cont-input">
                        <label>Materno</label>
                        <input type='text' class='controls' name='tfMaterno' value = "<%= alumno.getMaterno() %>">
                    </div>
                    <div class="cont-input">
                        <label>Sexo</label>
                        <select name="tfSexo" id="tfSexo">
                            <option value = "<%= alumno.getSexo() %>" selected><%= alumno.getSexo() %></option>
                            <option value="F">Femenino</option>
                            <option value="M">Masculino</option>
                        </select>
                    </div>
                    <div class="cont-input">
                        <label>Fecha de nacimiento</label>
                        <input type='text' class='controls' name='tfFechaNac' value = "<%= alumno.getFechaNac() %>">
                    </div>
                    <div class="cont-input">
                        <label>Telefono</label>
                        <input type='text' class='controls' name='tfTelefono' value = "<%= alumno.getTelefono() %>">
                    </div>
                    <div class="cont-input">
                        <label>Dirección</label>
                        <input type='text' class='controls' name='tfDireccion' value = "<%= alumno.getDireccion() %>">
                    </div>
                    <div class="cont-input">
                        <label>Correo</label>
                        <input type='email' class='controls' name='tfCorreo' value = "<%= alumno.getCorreo() %>">
                    </div>
                    <!--<button type='submit' class='botons' name='accion' value="actualizar">Actualizar</button>-->
                    <button type="submit" class="boton btnActulizarA" name="btnActulizarAlumno">Actualizar </button>
                    <a class="fcc-btn" href="SvMenu?accion=regresarM" >Regresar</a>  
                </form>
            </div> 
        </div>
        <jsp:include page="../components/scripts.jsp" />
    </body>
</html>
