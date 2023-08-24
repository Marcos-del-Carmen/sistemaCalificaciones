package controlador;

import dao.AccesoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SvLogin extends HttpServlet {

    private String url_file = "";
    AccesoDAO validar = new AccesoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("correo");
        String password = request.getParameter("contrasena");

        // Verificar las credenciales (esto es solo un ejemplo, no es seguro)
        if ("usuario" .equals(username) && "contraseña" .equals(password)) {
            // Crear una sesión o recuperarla si ya existe
            HttpSession session = request.getSession(true);
            session.setAttribute("correo", username);

            // Redirigir al área protegida
            response.sendRedirect("views/index.jsp");
        }
        
        //url_file = "views/login.jsp";
        //request.getRequestDispatcher(url_file).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
