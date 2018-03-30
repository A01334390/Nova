<%-- 
    Document   : geriatraForm
    Created on : Mar 29, 2018, 3:45:17 PM
    Author     : Luna
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>

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
        <title>Nova - Forma Geriatrica</title>
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
                <h1>Forma de Geriatria</h1>
            </div>
        </div>

        <div class="container">
            <form action="geriatria" method="POST" name="formAddFormita">
                <div class="row">
                    <div class="three columns">
                        <label for="katz">ID Formato Geriatria :</label>
                        <input class="u-full-width" type="text" name="idformatoGeriatria" value="<c:out value="${forma.getIdformatoGeriatra()}"/>">
                    </div>
                    <div class="three columns">
                        <label for="katz">ID de Usuario :</label>
                        <input class="u-full-width" type="text" name="idUsuario" value="<c:out value="${usuario.getId()}"/>">
                    </div>
                    <div class="three columns">
                        <label for="katz">ID de Paciente :</label>
                        <input class="u-full-width" type="text" name="idPaciente" value="<c:out value="${paciente.getPacienteID()}"/>">
                    </div>
                </div>
                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Fecha de Aplicacion :</label>
                        <input class="u-full-width" type="date" name="fechaLlenado" value="<c:out value="${forma.getFechaLlenado()}"/>">
                    </div>
                </div>
                <div class="row">
                    <div class="four columns">
                        <label for="katz">Escala de Katz :</label>
                        <input class="u-full-width" type="text" placeholder="X/6" name="katz" value="<c:out value="${forma.getKatz()}"/>">
                    </div>
                    <div class="five columns">
                        <label for="katz">Interpretacion :</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="katz_interpretacion" value="<c:out value="${forma.getKatz_interpretacion()}"/>">
                    </div>
                </div>
                <div class="row">
                    <div class="four columns">
                        <label for="katz">Escala de Barthel :</label>
                        <input class="u-full-width" type="text" placeholder="X/100" name="barthel" value="<c:out value="${forma.getBarthel()}"/>">
                    </div>
                    <div class="five columns">
                        <label for="katz">Interpretacion :</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="barthel_interpretacion" value="<c:out value="${forma.getBarthel_interpretacion()}"/>">
                    </div>
                </div>
                <div class="row">
                    <div class="four columns">
                        <label for="katz">Escala de Lawton-Brody :</label>
                        <input class="u-full-width" type="text" placeholder="X/8" name="lawtonBrody" value="<c:out value="${forma.getLawtonBrody()}"/>">
                    </div>
                    <div class="five columns">
                        <label for="katz">Interpretacion :</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="lawtonBrody_interpretacion" value="<c:out value="${forma.getLawtonBrody_interpretacion()}"/>">
                    </div>
                </div>
                <div class="row">
                    <div class="four columns">
                        <label for="katz">Examen minimo del estado mental :</label>
                        <input class="u-full-width" type="text" placeholder="X/30" name="estadoMental" value="<c:out value="${forma.getEstadoMental()}"/>">
                    </div>
                    <div class="five columns">
                        <label for="katz">Interpretacion :</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="estadoMental_interpretacion" value="<c:out value="${forma.getEstadoMental_interpretacion()}"/>">
                    </div>
                </div>
                <div class="row">
                    <div class="four columns">
                        <label for="katz">Escala de Depresion :</label>
                        <input class="u-full-width" type="text" placeholder="X/15" name="escalaDepresion" value="<c:out value="${forma.getEscalaDepresion()}"/>">
                    </div>
                    <div class="five columns">
                        <label for="katz">Interpretacion :</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="escalaDepresion_interpretacion" value="<c:out value="${forma.getEscalaDepresion_interpretacion()}"/>">
                    </div>
                </div>
                <div class="row">
                    <div class="four columns">
                        <label for="katz">Cribado Nutricional :</label>
                        <input class="u-full-width" type="text" placeholder="X/30" name="cribadoNutricional" value="<c:out value="${forma.getCribadoNutricional()}"/>">
                    </div>
                    <div class="five columns">
                        <label for="katz">Interpretacion :</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="cribadoNutricional_interpretacion" value="<c:out value="${forma.getCribadoNutricional_interpretacion()}"/>">
                    </div>
                </div>
                <div class="row">
                    <div class="four columns">
                        <label for="katz">Prueba corta de desempenio :</label>
                        <input class="u-full-width" type="text" placeholder="X/30" name="pruebaDesempenio" value="<c:out value="${forma.getPruebaDesempenio_interpretacion()}"/>">
                    </div>
                    <div class="five columns">
                        <label for="katz">Interpretacion :</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="pruebaDesempenio_interpretacion" value="<c:out value="${forma.getPruebaDesempenio_interpretacion()}"/>">
                    </div>
                </div>
                <div class="row">
                    <div class="four columns">
                        <label for="katz">Prueba levantate y anda :</label>
                        <input class="u-full-width" type="text" placeholder="Segundos" name="levantateAnda" value="<c:out value="${forma.getLevantateAnda()}"/>">
                    </div>
                    <div class="five columns">
                        <label for="katz">Interpretacion :</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="levantateAnda_interpretacion" value="<c:out value="${forma.getLevantateAnda_interpretacion()}"/>">
                    </div>
                </div>
                        <%if(!request.getAttribute("show").equals(true)){%>
                <input class="button-primary" type="submit" value="Submit">
                 <%}%>
            </form>
        </div>
    </body>
</html>
