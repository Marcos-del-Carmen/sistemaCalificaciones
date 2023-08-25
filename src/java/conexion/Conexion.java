package conexion;

import java.sql.*;

public class Conexion {
    // Con esto configuramos en donde esta nuestra base de datos su nombre y la hora (UTC es una manera global de interpretar la hora)
    private static final String URL = "jdbc:mysql://localhost:3306/dwi_final_marcos_francisco?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "12345";
    
    public static Connection getConexion() throws SQLException {
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");

      } catch (ClassNotFoundException ex ) {
        System.out.println("No se cargo el driver");
      }
      return DriverManager.getConnection(URL, USER, PASS);
  }
}
