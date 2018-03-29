<%-- 
    Document   : pacienteAll
    Created on : Mar 29, 2018, 11:51:02 AM
    Author     : Luna
--%>

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
                <h5>${paciente.getGenero()==0 ? 'Hombre' : paciente.getGenero()==1 ? 'Mujer' : paciente.getGenero()==2 ? 'Deseo no mencionarlo' : 'Otros'}</h5>
            </div>
        </div>

        
</html>
