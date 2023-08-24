package beans;

public class Alumnos {
    private String matricula;
    private String nombre;
    private String paterno;
    private String materno;
    private String sexo;
    private String fechaNac;
    private String telefono;
    private String direccion;
    private String correo;
    

    public Alumnos() {
    }

    public Alumnos(String matricula) {
        this.matricula = matricula;
    }

    public Alumnos(String matricula, String nombre, String paterno, String materno, String sexo, String fechaNac, String telefono, String direccion, String correo) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.sexo = sexo;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }
 
    public Alumnos(String matricula, String nombre, String paterno, String materno) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Alumnos{");
        sb.append("matricula=").append(matricula);
        sb.append(", nombre=").append(nombre);
        sb.append(", paterno=").append(paterno);
        sb.append(", materno=").append(materno);
        sb.append(", telefono=").append(telefono);
        sb.append(", direccion=").append(direccion);
        sb.append(", correo=").append(correo);
        sb.append(", fechaNac=").append(fechaNac);
        sb.append('}');
        return sb.toString();
    }
    
}
