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
        <!-- Basic Page Needs
–––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <meta charset="utf-8">
        <title>Vista basica</title>
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
    </body>
    <div class="container" style="padding-top: 5%">
        <h1>Vista de movilidad</h1>
        <%
            valoracionFitbit vf = Handler.searchValoracionFitbit(session.getAttribute("valoracionID").toString());
        %>
        <h5>Id de valoracion</h5>
        <p><%=vf.getIdValoracionFitbit()%></p>
        <h5>Nombre de usuario</h5>
        <p><%=vf.getUsuario()%></p>
        <div class ="container">
            <div class="ct-chart ct-perfect-fourth"></div>
        </div>
    </div>
    <script src="js/chartist.js"></script>
    <link rel="stylesheet" href="css/chartist.css" type="text/css">
    <script>
        let jsondata = <%=vf.getDatosMovilidad()%>
        console.log(jsondata)
        let labelsprep = []
        let seriesprep = []
        for (var dat in jsondata) {
            for (var elem in jsondata[dat]) {
                labelsprep.push(jsondata[dat][elem].dateTime)
                seriesprep.push(jsondata[dat][elem].value)
            }
        }

        var data = {
            //prepare the data

            // A labels array that can contain any sort of values
            labels: labelsprep,
            // Our series array that contains series objects or in this case series data arrays
            series: [seriesprep]
        };

        var options = {
            width: 500,
            height: 600
        };

        // Create a new line chart object where as first parameter we pass in a selector
        // that is resolving to our chart container element. The Second parameter
        // is the actual data object.
        new Chartist.Line('.ct-chart', data, options);

    </script>
</html>
