<%-- 
    Document   : fitbit-connection
    Created on : Apr 10, 2018, 7:11:22 PM
    Author     : Luna
--%>

<%@page import="com.sun.xml.internal.messaging.saaj.util.Base64"%>
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
        <%Fitbit fitbit = Handler.getAllFitbit();
            String text = fitbit.getOAUTH_CLIENTID() + ":" + fitbit.getCLIENT_SECRET();
            byte[] encodedBytes = Base64.encode(text.getBytes());
            String encoded = new String(encodedBytes);
        %>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">

            // Load the Visualization API and the corechart package.
            google.charts.load('current', {packages: ['corechart', 'line']});

            // Set a callback to run when the Google Visualization API is loaded.
            google.charts.setOnLoadCallback(drawChart);

            // Callback that creates and populates a data table,
            // instantiates the pie chart, passes in the data and
            // draws it.
            function drawChart(data) {

                // Create the data table.
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Topping');
                data.addColumn('number', 'Slices');
                data.addRows([
                    ['Mushrooms', 3],
                    ['Onions', 1],
                    ['Olives', 1],
                    ['Zucchini', 1],
                    ['Pepperoni', 2]
                ]);

                // Set chart options
                var options = {
                    hAxis: {
                        title: 'Actividad'
                    },
                    vAxis: {
                        title: 'Tiempo'
                    }
                };

                // Instantiate and draw our chart, passing in some options.
                var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
                chart.draw(data, options);
            }
        </script>
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
            xhr.open('GET', 'https://api.fitbit.com/1/user/' + userId + '/activities/steps/date/1w.json');
            xhr.setRequestHeader("Authorization", 'Bearer ' + access_token);
            xhr.onload = function () {
                if (xhr.status === 200) {
                    drawChart(xhr.responseText);
                    document.getElementById("results").innerHTML = xhr.responseText;
                }
            };
            xhr.send();

            function revokeAccess() {

                var params = "token=" + access_token;

                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'https://api.fitbit.com/oauth2/revoke');
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xhr.setRequestHeader("Authorization", 'Basic <%=encoded%>', true);
                xhr.onload = function () {
                    if (xhr.status === 200) {
                        console.log(xhr.responseText)
                    }
                };
                xhr.send(params);
            }
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
                <button onclick="revokeAccess()">revokeAccess</button>
            </div>
        </div>

        <div>
            <div class='container' id='results'>

            </div>
            <div id="chart_div"></div>
        </div>
    </body>
</html>
