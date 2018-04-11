/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BasicElements.Fitbit;
import DatabaseManager.Handler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luna
 */
public class fitbit extends HttpServlet {

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
            out.println("<title>Servlet fitbit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet fitbit at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("action").equals("add")) {
            request.setAttribute("fitbit", null);
            RequestDispatcher req = request.getRequestDispatcher("/FitbitViews/fitbitForm.jsp");
            req.forward(request, response);
        }
        
        if(request.getParameter("action").equals("show")){
            Fitbit fitbit = Handler.getAllFitbit();
            RequestDispatcher req = request.getRequestDispatcher("/FitbitViews/fitbit-connection.jsp");
            req.forward(request, response);
        }   
        if (request.getParameter("action").equals("erase")) {
            String OAUTH_CLIENTID = request.getParameter("OAUTH_CLIENTID");
            Handler.deleteFitbit(OAUTH_CLIENTID);
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/FitbitViews/fitbitForm.jsp");
            if (disp != null) {
                disp.include(request, response);
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
        Fitbit fitbit = new Fitbit(1000, request.getParameter("FITBIT_URL"), request.getParameter("FITBIT_API_URL"), request.getParameter("OAUTH_CLIENTID"), request.getParameter("CLIENT_SECRET"), request.getParameter("REDIRECT_URI"), request.getParameter("EXPIRATION_TIME"));
        Handler.addFitbit(fitbit);
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/adminHome.jsp");
        if (disp != null) {
            disp.include(request, response);
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