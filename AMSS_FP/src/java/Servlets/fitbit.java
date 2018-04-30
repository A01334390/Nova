/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BasicElements.Fitbit;
import BasicElements.valoracionFitbit;
import DatabaseManager.Handler;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
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
        if (request.getParameter("action").equals("graph")) {
            request.getSession().setAttribute("time", request.getParameter("time"));
            request.getSession().setAttribute("lapsus", request.getParameter("lapsus"));
            RequestDispatcher req = request.getRequestDispatcher("/FitbitViews/fitbit-connection.jsp");
            req.forward(request, response);
        }
        if (request.getParameter("action").equals("show")) {
            try {
                Fitbit fitbit = Handler.getAllFitbit();
                request.setAttribute("fitbit", fitbit);
                RequestDispatcher req = request.getRequestDispatcher("/FitbitViews/fitbit-connection.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(fitbit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (request.getParameter("action").equals("erase")) {
            try {
                String OAUTH_CLIENTID = request.getParameter("OAUTH_CLIENTID");
                Handler.deleteFitbit(OAUTH_CLIENTID);
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/FitbitViews/fitbitForm.jsp");
                if (disp != null) {
                    disp.include(request, response);
                }
            } catch (NamingException ex) {
                Logger.getLogger(fitbit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (request.getParameter("action").equals("see")) {
            request.getSession().setAttribute("valoracionID", request.getParameter("valoracionID"));
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/FitbitViews/valoracionView.jsp");
            if (disp != null) {
                disp.include(request, response);
            }
            ;
        }

        if (request.getParameter("action").equals("eliminate")) {
            try {
                String valoracionID = request.getParameter("valoracionID");
                Handler.deleteValoracionFitbit(valoracionID);
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/PacienteViews/pacienteAll.jsp");
                if (disp != null) {
                    disp.include(request, response);
                }
            } catch (NamingException ex) {
                Logger.getLogger(fitbit.class.getName()).log(Level.SEVERE, null, ex);
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
        if (!request.getParameter("diff").equals("graph")) {
            try {
                Fitbit fitbit = new Fitbit(1000, request.getParameter("FITBIT_URL"), request.getParameter("FITBIT_API_URL"), request.getParameter("OAUTH_CLIENTID"), request.getParameter("CLIENT_SECRET"), request.getParameter("REDIRECT_URI"), request.getParameter("EXPIRATION_TIME"));
                Handler.addFitbit(fitbit);
                RequestDispatcher req = request.getRequestDispatcher("/adminHome.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(fitbit.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                valoracionFitbit vf = null;
                try {
                    String username = request.getParameter("username");
                    String jsonResult = request.getParameter("jsonresult");
                    String time = request.getParameter("time");
                    String lapsus = request.getParameter("lapsus");
                    vf = new valoracionFitbit(10000, username,
                            jsonResult,
                            new SimpleDateFormat("yyyy-MM-dd").parse(time),
                            lapsus);
                } catch (ParseException ex) {
                    Logger.getLogger(fitbit.class.getName()).log(Level.SEVERE, null, ex);
                }
                Handler.addValoracionFitbit(vf);
                RequestDispatcher req = request.getRequestDispatcher("/PacienteViews/pacienteAll.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(fitbit.class.getName()).log(Level.SEVERE, null, ex);
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
