<%-- 
    Document   : index
    Created on : Mar 28, 2018, 1:52:51 PM
    Author     : Luna
--%>

<%@page import = "java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>

        <!-- Basic Page Needs
      –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <meta charset="utf-8">
        <title>Nova - Pagina de Inicio</title>
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
        <link rel="stylesheet" href="css/custom.css" type="text/css">

        <!-- Favicon
      –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <link rel="icon" type="image/png" href="images/favicon.png">

        <!-- SCRIPT
      –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <script src="js/site.js"></script>
        
    </head>
    <body>
        <!-- @@@ NAVBAR @@@-->

        <div class="navbar-spacer"></div>
        <nav class="navbar">
            <div class="container">
                <ul class="navbar-list">
                    <li class="navbar-item">
                        <a class="navbar-link" href="#">Nova</a>
                    </li>
                </ul>
            </div>
        </nav>

        <!-- -->    

        <div class="container">
            <div class="six columns" style="margin-top: 15%">
                <h1>Nova</h1>
                <h5>Proyecto de AMSS</h5>
            </div>
        </div>

        <div class="container">
            <form action="login.php" method="POST">
                <div class="row">
                    <div class="one-half column" style="margin-top: 5%">
                        <label>Inicio de sesion</label>
                        <p>Si deseas iniciar sesion, haz click <a href="login.jsp">aqui</a></p>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
