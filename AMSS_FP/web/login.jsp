<%-- 
    Document   : index
    Created on : Mar 28, 2018, 1:52:51 PM
    Author     : Luna
--%>

<%@page import = "java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <!-- Basic Page Needs
–––––––––––––––––––––––––––––––––––––––––––––––––– -->
    <meta charset="utf-8">
    <title>Nova - Inicio de sesion</title>
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
    <div class="navbar-spacer"></div>
        <nav class="navbar">
            <div class="container">
                <ul class="navbar-list">
                    <li class="navbar-item">
                        <a class="navbar-link" href="index.jsp">Nova</a>
                    </li>
                </ul>
            </div>
        </nav>
    
    
<div class="container">
    <div class="six columns" style="margin-top: 15%">
        <h1>Bienvenido a la pagina de Inicio de Sesion</h1>
        <h5> Si tienes una cuenta en la pagina, usa tus credenciales para iniciar sesion </h5>
    </div>
</div>

<div class="container">
<form method="POST" action="login" name="login">
    <div class="row">
        <div class="one-half column" style="margin-top: 10%">
            <label for="exampleEmailInput">Nombre de Usuario</label>
            <input class="u-full-width" type="text" placeholder="username" name="username">
        </div>
    </div>

    <div class="row">
        <div class="one-half column">
            <label for ="passwordInput">Clave</label>
            <input class="u-full-width" type="password" placeholder="password123"  name="password">
        </div>
    </div>
    <input class="button-primary" type="submit" value="Submit">
</form>
</div>
</body>
</html>