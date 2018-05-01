<%-- 
    Document   : pacienteAll
    Created on : Mar 29, 2018, 11:51:02 AM
    Author     : Luna
--%>

<%@page import="BasicElements.valoracionFitbit"%>
<%@page import="BasicElements.Paciente"%>
<%@page import="BasicElements.formaGerontologia"%>
<%@page import="BasicElements.formaFragilidad"%>
<%@page import="BasicElements.formaGeriatria"%>
<%@page import="DatabaseManager.Handler"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (session.getAttribute("currentSessionName") == null) {
        response.sendRedirect("/index.jsp");
    }
%>
<html>
    <head>
        <!-- Basic Page Needs
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <meta charset="utf-8">
        <title>Nova - Expediente del paciente</title>
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
        <script src="js/site.js"></script>
        <link rel="stylesheet" href="css/custom.css" type="text/css">

        <!-- Favicon
      –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <link rel="icon" type="image/png" href="images/favicon.png">
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
                        <a class="navbar-link" href="home.jsp">Regresar</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <div class="six columns" style="margin-top: 15%">
                <%String username = session.getAttribute("pacienteUsername").toString();
                    Paciente paciente = Handler.pacienteSearch(username, "*");%>
                <h1>Expediente del paciente #<%=paciente.getPacienteID()%></h1>
                <label>Nombre Completo</label>
                <h5><%=paciente.getPrimerNombre()%> <%=paciente.getSegundoNombre()%></h5>
                <label>Genero</label>
                <p><%
                    int genero = paciente.getGenero();
                    String vals;
                    if (genero == 0) {
                        vals = "Hombre";
                    } else if (genero == 1) {
                        vals = "Mujer";
                    } else if (genero == 3) {
                        vals = "Deseo no mencionarlo";
                    } else {
                        vals = "Otros";
                    }
                    %><%=vals%>
                </p>
                <label>Nombre de usuario</label>
                <p><%=paciente.getUsuario()%></p>
                <label>Nacionalidad</label>
                <p><%=paciente.getNacionalidad()%></p>
                <label>Fecha de Nacimiento</label>
                <p><%=paciente.getFechaDeNacimiento()%></p>
                <label> Tipo de Sangre </label>
                <p><%=paciente.getTipoSangre()%></p>
                <label> Afiliación médica </label>
                <p><%=paciente.getAfiliacionMedica()%></p>
                <label> AMAI </label>
                <p><%=paciente.getAmai()%></p>
                <label>Cohabitacion</label>
                <p><%=paciente.getCohabitacion() == 0 ? "No" : "Si"%></p>
                <label>Estado Civil</label>
                <p><%=paciente.getEstadoCivil() == 0 ? "Soltero/A" : paciente.getEstadoCivil() == 1 ? "Casado/a" : paciente.getEstadoCivil() == 2 ? "Viudo/a" : "Divorciado/a"%></p>
            </div>
        </div>

        <div class ="container">
            <%
                if (session.getAttribute("currentPrivilegeLevel").equals(1)) {
            %>
            <a href="paciente?action=edit&username=<%=paciente.getUsuario()%>">Editar</a> /  
            <a href="paciente?action=erase&username=<%=paciente.getUsuario()%>">Eliminar</a> /
            <a href="reporte?action=create&username=<%=paciente.getUsuario()%>">Hacer Reporte</a> /
            <a href="reporte?action=showAll&username=<%=paciente.getUsuario()%>">Ver todos los reportes</a>
            <%
                }
            %>
        </div>

        <div class="container" style="margin-top: 5%">
            <%
                if (session.getAttribute("currentPrivilegeLevel").equals(1) || session.getAttribute("currentPrivilegeLevel").equals(2)) {
            %>
            <hr>
            <h5> Formatos de Geriatria </h5>
            <a href='geriatria?action=add&idUsuario=<%=session.getAttribute("idUsuario")%>&idPaciente=<%=paciente.getPacienteID()%>'>Agregar nueva valoracion geriatrica</a>
            <table>
                <thead>
                <th>ID Formato</th>
                <th>Fecha de llenado</th>
                <th>Acciones</th>
                </thead>
                <tbody>
                    <%
                        }
                    %>
                <tbody>
                    <%
                        formaGeriatria forma[] = Handler.getAllformaGeriatria(Integer.toString(paciente.getPacienteID()));
                        for (int i = 0; i < forma.length; i++) {
                    %>
                    <tr>
                        <td><%=forma[i].getIdformatoGeriatra()%></td>
                        <td><%=forma[i].getFechaLlenado()%></td>
                        <td>
                            <a href="geriatria?action=show&idForm=<%=forma[i].getIdformatoGeriatra()%>&idUsuario=<%=session.getAttribute("idUsuario")%>&idPaciente=<%=paciente.getPacienteID()%>">Ver</a>
                            <a href="geriatria?action=edit&idForm=<%=forma[i].getIdformatoGeriatra()%>&idUsuario=<%=session.getAttribute("idUsuario")%>&idPaciente=<%=paciente.getPacienteID()%>">Editar</a>
                            <a href="geriatria?action=erase&idForm=<%=forma[i].getIdformatoGeriatra()%>&idUsuario=<%=session.getAttribute("idUsuario")%>&idPaciente=<%=paciente.getPacienteID()%>">Eliminar</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>

        <div class="container" style="margin-top: 5%">
            <%
                if (session.getAttribute("currentPrivilegeLevel").equals(1) || session.getAttribute("currentPrivilegeLevel").equals(3)) {
            %>
            <hr>
            <h5> Formatos de Nutriologia </h5>
            <a href='nutricion?action=add&idUsuario=<%=session.getAttribute("idUsuario")%>&idPaciente=<%=paciente.getPacienteID()%>'>Agregar nueva valoracion de nutricion</a>
            <table>
                <thead>
                <th>ID Formato</th>
                <th>Fecha de llenado</th>
                <th>Acciones</th>
                </thead>
                <%
                    }
                %>
                <tbody>
                    <%
                        formaFragilidad formaFrag[] = Handler.getAllformaFragilidad(Integer.toString(paciente.getPacienteID()));
                        for (int i = 0; i < formaFrag.length; i++) {
                    %>
                    <tr>
                        <td><%=formaFrag[i].getIdevaluacionFragilidad()%></td>
                        <td><%=formaFrag[i].getFechaLlenado()%></td>
                        <td>
                            <a href="nutricion?action=show&idForm=<%=formaFrag[i].getIdevaluacionFragilidad()%>&idUsuario=<%=session.getAttribute("idUsuario")%>&idPaciente=<%=paciente.getPacienteID()%>">Ver</a>
                            <a href="nutricion?action=edit&idForm=<%=formaFrag[i].getIdevaluacionFragilidad()%>&idUsuario=<%=session.getAttribute("idUsuario")%>&idPaciente=<%=paciente.getPacienteID()%>">Editar</a>
                            <a href="nutricion?action=erase&idForm=<%=formaFrag[i].getIdevaluacionFragilidad()%>">Eliminar</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

        </div>

        <div class="container" style="margin-top: 5%">
            <%
                if (session.getAttribute("currentPrivilegeLevel").equals(1) || session.getAttribute("currentPrivilegeLevel").equals(4)) {
            %>
            <hr>
            <h5> Información de movilidad </h5>
            <a href='fitbit?action=show'>Ver información de movilidad del paciente</a>
            <table>
                <thead>
                <th> Fecha pedida </th>
                <th> Periodo Pedido </th>
                <th> Acciones </th>
                </thead>
                <tbody>
                    <%
                        valoracionFitbit vf[] = Handler.getAllValoracionFitbit(session.getAttribute("pacienteUsername").toString());
                        for(int i=0;i<vf.length;i++){                             
                    %>
                    <tr>
                <td><%=vf[i].getFechaPedida()%></td>
                <td><%=vf[i].getTiempoPedido()%></td>
                <td>
                    <a href='fitbit?action=see&valoracionID=<%=vf[i].getIdValoracionFitbit()%>'>Ver</a>
                    <a href='fitbit?action=eliminate&valoracionID=<%=vf[i].getIdValoracionFitbit()%>'>Eliminar</a>
                </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <%
                }
            %>
            
        </div>

        <div class="container" style="margin-top: 5%">
            <%
                if (session.getAttribute("currentPrivilegeLevel").equals(1) || session.getAttribute("currentPrivilegeLevel").equals(4)) {
            %>
            <hr>
            <h5> Formatos de Gerontologia Asociados </h5>
            <a href='social?action=add&idUsuario=<%=session.getAttribute("idUsuario")%>&idPaciente=<%=paciente.getPacienteID()%>'>Agregar nueva valoracion gerontologica</a>
            <table>
                <thead>
                <th>ID Forma</th>
                <th>Fecha de asignacion</th>
                <th>Acciones</th>
                </thead>
                <tbody>
                    <%
                        formaGerontologia formGeront[] = Handler.getAllformaGerontologia(Integer.toString(paciente.getPacienteID()));
                        for (int i = 0; i < formGeront.length; i++) {
                    %>
                    <tr>
                        <td><%=formGeront[i].getIdvaloracionGerontologica()%></td>
                        <td><%=formGeront[i].getFechaLlenado()%></td>
                        <td>
                            <a href="social?action=show&idForm=<%=formGeront[i].getIdvaloracionGerontologica()%>&idUsuario=<%=session.getAttribute("idUsuario")%>&idPaciente=<%=paciente.getPacienteID()%>">Ver</a>
                            <a href="social?action=edit&idForm=<%=formGeront[i].getIdvaloracionGerontologica()%>&idUsuario=<%=session.getAttribute("idUsuario")%>&idPaciente=<%=paciente.getPacienteID()%>">Editar</a>
                            <a href="social?action=erase&idForm=<%=formGeront[i].getIdvaloracionGerontologica()%>">Eliminar</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <%
                }
            %>
        </div>
</html>
