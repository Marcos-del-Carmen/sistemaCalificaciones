<%@page import="beans.Alumnos"%>
<%@page import="java.util.*"%>
<%@page import="dao.AlumnosDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    AlumnosDAO alumnoDao = new AlumnosDAO();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alumnos</title>
        <jsp:include page="../components/estilos.jsp" />
    </head>
    <body>
        <jsp:include page="../components/menu.jsp" />
        <div class="contenido">

            <h3>Ingresa a un alumno</h3>
            <button class="toggle-button" onclick="mostrarFormAlumnos()">Mostrar formulario</button>
            <div class="cont-form-alumnos">
                <form action="SvMenu" method="POST">
                    <div class="cont-input">
                        <label>Matricula</label>
                        <input type="text" name="tfMatricula">
                    </div>
                    <div class="cont-input">
                        <label>Nombre</label>
                        <input type="text" name="tfNombre">
                    </div>

                    <div class="cont-input">
                        <label>Apellido paterno</label>
                        <input type="text" name="tfPaterno">
                    </div>

                    <div class="cont-input">
                        <label>Apellido materno</label>                       
                        <input type="text" name="tfMaterno">
                    </div>

                    <div class="cont-input">
                        <label>Sexo</label>
                        <select name="tfSexo" id="tfSexo">
                            <option value="F">Femenino</option>
                            <option value="M">Masculino</option>
                        </select>
                    </div>

                    <div class="cont-input">
                        <label>Fecha de nacimiento</label>                        
                        <input type="date" name="tfFechaNac">
                    </div>

                    <div class="cont-input">
                        <label>Telefono</label>
                        <input type="text" name="tfTelefono">
                    </div>

                    <div class="cont-input">
                        <label>Dirección</label>
                        <input type="text" name="tfDireccion">
                    </div>

                    <div class="cont-input">
                        <label>Correo</label>                       
                        <input type="email" name="tfCorreo">
                    </div>

                    <input type="submit" name="btnAlumnoNuevo" value="Guardar registro" class="boton"/>
                </form>
            </div>

            <div class="cont-tabla-alumnos">
                <table>
                    <thead>
                        <tr>
                            <th>Matricula</th>
                            <th>Nombre</th>
                            <th>Paterno</th>
                            <th>Materno</th>
                            <th>Sexo</th>
                            <th>Fecha de nacimiento</th>
                            <th>Telefono</th>
                            <th>Dirección</th>
                            <th>Correo</th>
                            <th colspan="2">Acciones</th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%                        
                            // AlumnosDAO alumnoDao = new AlumnosDAO();
                            List<Alumnos> alumnos = alumnoDao.mostrar();
                            for (Alumnos alumno : alumnos) {
                        %>
                        <tr>
                            <td><%= alumno.getMatricula()%></td>
                            <td><%= alumno.getNombre()%></td>
                            <td><%= alumno.getPaterno()%></td>
                            <td><%= alumno.getMaterno()%></td>
                            <td><%= alumno.getSexo()%></td>
                            <td><%= alumno.getFechaNac()%></td>
                            <td><%= alumno.getTelefono()%></td>
                            <td><%= alumno.getDireccion()%></td>
                            <td><%= alumno.getCorreo()%></td>
                            <td><a href="SvMenu?accion=editarM&matricula=<%= alumno.getMatricula() %>" class="boton">Editar</a></td>
                            <td><a href="javascript:void(0);" onclick="confirmarEliminar('<%= alumno.getMatricula() %>')" class="boton boton-eliminar">Eliminar</a></td>
                            <!--<td><a href="SvMenu?accion=eliminarM&matricula=<%= alumno.getMatricula() %>">Eliminar</a></td>-->
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <div class="ventana-confirmacion" id="ventanaConfirmacion">
                <div class="ventana-confirmacion-contenido">
                    <p>¿Estás seguro de que deseas eliminar el registro con matrícula: <span id="matriculaEliminar"></span>?</p>
                    <button class="boton boton-confirmar" onclick="eliminarRegistro()">Confirmar</button>
                    <button class="boton boton-cancelar" onclick="cerrarVentana()">Cancelar</button>
                </div>
            </div>
        </div>
        <jsp:include page="../components/scripts.jsp" />
    </body>
</html>
