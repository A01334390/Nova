<%-- 
    Document   : gerontologiaForm
    Created on : Mar 29, 2018, 11:07:21 PM
    Author     : Luna
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Nova - Forma de Gerontologia</title>
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
                        <a class="navbar-link" href="PacienteViews/pacienteAll.jsp">Regresar</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <div class="six columns" style="margin-top: 15%">
                <h1>Bienvenido a la Forma de Gerontologia</h1>
            </div>
        </div>

        <div class="container">
            <form action="social" method="POST" name="formAddGerontologia">
                <div class="row">
                    <div class="three columns">
                        <input  class="u-full-width" type="text" name="idevaluacionFragilidad" value="<c:out value="${forma.getIdvaloracionGerontologica()}"/>">
                    </div>
                    <div class="three columns">
                        <input  class="u-full-width" type="text" name="idUsuario" value="<c:out value="${usuario}"/>">
                    </div>
                    <div class="three columns">
                        <input  class="u-full-width" type="text" name="idPaciente" value="<c:out value="${paciente}"/>">
                    </div>
                </div>

                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Fecha de Aplicacion :</label>
                        <input class="u-full-width" type="date" name="fechaLlenado" value="<c:out value="${forma.getFechaLlenado()}"/>">
                    </div>
                </div>

                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Dispositivos en uso:</label>
                        <input class="u-full-width" type="text" name="dispositivosUso" value="<c:out value="${forma.getDispositivosUso()}"/>"></div>
                </div>

                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Dispositivo de mayor uso:</label>
                        <input class="u-full-width" type="text" name="dispositivoMayorUso" value="<c:out value="${forma.getDispositivoMayorUso()}"/>"></div>
                </div>

                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Frecuencia de uso:</label>
                        <input class="u-full-width" type="text" name="frecuenciaUso" value="<c:out value="${forma.getFrecuenciaUso()}"/>"></div>
                </div>
                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Actividades de uso:</label>
                        <input class="u-full-width" type="text" name="actividadesUso" value="<c:out value="${forma.getActividadesUso()}"/>"></div>
                </div>

                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Usos a favorecer:</label>
                        <input class="u-full-width" type="text" name="usosFavorecer" value="<c:out value="${forma.getUsosFavorecer()}"/>"></div>
                </div>

                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Apoyo social percibido (Prueba Duke-corta):</label>
                        <input class="u-full-width" type="text" name="apoyoSocialPercibido" value="<c:out value="${forma.getApoyosocialPercibido()}"/>"></div>
                </div>
                
                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Actividades Comunitarias:</label>
                        <input class="u-full-width" type="text" name="actividadesComunitarias" value="<c:out value="${forma.getActividadesComunitarias()}"/>"></div>
                </div>

                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Impresion Diagnostica:</label>
                        <input class="u-full-width" type="text" name="impresionDiagnostica" value="<c:out value="${forma.getImpresionDiagnostica()}"/>"></div>
                </div>
        <%if (!request.getAttribute("show").equals(true)) {%>
        <input class="button-primary" type="submit" value="Submit">
        <%}%>
    </form>
</body>
</html>
