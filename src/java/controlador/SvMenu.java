package controlador;

import beans.Alumnos;
import beans.Materias;
import dao.AlumnosDAO;
import dao.MateriasDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.http.HttpSession;

public class SvMenu extends HttpServlet {
    AlumnosDAO alumnoDao = new AlumnosDAO();
    String url_files = "";
    String accion = "";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
                    
        if(request.getParameter("btnAlumnos")!=null){
            url_files = "views/alumnos.jsp";                 
        } else if (request.getParameter("btnMaterias")!=null){
            url_files = "views/materias.jsp";
        } else if (request.getParameter("btnsMaterias")!=null) {
            url_files = "views/materias2Ajax.jsp";
        } else if (request.getParameter("btnCalificaciones")!=null){
            url_files = "views/capturarCalificaciones.jsp";
        } else if (request.getParameter("btnDCalificaciones")!=null){
            url_files = "views/resumenC.jsp";
        } else if(request.getParameter("btnCerrarSession")!=null) {
            url_files = "views/login.jsp";
        } else {
            url_files = "views/index.jsp";
        } 
        
        
        if(request.getParameter("btnAlumnoNuevo")!=null){
            url_files = "views/alumnos.jsp";    

            Alumnos alumno = new Alumnos();
            alumno.setMatricula(request.getParameter("tfMatricula"));
            alumno.setNombre(request.getParameter("tfNombre"));
            alumno.setPaterno(request.getParameter("tfPaterno"));
            alumno.setMaterno(request.getParameter("tfMaterno"));
            alumno.setSexo(request.getParameter("tfSexo"));
            alumno.setFechaNac(request.getParameter("tfFechaNac"));
            alumno.setTelefono(request.getParameter("tfTelefono"));
            alumno.setDireccion(request.getParameter("tfDireccion"));
            alumno.setCorreo(request.getParameter("tfCorreo"));

            AlumnosDAO alumnosDao = new AlumnosDAO();
            alumnosDao.agregar(alumno);

        }
        if (request.getParameter("btnMateriaNueva") != null) {
            //System.out.println("\nEntrando en la condición");

            Materias materia = new Materias();
            materia.setClaveMateria(request.getParameter("tfClaveMat"));
            materia.setNombre(request.getParameter("tfNombre"));
            materia.setCuatrimestre(request.getParameter("tfCuatrimestre"));

            //System.out.println("Clave: " + materia.getClaveMateria());
            //System.out.println("Nombre: " + materia.getNombre());
            //System.out.println("Cuatrimestre: " + materia.getCuatrimestre());

            MateriasDAO materiasDao = new MateriasDAO();
            materiasDao.agregar(materia);

            url_files = "views/materias.jsp";
        }        
        
        if(request.getParameter("btnActulizarMateria")!=null)
        {
            Materias materia = new Materias();
            String claveMatOld = request.getParameter("tfClaveMateriaOld");
            materia.setClaveMateria(request.getParameter("tfClaveMateria"));
            materia.setNombre(request.getParameter("tfNombre"));
            materia.setCuatrimestre(request.getParameter("tfCuatrimestre"));
            
            System.out.println("Clave de materia en el servlet: " + claveMatOld);
            MateriasDAO materiasDao = new MateriasDAO();
            materiasDao.editar(materia, claveMatOld);
            
            url_files = "views/materias.jsp";
        }
        
        if(request.getParameter("btnActulizarAlumno")!=null)
        {
            Alumnos alumno = new Alumnos();
            String matricula = request.getParameter("tfMatriculaOld");
            
            alumno.setMatricula(request.getParameter("tfMatricula"));
            alumno.setNombre(request.getParameter("tfNombre"));
            alumno.setPaterno(request.getParameter("tfPaterno"));
            alumno.setMaterno(request.getParameter("tfMaterno"));
            alumno.setSexo(request.getParameter("tfSexo"));
            alumno.setFechaNac(request.getParameter("tfFechaNac"));
            alumno.setTelefono(request.getParameter("tfTelefono"));
            alumno.setDireccion(request.getParameter("tfDireccion"));
            alumno.setCorreo(request.getParameter("tfCorreo"));
            
            // System.out.println("Matricula en el servlet: " + matricula);
            AlumnosDAO alumnosDao = new AlumnosDAO();
            alumnosDao.editar(alumno, matricula);
            
            url_files = "views/alumnos.jsp";
        }
        
        accion=request.getParameter("accion");
        
        if(accion!=null && accion.equalsIgnoreCase("resumen")){
            url_files = "views/calificaciones.jsp";
        } else if (accion!=null && accion.equalsIgnoreCase("boleta")) {
            url_files = "views/boleta.jsp";
        }
        
        if(accion!=null && accion.equalsIgnoreCase("editar"))
        {
           request.setAttribute("claveMateria", request.getParameter("claveMateria"));
           url_files = "views/materiasEditar.jsp";
        } else if(accion!=null && accion.equalsIgnoreCase("eliminar")) {
            String claveMateria = request.getParameter("claveMateria");
            MateriasDAO materiasDao = new MateriasDAO();
            materiasDao.eliminar(claveMateria);
            url_files = "views/materias.jsp";
        } else if(accion!=null && accion.equalsIgnoreCase("regresar")) {
            url_files = "views/materias.jsp";
        }
        
        
        if(accion!=null && accion.equalsIgnoreCase("editarM")){
           request.setAttribute("matricula", request.getParameter("matricula"));
           url_files = "views/alumnosEditar.jsp";
        } else if(accion!=null && accion.equalsIgnoreCase("eliminarM"))
        {
            String matricula = request.getParameter("matricula");
            AlumnosDAO alumnosDao = new AlumnosDAO();
            alumnosDao.eliminar(matricula);
            url_files = "views/alumnos.jsp";
        } else if(accion!=null && accion.equalsIgnoreCase("regresarM")) {
            url_files = "views/alumnos.jsp";
        }

        request.getRequestDispatcher(url_files).forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
