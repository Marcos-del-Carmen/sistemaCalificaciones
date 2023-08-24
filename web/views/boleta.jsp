<%@ page import="conexion.Conexion" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Boleta de Calificaciones</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }

            h1 {
                background-color: #4CAF50;
                color: white;
                padding: 10px;
                text-align: center;
            }

            .form {
                background-color: white;
                max-width: 400px;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ddd;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            label {
                font-weight: bold;
            }

            select, button {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 16px;
            }

            button {
                background-color: #4CAF50;
                color: white;
                border: none;
                cursor: pointer;
            }

            button:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <jsp:include page="../components/estilos.jsp" />
    <body>
        <jsp:include page="../components/menu.jsp" />
        <div class="contenido">


            <h1>Boleta de Calificaciones</h1>


            <form class="form"  action="Sboleta" method="GET">
                <!--    <form class="form" action="capturarCalificaciones" method="get">-->
                <label for="alumno">Alumno:</label>
                <select name="alumno" required>
                    <option value="" disabled selected required> Seleccionar alumno </option>
                    <% 
                    try {
                        Connection conn = conexion.Conexion.getConexion();
                        Statement stmt = conn.createStatement();
                        String query = "SELECT Matricula, Nombre FROM alumnos";
                        ResultSet rsMaterias = stmt.executeQuery(query);
                        while (rsMaterias.next()) {
                            String matricula = rsMaterias.getString("Matricula");
                            String nombre = rsMaterias.getString("Nombre");
                            String nombreSeleccionada = (String) session.getAttribute("nombreSeleccionada");
                    %>

                    <option value="<%= matricula %>" <%= (nombreSeleccionada != null && nombreSeleccionada.equals(matricula)) ? "selected" : "" %>><%=nombre%></option>

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


                <!-- Aquí empieza selecionar cuatrimestre-->
                <label for="cuatrimestre">Materia:</label>
                <select name="cuatrimestre" required>
                    <option value="" disabled selected required>Seleccionar cuatrimestre</option>
                    <% 
                    try {
                        Connection conn = conexion.Conexion.getConexion();
                        Statement stmt = conn.createStatement();
                        String query = "SELECT ClaveMateria, Cuatrimestre FROM materias";
                        ResultSet rsMaterias = stmt.executeQuery(query);
        
                        // Usar un conjunto para almacenar cuatrimestres únicos
                        Set<String> cuatrimestresUnicos = new HashSet<>();
        
                        while (rsMaterias.next()) {
                            String cuat = rsMaterias.getString("Cuatrimestre");
                            String claveMateria = rsMaterias.getString("ClaveMateria");
            
                            // Verificar si el cuatrimestre ya ha sido agregado al conjunto
                            if (!cuatrimestresUnicos.contains(cuat)) {
                                cuatrimestresUnicos.add(cuat);
                    %>
                    <option value="<%= claveMateria %>"><%= cuat %></option>
                    <%
                            }
                        }
                        rsMaterias.close();
                        stmt.close();
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    %>
                </select>

                <button type="button" id="btnBuscar">Buscar Calificaciones</button>

            </form>

            <div id="calificaciones">
                <!-- Aquí se mostrarán las calificaciones recuperadas del servlet -->
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $("#btnBuscar").click(function () {
                    var matricula = $("select[name='alumno']").val();  // Obtén el valor del elemento con nombre "alumno"
                    var cuatrimestre = $("select[name='cuatrimestre'] option:selected").text();  // Obtén el texto del elemento seleccionado

                    $.ajax({
                        url: "Sboleta",
                        type: "GET",
                        data: {
                            matricula: matricula,
                            cuatrimestre: cuatrimestre  // Envía el cuatrimestre seleccionado en lugar de la clave de materia
                        },
                        success: function (response) {
                            // Imprimir los datos de matrícula y cuatrimestre para depurar
                            console.log(" estecula: " + matricula);
                            console.log("esatrimestre: " + cuatrimestre);

                            $("#calificaciones").html(response);
                        }
                    });
                });
            });

        </script>
        <jsp:include page="../components/scripts.jsp" />
    </body>
</html>