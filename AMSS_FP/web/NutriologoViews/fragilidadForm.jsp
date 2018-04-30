<%-- 
    Document   : fragilidadForm
    Created on : Mar 29, 2018, 9:32:03 PM
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
        <title>Nova - Forma de Fragilidad</title>
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
                        <a class="navbar-link" href="backbutt?action=returnPacienteAll">Regresar</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <div class="six columns" style="margin-top: 15%">
                <h1>Bienvenido a la Forma de Fragilidad</h1>
            </div>
        </div>
        <div class="container">
            <form action="nutricion" method="POST" name="formAddFormita">
                <div class="row">
                    <div class="three columns">
                        <input hidden class="u-full-width" type="text" name="idevaluacionFragilidad" value="<c:out value="${forma.getIdevaluacionFragilidad()}"/>">
                    </div>
                    <div class="three columns">
                        <input hidden class="u-full-width" type="text" name="idUsuario" value="<c:out value="${usuario.getId()}"/>">
                    </div>
                    <div class="three columns">
                        <input hidden class="u-full-width" type="text" name="idPaciente" value="<c:out value="${paciente.getPacienteID()}"/>">
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
                        <label for="katz">Perdida de Peso:</label>
                        <select name="perdidaPeso" type="text" required>
                            <option value ="0" ${forma.getPerdidaPeso() == 0 ? 'selected="selected"' : ''}> No </option>
                            <option value ="1" ${forma.getPerdidaPeso() == 1 ? 'selected="selected"' : ''}> Si </option>
                        </select>
                    </div>
                    <div class="five columns">
                        <label for="katz">Interpretacion :</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="perdidaPeso_interpretacion" value="<c:out value="${forma.getPerdidaPeso_interpretacion()}"/>">
                    </div>
                </div>

                <div class="row">
                    <div class="four columns">
                        <label for="katz">Pobre Resistencia:</label>
                        <select name="pobreResistencia" type="text" required>
                            <option value ="0" ${forma.getPobreResistencia() == 0 ? 'selected="selected"' : ''}> No </option>
                            <option value ="1" ${forma.getPobreResistencia() == 1 ? 'selected="selected"' : ''}> Si </option>
                        </select>
                    </div>
                    <div class="five columns">
                        <label for="katz">Interpretacion :</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="pobreResistencia_interpretacion" value="<c:out value="${forma.getPerdidaPeso_interpretacion()}"/>">
                    </div>
                </div>

                <div class="row">
                    <div class="four columns">
                        <label for="katz">Velocidad de la Marcha:</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="velocidadMarcha" value="<c:out value="${forma.getVelocidadMarcha()}"/>">
                    </div>
                    <div class="five columns">
                        <label for="katz">Interpretacion :</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="velocidadMarcha_interpretacion" value="<c:out value="${forma.getVelocidadMarcha_interpretacion()}"/>">
                    </div>
                </div>

                <div class="row">
                    <div class="four columns">
                        <label for="katz">Debilitamiento (Fuerza de Prension):</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="fuerzaPresion" value="<c:out value="${forma.getFuerzaPresion()}"/>">
                    </div>
                    <div class="five columns">
                        <label for="katz">Interpretacion :</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="getFuerzaPresion_interpretacion" value="<c:out value="${forma.getFuerzaPresion_interpretacion()}"/>">
                    </div>
                </div>

                <div class="row">
                    <div class="four columns">
                        <label for="katz">Actividad Fisica:</label>
                        <select name="actividadFisica" type="text" required>
                            <option value ="0" ${forma.getActividadFisica() == 0 ? 'selected="selected"' : ''}> No </option>
                            <option value ="1" ${forma.getActividadFisica() == 1 ? 'selected="selected"' : ''}> Si </option>
                        </select>
                    </div>
                    <div class="five columns">
                        <label for="katz">Interpretacion :</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="actividadFisica_interpretacion" value="<c:out value="${forma.getActividadFisica_interpretacion()}"/>">
                    </div>
                </div>

                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Diagnostico:</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="diagnostico" value="<c:out value="${forma.getDiagnostico()}"/>">
                    </div>
                </div>

                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Evaluacion Funcional</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="evaluacionFuncional" rows="5" value="<c:out value="${forma.getEvaluacionFuncional()}"/>">
                    </div>
                </div>

                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Evaluacion Cognitiva</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="evaluacionCognitiva" rows="5" value="<c:out value="${forma.getEvaluacionCognitiva()}"/>">
                    </div>
                </div>

                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Evaluacion Nutricional</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="evaluacionNutricional" rows="5" value="<c:out value="${forma.getEvaluacionNutricional()}"/>">
                    </div>
                </div>

                <div class="row">
                    <div class="nine columns">
                        <label for="katz">Evaluacion De Fragilidad</label>
                        <input class="u-full-width" type="text" placeholder="Normal" name="evaluacionDeFragilidad" rows="5" value="<c:out value="${forma.getEvaluacionDeFragilidad()}"/>">
                    </div>
                </div>

                <%if (!request.getAttribute("show").equals(true)) {%>
                <input class="button-primary" type="submit" value="Submit">
                <%}%>

            </form>
        </div>
    </body>
</html>
