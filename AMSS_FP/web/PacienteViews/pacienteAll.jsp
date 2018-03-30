<%-- 
    Document   : pacienteAll
    Created on : Mar 29, 2018, 11:51:02 AM
    Author     : Luna
--%>

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
        <title>Nova - Expediente de <c:out value="${paciente.getPrimerNombre()}"/></title>
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
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/skeleton.css">

        <!-- Favicon
      –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <link rel="icon" type="image/png" href="images/favicon.png">
    </head>
    <body>
        <div class="container">
            <div class="six columns" style="margin-top: 15%">
                <h1>Expediente del paciente #<c:out value="${paciente.getPacienteID()}"/></h1>
                <label>Nombre Completo</label>
                <h5><c:out value="${paciente.getPrimerNombre()}"/> <c:out value="${paciente.getSegundoNombre()}"/></h5>
                <label>Genero</label>
                <p>${paciente.getGenero()==0 ? 'Hombre' : paciente.getGenero()==1 ? 'Mujer' : paciente.getGenero()==2 ? 'Deseo no mencionarlo' : 'Otros'}</p>
                <label>Nombre de usuario</label>
                <p><c:out value="${paciente.getUsuario()}"/></p>
                <label>Nacionalidad</label>
                <p><c:out value="${paciente.getNacionalidad()}"/></p>
                <label>Fecha de Nacimiento</label>
                <p><c:out value="${paciente.getFechaDeNacimiento()}"/></p>
                <label> Tipo de Sangre </label>
                <p><c:out value="${paciente.getTipoSangre()}"/></p>
                <label> Afiliación médica </label>
                <p><c:out value="${paciente.getAfiliacionMedica()}"/></p>
                <label> AMAI </label>
                <p><c:out value="${paciente.getAmai()}"/></p>
                <label>Cohabitacion</label>
                <p>${paciente.getCohabitacion()==0? 'No' : 'Si'}</p>
                <label>Estado Civil</label>
                <p>${paciente.getEstadoCivil()==0? 'Soltero/a' : paciente.getEstadoCivil()==1? 'Casado/a' : paciente.getEstadoCivil()==2? 'Viudo/a' : 'Divorciado/a'}</p>
            </div>
        </div>

        <div class ="container">
            <%
                if (session.getAttribute("currentPrivilegeLevel").equals(1)) {
            %>
            <a href="paciente?action=edit&username=<c:out value="${paciente.getUsuario()}"/>">Editar /</a>  
            <a href="paciente?action=erase&username=<c:out value="${paciente.getUsuario()}"/>">Eliminar</a>
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
            <a href='geriatria?action=add&idUsuario=<%=session.getAttribute("idUsuario")%>&idPaciente=<c:out value="${paciente.getPacienteID()}"/>'>Agregar nueva valoracion geriatrica</a>
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
                        formaGeriatria forma[] = Handler.getAllformaGeriatria();
                        for (int i = 0; i < forma.length; i++) {
                    %>
                    <tr>
                        <td><%=forma[i].getIdformatoGeriatra()%></td>
                        <td><%=forma[i].getFechaLlenado()%></td>
                        <td>
                            <a href="geriatria?action=show&idForm=<%=forma[i].getIdformatoGeriatra()%>&idUsuario=<%=session.getAttribute("idUsuario")%>&idPaciente=<c:out value="${paciente.getPacienteID()}"/>">Ver</a>
                            <a href="geriatria?action=edit&idForm=<%=forma[i].getIdformatoGeriatra()%>&idUsuario=<%=session.getAttribute("idUsuario")%>&idPaciente=<c:out value="${paciente.getPacienteID()}"/>">Editar</a>
                            <a href="geriatria?action=erase&idForm=<%=forma[i].getIdformatoGeriatra()%>&idUsuario=<%=session.getAttribute("idUsuario")%>&idPaciente=<c:out value="${paciente.getPacienteID()}"/>">Eliminar</a>
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
            <a href='nutricion?action=add'>Agregar nueva valoracion de nutricion</a>
            <table>
                <thead>
                <th>ID Formato</th>
                <th>Fecha de llenado</th>
                <th>Acciones</th>
                </thead>
                <%

                %>
                <tbody>
                </tbody>
            </table>
            <%                }
            %>
        </div>

        <div class="container" style="margin-top: 5%">
            <%
                if (session.getAttribute("currentPrivilegeLevel").equals(1) || session.getAttribute("currentPrivilegeLevel").equals(4)) {
            %>
            <hr>
            <h5> Dispositivos Fitbit Asociados </h5>
            <a href='social?action=add'>Agregar nueva valoracion social</a>
            <table>
                <thead>
                <th>ID Pebble</th>
                <th>Fecha de asignacion</th>
                <th>Acciones</th>
                </thead>
                <tbody>
                </tbody>
            </table>
            <%
                }
            %>
        </div>
</html>
