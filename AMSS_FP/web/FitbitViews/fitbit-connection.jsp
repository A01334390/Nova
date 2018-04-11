<%-- 
    Document   : fitbit-connection
    Created on : Apr 10, 2018, 7:11:22 PM
    Author     : Luna
--%>

<%@page import="BasicElements.Fitbit"%>
<%@page import="DatabaseManager.Handler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Basic Page Needs
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <meta charset="utf-8">
        <title>Conexion con Fitbit</title>
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

        <!-- SCRIPT
      –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <%Fitbit fitbit = Handler.getAllFitbit();%>
        <script>
                // get the url
                var url = window.location.href;
                //getting the access token from url
                var access_token = url.split("#")[1].split("=")[1].split("&")[0];

                // get the userid
                var userId = url.split("#")[1].split("=")[2].split("&")[0];

                console.log(access_token);
                console.log(userId);

                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'https://api.fitbit.com/1/user/' + userId + '/activities/heart/date/today/1w.json');
                xhr.setRequestHeader("Authorization", 'Bearer ' + access_token);
                xhr.onload = function () {
                    if (xhr.status === 200) {
                        document.getElementById("results").innerHTML += xhr.responseText;
                    }
                };
                xhr.send();
        </script>
        <!-- Favicon
      –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <link rel="icon" type="image/png" href="images/favicon.png">
    </head>
    <body>
        <div class="container">
            <div class="six columns" style="margin-top: 15%">
                <h1>Obtener información de movilidad</h1>
            </div>
        </div>
        <div>
            <div class ="container">
                <div id="results"></div>
            </div>
        </div>

        <div>
            <div class="container">
                <div class="row">
                    <div class="seven columns">
                        <label for="katz">Fecha de termino :</label>
                        <input class="u-full-width" type="text" name="date">
                    </div>
                </div>
                <div class="row">
                    <div class="seven columns">
                        <label for="katz">Periodo de fechas :</label>
                        <select name='period' type='text' required>
                            <option value='1d'>1 dia</option>
                            <option value='7d'>7 dias</option>
                            <option value='40d'>30 dias</option>
                            <option value='1w'>1 semana</option>
                            <option value='1m'>1 mes</option>
                        </select>
                    </div>
                </div>
                <a href="https://www.fitbit.com/oauth2/authorize?response_type=token&client_id=22CWB4&redirect_uri=http%3A%2F%2Flocalhost%2FAMSS_FP%2Ffitbit%3Faction%3Dshow&scope=activity%20heartrate%20location%20nutrition%20profile%20settings%20sleep%20social%20weight&expires_in=604800"/>Accesar a Fitbit</a>
            </div>
        </div>
        
        <div>
            <div class='container' id='results'>
                
            </div>
        </div>
    </body>
</html>
