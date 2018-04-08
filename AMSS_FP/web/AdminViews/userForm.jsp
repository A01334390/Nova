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
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/skeleton.css">

        <!-- Favicon
      –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <link rel="icon" type="image/png" href="images/favicon.png">
    </head>
    <body>
        <div class="container">
            <div class="six columns" style="margin-top: 15%">
                <h1>Bienvenido a la Forma de Usuarios</h1>
            </div>
        </div>

        <div>
            <div class ="container">
                <form action ="admin" method="POST" name="formAddUsuario">
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
                            <input class="u-full-width" type="text" placeholder="hello@nova.io"  name="email" value="<c:out value="${usuario.getEmail()}"/>">
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
                            <input class="u-full-width" type="date" name="fechaNacimiento" value="<c:out value="${usuario.getFechaNacimiento()}"/>">
                        </div>
                    </div>

                    <div class="row">
                        <div class="one-half column">
                            <label for ="passwordInput">Fecha de Validez de Acceso : </label>
                            <input class="u-full-width" type="date" name="fechaValidez" value="<c:out value="${usuario.getFechaValidez()}"/>">
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
                    <input class="button-primary" type="submit" value="Submit">
                </form>
            </div>
        </div>

    </body>
</html>
