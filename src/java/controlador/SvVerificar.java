package controlador;

import dao.AccesoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SvVerificar extends HttpServlet {
    private String url_file;
    AccesoDAO validar = new AccesoDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); 
        PrintWriter out = response.getWriter();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        AccesoDAO accesoDAO = new AccesoDAO();
        boolean usuarioValido = false;

        try {
            usuarioValido = accesoDAO.validarCorreo(correo, contrasena);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
            System.out.println("Entre a la condicion");
        if (usuarioValido) {
            // Si el usuario es válido, almacena el correo en la sesión
            HttpSession sesion = request.getSession();
            sesion.setAttribute("correo", correo);
            url_file="views/index.jsp";
            
            String mensajeError = "";
            request.setAttribute("mensajeError", mensajeError);
        } else {
            String mensajeError = "<div class=\"alert-modal\">"
                                + " <p>El correo <b>"+correo+"</b> o la contraseña <b>"+ contrasena + "</b> pueda que no existan</p>"
                                + "</div>";
            request.setAttribute("mensajeError", mensajeError);
            url_file="views/login.jsp";
        }
        request.getRequestDispatcher(url_file).forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
