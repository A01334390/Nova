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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        if (request.getParameter("action") != null) {
            if (request.getParameter("action").equals("inventory")) {
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/BasicViews/inventory/inventoryindex.jsp");
                if (disp != null) {
                    disp.include(request, response);
                }
            }
            if (request.getParameter("action").equals("order")) {
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/BasicViews/order/orderHome.jsp");
                if (disp != null) {
                    disp.include(request, response);
                }
            }
            if (request.getParameter("action").equals("advanced")) {
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/AdvancedViews/advancedhome.jsp");
            if (disp != null) {
                    disp.include(request, response);
                }                
            }
            if (request.getParameter("action").equals("material")) {
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/BasicViews/material/materialindex.jsp");
            if (disp != null) {
                    disp.include(request, response);
                }                
            }
            if (request.getParameter("action").equals("user")) {
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/BasicViews/user/userindex.jsp");
                if (disp != null) {
                    disp.include(request, response);
                }
            }
            if (request.getParameter("action").equals("client")) {
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/BasicViews/client/clientindex.jsp");
                if (disp != null) {
                    disp.include(request, response);
                }
            }

            if (request.getParameter("action").equals("advanced")) {
                System.out.println("advanced");
            }
            if (request.getParameter("action").equals("notebook")) {
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/BasicViews/notebook/notebookindex.jsp");
                if (disp != null) {
                    disp.include(request, response);
                }
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //Make this an MD5 hash
        if (Handler.userValidation(username, password)) {
            Usuario us = Handler.userSearch(username,"*");
            request.getSession().setAttribute("currentSessionName", us.getPrimerNombre());
            request.getSession().setAttribute("currentPrivilegeLevel", us.getPrivilegio());
            String goTo;
            if(us.getPrivilegio() == 0){
                goTo = "adminHome.jsp";
            }else{
                goTo = "home.jsp";
            }
            response.sendRedirect(goTo);
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/"+goTo);
            if (disp != null) {
                disp.include(request, response);
            }
        } else {
            System.out.print("Not Successful");
            request.getSession().setAttribute("success",false);
            response.sendRedirect("index.jsp");
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/index.jsp");
            if (disp != null) {
                disp.include(request, response);
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
        return "This processes everything that handles the user's interaction with the database";
    }// </editor-fold>

}
