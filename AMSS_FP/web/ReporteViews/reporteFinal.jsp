<%-- 
    Document   : reporteFinal
    Created on : Apr 14, 2018, 5:15:18 PM
    Author     : Luna
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="BasicElements.formaGerontologia"%>
<%@page import="BasicElements.valoracionFitbit"%>
<%@page import="BasicElements.formaFragilidad"%>
<%@page import="BasicElements.formaGeriatria"%>
<%@page import="BasicElements.Paciente"%>
<%@page import="DatabaseManager.Handler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Basic Page Needs
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <meta charset="utf-8">
        <title>Nova - Expediente</title>
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
                        <a class="navbar-link" href="backbutt?action=returnReporteParams">Regresar</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container" style="margin-top:15%">
            <h1>Protocolo de Investigacion</h1>
            <p>Detección y predicción de factores asociados al síndrome de fragilidad en personas mayores. Estudio de caso para la Ciudad de México</p>
            <h2>Reporte de resultados para participantes</h2>
            <h3>Valoracion Geriatrica Integral</h3>
        </div>

        <hr>
        <div class='container'>
            <h2> Datos generales: </h2>
            <!-- @@@@@INFORMACION PERSONAL DEL PACIENTE@@@@@@-->
            <%
                Paciente paciente = Handler.pacienteSearch(session.getAttribute("paciente").toString(), "*");
            %>
            <div class="row">
                <div class='four columns'>
                    <div>
                        <label>Nombre completo:</label>
                        <p><%=paciente.getPrimerNombre()%> <%=paciente.getSegundoNombre()%></p>
                    </div>
                    <div>
                        <label>Escolaridad maxima</label>
                        <p><%=paciente.getEscolaridadMaxima().equals("pri") ? "Primaria" : paciente.getEscolaridadMaxima().equals("sec") ? "Secundaria" : paciente.getEscolaridadMaxima().equals("pre") ? "Preparatoria" : paciente.getEscolaridadMaxima().equals("lic") ? "Licenciatura" : paciente.getEscolaridadMaxima().equals("mae") ? "Maestria" : "Doctorado"%></p>
                    </div>
                    <div>
                        <label>Estado civil:</label>
                        <p><%=paciente.getEstadoCivil() == 0 ? "Soltero/A" : paciente.getEstadoCivil() == 1 ? "Casado/a" : paciente.getEstadoCivil() == 2 ? "Viudo/a" : "Divorciado/a"%></p>
                    </div>
                    <div>
                        <label>Autoreporte de padecimientos:</label>
                        <p><%=paciente.getAutopadecimiento()%></p>
                    </div>
                    <div>
                        <label>Correo electrónico:</label>
                        <p><%=paciente.getEmail()%></p>
                    </div>
                </div>
                <div class='four columns'>
                    <div>
                        <label>Edad:</label>
                        <p><%=paciente.getFechaDeNacimiento()%></p>
                    </div>
                    <div>
                        <label>Fecha de evaluación:</label>
                        <p><%=Calendar.getInstance().getTime()%></p>
                    </div>
                    <div>
                        <label>Afiliación médica:</label>
                        <p><%=paciente.getAfiliacionMedica()%></p>
                    </div>
                    <div>
                        <label>Co-habitación:</label>
                        <p><%=paciente.getCohabitacion() == 0 ? "No" : "Si"%></p>
                    </div>
                    <div>
                        <label>Escala AMAI:</label>
                        <p><%=paciente.getAmai()%></p>
                    </div>
                </div>
            </div>
            <hr>
            <h2>Resultados de la valoracion geriatrica</h2>
            <!-- @@@@@INFORMACION DE GERIATRIA @@@@@@ -->
            <%
                formaGeriatria geriatria = Handler.formaGeriatriaSearch(session.getAttribute("geriatria").toString());
            %>
            <div class='row'>
                <div class='nine columns'>
                    <table>
                        <thead>
                        <th>
                            Pruebas aplicadas
                        </th>
                        <th>
                            Resultado
                        </th>
                        <th>
                            Interpretacion
                        </th>
                        </thead>
                        <tr>
                            <td>
                                Escala de Katz
                            </td>
                            <td>
                                <%=geriatria.getKatz()%>
                            </td>
                            <td>
                                <%=geriatria.getKatz_interpretacion()%>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Escala de Barthel
                            </td>
                            <td>
                                <%=geriatria.getBarthel()%>
                            </td>
                            <td>    
                                <%=geriatria.getBarthel_interpretacion()%>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Escala de Lawthon-Brody
                            </td>
                            <td>
                                <%=geriatria.getLawtonBrody()%>
                            </td>
                            <td>
                                <%=geriatria.getLawtonBrody_interpretacion()%>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Examen minimo del estado mental
                            </td>
                            <td>
                                <%=geriatria.getEstadoMental()%>
                            </td>
                            <td>
                                <%=geriatria.getEstadoMental_interpretacion()%>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Escala de depresion geriatrica
                            </td>
                            <td>
                                <%=geriatria.getEscalaDepresion()%>
                            </td>
                            <td>
                                <%=geriatria.getEscalaDepresion_interpretacion()%>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Cribado nutricional
                            </td>
                            <td>
                                <%=geriatria.getCribadoNutricional()%>
                            </td>
                            <td>
                                <%=geriatria.getCribadoNutricional_interpretacion()%>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Prueba corta de desempeño físico
                            </td>
                            <td>
                                <%=geriatria.getPruebaDesempenio()%>
                            </td>
                            <td>
                                <%=geriatria.getPruebaDesempenio_interpretacion()%>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Prueba levántate y anda
                            </td>
                            <td>
                                <%=geriatria.getLevantateAnda()%>
                            </td>
                            <td>
                                <%=geriatria.getLevantateAnda_interpretacion()%>
                            </td>
                        </tr>
                    </table>
                </div>        
            </div>
            <br>
            <div class='row'>
                <h2>Evaluacion de Fragilidad</h2>
                <%
                    formaFragilidad ff = Handler.formaFragilidadSearch(session.getAttribute("nutricion").toString());
                %>
                <div class='nine columns'>
                    <table>
                        <thead>
                        <th>Evaluacion de Fragilidad</th>
                        <th>Resultado</th>
                        <th>Interpretacion</th>
                        </thead>
                        <tr>
                            <td>
                                Pérdida de peso
                            </td>
                            <td>
                                <%=ff.getPerdidaPeso()%>
                            </td>
                            <td>
                                <%=ff.getPerdidaPeso_interpretacion()%>
                            </td>
                        </tr>
                        <tr>
                            <td> Pobre resistencia y energía disminuida</td>
                            <td>
                                <%=ff.getPobreResistencia()%>
                            </td>
                            <td>
                                <%=ff.getPobreResistencia_interpretacion()%>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Velocidad de la Marcha
                            </td>
                            <td>
                                <%=ff.getVelocidadMarcha()%>
                            </td>
                            <td>
                                <%=ff.getVelocidadMarcha_interpretacion()%>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Debilitamiento (Fuerza de Prensión)
                            </td>
                            <td>
                                <%=ff.getFuerzaPresion()%>
                            </td>
                            <td>
                                <%=ff.getFuerzaPresion_interpretacion()%>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Actividad Física
                            </td>
                            <td>
                                <%=ff.getActividadFisica()%>
                            </td>
                            <td>
                                <%=ff.getActividadFisica_interpretacion()%>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                DIAGNÓSTICO
                            </td>
                            <td>
                                <%=ff.getDiagnostico()%>
                            </td>
                            <td>

                            </td>
                        </tr>
                    </table>                         
                </div>
            </div>
            <div class="row">
                <h3>Impresion diagnostica:</h3>
                <div class="5 columns">
                    <label>Evaluacion funcional:</label>
                    <textarea cols="70" readonly="false">
                        <%=ff.getEvaluacionFuncional()%>
                    </textarea>
                </div>
                <div class="5 columns">
                    <label>Evaluacion cognitiva y afectiva</label>
                    <textarea cols="70" readonly="false">
                        <%=ff.getEvaluacionCognitiva()%>
                    </textarea>
                </div>
                <div class="5 columns">
                    <label>Evaluacion nutricional</label>
                    <textarea cols="70"readonly="false">
                        <%=ff.getEvaluacionNutricional()%>
                    </textarea>
                </div>
                <div class="5 columns">
                    <label>Evaluacion de fragilidad</label>
                    <textarea cols="70" readonly="false">
                        <%=ff.getEvaluacionDeFragilidad()%>
                    </textarea>
                </div>
            </div>

            <hr>
            <!-- @@@@@INFORMACION DE NUTRICION @@@@@@ -->
            <%
                valoracionFitbit vf = Handler.searchValoracionFitbit(session.getAttribute("movilidad").toString());
            %>
            <div class="row">
                <h2>Resultados de valoracion con Fitbit</h2>
                <div class="nine columns">
                    <textarea readonly="false" cols="70">
                        <%=vf.getDatosMovilidad()%>
                    </textarea>
                </div>
            </div>
            <div>

            </div>

            <hr>
            <h2>Valoracion gerontologica</h2>
            <%
                formaGerontologia fg = Handler.formaGerontologiaSearch(session.getAttribute("gerontologia").toString());
            %>
            <div class="row">
                <div class="nine columns">
                    <div>
                        <label>Dispositivos en uso</label>
                        <%=fg.getDispositivosUso()%>
                    </div>
                </div>
                <div class="nine columns">
                    <div>
                        <label>Dispositivo de mayor uso</label>
                        <%=fg.getDispositivoMayorUso()%>
                    </div>
                </div>
                <div class="nine columns">
                    <div>
                        <label>Frecuencia de uso</label>
                        <%=fg.getFrecuenciaUso()%>
                    </div>
                </div>
                <div class="nine columns">
                    <div>
                        <label>Actividades de uso</label>
                        <%=fg.getActividadesUso()%>
                    </div>
                </div>
                <div class="nine columns">
                    <div>
                        <label>Usos a favorecer</label>
                        <%=fg.getUsosFavorecer()%>
                    </div>
                </div>
                <div class="nine columns">
                    <div>
                        <label>Apoyo social percibido (prueba Duke - corta)</label>
                        <%=fg.getApoyosocialPercibido()%>
                    </div>
                </div>
                <div class="nine columns">
                    <div>
                        <label>Actividades comunitarias a favorecer</label>
                        <%=fg.getActividadesComunitarias()%>
                    </div>
                </div>
                <div class="nine columns">
                    <div>
                        <label>Impresion diagnostica</label>
                        <%=fg.getImpresionDiagnostica()%>
                    </div>
                </div>
            </div>
            <!-- @@@@@HIDDEN FORMS@@@@@ -->
            <div class="row">
                <label>Conclusiones finales</label>
                <form action="reporte" method="POST">
                    <input type="text" name="conclusion" required>
                    <input hidden name="paciente" value="<%=session.getAttribute("paciente")%>" required>
                    <input hidden name="geriatria" value="<%=session.getAttribute("geriatria")%>" required>
                    <input hidden name="nutricion" value="<%=session.getAttribute("nutricion")%>" required>
                    <input hidden name="movilidad" value="<%=session.getAttribute("movilidad")%>" required>
                    <input hidden name="gerontologia" value="<%=session.getAttribute("gerontologia")%>" required>
                    <input hidden name="act" value="ngraph" required>
                    <input class="button-primary" type="submit" value="Submit" />
                </form>
            </div>
            <div class="container">
                <div class="row">
                    <input type="submit" id="print-in-pdf" onclick="doPrint()" value="imprimir en pdf">
                </div>
            </div>
        </div>

        <script>
            function doPrint(){
                window.print()
            }
        </script>
    </body>
</html>
