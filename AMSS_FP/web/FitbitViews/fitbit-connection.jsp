<%-- 
    Document   : fitbit-connection
    Created on : Apr 10, 2018, 7:11:22 PM
    Author     : Luna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <title>Fitbit API JavaScript</title>
    </head>
    <body>
        <script src="/FitbitViews/app.js"></script>
        <a href="https://www.fitbit.com/oauth2/authorize?response_type=token&client_id=22CWB4&redirect_uri=http%3A%2F%2Flocalhost%2Ffitbit&scope=activity%20heartrate%20location%20nutrition%20profile%20settings%20sleep%20social%20weight&expires_in=604800">Login to Fitbit</a>
        <div id="results"></div>
    </body>
</html>
