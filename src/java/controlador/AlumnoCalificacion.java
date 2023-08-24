/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author francisco
 */
public class AlumnoCalificacion {
private String matricula;
private String nombre;
private String materia;
private double calificacion;

public AlumnoCalificacion(String matricula, String nombre, String materia, double calificacion) {
    this.matricula = matricula;
    this.nombre = nombre;
    this.materia = materia;
    this.calificacion = calificacion;
}

// Agrega los métodos getter para los nuevos campos (nombre y materia)
public String getMatricula() {
    return matricula;
}

public String getNombre() {
    return nombre;
}

public String getMateria() {
    return materia;
}

public double getCalificacion() {
    return calificacion;
}
}
