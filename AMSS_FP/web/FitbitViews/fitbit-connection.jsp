<%-- 
    Document   : fitbit-connection
    Created on : Apr 10, 2018, 7:11:22 PM
    Author     : Luna
--%>
<%@page import="BasicElements.Fitbit"%>
<%@page import="DatabaseManager.Handler"%>
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
        <link rel="stylesheet" href="css/normalize.css" type="text/css">
        <link rel="stylesheet" href="css/skeleton.css" type="text/css">
        <script src="js/site.js"></script>
        <link rel="stylesheet" href="css/custom.css" type="text/css">
        <script src="js/chartist.js"></script>
        <link rel="stylesheet" href="css/chartist.css" type="text/css">

        <!-- Favicon
      –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <link rel="icon" type="image/png" href="images/favicon.png">

        <script>
            // get the url
            var url = window.location.href;
            //getting the access token from url
            var access_token = url.split("#")[1].split("=")[1].split("&")[0];
            // get the userid
            var userId = url.split("#")[1].split("=")[2].split("&")[0];

            function getData() {
                var xhr = new XMLHttpRequest();
                var uri = 'https://api.fitbit.com/1/user/' + userId + '/activities/tracker/steps/date/' + document.getElementById('time').value + '/' + document.getElementById('lapsus').value + '.json';
                xhr.open('GET', uri);
                xhr.setRequestHeader("Authorization", 'Bearer ' + access_token);
                xhr.onload = function () {
                    if (xhr.status === 200) {
                        document.getElementById("results").innerHTML = xhr.responseText;
                        graphMovility(xhr.responseText);
                    }
                };
                xhr.send();
                getElements();
            }

            function getElements() {
                document.getElementById('lap').value = document.getElementById('lapsus').value;
                document.getElementById('tim').value = document.getElementById('time').value;
            }
        </script>
    </head>
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
    <body>
        <div class='container'>
            <div class='six columns' style='margin-top:15%'>
                <h2>Paso 1</h2><p>Iniciar sesión en Fitbit<p>
                    <a href='https://www.fitbit.com/oauth2/authorize?response_type=token&client_id=22CWB4&redirect_uri=http%3A%2F%2Flocalhost%2FNova%2Ffitbit%3Faction%3Dshow&scope=activity%20heartrate%20location%20nutrition%20profile%20settings%20sleep%20social%20weight&expires_in=6040'>Acceder a Fitbit</a>
            </div>
        </div>
        <div class="container">
            <div class="six columns">
                <h2>Paso 2</h2>
                <p>Ingresa la fecha a partir de la cual deseas obtener los datos de movilidad y el tiempo de obtencion<p>

            </div>
        </div>
        <div>
            <div class="container"> 
                <div class="row">
                    <div class="four columns">
                        <label for="katz">Fecha de termino :</label>
                        <input class="u-full-width" pattern="/\d\d\d\d-\d\d-\d\d/" type="text" name="time" placeholder='yyyy-MM-dd' id='time'required>
                    </div>
                </div>
                <div class="row">
                    <div class="four columns">
                        <label for="katz">Periodo de fechas :</label>
                        <select name='lapsus' type='text'id='lapsus' required>
                            <option value='1d'>1 dia</option>
                            <option value='7d'>7 dias</option>
                            <option value='30d'>30 dias</option>
                            <option value='1w'>1 semana</option>
                            <option value='1m'>1 mes</option>
                        </select>
                    </div>
                </div>
                <input class="button-primary" onclick='getData()' type="submit" value="Obtener los datos">
                <div class='row'>
                    <div class='six columns'>
                        <h2>Paso 3</h2>
                        <p>Guardar esta informacion<p>
                    </div>
                    <div class='container'>
                        <div class='row'>
                            <div class='seven columns'>
                                <form action='fitbit' method='POST'>
                                    <input class='u-full-width' type='text' value='<%=session.getAttribute("pacienteUsername")%>' name='username' hidden>
                                    <input class="u-full-width" type="text" value="graph" name="diff" hidden>
                                    <input class='u-full-width' id='lap' type='text' name='lapsus'required hidden>
                                    <input class="u-full-width" id='tim' type="text" name="time" required hidden>
                                    <textarea name='jsonresult' rows='4' cols='50' id="results" hidden></textarea>
                                    <input class="button-primary" type="submit" value="Almacenar los datos">
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class ="container">
                        <div class="ct-chart ct-perfect-fourth"></div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function graphMovility(jsondata) {
                jsondata = JSON.parse(jsondata)
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
                    width: 800,
                    height: 600
                };

                // Create a new line chart object where as first parameter we pass in a selector
                // that is resolving to our chart container element. The Second parameter
                // is the actual data object.
                new Chartist.Line('.ct-chart', data, options);
            }
        </script>
    </body>
</html>
