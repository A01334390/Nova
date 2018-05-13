<%-- 
    Document   : fitbitForm
    Created on : Apr 11, 2018, 12:27:21 PM
    Author     : Luna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
        if (session.getAttribute("currentSessionName") == null) {
            session.setAttribute("success", false);
            RequestDispatcher req = request.getRequestDispatcher("/login.jsp");
            req.forward(request, response);
        }
    %>
<html>
    <head>
        <!-- Basic Page Needs
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <meta charset="utf-8">
        <title>Nova - Forma para Credenciales de Fitbit</title>
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
        <link rel="stylesheet" href="css/custom.css">

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
                        <a class="navbar-link" href="adminHome.jsp">Regresar</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <div class="six columns" style="margin-top: 15%">
                <h1>Bienvenido a la Forma de Fitbit</h1>
            </div>
        </div>

        <div>
            <div class ="container">
                <form action="fitbit" method="POST" name="formAddFitbit">
                    <div class="row">
                        <div class="one-half column">
                            <label for="primerNombre">URL de Fitbit :</label>
                            <input required class="u-full-width" type="text" placeholder="fitbit.com" name="FITBIT_URL" value="">
                        </div>
                    </div>
                    <div class="row">
                        <div class="one-half column">
                            <label for="primerNombre">URL del API de Fitbit :</label>
                            <input required class="u-full-width" type="text" placeholder="api.fitbit.com" name="FITBIT_API_URL" value="">
                        </div>
                    </div>
                    <div class="row">
                        <div class="one-half column">
                            <label for="primerNombre">OAuth 2.0 ID de Cliente :</label>
                            <input required class="u-full-width" type="text" placeholder="22CWB4" name="OAUTH_CLIENTID" value="">
                        </div>
                    </div>
                    <div class="row">
                        <div class="one-half column">
                            <label for="primerNombre">Secreto del cliente :</label>
                            <input required class="u-full-width" type="text" placeholder="db0f341198f4914dcb22ce837056292c" name="CLIENT_SECRET" value="">
                        </div>
                    </div>
                    <div class="row">
                        <div class="one-half column">
                            <label for="primerNombre">URI de redirección :</label>
                            <input required class="u-full-width" type="text" placeholder="http://localhost/fitbit" name="REDIRECT_URI" value="">
                        </div>
                    </div>
                    <div class="row">
                        <div class="one-half column">
                            <label for="primerNombre">Tiempo de expiración :</label>
                            <input required class="u-full-width" type="text" placeholder="604800" name="EXPIRATION_TIME" value="">
                        </div>
                    </div>
                    <input class="u-full-width" type="text" value="ngraph" name="diff" hidden>
                    <input class="button-primary" type="submit" value="Submit" />
            </div>
        </div>
    </body>
</html>
