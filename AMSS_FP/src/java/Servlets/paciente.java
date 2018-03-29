/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BasicElements.Paciente;
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
public class paciente extends HttpServlet {

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
            out.println("<title>Servlet paciente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet paciente at " + request.getContextPath() + "</h1>");
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
            request.setAttribute("paciente", null);
            RequestDispatcher req = request.getRequestDispatcher("/PacienteViews/pacienteForm.jsp");
            req.forward(request, response);
        }
        if (request.getParameter("action").equals("edit")) {
            String pacienteID = request.getParameter("username");
            Paciente paciente = Handler.pacienteSearch(pacienteID, "*");
            request.setAttribute("paciente", paciente);
            RequestDispatcher req = request.getRequestDispatcher("/PacienteViews/pacienteForm.jsp");
            req.forward(request, response);
        }
        if (request.getParameter("action").equals("erase")) {
            String pacienteID = request.getParameter("username");
            Handler.deletePaciente(pacienteID);
            RequestDispatcher req = request.getRequestDispatcher("/home.jsp");
            req.forward(request, response);
        }
        if (request.getParameter("action").equals("view")) {
            String pacienteID = request.getParameter("username");
            Paciente paciente = Handler.pacienteSearch(pacienteID, "*");
            request.setAttribute("paciente", paciente);
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
        String id = request.getParameter("usuario");
        if (Handler.pacienteSearch(id, "*") == null) {
            Paciente pac = null;
            try {
                pac = new Paciente(1000, Integer.parseInt(request.getParameter("genero")), Integer.parseInt(request.getParameter("estadoCivil")), Integer.parseInt(request.getParameter("cohabitacion")), request.getParameter("primerNombre"), request.getParameter("segundoNombre"), request.getParameter("usuario"), request.getParameter("email"), request.getParameter("nacionalidad"), request.getParameter("estadoNacimiento"), request.getParameter("tipoSangre"), request.getParameter("afiliacionMedica"), request.getParameter("amai"), new SimpleDateFormat("dd/MM/yy").parse(request.getParameter("fechaDeNacimiento")));
            } catch (ParseException ex) {
                Logger.getLogger(paciente.class.getName()).log(Level.SEVERE, null, ex);
            }
            Handler.addPaciente(pac);
        } else {
            Paciente pac = null;
            try {
                pac = new Paciente(1000, Integer.parseInt(request.getParameter("genero")), Integer.parseInt(request.getParameter("estadoCivil")), Integer.parseInt(request.getParameter("cohabitacion")), request.getParameter("primerNombre"), request.getParameter("segundoNombre"), request.getParameter("usuario"), request.getParameter("email"), request.getParameter("nacionalidad"), request.getParameter("estadoNacimiento"), request.getParameter("tipoSangre"), request.getParameter("afiliacionMedica"), request.getParameter("amai"), new SimpleDateFormat("dd/MM/yy").parse(request.getParameter("fechaDeNacimiento")));
            } catch (ParseException ex) {
                Logger.getLogger(paciente.class.getName()).log(Level.SEVERE, null, ex);
            }
            Handler.updatePaciente(pac);
        }
        response.sendRedirect("home.jsp");
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/index.jsp");
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
