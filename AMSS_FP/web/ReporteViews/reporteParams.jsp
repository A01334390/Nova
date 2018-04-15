<%-- 
    Document   : reporteParams
    Created on : Apr 14, 2018, 3:30:53 PM
    Author     : Luna
--%>

<%@page import="BasicElements.Paciente"%>
<%@page import="BasicElements.formaGerontologia"%>
<%@page import="BasicElements.valoracionFitbit"%>
<%@page import="BasicElements.formaFragilidad"%>
<%@page import="Servlets.paciente"%>
<%@page import="BasicElements.formaGeriatria"%>
<%@page import="DatabaseManager.Handler"%>
<%@page import="DatabaseManager.Handler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/site.js"></script>
        <link rel="stylesheet" href="css/custom.css">
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
        <h1>Chooser</h1>
        <p>Aqui podras escoger que formas deseas incluir en el reporte</p>
        <hr>
        <form action="reporte" method="POST">
            <%
                Paciente paciente = Handler.pacienteSearch(session.getAttribute("paciente").toString(), "*");
            %>
            <div class="container" style="margin-top: 5%">
                <h5> Formatos de Geriatria </h5>
                <select name='geriatria'>
                    <optgroup>
                        <%
                            formaGeriatria forma[] = Handler.getAllformaGeriatria(Integer.toString(paciente.getPacienteID()));
                            for (int i = 0; i < forma.length; i++) {
                        %>
                        <option value='<%=forma[i].getIdformatoGeriatra()%>'>ID. <%=forma[i].getIdformatoGeriatra()%> Fecha de Llenado: <%=forma[i].getFechaLlenado()%></option>
                        <%
                            }
                        %>
                    </optgroup>
                </select>
            </div>

            <div class="container" style="margin-top: 5%">
                <hr>
                <h5> Formatos de Nutriologia </h5>
                <select name='nutriologia'>
                    <optgroup>
                        <%
                            formaFragilidad formaFrag[] = Handler.getAllformaFragilidad(Integer.toString(paciente.getPacienteID()));
                            for (int i = 0; i < formaFrag.length; i++) {
                        %>
                        <option value='<%=formaFrag[i].getIdevaluacionFragilidad()%>'>ID. <%=formaFrag[i].getIdevaluacionFragilidad()%> Fecha de Llenado: <%=formaFrag[i].getFechaLlenado()%></option>
                        <%
                            }
                        %>
                    </optgroup>
                </select>
            </div>

            <div class="container" style="margin-top: 5%">
                <hr>
                <h5> Información de movilidad </h5>

                <select name='movilidad'>
                    <optgroup>
                        <%
                            valoracionFitbit vf[] = Handler.getAllValoracionFitbit(session.getAttribute("pacienteUsername").toString());
                            for (int i = 0; i < vf.length; i++) {
                        %>
                        <option value='<%=vf[i].getIdValoracionFitbit()%>'>ID. <%=vf[i].getIdValoracionFitbit()%> Tiempo Pedido: <%=vf[i].getTiempoPedido()%> Fecha Pedido: <%=vf[i].getFechaPedida()%></option>
                        <%
                            }
                        %>
                    </optgroup>
                </select>

            </div>

            <div class="container" style="margin-top: 5%">
                <hr>
                <select name='gerontologia'>
                    <optgroup>
                    <h5> Formatos de Gerontologia Asociados </h5>

                    <%
                        formaGerontologia formGeront[] = Handler.getAllformaGerontologia(Integer.toString(paciente.getPacienteID()));
                        for (int i = 0; i < formGeront.length; i++) {
                    %>
                    <option value='<%=formGeront[i].getIdvaloracionGerontologica()%>'>ID. <%=formGeront[i].getIdvaloracionGerontologica()%> Fecha llenado: <%=formGeront[i].getFechaLlenado()%></option>
                    <%
                        }
                    %>
                    </optgroup>
                </select>
                </tbody>
                </table>
            </div>
                    <input name="pacienteUsername" value="<%=session.getAttribute("paciente")%>" type='text' hidden >
                    <input name="act" value="gen" hidden>
            <input class="button-primary" type="submit" value="Submit" />
        </form>
    </body>
</html>
