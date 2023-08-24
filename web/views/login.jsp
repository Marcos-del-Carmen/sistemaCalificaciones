<%-- 
    Document   : login
    Created on : 22 jul 2023, 12:10:50
    Author     : Marcos del Carmen
--%>

<%@page import="dao.AccesoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar seción</title>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="css/componentes.css"/>
    </head>
    <body class="body-login">
         <div class="login">
            <form action="SvVerificar" method="POST">
                <h4 class="login__heading">Iniciar sección</h4>
                <span class="login__circuit-mask"></span>
                <div class="datos">
                    <label>Correo electronico</label>
                    <input type="email" name="correo"/>
                    
                    <label>Contraseña</label>
                    <input type="password"  name="contrasena"/>
                </div>
                <div class="cont__btn-login">
                    <input type="submit" value="Ingresar" name="btnIngresar"/>
                </div>
            </form>
        </div>
        <!--<a href="views/conexion.jsp">Revisar la conexion ala base de datos</a>-->
        <%
            String mensajeError = (String) request.getAttribute("mensajeError");
  
            if(mensajeError == null) {
                out.println("");
            } else {
                out.println(mensajeError);
            }
            
        %>
    </body>
</html>
