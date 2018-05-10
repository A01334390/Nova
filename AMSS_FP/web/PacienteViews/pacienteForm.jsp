<%-- 
    Document   : pacienteAll
    Created on : Mar 29, 2018, 11:51:02 AM
    Author     : Luna
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Nova - Forma para Pacientes</title>
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
        <script>
            submitForms = function () {
                document.forms["formAddPaciente"].submit();
                document.forms["formAddDomicilio"].submit();
            }
        </script>

        <!-- Favicon
      –––––––––––––––––––––––––––––––––––––––––––––––––– -->
        <link rel="icon" type="image/png" href="images/favicon.png">
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
                    <a class="navbar-link" href="home.jsp">Regresar</a>
                </li>
            </ul>
        </div>
    </nav>
    <body>
        <div class="container">
            <div class="six columns" style="margin-top: 15%">
                <h1>Bienvenido a la Forma de Pacientes</h1>
            </div>
        </div>

        <div>
            <div class ="container">
                <form action="paciente" method="POST" name="formAddPaciente">
                    <div class="row">
                        <div class="one-half column">
                            <label for="primerNombre">Primer nombre :</label>
                            <input class="u-full-width" required type="text" placeholder="Nombres" name="primerNombre" value="<c:out value="${paciente.getPrimerNombre()}"/>">
                        </div>
                    </div>

                    <div class="row">
                        <div class="one-half column">
                            <label for ="passwordInput">Segundo Nombre : </label>
                            <input class="u-full-width" required type="text" placeholder="Apellidos"  name="segundoNombre" value="<c:out value="${paciente.getSegundoNombre()}"/>">
                        </div>
                    </div>

                    <div class="row">
                        <div class="one-half column">
                            <label for ="passwordInput">Correo Electronico : </label>
                            <input class="u-full-width" required type="text" placeholder="hello@nova.io"  name="email" value="<c:out value="${paciente.getEmail()}"/>">
                        </div>
                    </div>

                    <div class="row">
                        <div class="one-half column">
                            <label for ="passwordInput">Nombre de Usuario : </label>
                            <input class="u-full-width" required type="text" placeholder="username"  name="usuario" value="<c:out value="${paciente.getUsuario()}"/>">
                        </div>
                    </div>

                    <div class="row">
                        <div class="one-half column">
                            <label for ="passwordInput">Nacionalidad : </label>
                            <input class="u-full-width" required type="text" placeholder="Mexicana"  name="nacionalidad" value="<c:out value="${paciente.getNacionalidad()}"/>">
                        </div>
                    </div>

                    <div class="row">
                        <div class="one-half column">
                            <label for ="passwordInput">Estado de Nacimiento : </label>
                            <input class="u-full-width" required type="text" name="estadoNacimiento" value="<c:out value="${paciente.getEstadoNacimiento()}"/>">
                        </div>
                    </div>

                    <div class="row">
                        <div class="one-half column">
                            <label for ="passwordInput">Fecha de Nacimiento : </label>
                            <input class="u-full-width" required type="date" name="fechaDeNacimiento" value="<c:out value="${paciente.getFechaDeNacimiento()}"/>">
                        </div>
                    </div>   

                        <div class="row">
                            <div class="one-half column">
                                <label>Escolaridad Maxima</label>
                                <select name="escolaridadMaxima" type="text" required>
                                    <option value="pri" ${paciente.getEscolaridadMaxima().equals("pri")? 'selected' : ''}>Primaria</option>
                                    <option value="sec" ${paciente.getEscolaridadMaxima().equals("sec")? 'selected' : ''}>Secundaria</option>
                                    <option value="pre" ${paciente.getEscolaridadMaxima().equals("pre")? 'selected' : ''}>Preparatoria</option>
                                    <option value="lic" ${paciente.getEscolaridadMaxima().equals("lic")? 'selected' : ''}>Licenciatura</option>
                                    <option value="mae" ${paciente.getEscolaridadMaxima().equals("mae")? 'selected' : ''}>Maestria</option>
                                    <option value="doc" ${paciente.getEscolaridadMaxima().equals("doc")? 'selected' : ''}>Doctorado</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="one-half column">
                                <label>Reporte de autopadecimiento:</label>
                                <input required type="text" name="autopadecimiento" value="<c:out value="${paciente.getAutopadecimiento()}"/>" required>
                            </div>
                        </div>

                    <div class="row">
                        <div class="row">
                            <label >Genero : </label>
                            <div class="one-half column">
                                <select required name="genero" type="text" required>
                                    <option value ="0" ${paciente.getGenero() == 0 ? 'selected="selected"' : ''}> Hombre </option>
                                    <option value ="1" ${paciente.getGenero() == 1 ? 'selected="selected"' : ''}> Mujer </option>
                                    <option value ="2" ${paciente.getGenero() == 2 ? 'selected="selected"' : ''}> No deseo identificarme </option>
                                    <option value ="3" ${paciente.getGenero() == 3 ? 'selected="selected"' : ''}> Otro </option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="row">
                            <label >Tipo de Sangre : </label>
                            <div class="one-half column">
                                <select name="tipoSangre" type="text" required>
                                    <option value ="A+" ${paciente.getTipoSangre().equals("A+") ? 'selected="selected"' : ''}> A+ </option>
                                    <option value ="B+" ${paciente.getTipoSangre().equals("B+") ? 'selected="selected"' : ''}> B+ </option>
                                    <option value ="O+" ${paciente.getTipoSangre().equals("O+") ? 'selected="selected"' : ''}> O+ </option>
                                    <option value ="AB+" ${paciente.getTipoSangre().equals("AB+") ? 'selected="selected"' : ''}> AB+ </option>
                                    <option value ="A-" ${paciente.getTipoSangre().equals("A-") ? 'selected="selected"' : ''}> A- </option>
                                    <option value ="B-" ${paciente.getTipoSangre().equals("B-") ? 'selected="selected"' : ''}> B- </option>
                                    <option value ="O-" ${paciente.getTipoSangre().equals("O-") ? 'selected="selected"' : ''}> O- </option>
                                    <option value ="AB-" ${paciente.getTipoSangre().equals("AB-") ? 'selected="selected"' : ''}> AB- </option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="row">
                            <label >Estado Civil : </label>
                            <div class="one-half column">
                                <select name="estadoCivil" type="text" required>
                                    <option value ="0" ${paciente.getEstadoCivil() == 0 ? 'selected="selected"' : ''}> Soltero/a </option>
                                    <option value ="1" ${paciente.getEstadoCivil() == 1 ? 'selected="selected"' : ''}> Casado/a </option>
                                    <option value ="2" ${paciente.getEstadoCivil() == 2 ? 'selected="selected"' : ''}> Viudo/a </option>
                                    <option value ="3" ${paciente.getEstadoCivil() == 3 ? 'selected="selected"' : ''}> Divorciado/a </option>
                                </select>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="row">
                            <label >Afiliacion Medica : </label>
                            <div class="one-half column">
                                <select name="afiliacionMedica" type="text" required>
                                    <option value ="IMSS" ${paciente.getAfiliacionMedica().equals("IMSS") ? 'selected="selected"' : ''}> IMSS </option>
                                    <option value ="ISSTE" ${paciente.getAfiliacionMedica().equals("ISSTE") ? 'selected="selected"' : ''}> ISSTE </option>

                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="row">
                            <label >AMAI : </label>
                            <div class="one-half column">
                                <select name="amai" type="text" required>
                                    <option value ="A/B" ${paciente.getAmai().equals("A/B") ? 'selected="selected"' : ''}> A/B </option>
                                    <option value ="C+" ${paciente.getAmai().equals("C+") ? 'selected="selected"' : ''}> C+ </option>
                                    <option value ="C" ${paciente.getAmai().equals("C") ? 'selected="selected"' : ''}> C </option>
                                    <option value ="C-" ${paciente.getAmai().equals("C-") ? 'selected="selected"' : ''}> C- </option>
                                    <option value ="D+" ${paciente.getAmai().equals("D+") ? 'selected="selected"' : ''}> D+ </option>
                                    <option value ="D" ${paciente.getAmai().equals("D") ? 'selected="selected"' : ''}> D </option>
                                    <option value ="E" ${paciente.getAmai().equals("E") ? 'selected="selected"' : ''}> E </option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="row">
                            <label >Vive usted con alguien mas? : </label>
                            <div class="one-half column">
                                <select name="cohabitacion" type="text" required>
                                    <option value ="0" ${paciente.getCohabitacion() == 0 ? 'selected="selected"' : ''}> No </option>
                                    <option value ="1" ${paciente.getCohabitacion() == 1 ? 'selected="selected"' : ''}> Si </option>

                                </select>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <h1>Domicilio</h1>
                    <p>A continuacion, ingresa tu domicilio.</p>
                    <div class="row">
                        <div class="three columns">
                            <label for="katz">Pais :</label>
                            <input required class="u-full-width" type="text" name="pais" value="<c:out value="${domicilio.getPais()}"/>">
                        </div>
                        <div class="four columns">
                            <label for="katz">Estado :</label>
                            <input required class="u-full-width" type="text" name="estado" value="<c:out value="${domicilio.getEstado()}"/>">
                        </div>
                    </div>
                    <div class="row">
                        <div class="three columns">
                            <label for="katz">Ciudad :</label>
                            <input required class="u-full-width" type="text" name="ciudad" value="<c:out value="${domicilio.getCiudad()}"/>">
                        </div>
                        <div class="four columns">
                            <label for="katz">Colonia :</label>
                            <input required class="u-full-width" type="text" name="colonia" value="<c:out value="${domicilio.getColonia()}"/>">
                        </div>
                    </div>
                    <div class="row">
                        <div class="three columns">
                            <label for="katz">Calle :</label>
                            <input required class="u-full-width" type="text" name="calle" value="<c:out value="${domicilio.getCalle()}"/>">
                        </div>
                        <div class="two columns">
                            <label for="katz">Numero Externo :</label>
                            <input required class="u-full-width" type="text" name="numeroExterno" value="<c:out value="${domicilio.getNumeroExterno()}"/>">
                        </div>
                        <div class="two columns">
                            <label for="katz">Numero Interno :</label>
                            <input required class="u-full-width" type="text" name="numeroInterno" value="<c:out value="${domicilio.getNumeroInterno()}"/>">
                        </div>
                    </div>
                    <div class="row">
                        <div class="seven columns">
                            <label for="katz">Codigo Postal :</label>
                            <input required class="u-full-width"  type="text" name="codigoPostal" value="<c:out value="${domicilio.getCodigoPostal()}"/>">
                        </div>
                    </div>
                    <input class="button-primary" type="submit" value="Submit" />
                </form>
            </div>
        </div>

</html>
