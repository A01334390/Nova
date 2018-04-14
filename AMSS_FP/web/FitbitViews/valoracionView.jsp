<%-- 
    Document   : valoracionView
    Created on : Apr 13, 2018, 11:27:59 PM
    Author     : Luna
--%>

<%@page import="BasicElements.valoracionFitbit"%>
<%@page import="DatabaseManager.Handler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
    <%
        valoracionFitbit vf = Handler.searchValoracionFitbit(session.getAttribute("valoracionID").toString());
    %>
    <p><%=vf.getIdValoracionFitbit()%></p>
    <p><%=vf.getUsuario()%></p>
    <p><%=vf.getDatosMovilidad()%></p>
</html>
