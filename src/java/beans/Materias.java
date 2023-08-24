
package beans;

public class Materias {
    private String claveMateria;
    private String nombre;
    private String cuatrimestre;

    public Materias() {
    }

    
    public Materias(String claveMateria) {
        this.claveMateria = claveMateria;
    }

    public Materias(String claveMateria, String nombre, String cuatrimestre) {
        this.claveMateria = claveMateria;
        this.nombre = nombre;
        this.cuatrimestre = cuatrimestre;
    }
    
    public String getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(String claveMateria) {
        this.claveMateria = claveMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(String cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }
    
}
