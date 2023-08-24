package beans;

public class Acceso {
    private int id;
    private String correo;
    private String contrasena;

    public Acceso() {
    }
    
    // Eliminar un usuario
    public Acceso(int id) {
        this.id = id;
    }
    
    // Agregar un usuario
    public Acceso(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }    
    
    // Modificar un usuario
    public Acceso(int id, String correo, String contrasena) {
        this.id = id;
        this.correo = correo;
        this.contrasena = contrasena;
    }
    
    public int getId() {
        return id;
    }
    
    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }  

    public void setId(int id) {
        this.id = id;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Acceso{");
        sb.append("id=").append(id);
        sb.append(", correo=").append(correo);
        sb.append(", contrasena=").append(contrasena);
        sb.append('}');
        return sb.toString();
    }
}
