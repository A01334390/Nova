<%-- 
    Document   : reporteAll
    Created on : May 1, 2018, 2:40:35 PM
    Author     : Luna
--%>

<%@page import="BasicElements.Reporte"%>
<%@page import="DatabaseManager.Handler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
        if (session.getAttribute("currentSessionName") == null) {
            session.setAttribute("success", false);
            RequestDispatcher req = request.getRequestDispatcher("/login.jsp");
            req.forward(request, response);
        }
    %>
<html>
    <head>
        <!-- Basic Page Needs
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <meta charset="utf-8">
        <title>Nova - Pagina de Reportes</title>
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Mobile Specific Metas
      –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- FONT
      –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <link href="//fonts.googleapis.com/css?family=Raleway:400,300,600" rel="stylesheet" type="text/css">

        <!-- CSS
      –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <link rel="stylesheet" href="css/normalize.css" type="text/css">
        <link rel="stylesheet" href="css/skeleton.css" type="text/css">
        <link rel="stylesheet" href="css/custom.css" type="text/css">
        <!-- Favicon
      –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <link rel="icon" type="image/png" href="images/favicon.png">

    </head>
    <body>
        <!-- NAV BAR-->
        <div class="navbar-spacer"></div>
        <nav class="navbar">
            <div class="container">
                <ul class="navbar-list">
                    <li class="navbar-item">
                        <a class="navbar-link" href="index.jsp">Nova</a>
                    </li>
                </ul>
                <ul class="navbar-list">
                    <li class="navbar-item">
                        <a class="navbar-link" href="backbutt?action=returnPacienteAll">Regresar</a>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- / NAV BAR -->
        <div class="container">
            <div class="six columns" style="margin-top: 15%">
                <h1>Bienvenido a la pagina de reportes, <%=session.getAttribute("currentSessionName")%></h1>
                <h5> Aqui podras ver los reportes mas recientes y tendras la capacidad de crear nuevos. </h5>
            </div>
        </div>
        <div class='container'>
            <div class='row'>
                <a href='reporte?action=create&username=<%=session.getAttribute("paciente")%>'>Crear un nuevo reporte</a>
                <table>
                    <thead>
                        <th>Nombre de usuario</th>
                        <th>Fecha de llenado</th>
                        <th>Acciones</th>
                    </thead>
                    <tbody>
                <%
                Reporte[] reportes = Handler.getAllReportes(session.getAttribute("paciente").toString());
                for(int i = 0 ; i < reportes.length ; i++){
                %>
                <tr>
                    <td><%=reportes[i].getUsuario()%></td>
                    <td><%=reportes[i].getFechaLlenado()%></td>
                    <td>
                        <a href='reporte?action=show&idreportePaciente=<%=reportes[i].getIdreportePaciente()%>'>Ver reporte</a>
                    </td>
                </tr>
                <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
