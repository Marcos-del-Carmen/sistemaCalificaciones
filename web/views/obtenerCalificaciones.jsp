
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%
try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    String jdbcUrl = "jdbc:mysql://localhost:3306/DBfinal";
    String usuario = "root";
    String contraseña = "Nejua1998*";
    Connection conn = DriverManager.getConnection(jdbcUrl, usuario, contraseña);

    String sql = "SELECT MatriculaAlumno AS Matricula, " +
        "Nombre AS NombreAlumno, " +
        "MAX(CASE WHEN ClaveMateria = 'MAT101' THEN Parcial1 END) AS Matematicas, " +
        "MAX(CASE WHEN ClaveMateria = 'FIS201' THEN Parcial1 END) AS Fisica, " +
        "MAX(CASE WHEN ClaveMateria = 'INF401' THEN Parcial1 END) AS Informatica, " +
        "MAX(CASE WHEN ClaveMateria = 'QUI301' THEN Parcial1 END) AS Quimica, " +
        "MAX(CASE WHEN ClaveMateria = 'ENG501' THEN Parcial1 END) AS Ingles, " +
        "(MAX(CASE WHEN ClaveMateria = 'MAT101' THEN Parcial1 END) " +
        "+ MAX(CASE WHEN ClaveMateria = 'FIS201' THEN Parcial1 END) " +
        "+ MAX(CASE WHEN ClaveMateria = 'INF401' THEN Parcial1 END) " +
        "+ MAX(CASE WHEN ClaveMateria = 'QUI301' THEN Parcial1 END) " +
        "+ MAX(CASE WHEN ClaveMateria = 'ENG501' THEN Parcial1 END)) / 5 AS PromedioIndiual " +
        "FROM (SELECT " +
        "calificaciones.MatriculaAlumno, " +
        "alumnos.Nombre, " +
        "calificaciones.ClaveMateria, " +
        "calificaciones.Parcial1 " +
        "FROM calificaciones " +
        "LEFT JOIN alumnos ON calificaciones.MatriculaAlumno = alumnos.Matricula) AS PivotData " +
        "GROUP BY MatriculaAlumno, NombreAlumno";

    PreparedStatement stmt = conn.prepareStatement(sql);
    ResultSet rsCalificaciones = stmt.executeQuery();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Calificaciones</title>
    <jsp:include page="../components/estilos.jsp" />
</head>
<body>
    <jsp:include page="../components/menu.jsp" />
    <div class="contendio">
        <table border="1">
        <tr>
            <th>Matricula</th>
            <th>Nombre Alumno</th>
            <th>Matematicas</th>
            <th>Fisica</th>
            <th>Informatica</th>
            <th>Quimica</th>
            <th>Ingles</th>
            <th>Promedio Individual</th>
        </tr>
        <%
            while (rsCalificaciones.next()) {
                String matricula = rsCalificaciones.getString("Matricula");
                String nombreAlumno = rsCalificaciones.getString("NombreAlumno");
                double matematicas = rsCalificaciones.getDouble("Matematicas");
                double fisica = rsCalificaciones.getDouble("Fisica");
                double informatica = rsCalificaciones.getDouble("Informatica");
                double quimica = rsCalificaciones.getDouble("Quimica");
                double ingles = rsCalificaciones.getDouble("Ingles");
                double promedioIndividual = rsCalificaciones.getDouble("PromedioIndiual");
        %>
        <tr>
            <td><%= matricula %></td>
            <td><%= nombreAlumno %></td>
            <td><%= matematicas %></td>
            <td><%= fisica %></td>
            <td><%= informatica %></td>
            <td><%= quimica %></td>
            <td><%= ingles %></td>
            <td><%= promedioIndividual %></td>
        </tr>
        <%
            }
            rsCalificaciones.close();
            stmt.close();
            conn.close();
        %>
        </table>
    </div>
    
    <jsp:include page="../components/scripts.jsp" />
</body>
</html>

