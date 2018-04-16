<%-- 
    Document   : userForm
    Created on : Mar 28, 2018, 6:06:28 PM
    Author     : Luna
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (session.getAttribute("currentSessionName") == null) {
        response.sendRedirect("/index.jsp");
    }
%>
<html>
    <head>
        <!-- Basic Page Needs
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <meta charset="utf-8">
        <title>Nova - Forma para Usuarios</title>
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

        <style>
            #circle{
                border-radius: 50%;
                width: 15rem;
                height: 15rem;
                border: 3px solid gold;
            }
        </style>

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
                <h1>Bienvenido a la Forma de Usuarios</h1>
            </div>
            <div class="six columns" style="margin-top: 15%;margin-left: 1%">
            </div>
        </div>

        <div>
            <div class ="container">
                <form action ="admin" method="POST" name="formAddUsuario" enctype="multipart/form-data">
                    <div class="row">
                        <div class="one-half column">
                            <label for="primerNombre">Primer nombre :</label>
                            <input class="u-full-width" type="text" placeholder="Nombres" name="primerNombre" value="<c:out value="${usuario.getPrimerNombre()}"/>">
                        </div>
                    </div>

                    <div class="row">
                        <div class="one-half column">
                            <label for ="passwordInput">Segundo Nombre : </label>
                            <input class="u-full-width" type="text" placeholder="Apellidos"  name="segundoNombre" value="<c:out value="${usuario.getSegundoNombre()}"/>">
                        </div>
                    </div>

                    <div class="row">
                        <div class="one-half column">
                            <label for ="passwordInput">Correo Electronico : </label>
                            <input class="u-full-width" pattern="/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/" type="text" placeholder="hello@nova.io"  name="email" value="<c:out value="${usuario.getEmail()}"/>">
                        </div>
                    </div>

                    <div class="row">
                        <div class="one-half column">
                            <label for ="passwordInput">Nombre de Usuario : </label>
                            <input class="u-full-width" type="text" placeholder="username"  name="usuario" value="<c:out value="${usuario.getUsuario()}"/>">
                        </div>
                    </div>

                    <div class="row">
                        <div class="one-half column">
                            <label for ="passwordInput">Contraseña : </label>
                            <input class="u-full-width" type="password" placeholder="password"  name="password" value="<c:out value="${usuario.getUsuario()}"/>">
                        </div>
                    </div>

                    <div class="row">
                        <div class="one-half column">
                            <label for ="passwordInput">Fecha de Nacimiento : </label>
                            <input class="u-full-width" pattern="/((0[1-9]|1[0-2])-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/" type="date" name="fechaNacimiento" value="<c:out value="${usuario.getFechaNacimiento()}"/>">
                        </div>
                    </div>

                    <div class="row">
                        <div class="one-half column">
                            <label for ="passwordInput">Fecha de Validez de Acceso : </label>
                            <input class="u-full-width" pattern="/((0[1-9]|1[0-2])-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/" type="date" name="fechaValidez" value="<c:out value="${usuario.getFechaValidez()}"/>">
                        </div>
                    </div>

                    <div class="row">
                        <div class="one-half column">
                            <select name="privilegio" type="text" required>
                                <option value ="0" ${usuario.getPrivilegio() == 0 ? 'selected="selected"' : ''}> Administrador </option>
                                <option value ="1" ${usuario.getPrivilegio() == 1 ? 'selected="selected"' : ''}> Jefe de Geriatria </option>
                                <option value ="2" ${usuario.getPrivilegio() == 2 ? 'selected="selected"' : ''}> Medico Geriatra </option>
                                <option value ="3" ${usuario.getPrivilegio() == 3 ? 'selected="selected"' : ''}> Nutriologo </option>
                                <option value ="4" ${usuario.getPrivilegio() == 4 ? 'selected="selected"' : ''}> Servicio Social </option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="one-half column">
                            <label hidden>Fotografia del usuario</label>
                            <input type="file" name="photo" hidden>
                            <input class="button-primary" type="submit" value="Submit">
                        </div>
                    </div>
            </div>

        </form>
    </div>
</div>

</body>
</html>
