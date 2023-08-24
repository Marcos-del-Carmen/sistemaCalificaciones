<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Tabla de Materias</title>
        <style>
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

            select, #btnBuscar {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 16px;
            }

            button {
                background-color: #2196F3;
                border-radius: 10px; 
                color: white;
                border: none;
                cursor: pointer;
            }

            button:hover {
                background-color: #2196F3;                
            }
            /* Estilos aquí */
            body {
                background-color: #f0f0f0;
                font-family: Arial, sans-serif;
                margin: 0 auto;
            }
            h3 {
                text-align: center;
                font-size: 24px;
                margin-top: 20px;
            }
            h2
            {
                color: white;
                text-align: center;
                font-size: 150%;
                /*margin-top: 20px;*/
                background-color: #4CAF50;
                padding: 1%;
            }

            table {
                width:70%;
                margin: 0 auto;
                border-collapse: collapse;
                border: 1px solid #ccc;
                background-color: white;
            }
            th, td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ccc;
            }
            th {
                background-color: #f2f2f2;
            }
            #resultados {
                display: none;
                margin:0 auto;
                width: 90%;
                margin-bottom:1%;
                margin-left: 1%;
                margin-right: 1%;
                margin-top: 1%;
            }
            /*        #resultadoss {
                        display: none;
                        margin:0 auto;
                        margin-bottom:1%;
                        margin-left: 1%;
                        margin-right: 1%;
                        margin-top: 1%;
                        width: 90%;
                    }*/
            #cerrarBtn {
                display:none;
                margin: 0 auto;
                font-size: 150%;
                background-color: #2196F3;
                padding: 10px;
                border-radius: 10px; 

            }

            #btnM
            {
                background-color: #2196F3;
                border-radius: 5%;
                display:inline;
                margin: 0 auto;
                margin-left: 40%;
                font-size: 150%;
                padding: 10px;
                border-radius: 10px; 
            }

        </style>
        <jsp:include page="../components/estilos.jsp" />
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            function mostrarTodasMaterias() {
                var resultadosDiv = $("#resultados");
                var cerrarBtn = $("#cerrarBtn");

                resultadosDiv.empty(); // Limpiar los resultados anteriores

                $.ajax({
                    url: "views/obtenerMaterias2.jsp", // Nombre del archivo JSP que realiza la consulta
                    type: "GET",
                    success: function (data) {
                        resultadosDiv.html(data);
                        resultadosDiv.show();
                        cerrarBtn.show();
                    },
                    error: function (xhr, status, error) {
                        console.error(error);
                    }
                });
            }

            function cerrarTabla() {
                var resultadosDiv = $("#resultados");
                var cerrarBtn = $("#cerrarBtn");

                resultadosDiv.hide();
                cerrarBtn.hide();
            }

            //    </script>
    </head>

    <body>
        <jsp:include page="../components/menu.jsp" />
        
        <h2 id="h2p">Tabla de Materias</h2>

        <button id="btnM" onclick="mostrarTodasMaterias()">Mostrar Todas las Materias</button>
        <button id="cerrarBtn" onclick="cerrarTabla()">Cerrar</button>

        <!-- Contenedor para mostrar resultados -->
        <div id="resultados">
            <!--        // Resultados de la consulta se mostrarán aquí -->
        </div>

        <h1>Materias por cuatrimestre </h1>


        <form class="form"  action="Sboleta" method="GET">

            <!-- Aquí empieza selecionar cuatrimestre-->
            <label for="cuatrimestre">Materia:</label>
            <select name="nombre" required>
                <option value="" disabled selected required>Seleccionar cuatrimestre</option>
                <%
  try {
      Connection conn = conexion.Conexion.getConexion();
      Statement stmt = conn.createStatement();
      String query = "SELECT ClaveMateria, Nombre, Cuatrimestre FROM materias";
      ResultSet rsMaterias = stmt.executeQuery(query);
    
      Set<String> cuatUnicos = new HashSet<>();

  //    Set<String> matUnicos = new HashSet<>();

      while (rsMaterias.next()) {
          String cuat = rsMaterias.getString("Cuatrimestre");
          String nombreMat = rsMaterias.getString("Nombre");
          String claveMateria = rsMaterias.getString("ClaveMateria");

          if (!cuatUnicos.contains(cuat)) {
              cuatUnicos.add(cuat);
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

            <button   type="button" id="btnBuscar">Mostrar materias </button>

        </form>

        <div id="resultadom">
            <!-- Aquí se mostrarán las calificaciones recuperadas del servlet -->
        </div>

        <script>
            $(document).ready(function () {
                $("#btnBuscar").click(function () {
                    var matricula = $("select[name='alumno']").val();  // Obtén el valor del elemento con nombre "alumno"
                    var cuatrimestre = $("select[name='cuatrimestre'] option:selected").text();  // Obtén el texto del elemento seleccionado
                    var nombre = $("select[name='nombre'] option:selected").text();  // Obtén el texto del elemento seleccionado

                    $.ajax({
                        url: "Smaterias",
                        type: "GET",
                        data: {
                            // Envía el cuatrimestre seleccionado en lugar de la clave de materia
                            nombre: nombre,

                        },
                        success: function (response) {
                            // Imprimir los datos de matrícula y cuatrimestre para depurar
                            console.log(" estecula: " + matricula);

                            //console.log("esatrimestre: " + cuatrimestre);

                            console.log("Materia: " + nombre);

                            $("#resultadom").html(response);
                        }
                    });
                });
            });

        </script>

        <jsp:include page="../components/scripts.jsp" />
    </body>
</html>