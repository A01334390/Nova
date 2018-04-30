/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BasicElements.Paciente;
import BasicElements.Usuario;
import BasicElements.formaGeriatria;
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
public class geriatria extends HttpServlet {

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
            out.println("<title>Servlet geriatria</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet geriatria at " + request.getContextPath() + "</h1>");
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
                request.setAttribute("usuario",us);
                request.setAttribute("paciente",pac);
                request.setAttribute("show",false);
                RequestDispatcher req = request.getRequestDispatcher("/GeriatraViews/geriatraForm.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(geriatria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (request.getParameter("action").equals("edit")) {
            try {
                String idForm = request.getParameter("idForm");
                String idUsuario = request.getParameter("idUsuario");
                String idPaciente = request.getParameter("idPaciente");
                formaGeriatria forma = Handler.formaGeriatriaSearch(idForm);
                Usuario us = Handler.userSearchid(idUsuario, "*");
                Paciente pac = Handler.pacienteSearchid(idPaciente, "*");
                request.setAttribute("forma", forma);
                request.setAttribute("usuario",us);
                request.setAttribute("paciente",pac);
                request.setAttribute("show",false);
                RequestDispatcher req = request.getRequestDispatcher("/GeriatraViews/geriatraForm.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(geriatria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (request.getParameter("action").equals("erase")) {
            try {
                String formID = request.getParameter("idForm");
                Handler.deleteFormaGeriatria(formID);
                RequestDispatcher req = request.getRequestDispatcher("/PacienteViews/pacienteAll.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(geriatria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(request.getParameter("action").equals("show")){
            try {
                String formID = request.getParameter("idForm");
                String idUsuario = request.getParameter("idUsuario");
                String idPaciente = request.getParameter("idPaciente");
                formaGeriatria forma = Handler.formaGeriatriaSearch(formID);
                Usuario us = Handler.userSearchid(idUsuario, "*");
                Paciente pac = Handler.pacienteSearchid(idPaciente, "*");
                request.setAttribute("forma", forma);
                request.setAttribute("forma", forma);
                request.setAttribute("usuario",us);
                request.setAttribute("paciente",pac);
                request.setAttribute("show",true);
                RequestDispatcher req = request.getRequestDispatcher("/GeriatraViews/geriatraForm.jsp");
                req.forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(geriatria.class.getName()).log(Level.SEVERE, null, ex);
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
            String formID = request.getParameter("idForm");
            if (Handler.formaGeriatriaSearch(formID) == null) {
                formaGeriatria forma = null;
                try {
                    String idUsuario = request.getParameter("idUsuario");
                    String idPaciente = request.getParameter("idPaciente");
                    forma = new formaGeriatria(1000, request.getParameter("katz"), request.getParameter("katz_interpretacion"), request.getParameter("barthel"), request.getParameter("barthel_interpretacion"), request.getParameter("lawtonBrody"), request.getParameter("lawtonBrody_interpretacion"), request.getParameter("estadoMental"), request.getParameter("estadoMental_interpretacion"), request.getParameter("escalaDepresion"), request.getParameter("escalaDepresion_interpretacion"), request.getParameter("cribadoNutricional"), request.getParameter("cribadoNutricional_interpretacion"), request.getParameter("pruebaDesempenio"), request.getParameter("pruebaDesempenio_interpretacion"), request.getParameter("levantateAnda"), request.getParameter("levantateAnda_interpretacion"), new SimpleDateFormat("dd-MM-yy").parse(request.getParameter("fechaLlenado")), Integer.parseInt(idUsuario), Integer.parseInt(idPaciente));
                } catch (ParseException ex) {
                    Logger.getLogger(geriatria.class.getName()).log(Level.SEVERE, null, ex);
                }
                Handler.addFormaGeriatria(forma);
            } else {
                formaGeriatria forma = null;
                try {
                    forma = new formaGeriatria(Integer.parseInt("-1"), request.getParameter("katz"), request.getParameter("katz_interpretacion"), request.getParameter("barthel"), request.getParameter("barthel_interpretacion"), request.getParameter("lawthonBrody"), request.getParameter("lawthonBrody_interpretacion"), request.getParameter("estadoMental"), request.getParameter("estadoMental_interpretacion"), request.getParameter("escalaDepresion"), request.getParameter("escalaDepresion_interpretacion"), request.getParameter("cribadoNutricional"), request.getParameter("cribadoNutricional_interpretacion"), request.getParameter("pruebaDesempenio"), request.getParameter("pruebaDesempenio_interpretacion"), request.getParameter("levantateAnda"), request.getParameter("levantateAnda_interpretacion"), new SimpleDateFormat("dd-MM-yy").parse(request.getParameter("fechaLlenado")), Integer.parseInt(request.getParameter("idUsuario")), Integer.parseInt(request.getParameter("idPaciente")));
                } catch (ParseException ex) {
                    Logger.getLogger(geriatria.class.getName()).log(Level.SEVERE, null, ex);
                }
                Handler.updateFormaGeriatria(forma);
            }
            RequestDispatcher req = request.getRequestDispatcher("/PacienteViews/pacienteAll.jsp");
            req.forward(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(geriatria.class.getName()).log(Level.SEVERE, null, ex);
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
