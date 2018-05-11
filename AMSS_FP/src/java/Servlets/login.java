/*
 * Built in 2017© By Worker's in Kraken Tech Ltd.
 * Compiled and Revised by Advisors outside the company
 * Refer to documentation attached for any other reference on code or anything related to this Source Code
 */
package Servlets;

import BasicElements.*;
import java.security.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DatabaseManager.Handler;
import static DatabaseManager.Handler.loginPaciente;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author a01334390
 */
public class login extends HttpServlet {

    private static String host = "jdbc:mysql://localhost/Pola";
    private static String huser = "root";
    private static String hpassword = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"https://dhbhdrzi4tiry.cloudfront.net/cdn/sites/foundation.min.css\">");
            out.println("<title>Procesando Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div align = \"center\">");
            out.println("<h1> Procesando.. </h1>");
            out.println("<p class=\"show-for-medium\">Esto deberia tomar solo un <em> momento </em></p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnsupportedEncodingException {
        try {
            String isPaciente = request.getParameter("paciente");
            //Make this an MD5 hash
            
            if ("true".equals(isPaciente)) {
                String idPaciente = request.getParameter("idPaciente");
                String apellidoPaterno = request.getParameter("apellidoPaterno");
                if (loginPaciente(idPaciente, apellidoPaterno)) {
                    request.getSession().setAttribute("currentSessionName", "Usuario externo");
                    request.setAttribute("forma", null);
                    request.setAttribute("usuario", 1);
                    request.setAttribute("paciente", request.getParameter("idPaciente"));
                    request.setAttribute("show", false);
                    request.setAttribute("outside", true);
                    RequestDispatcher req = request.getRequestDispatcher("/SocialViews/gerontologiaForm.jsp");
                    req.forward(request, response);
                } else {
                    System.out.print("Not Successful");
                    request.getSession().setAttribute("success", false);
                    RequestDispatcher req = request.getRequestDispatcher("/paclogin.jsp");
                    req.forward(request, response);
                }
            } else {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                if (Handler.userValidation(username, password)) {
                    Usuario us = Handler.userSearch(username, "*");
                    request.getSession().setAttribute("currentSessionName", us.getPrimerNombre());
                    request.getSession().setAttribute("currentPrivilegeLevel", us.getPrivilegio());
                    request.getSession().setAttribute("idUsuario", us.getId());
                    String goTo;
                    if (us.getPrivilegio() == 0) {
                        goTo = "adminHome.jsp";
                    } else {
                        goTo = "home.jsp";
                    }
                    response.sendRedirect(goTo);
                    if(us.getFechaValidez().before(new Date())){
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/" + goTo);
                    if (disp != null) {
                        disp.include(request, response);
                    }
                    }else{
                        
                    }
                    
                } else {
                    System.out.print("Not Successful");
                    request.getSession().setAttribute("success", false);
                    response.sendRedirect("/index.jsp");
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/index.jsp");
                    if (disp != null) {
                        disp.include(request, response);
                    }
                }
            }
        } catch (NamingException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "This processes everything that handles the user's interaction with the database";
    }// </editor-fold>

}
