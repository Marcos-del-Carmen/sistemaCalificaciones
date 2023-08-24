package dao;

import static conexion.Conexion.*;
import java.sql.*;

public class AccesoDAO {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public boolean validarCorreo(String correo, String contrasena ) throws ClassNotFoundException  {
        String sql = "SELECT * FROM accesos WHERE Correo = ? AND Contrasena = ?";
        boolean existe = false;
       
        try {
            this.conn = getConexion();
            this.ps = this.conn.prepareStatement(sql);
            this.ps.setString(1, correo);
            this.ps.setString(2, contrasena);
            this.rs = this.ps.executeQuery();
            
            if(this.rs.next()){
                // Si hay un registro en el resultado, significa que el usuario existe en la base de datos
                existe = true;
            } else {
                // Si no hay registros, el usuario no existe en la base de datos
                existe = false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try{
                this.conn.close();
                this.ps.close();
                this.rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return existe;
    }
}
