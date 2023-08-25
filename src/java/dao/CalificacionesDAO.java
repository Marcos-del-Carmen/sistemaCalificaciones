package dao;

import beans.Calificaciones;
import beans.Materias;
import conexion.Conexion;
import java.util.*;
import java.sql.*;

public class CalificacionesDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private Calificaciones calificaciones;
    
    
    // Método para obtener los promedios por materia
    public List<Calificaciones> resumen() throws SQLException, ClassNotFoundException {
        List<Calificaciones> calificacion = new ArrayList<>();

        // Realiza la conexión y ejecución de la consulta
        try (Connection connection =  Conexion.getConexion()) {
            String sql = "SELECT " +
                         "    a.Matricula, " +
                         "    CONCAT(a.Nombre, ' ', a.Paterno, ' ', a.Materno) AS NombreCompleto, " +
                         "    MAX(CASE WHEN m.ClaveMateria = 'ABD023' THEN ROUND((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3, 1) END) AS ABD, " +
                         "    MAX(CASE WHEN m.ClaveMateria = 'DDI023' THEN ROUND((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3, 1) END) AS DDI, " +
                         "    MAX(CASE WHEN m.ClaveMateria = 'DWI023' THEN ROUND((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3, 1) END) AS DWI, " +
                         "    MAX(CASE WHEN m.ClaveMateria = 'DWP023' THEN ROUND((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3, 1) END) AS DWP, " +
                         "    ag.promedio AS Promedio " +
                         "FROM " +
                         "    calificaciones c " +
                         "JOIN " +
                         "    materias m ON c.ClaveMateria = m.ClaveMateria " +
                         "JOIN " +
                         "    alumnos a ON c.MatriculaAlumno = a.Matricula " +
                         "JOIN ( " +
                         "    SELECT " +
                         "        MatriculaAlumno, " +
                         "        ROUND(AVG((Parcial1 + Parcial2 + Parcial3) / 3), 1) AS promedio " +
                         "    FROM " +
                         "        calificaciones " +
                         "    GROUP BY " +
                         "        MatriculaAlumno " +
                         ") ag ON a.Matricula = ag.MatriculaAlumno " +
                         "GROUP BY " +
                         "    a.Matricula, ag.promedio; ";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Crea objetos PromedioMateria con los datos del resultado
                Calificaciones promedio = new Calificaciones();
                promedio.setMatricula(resultSet.getString("Matricula"));
                promedio.setNombreCompleto(resultSet.getString("NombreCompleto"));
                promedio.setMat1(resultSet.getDouble("ABD"));
                promedio.setMat2(resultSet.getDouble("DDI"));
                promedio.setMat3(resultSet.getDouble("DWI"));
                promedio.setMat4(resultSet.getDouble("DWP"));
                promedio.setProm(resultSet.getDouble("Promedio"));
                calificacion.add(promedio);
            }
        }

        return calificacion;
    }
}
