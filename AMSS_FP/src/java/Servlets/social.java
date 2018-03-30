/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BasicElements.Paciente;
import BasicElements.Usuario;
import BasicElements.formaGerontologia;
import DatabaseManager.Handler;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luna
 */
public class social extends HttpServlet {

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
            out.println("<title>Servlet social</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet social at " + request.getContextPath() + "</h1>");
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
            request.setAttribute("forma", null);
            String idUsuario = request.getParameter("idUsuario");
            String idPaciente = request.getParameter("idPaciente");
            Usuario us = Handler.userSearchid(idUsuario, "*");
            Paciente pac = Handler.pacienteSearchid(idPaciente, "*");
            request.setAttribute("usuario", us);
            request.setAttribute("paciente", pac);
            request.setAttribute("show", false);
            RequestDispatcher req = request.getRequestDispatcher("/SocialViews/gerontologiaForm.jsp");
            req.forward(request, response);
        }
        if (request.getParameter("action").equals("edit")) {
            String idUsuario = request.getParameter("idUsuario");
            String idPaciente = request.getParameter("idPaciente");
            String idForm = request.getParameter("idForm");
            Usuario us = Handler.userSearchid(idUsuario, "*");
            Paciente pac = Handler.pacienteSearchid(idPaciente, "*");
            formaGerontologia form = Handler.formaGerontologiaSearch(idForm);
            request.setAttribute("usuario", us);
            request.setAttribute("paciente", pac);
            request.setAttribute("forma", form);
            request.setAttribute("show", false);
            RequestDispatcher req = request.getRequestDispatcher("/SocialViews/gerontologiaForm.jsp");
            req.forward(request, response);
        }
        if (request.getParameter("action").equals("show")) {
            String idUsuario = request.getParameter("idUsuario");
            String idPaciente = request.getParameter("idPaciente");
            String idForm = request.getParameter("idForm");
            Usuario us = Handler.userSearchid(idUsuario, "*");
            Paciente pac = Handler.pacienteSearchid(idPaciente, "*");
            formaGerontologia form = Handler.formaGerontologiaSearch(idForm);
            request.setAttribute("usuario", us);
            request.setAttribute("paciente", pac);
            request.setAttribute("forma", form);
            request.setAttribute("show", true);
            RequestDispatcher req = request.getRequestDispatcher("/SocialViews/gerontologiaForm.jsp");
            req.forward(request, response);
        }
        if (request.getParameter("action").equals("erase")) {
            String idForm = request.getParameter("idForm");
            Handler.deleteFormaGerontologia(idForm);
            RequestDispatcher req = request.getRequestDispatcher("/PacienteViews/pacienteAll.jsp");
            req.forward(request, response);
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
        String formId = request.getParameter("idevaluacionFragilidad");
        if (Handler.formaGerontologiaSearch(formId) == null) {
            formaGerontologia forma = null;
            try {
                forma = new formaGerontologia(1000, request.getParameter("dispositivosUso"), request.getParameter("dispositivoMayorUso"), request.getParameter("frecuenciaUso"), request.getParameter("actividadesUso"), request.getParameter("usosFavorecer"), request.getParameter("apoyoSocialPercibido"), request.getParameter("actividadesComunitarias"), request.getParameter("impresionDiagnostica"), new SimpleDateFormat("dd/MM/yy").parse(request.getParameter("fechaLlenado")), Integer.parseInt(request.getParameter("idUsuario")), Integer.parseInt(request.getParameter("idPaciente")));
            } catch (ParseException ex) {
                Logger.getLogger(social.class.getName()).log(Level.SEVERE, null, ex);
            }
            Handler.addFormaGerontologia(forma);
        } else {
            formaGerontologia forma = null;
            try {
                forma = new formaGerontologia(Integer.parseInt(request.getParameter("idevaluacionFragilidad")), request.getParameter("dispositivosUso"), request.getParameter("dispositivoMayorUso"), request.getParameter("frecuenciaUso"), request.getParameter("actividadesUso"), request.getParameter("usosFavorecer"), request.getParameter("apoyoSocialPercibido"), request.getParameter("actividadesComunitarias"), request.getParameter("impresionDiagnostica"), new SimpleDateFormat("dd/MM/yy").parse(request.getParameter("fechaLlenado")), Integer.parseInt(request.getParameter("idUsuario")), Integer.parseInt(request.getParameter("idPaciente")));
            } catch (ParseException ex) {
                Logger.getLogger(social.class.getName()).log(Level.SEVERE, null, ex);
            }
            Handler.updateFormaGerontologia(forma);
        }
        response.sendRedirect("PacienteViews/pacienteAll.jsp");
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/PacienteViews/pacienteAll.jsp");
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