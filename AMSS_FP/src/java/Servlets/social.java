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
            request.setAttribute("usuario", request.getParameter("idUsuario"));
            request.setAttribute("paciente", request.getParameter("idPaciente"));
            request.setAttribute("show", false);
            request.setAttribute("outside", false);
            RequestDispatcher req = request.getRequestDispatcher("/SocialViews/gerontologiaForm.jsp");
            req.forward(request, response);
        }
        if (request.getParameter("action").equals("edit")) {
            try {
                String idForm = request.getParameter("idForm");
                formaGerontologia form = Handler.formaGerontologiaSearch(idForm);
                request.setAttribute("usuario", request.getParameter("idUsuario"));
                request.setAttribute("paciente", request.getParameter("idPaciente"));
                request.setAttribute("forma", form);
                request.setAttribute("show", false);
                request.setAttribute("outside", false);
                RequestDispatcher req = request.getRequestDispatcher("/SocialViews/gerontologiaForm.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(social.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (request.getParameter("action").equals("show")) {
            try {
                String idForm = request.getParameter("idForm");
                formaGerontologia form = Handler.formaGerontologiaSearch(idForm);
                request.setAttribute("usuario", request.getParameter("idUsuario"));
                request.setAttribute("paciente", request.getParameter("idPaciente"));
                request.setAttribute("forma", form);
                request.setAttribute("show", true);
                request.setAttribute("outside", false);
                RequestDispatcher req = request.getRequestDispatcher("/SocialViews/gerontologiaForm.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(social.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (request.getParameter("action").equals("erase")) {
            try {
                String idForm = request.getParameter("idForm");
                Handler.deleteFormaGerontologia(idForm);
                RequestDispatcher req = request.getRequestDispatcher("/PacienteViews/pacienteAll.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(social.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
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
            RequestDispatcher req;
            if(request.getParameter("outside").equals("true")){
                req = request.getRequestDispatcher("/index.jsp");
            }else{
                req = request.getRequestDispatcher("/PacienteViews/pacienteAll.jsp");
            }
            req.forward(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(social.class.getName()).log(Level.SEVERE, null, ex);
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
