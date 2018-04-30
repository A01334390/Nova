/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BasicElements.Paciente;
import BasicElements.Usuario;
import BasicElements.formaFragilidad;
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
public class nutricion extends HttpServlet {

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
            out.println("<title>Servlet nutricion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet nutricion at " + request.getContextPath() + "</h1>");
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
            try {
                request.setAttribute("forma", null);
                String idUsuario = request.getParameter("idUsuario");
                String idPaciente = request.getParameter("idPaciente");
                Usuario us = Handler.userSearchid(idUsuario, "*");
                Paciente pac = Handler.pacienteSearchid(idPaciente, "*");
                request.setAttribute("usuario", us);
                request.setAttribute("paciente", pac);
                request.setAttribute("show", false);
                RequestDispatcher req = request.getRequestDispatcher("/NutriologoViews/fragilidadForm.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(nutricion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (request.getParameter("action").equals("edit")) {
            try {
                String idForm = request.getParameter("idForm");
                String idUsuario = request.getParameter("idUsuario");
                String idPaciente = request.getParameter("idPaciente");
                Usuario us = Handler.userSearchid(idUsuario, "*");
                Paciente pac = Handler.pacienteSearchid(idPaciente, "*");
                formaFragilidad ff = Handler.formaFragilidadSearch(idForm);
                request.setAttribute("usuario", us);
                request.setAttribute("paciente", pac);
                request.setAttribute("forma", ff);
                request.setAttribute("show", false);
                RequestDispatcher req = request.getRequestDispatcher("/NutriologoViews/fragilidadForm.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(nutricion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (request.getParameter("action").equals("erase")) {
            try {
                request.setAttribute("forma", null);
                String idForm = request.getParameter("idForm");
                Handler.deleteFormaFragilidad(idForm);
                RequestDispatcher req = request.getRequestDispatcher("/PacienteViews/pacienteAll.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(nutricion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (request.getParameter("action").equals("show")) {
            try {
                String idForm = request.getParameter("idForm");
                String idUsuario = request.getParameter("idUsuario");
                String idPaciente = request.getParameter("idPaciente");
                Usuario us = Handler.userSearchid(idUsuario, "*");
                Paciente pac = Handler.pacienteSearchid(idPaciente, "*");
                formaFragilidad ff = Handler.formaFragilidadSearch(idForm);
                request.setAttribute("usuario", us);
                request.setAttribute("paciente", pac);
                request.setAttribute("forma", ff);
                request.setAttribute("show", true);
                RequestDispatcher req = request.getRequestDispatcher("/NutriologoViews/fragilidadForm.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(nutricion.class.getName()).log(Level.SEVERE, null, ex);
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
            String formID = request.getParameter("idevaluacionFragilidad");
            if (Handler.formaFragilidadSearch(formID) == null) {
                formaFragilidad forma = null;
                try {
                    forma = new formaFragilidad(1000, Integer.parseInt(request.getParameter("pobreResistencia")), Integer.parseInt(request.getParameter("actividadFisica")), request.getParameter("perdidaPeso"), request.getParameter("perdidaPeso_interpretacion"), request.getParameter("pobreResistencia_interpretacion"), request.getParameter("velocidadMarcha"), request.getParameter("velocidadMarcha_interpretacion"), request.getParameter("fuerzaPresion"), request.getParameter("fuerzaPresion_interpretacion"), request.getParameter("actividadFisica_interpretacion"), request.getParameter("diagnostico"), request.getParameter("evaluacionFuncional"), request.getParameter("evaluacionCognitiva"), request.getParameter("evaluacionNutricional"), request.getParameter("evaluacionDeFragilidad"), new SimpleDateFormat("dd-MM-yy").parse(request.getParameter("fechaLlenado")), Integer.parseInt(request.getParameter("idUsuario")), Integer.parseInt(request.getParameter("idPaciente")));
                } catch (ParseException ex) {
                    Logger.getLogger(nutricion.class.getName()).log(Level.SEVERE, null, ex);
                }
                Handler.addFormaFragilidad(forma);
            } else {
                formaFragilidad forma = null;
                try {
                    forma = new formaFragilidad(Integer.parseInt(request.getParameter("idevaluacionFragilidad")), Integer.parseInt(request.getParameter("pobreResistencia")), Integer.parseInt(request.getParameter("actividadFisica")), request.getParameter("perdidaPeso"), request.getParameter("perdidaPeso_interpretacion"), request.getParameter("pobreResistencia_interpretacion"), request.getParameter("velocidadMarcha"), request.getParameter("velocidadMarcha_interpretacion"), request.getParameter("fuerzaPresion"), request.getParameter("fuerzaPresion_interpretacion"), request.getParameter("actividadFisica_interpretacion"), request.getParameter("diagnostico"), request.getParameter("evaluacionFuncional"), request.getParameter("evaluacionCognitiva"), request.getParameter("evaluacionNutricional"), request.getParameter("evaluacionDeFragilidad"), new SimpleDateFormat("dd-MM-yy").parse(request.getParameter("fechaLlenado")), Integer.parseInt(request.getParameter("idUsuario")), Integer.parseInt(request.getParameter("idPaciente")));
                } catch (ParseException ex) {
                    Logger.getLogger(nutricion.class.getName()).log(Level.SEVERE, null, ex);
                }
                Handler.updateFormaFragilidad(forma);
            }
            RequestDispatcher req = request.getRequestDispatcher("/PacienteViews/pacienteAll.jsp");
            req.forward(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(nutricion.class.getName()).log(Level.SEVERE, null, ex);
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
