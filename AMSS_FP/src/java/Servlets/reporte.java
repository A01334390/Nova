/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BasicElements.Paciente;
import BasicElements.Reporte;
import BasicElements.Usuario;
import BasicElements.formaFragilidad;
import BasicElements.formaGeriatria;
import BasicElements.formaGerontologia;
import BasicElements.valoracionFitbit;
import DatabaseManager.Handler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
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
public class reporte extends HttpServlet {

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
            out.println("<title>Servlet reporte</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet reporte at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("action").equals("create")) {
            RequestDispatcher req = request.getRequestDispatcher("/ReporteViews/reporteParams.jsp");
            request.getSession().setAttribute("paciente", request.getParameter("username"));
            req.forward(request, response);
        }
        if (request.getParameter("action").equals("showAll")) {
            RequestDispatcher req = request.getRequestDispatcher("/ReporteViews/reporteAll.jsp");
            request.getSession().setAttribute("paciente", request.getParameter("username"));
            req.forward(request, response);
        }
        if (request.getParameter("action").equals("show")) {
            try {
                Reporte reporter = Handler.searchReporte(request.getParameter("idreportePaciente"));
                request.getSession().setAttribute("paciente", reporter.getUsuario());
                request.getSession().setAttribute("geriatria", reporter.getIdGeriatra());
                request.getSession().setAttribute("nutricion", reporter.getIdNutricion());
                request.getSession().setAttribute("movilidad", reporter.getIdMovilidad());
                request.getSession().setAttribute("gerontologia", reporter.getIdGerontologia());
                request.getSession().setAttribute("conclusiones", reporter.getConclusion());
                RequestDispatcher req = request.getRequestDispatcher("/ReporteViews/reporteFinal.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(reporte.class.getName()).log(Level.SEVERE, null, ex);
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
        if (request.getParameter("act").equals("gen")) {
            //Send the forms
            request.getSession().setAttribute("paciente", request.getParameter("pacienteUsername"));
            request.getSession().setAttribute("geriatria", request.getParameter("geriatria"));
            request.getSession().setAttribute("nutricion", request.getParameter("nutriologia"));
            request.getSession().setAttribute("movilidad", request.getParameter("movilidad"));
            request.getSession().setAttribute("gerontologia", request.getParameter("gerontologia"));
            request.getSession().setAttribute("conclusiones", null);
            RequestDispatcher req = request.getRequestDispatcher("/ReporteViews/reporteFinal.jsp");
            req.forward(request, response);
        } else {
            try {
                String paciente = request.getParameter("paciente");
                String geriatria = request.getParameter("geriatria");
                String nutricion = request.getParameter("nutricion");
                String movilidad = request.getParameter("movilidad");
                String gerontologia = request.getParameter("gerontologia");
                String conclusiones = request.getParameter("conclusion");
                //Yes, I think we did it
                request.getSession().setAttribute("pacienteUsername", paciente);
                Date date = Calendar.getInstance().getTime();
                Reporte repo = new Reporte(1000, paciente, Integer.parseInt(geriatria), Integer.parseInt(nutricion), Integer.parseInt(movilidad), Integer.parseInt(gerontologia), conclusiones, date);
                Handler.addReporte(repo);
                //Send him back
                RequestDispatcher req = request.getRequestDispatcher("/PacienteViews/pacienteAll.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(reporte.class.getName()).log(Level.SEVERE, null, ex);
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
