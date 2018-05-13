/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BasicElements.Usuario;
import DatabaseManager.Handler;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author Luna
 */
@MultipartConfig(maxFileSize = 16177215)
public class admin extends HttpServlet {

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
            out.println("<title>Servlet admin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet admin at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("action").equalsIgnoreCase("add")) {
            request.setAttribute("usuario", null);
            RequestDispatcher req = request.getRequestDispatcher("/AdminViews/userForm.jsp");
            req.forward(request, response);
        }
        if (request.getParameter("action").equals("edit")) {
            try {
                String usuarioID = request.getParameter("username");
                Usuario usuario = Handler.userSearch(usuarioID, "*");
                request.setAttribute("usuario", usuario);
                RequestDispatcher req = request.getRequestDispatcher("/AdminViews/userForm.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (request.getParameter("action").equals("erase")) {
            try {
                String usuarioID = request.getParameter("username");
                Handler.deleteUsuario(usuarioID);
                RequestDispatcher req = request.getRequestDispatcher("/adminHome.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("username") == null) {
            try {
                String id = request.getParameter("usuario");
                if (Handler.userSearch(id, "*") == null) {
                    Usuario user = null;
                    try {
                        user = new Usuario(1000, request.getParameter("primerNombre"), request.getParameter("segundoNombre"), request.getParameter("email"), request.getParameter("usuario"), new SimpleDateFormat("dd-MM-yy").parse(request.getParameter("fechaNacimiento")), new SimpleDateFormat("dd-MM-yy").parse(request.getParameter("fechaValidez")), Integer.parseInt(request.getParameter("privilegio")), null);
                    } catch (ParseException ex) {
                        Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Handler.addUser(user, request.getParameter("password"));
                } else {
                    Usuario user = null;
                    try {
                        user = new Usuario(1000,
                                request.getParameter("primerNombre"),
                                request.getParameter("segundoNombre"),
                                request.getParameter("email"),
                                request.getParameter("usuario"),
                                new SimpleDateFormat("dd-MM-yy").parse(request.getParameter("fechaNacimiento")),
                                new SimpleDateFormat("dd-MM-yy").parse(request.getParameter("fechaValidez")),
                                Integer.parseInt(request.getParameter("privilegio")),
                                null);
                        Handler.updateUser(user, request.getParameter("password"), 0);
                    } catch (ParseException ex) {
                        Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                RequestDispatcher req = request.getRequestDispatcher("/adminHome.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                String usuarioID = request.getParameter("username");
                Usuario usuario = Handler.userSearch(usuarioID, "*");
                request.setAttribute("usuario", usuario);
                RequestDispatcher req = request.getRequestDispatcher("/AdminViews/userForm.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
