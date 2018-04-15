<%-- 
    Document   : home
    Created on : Mar 28, 2018, 3:07:00 PM
    Author     : Luna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DatabaseManager.Handler"%>
<%@page import="BasicElements.*"%>
<html lang="en">

    <%
        if (session.getAttribute("currentSessionName") == null) {
            response.sendRedirect("/index.jsp");
        }
    %>

    <!-- Basic Page Needs
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
    <meta charset="utf-8">
    <title>Nova - Pagina de Administracion</title>
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

    <!-- Favicon
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
    <link rel="icon" type="image/png" href="images/favicon.png">
    
    <script src="js/site.js"></script>
        <link rel="stylesheet" href="css/custom.css" type="text/css">
</head>
<body>
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
                        <a class="navbar-link" href="index.jsp">Regresar</a>
                    </li>
                </ul>
            </div>
        </nav>
    <div class="container">
        <div class="six columns" style="margin-top: 15%">
            <h1>Bienvenido a la pagina principal, <%=session.getAttribute("currentSessionName")%></h1>
            <h5> Aqui podras ver los pacientes registrados, observar su informacion relacionada y sus formatos. </h5>
        </div>
    </div>

    <div class="container">
        <%if (session.getAttribute("currentPrivilegeLevel").equals(1)) {%>
        <p><a href="paciente?action=add">Agregar nuevo usuario</a></p>
        <% } %>
        <table>
            <thead>
            <th>Nombres</th>
            <th>Apellidos</th>
            <th>Fecha de Nacimiento</th>
            <th>Correo Electronico</th>
            <th>Usuario</th>
            <th>Genero</th>
            <th>Acciones</th>
            </thead>
            <tbody>
            <tbody>
                <%Paciente[] paciente = Handler.getAllPacientes();%>
                <%for (int i = 0; i < paciente.length; i++) {%>
                <tr>
                    <td ><%=paciente[i].getPrimerNombre()%></td>
                    <td ><%=paciente[i].getSegundoNombre()%></td>
                    <td><%=paciente[i].getFechaDeNacimiento()%></td>
                    <td><%=paciente[i].getEmail()%></td>
                    <td><%=paciente[i].getUsuario()%></td>
                    <%
                        String genero;
                        if (paciente[i].getGenero() == 0) {
                            genero = "Hombre";
                        } else if (paciente[i].getGenero() == 1) {
                            genero = "Mujer";
                        } else if (paciente[i].getGenero() == 2) {
                            genero = "Deseo no responder";
                        } else {
                            genero = "N/A";
                        }
                    %>
                    <td><%=genero%></td>
                    <td class="centeredform">
                        <%
                            if (session.getAttribute("currentPrivilegeLevel").equals(1)) {
                        %>
                        <a href="paciente?action=edit&username=<%=paciente[i].getUsuario()%>">Editar /</a>  
                        <a href="paciente?action=erase&username=<%=paciente[i].getUsuario()%>">Eliminar /</a>
                        <%
                            }
                        %>
                        <a href="paciente?action=view&username=<%=paciente[i].getUsuario()%>">Ver Expediente</a></td>
                </tr>
                <% }%>
            </tbody>
            </tbody>
        </table>
    </div>
</body>
</html>
