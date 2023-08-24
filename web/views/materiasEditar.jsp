<%-- 
    Document   : materiasEditar
    Created on : 11 ago 2023, 15:32:46
    Author     : Marcos del Carmen
--%>

<%@page import="beans.Materias"%>
<%@page import="dao.MateriasDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar materia</title>
        <jsp:include page="../components/estilos.jsp" />
    </head>
    <body>
        <jsp:include page="../components/menu.jsp" />
        <%
            MateriasDAO dao = new MateriasDAO();
            String claveMateria = request.getAttribute("claveMateria").toString();
            Materias materia = dao.buscar(claveMateria);
        %>
        
        <div class="contenido">
            <div class="cont-entradas" style="display:block;">
                <form action="SvMenu" method='post' id='formulario'>
                <div class='form-group'>
                    <input type="hidden" name="tfClaveMateriaOld" value="<%=materia.getClaveMateria()%>">
                </div>
                <div class='form-group'>
                    <label>Clave de materia</label>
                    <input type='text' class='controls' name='tfClaveMateria' value = "<%= materia.getClaveMateria()%>">
                </div>
                <div class="row">
                    <div class='col-4'>

                        <label>Nombre</label>
                        <input type='text' class='controls' name='tfNombre' value = "<%= materia.getNombre()%>">
                    </div>
                    <div class='col-4'>
                        <label>Selecciona un cuatrimestre</label>
                        <select id="tfCuatrimestre" name="tfCuatrimestre">
                            <option value="<%= materia.getCuatrimestre()%>"><%= materia.getCuatrimestre()%></option>
                            <option value="1ro">1ro</option>
                            <option value="2do">2do</option>
                            <option value="3ro">3ro</option>
                            <option value="4to">4to</option>
                            <option value="5to">5to</option>
                            <option value="6to">6to</option>
                            <option value="7mo">7mo</option>
                            <option value="8vo">8vo</option>
                            <option value="9no">9no</option>
                            <option value="10mo">10mo</option>
                            <option value="11vo">11vo</option>
                        </select>
                        <!-- <label>Cuatrimestre</label>
                        <input type='text' class='controls' name='tfCuatrimestre' value = ""> -->
                    </div>
                    <!--<button type='submit' class='botons' name='accion' value="actualizar">Actualizar</button>-->
                    <button type="submit" class="botons btnActualizarM" name="btnActulizarMateria">Actualizar </button>
                    <a class="fcc-btn" href="SvMenu">Regresar</a>  
                    
                </div>
            </form>
            </div> 
        </div>
        <jsp:include page="../components/scripts.jsp" />
    </body>
</html>
