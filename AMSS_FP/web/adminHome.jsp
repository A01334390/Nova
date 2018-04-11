<%-- 
    Document   : adminHome
    Created on : Mar 28, 2018, 3:47:32 PM
    Author     : Luna
--%>


<%@page import = "java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DatabaseManager.Handler"%>
<%@page import="BasicElements.*"%>
<html lang="en">

    <%
        if (session.getAttribute("currentSessionName") == null) {
            response.sendRedirect("/index.jsp");
        }
    %>
    <head>
        <!-- Basic Page Needs
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <meta charset="utf-8">
        <title>Nova - Pagina de Administracion</title>
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
                <h1>Bienvenido a la pagina de Administradores
                    , <%=session.getAttribute("currentSessionName")%></h1>
            </div>
        </div>
        <hr>
        <div class="container">
            <h1>Usuarios</h1>
            <h5> Aqui podras ver los usuarios registrados, editar sus datos y eliminarlos del sistema. </h5>
            <p><a href="admin?action=add">Agregar nuevo usuario</a></p>
            <table>
                <thead>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Fecha de Nacimiento</th>
                <th>Correo Electronico</th>
                <th>Usuario</th>
                <th>Privilegio</th>
                <th>Acciones</th>
                </thead>
                <tbody>
                <tbody>
                    <%Usuario[] user = Handler.getAllUsers();%>
                    <%for (int i = 0; i < user.length; i++) {%>
                    <tr>
                        <td ><%=user[i].getPrimerNombre()%></td>
                        <td ><%=user[i].getSegundoNombre()%></td>
                        <td><%=user[i].getFechaNacimiento()%></td>
                        <td><%=user[i].getEmail()%></td>
                        <td><%=user[i].getUsuario()%></td>
                        <%
                            String type;
                            if (user[i].getPrivilegio() == 0) {
                                type = "Administrador/0";
                            } else if (user[i].getPrivilegio() == 1) {
                                type = "Jefe de Geriatria/1";
                            } else if (user[i].getPrivilegio() == 2) {
                                type = "Medico Geriatra/2";
                            } else if (user[i].getPrivilegio() == 3) {
                                type = "Medico Nutriologo/3";
                            } else if (user[i].getPrivilegio() == 4) {
                                type = "Servicio Social/4";
                            } else {
                                type = "No Aunado/???";
                            }
                        %>
                        <td><%=type%></td>
                        <td class="centeredform"><a href="admin?action=edit&username=<%=user[i].getUsuario()%>">Editar</a>  
                            <a href="admin?action=erase&username=<%=user[i].getUsuario()%>">Eliminar</a> </td>
                    </tr>
                    <% }%>
                </tbody>
                </tbody>
            </table>
        </div>
        <hr>
        <div class='container'>
            <h1>Aplicaciones de Fitbit</h1>
            <h5>Aqui podras registrar los parametros de conexion con la app de Fitbit</h5>
            <table>
                <thead>
                <th>URL de Fitbit</th>
                <th>URL de API Fitbit</th>
                <th>OAuth2.0 ID de Cliente</th>
                <th>URI de redireccion</th>
                <th>Tiempo de expiracion</th>
                <th>Acciones</th>
                </thead>
                <tbody>
                    <%
                        Fitbit fitbit = Handler.getAllFitbit();
                        if (fitbit == null) {%>
                <a href="fitbit?action=add">Agregar nuevas credenciales</a>
                <%}else{%>
                <td><%=fitbit.getFITBIT_URL()%></td>
                <td><%=fitbit.getFITBIT_API_URL()%></td>
                <td><%=fitbit.getOAUTH_CLIENTID()%></td>
                <td><%=fitbit.getREDIRECT_URI()%></td>
                <td><%=fitbit.getEXPIRATION_TIME()%></td>
                <td><a href="fitbit?action=erase&OAUTH_CLIENTID=<%=fitbit.getOAUTH_CLIENTID()%>">Eliminar</a></td>
                <%}%>
                </tbody>
            </table>
        </div>

    </body>
</html>