/*
 * Built in 2017© By Worker's in Kraken Tech Ltd.
 * Compiled and Revised by Advisors outside the company
 * Refer to documentation attached for any other reference on code or anything related to this Source Code
 */
package DatabaseManager;

import BasicElements.*;
import java.security.*;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a01334390
 */
public class Handler {

    private static String host = "jdbc:mysql://localhost/AMSS_BDD";
    private static String huser = "root";
    private static String hpassword = "";

    /**
     * This method validates if the user exit, however, it does so without ever
     * touching the password
     *
     * @param username, as String
     * @param password, as String
     * @return if the user exists in the database
     */
    public static boolean userValidation(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        try {
            try (Connection connection = DriverManager.getConnection(host, huser, hpassword); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT usuario FROM AMSS_BDD.Usuario WHERE usuario='" + username + "' AND contrasenia='" + password + "'");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    count++;
                }
            }
            return count != 0;
            //return session;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }
//
//   __  __                      _     
//  / / / /______  ______ ______(_)___ 
// / / / / ___/ / / / __ `/ ___/ / __ \
/// /_/ (__  ) /_/ / /_/ / /  / / /_/ /
//\____/____/\__,_/\__,_/_/  /_/\____/ 
//                    

    public static Usuario userSearch(String username, String select) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(host, huser, hpassword); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT " + select + " FROM AMSS_BDD.Usuario WHERE usuario='" + username + "'");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    return new Usuario(resultset.getInt("idUsuario"), resultset.getString("primerNombre"), resultset.getString("segundoNombre"), resultset.getString("email"), resultset.getString("usuario"), resultset.getDate("fechaNacimiento"), resultset.getDate("fechaValidez"), resultset.getInt("privilegio"));
                }
                //return session;
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static Usuario userSearchid(String username, String select) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(host, huser, hpassword); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT " + select + " FROM AMSS_BDD.Usuario WHERE idUsuario='" + username + "'");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    return new Usuario(resultset.getInt("idUsuario"), resultset.getString("primerNombre"), resultset.getString("segundoNombre"), resultset.getString("email"), resultset.getString("usuario"), resultset.getDate("fechaNacimiento"), resultset.getDate("fechaValidez"), resultset.getInt("privilegio"));
                }
                //return session;
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static Usuario[] getAllUsers() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(host, huser, hpassword); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT * FROM AMSS_BDD.Usuario");
                //if there is no data on the data set, the session return will be false
                ArrayList<Usuario> array = new ArrayList<>();
                while (resultset.next()) {
                    array.add(new Usuario(resultset.getInt("idUsuario"), resultset.getString("primerNombre"), resultset.getString("segundoNombre"), resultset.getString("email"), resultset.getString("usuario"), resultset.getDate("fechaNacimiento"), resultset.getDate("fechaValidez"), resultset.getInt("privilegio")));
                }
                return array.toArray(new Usuario[array.size()]);
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static boolean deleteUsuario(String usuarioID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("DELETE FROM AMSS_BDD.Usuario WHERE usuario='" + usuarioID + "';");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean addUser(Usuario user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            String rment = "INSERT INTO AMSS_BDD.Usuario(primerNombre,segundoNombre,fechaNacimiento,email,usuario,contrasenia,fechaValidez,privilegio,idDomicilio) VALUES ('" + user.getPrimerNombre() + "','" + user.getSegundoNombre() + "','" + user.getFechaNacimiento() + "','" + user.getEmail() + "','" + user.getUsuario() + "','" + password + "','" + user.getFechaValidez() + "'," + user.getPrivilegio() + ",0);";
            System.out.println(rment);
            int rowsaffected = statement.executeUpdate("INSERT INTO AMSS_BDD.Usuario(primerNombre,segundoNombre,fechaNacimiento,email,usuario,contrasenia,fechaValidez,privilegio) VALUES ('" + user.getPrimerNombre() + "','" + user.getSegundoNombre() + "','" + user.getFechaNacimiento() + "','" + user.getEmail() + "','" + user.getUsuario() + "','" + password + "','" + user.getFechaValidez() + "'," + user.getPrivilegio() + ");");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean updateUser(Usuario user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("UPDATE AMSS_BDD.Usuario "
                    + "SET primerNombre='" + user.getPrimerNombre() + "',segundoNombre='" + user.getSegundoNombre() + "',fechaNacimiento='" + user.getFechaNacimiento().toString() + "',email='" + user.getEmail() + "',usuario='" + user.getUsuario() + "',contrasenia='" + password + "',fechaValidez='" + user.getFechaValidez().toString() + "',privilegio='" + user.getPrivilegio() + "'"
                    + " WHERE usuario='" + user.getUsuario() + "';");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

//    ____  ___   _________________   ______________
//   / __ \/   | / ____/  _/ ____/ | / /_  __/ ____/
//  / /_/ / /| |/ /    / // __/ /  |/ / / / / __/   
// / ____/ ___ / /____/ // /___/ /|  / / / / /___   
///_/   /_/  |_\____/___/_____/_/ |_/ /_/ /_____/   
//                                                  
    public static Paciente pacienteSearch(String username, String select) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(host, huser, hpassword); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT " + select + " FROM AMSS_BDD.Paciente WHERE usuario='" + username + "'");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    return new Paciente(resultset.getInt("idPaciente"), resultset.getInt("genero"), resultset.getInt("estadoCivil"), resultset.getInt("cohabitacion"), resultset.getString("primerNombre"), resultset.getString("segundoNombre"), resultset.getString("usuario"), resultset.getString("email"), resultset.getString("nacionalidad"), resultset.getString("estadoNacimiento"), resultset.getString("tipoSangre"), resultset.getString("afiliacionMedica"), resultset.getString("amai"), resultset.getDate("fechaDeNacimiento"));

                }
                //return session;
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static Paciente pacienteSearchid(String username, String select) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(host, huser, hpassword); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT " + select + " FROM AMSS_BDD.Paciente WHERE idPaciente=" + username + "");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    return new Paciente(resultset.getInt("idPaciente"), resultset.getInt("genero"), resultset.getInt("estadoCivil"), resultset.getInt("cohabitacion"), resultset.getString("primerNombre"), resultset.getString("segundoNombre"), resultset.getString("usuario"), resultset.getString("email"), resultset.getString("nacionalidad"), resultset.getString("estadoNacimiento"), resultset.getString("tipoSangre"), resultset.getString("afiliacionMedica"), resultset.getString("amai"), resultset.getDate("fechaDeNacimiento"));

                }
                //return session;
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static Paciente[] getAllPacientes() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(host, huser, hpassword); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT * FROM AMSS_BDD.Paciente;");
                //if there is no data on the data set, the session return will be false
                ArrayList<Paciente> array = new ArrayList<>();
                while (resultset.next()) {
                    Paciente pac = new Paciente(resultset.getInt("idPaciente"), resultset.getInt("genero"), resultset.getInt("estadoCivil"), resultset.getInt("cohabitacion"), resultset.getString("primerNombre"), resultset.getString("segundoNombre"), resultset.getString("usuario"), resultset.getString("email"), resultset.getString("nacionalidad"), resultset.getString("estadoNacimiento"), resultset.getString("tipoSangre"), resultset.getString("afiliacionMedica"), resultset.getString("amai"), resultset.getDate("fechaDeNacimiento"));
                    array.add(pac);
                }
                return array.toArray(new Paciente[array.size()]);
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static boolean deletePaciente(String usuarioID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("DELETE FROM AMSS_BDD.Paciente WHERE usuario='" + usuarioID + "';");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean addPaciente(Paciente paciente) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            String rment = "INSERT INTO `AMSS_BDD`.`Paciente`\n"
                    + "("
                    + "`primerNombre`,\n"
                    + "`segundoNombre`,\n"
                    + "`usuario`,\n"
                    + "`fechaDeNacimiento`,\n"
                    + "`genero`,\n"
                    + "`email`,\n"
                    + "`nacionalidad`,\n"
                    + "`estadoNacimiento`,\n"
                    + "`estadoCivil`,\n"
                    + "`tipoSangre`,\n"
                    + "`afiliacionMedica`,\n"
                    + "`amai`,\n"
                    + "`cohabitacion`,\n"
                    + "`FITAPP_CONSUMER_KEY`)\n"
                    + "VALUES(\n"
                    + "'" + paciente.getPrimerNombre() + "',\n"
                    + "'" + paciente.getSegundoNombre() + "',\n"
                    + "'" + paciente.getUsuario()+ "',\n"
                    + "'" + paciente.getFechaDeNacimiento() + "',\n"
                    + "" + paciente.getGenero() + ",\n"
                    + "'" + paciente.getEmail() + "',\n"
                    + "'" + paciente.getNacionalidad() + "',\n"
                    + "'" + paciente.getEstadoNacimiento() + "',\n"
                    + "" + paciente.getEstadoCivil() + ",\n"
                    + "'" + paciente.getTipoSangre() + "',\n"
                    + "'" + paciente.getAfiliacionMedica() + "',\n"
                    + "'" + paciente.getAmai() + "',\n"
                    + "" + paciente.getCohabitacion() + ",\n"
                    + "null\n"
                    + ");";
            System.out.println(rment);
            int rowsaffected = statement.executeUpdate(rment);
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean updatePaciente(Paciente paciente) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("UPDATE `AMSS_BDD`.`Paciente`\n"
                    + "SET\n"
                    + "`primerNombre` = '" + paciente.getPrimerNombre() + "',\n"
                    + "`segundoNombre` = '" + paciente.getSegundoNombre() + "',\n"
                    + "`usuario` = '" + paciente.getUsuario() + "',\n"
                    + "`fechaDeNacimiento` = '" + paciente.getFechaDeNacimiento() + "',\n"
                    + "`genero` = " + paciente.getGenero() + ",\n"
                    + "`email` = '" + paciente.getEmail() + "',\n"
                    + "`nacionalidad` = '" + paciente.getNacionalidad() + "',\n"
                    + "`estadoNacimiento` = '" + paciente.getEstadoNacimiento() + "',\n"
                    + "`estadoCivil` = " + paciente.getEstadoCivil() + ",\n"
                    + "`tipoSangre` = '" + paciente.getTipoSangre() + "',\n"
                    + "`afiliacionMedica` = '" + paciente.getAfiliacionMedica() + "',\n"
                    + "`amai` = '" + paciente.getAmai() + "',\n"
                    + "`cohabitacion` = " + paciente.getCohabitacion() + ",\n"
                    + "`FITAPP_CONSUMER_KEY` = null\n"
                    + "WHERE `usuario` = '" + paciente.getUsuario() + "';");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

// .---. .----..----. .-.  .--.  .---. .----. .-.  .--.  
///   __}| {_  | {}  }| | / {} \{_   _}| {}  }| | / {} \ 
//\  {_ }| {__ | .-. \| |/  /\  \ | |  | .-. \| |/  /\  \
// `---' `----'`-' `-'`-'`-'  `-' `-'  `-' `-'`-'`-'  `-'
    public static formaGeriatria formaGeriatriaSearch(String idForma) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(host, huser, hpassword); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT * FROM AMSS_BDD.formatoGeriatria WHERE idformatoGeriatria=" + idForma + ";");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    return new formaGeriatria(resultset.getInt("idformatoGeriatria"), resultset.getString("katz"), resultset.getString("katz_interpretacion"), resultset.getString("barthel"), resultset.getString("barthel_interpretacion"), resultset.getString("lawtonBrody"), resultset.getString("lawtonBrody_interpretacion"), resultset.getString("estadoMental"), resultset.getString("estadoMental_interpretacion"), resultset.getString("escalaDepresion"), resultset.getString("escalaDepresion_interpretacion"), resultset.getString("cribadoNutricional"), resultset.getString("cribadoNutricional_interpretacion"), resultset.getString("pruebaDesempenio"), resultset.getString("pruebaDesempenio_interpretacion"), resultset.getString("levantateAnda"), resultset.getString("levantateAnda_interpretacion"), resultset.getDate("fechaLlenado"), resultset.getInt("idUsuario"), resultset.getInt("idPaciente"));
                }
                //return session;
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static formaGeriatria[] getAllformaGeriatria(String idPaciente) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(host, huser, hpassword); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT * FROM AMSS_BDD.formatoGeriatria WHERE idPaciente = "+idPaciente+";");
                //if there is no data on the data set, the session return will be false
                ArrayList<formaGeriatria> array = new ArrayList<>();
                while (resultset.next()) {
                    array.add(new formaGeriatria(resultset.getInt("idformatoGeriatria"), resultset.getString("katz"), resultset.getString("katz_interpretacion"), resultset.getString("barthel"), resultset.getString("barthel_interpretacion"), resultset.getString("lawtonBrody"), resultset.getString("lawtonBrody_interpretacion"), resultset.getString("estadoMental"), resultset.getString("estadoMental_interpretacion"), resultset.getString("escalaDepresion"), resultset.getString("escalaDepresion_interpretacion"), resultset.getString("cribadoNutricional"), resultset.getString("cribadoNutricional_interpretacion"), resultset.getString("pruebaDesempenio"), resultset.getString("pruebaDesempenio_interpretacion"), resultset.getString("levantateAnda"), resultset.getString("levantateAnda_interpretacion"), resultset.getDate("fechaLlenado"), resultset.getInt("idUsuario"), resultset.getInt("idPaciente")));
                }
                return array.toArray(new formaGeriatria[array.size()]);
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static boolean deleteFormaGeriatria(String formaID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("DELETE FROM `AMSS_BDD`.`formatoGeriatria`\n"
                    + "WHERE idformatoGeriatria=" + formaID + ";");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean addFormaGeriatria(formaGeriatria forma) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            String rment = "INSERT INTO `AMSS_BDD`.`formatoGeriatria`\n"
                    + "("
                    + "`katz`,\n"
                    + "`katz_interpretacion`,\n"
                    + "`barthel`,\n"
                    + "`barthel_interpretacion`,\n"
                    + "`lawtonBrody`,\n"
                    + "`lawtonBrody_interpretacion`,\n"
                    + "`estadoMental`,\n"
                    + "`estadoMental_interpretacion`,\n"
                    + "`escalaDepresion`,\n"
                    + "`escalaDepresion_interpretacion`,\n"
                    + "`cribadoNutricional`,\n"
                    + "`cribadoNutricional_interpretacion`,\n"
                    + "`pruebaDesempenio`,\n"
                    + "`pruebaDesempenio_interpretacion`,\n"
                    + "`levantateAnda`,\n"
                    + "`levantateAnda_interpretacion`,\n"
                    + "`fechaLlenado`,\n"
                    + "`idUsuario`,\n"
                    + "`idPaciente`)\n"
                    + "VALUES\n"
                    + "("
                    + "'" + forma.getKatz() + "',\n"
                    + "'" + forma.getKatz_interpretacion() + "',\n"
                    + "'" + forma.getBarthel() + "',\n"
                    + "'" + forma.getBarthel_interpretacion() + "',\n"
                    + "'" + forma.getLawtonBrody() + "',\n"
                    + "'" + forma.getLawtonBrody_interpretacion() + "',\n"
                    + "'" + forma.getEstadoMental() + "',\n"
                    + "'" + forma.getEstadoMental_interpretacion() + "',\n"
                    + "'" + forma.getEscalaDepresion() + "',\n"
                    + "'" + forma.getEscalaDepresion_interpretacion() + "',\n"
                    + "'" + forma.getCribadoNutricional() + "',\n"
                    + "'" + forma.getCribadoNutricional_interpretacion() + "',\n"
                    + "'" + forma.getPruebaDesempenio() + "',\n"
                    + "'" + forma.getPruebaDesempenio_interpretacion() + "',\n"
                    + "'" + forma.getLevantateAnda() + "',\n"
                    + "'" + forma.getLevantateAnda_interpretacion() + "',\n"
                    + "'" + forma.getFechaLlenado() + "',\n"
                    + "" + forma.getIdUsuario() + ",\n"
                    + "" + forma.getIdPaciente() + ");";
            System.out.println(rment);
            int rowsaffected = statement.executeUpdate(rment);
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean updateFormaGeriatria(formaGeriatria forma) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("UPDATE `AMSS_BDD`.`formatoGeriatria`\n"
                    + "SET\n"
                    + "`katz` = '" + forma.getKatz() + "',\n"
                    + "`katz_interpretacion` = '" + forma.getKatz_interpretacion() + "',\n"
                    + "`barthel` = '" + forma.getBarthel() + "',\n"
                    + "`barthel_interpretacion` = '" + forma.getBarthel_interpretacion() + "',\n"
                    + "`lawtonBrody` = '" + forma.getLawtonBrody() + "',\n"
                    + "`lawtonBrody_interpretacion` = '" + forma.getLawtonBrody_interpretacion() + "',\n"
                    + "`estadoMental` = '" + forma.getEstadoMental() + "',\n"
                    + "`estadoMental_interpretacion` = '" + forma.getEstadoMental_interpretacion() + "',\n"
                    + "`escalaDepresion` = '" + forma.getEscalaDepresion() + "',\n"
                    + "`escalaDepresion_interpretacion` = '" + forma.getEscalaDepresion_interpretacion() + "',\n"
                    + "`cribadoNutricional` = '" + forma.getCribadoNutricional() + "',\n"
                    + "`cribadoNutricional_interpretacion` = '" + forma.getCribadoNutricional_interpretacion() + "',\n"
                    + "`pruebaDesempenio` = '" + forma.getPruebaDesempenio() + "',\n"
                    + "`pruebaDesempenio_interpretacion` = '" + forma.getPruebaDesempenio_interpretacion() + "',\n"
                    + "`levantateAnda` = '" + forma.getLevantateAnda() + "',\n"
                    + "`levantateAnda_interpretacion` = '" + forma.getLevantateAnda_interpretacion() + "',\n"
                    + "`fechaLlenado` = '" + forma.getFechaLlenado() + "',\n"
                    + "`idUsuario` = " + forma.getIdUsuario() + ",\n"
                    + "`idPaciente` = " + forma.getIdPaciente() + "\n"
                    + "WHERE `idformatoGeriatria` = " + forma.getIdformatoGeriatra() + ";");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

// ________                                                        __                 
//|        \                                                      |  \                
//| $$$$$$$$______    ______   ______ ____    ______          ____| $$  ______        
//| $$__   /      \  /      \ |      \    \  |      \        /      $$ /      \       
//| $$  \ |  $$$$$$\|  $$$$$$\| $$$$$$\$$$$\  \$$$$$$\      |  $$$$$$$|  $$$$$$\      
//| $$$$$ | $$  | $$| $$   \$$| $$ | $$ | $$ /      $$      | $$  | $$| $$    $$      
//| $$    | $$__/ $$| $$      | $$ | $$ | $$|  $$$$$$$      | $$__| $$| $$$$$$$$      
//| $$     \$$    $$| $$      | $$ | $$ | $$ \$$    $$       \$$    $$ \$$     \      
// \$$      \$$$$$$  \$$       \$$  \$$  \$$  \$$$$$$$        \$$$$$$$  \$$$$$$$      
//                                                                                    
//                                                                                    
//                                                                                    
// ________                             __  __  __        __                  __      
//|        \                           |  \|  \|  \      |  \                |  \     
//| $$$$$$$$______   ______    ______   \$$| $$ \$$  ____| $$  ______    ____| $$     
//| $$__   /      \ |      \  /      \ |  \| $$|  \ /      $$ |      \  /      $$     
//| $$  \ |  $$$$$$\ \$$$$$$\|  $$$$$$\| $$| $$| $$|  $$$$$$$  \$$$$$$\|  $$$$$$$     
//| $$$$$ | $$   \$$/      $$| $$  | $$| $$| $$| $$| $$  | $$ /      $$| $$  | $$     
//| $$    | $$     |  $$$$$$$| $$__| $$| $$| $$| $$| $$__| $$|  $$$$$$$| $$__| $$     
//| $$    | $$      \$$    $$ \$$    $$| $$| $$| $$ \$$    $$ \$$    $$ \$$    $$     
// \$$     \$$       \$$$$$$$ _\$$$$$$$ \$$ \$$ \$$  \$$$$$$$  \$$$$$$$  \$$$$$$$     
//                           |  \__| $$                                               
//                            \$$    $$                                               
//                             \$$$$$$                                                
    public static formaFragilidad[] getAllformaFragilidad(String idPaciente) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(host, huser, hpassword); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT `evaluacionFragilidad`.`idevaluacionFragilidad`,\n"
                        + "    `evaluacionFragilidad`.`perdidaPeso`,\n"
                        + "    `evaluacionFragilidad`.`perdidaPeso_interpretacion`,\n"
                        + "    `evaluacionFragilidad`.`pobreResistencia`,\n"
                        + "    `evaluacionFragilidad`.`pobreResistencia_interpretacion`,\n"
                        + "    `evaluacionFragilidad`.`velocidadMarcha`,\n"
                        + "    `evaluacionFragilidad`.`velocidadMarcha_interpretacion`,\n"
                        + "    `evaluacionFragilidad`.`fuerzaPresion`,\n"
                        + "    `evaluacionFragilidad`.`fuerzaPresion_interpretacion`,\n"
                        + "    `evaluacionFragilidad`.`actividadFisica`,\n"
                        + "    `evaluacionFragilidad`.`actividadFisica_interpretacion`,\n"
                        + "    `evaluacionFragilidad`.`diagnostico`,\n"
                        + "    `evaluacionFragilidad`.`evaluacionFuncional`,\n"
                        + "    `evaluacionFragilidad`.`evaluacionCognitiva`,\n"
                        + "    `evaluacionFragilidad`.`evaluacionNutricional`,\n"
                        + "    `evaluacionFragilidad`.`evaluacionDeFragilidad`,\n"
                        + "    `evaluacionFragilidad`.`fechaLlenado`,\n"
                        + "    `evaluacionFragilidad`.`IdPaciente`,\n"
                        + "    `evaluacionFragilidad`.`idUsuario`\n"
                        + "FROM `AMSS_BDD`.`evaluacionFragilidad` WHERE idPaciente="+idPaciente+";");
                //if there is no data on the data set, the session return will be false
                ArrayList<formaFragilidad> array = new ArrayList<>();
                while (resultset.next()) {
                    array.add(new formaFragilidad(resultset.getInt("idevaluacionFragilidad"), resultset.getInt("pobreResistencia"), resultset.getInt("actividadFisica"), resultset.getString("perdidaPeso"), resultset.getString("perdidaPeso_interpretacion"), resultset.getString("pobreResistencia_interpretacion"), resultset.getString("velocidadMarcha"), resultset.getString("velocidadMarcha_interpretacion"), resultset.getString("fuerzaPresion"), resultset.getString("fuerzaPresion_interpretacion"), resultset.getString("actividadFisica_interpretacion"), resultset.getString("diagnostico"), resultset.getString("evaluacionFuncional"), resultset.getString("evaluacionCognitiva"), resultset.getString("evaluacionNutricional"), resultset.getString("evaluacionDeFragilidad"), resultset.getDate("fechaLlenado"), resultset.getInt("idUsuario"), resultset.getInt("idPaciente")));
                }
                return array.toArray(new formaFragilidad[array.size()]);
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static formaFragilidad formaFragilidadSearch(String idForma) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(host, huser, hpassword); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT * FROM AMSS_BDD.evaluacionFragilidad WHERE idevaluacionFragilidad=" + idForma + ";");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    formaFragilidad form;
                    String fuerzaPresion = resultset.getString("fuerzaPresion_interpretacion");
                    String actividadFisica = resultset.getString("actividadFisica_interpretacion");
                    form = new formaFragilidad(resultset.getInt("idevaluacionFragilidad"), resultset.getInt("pobreResistencia"), resultset.getInt("actividadFisica"), resultset.getString("perdidaPeso"), resultset.getString("perdidaPeso_interpretacion"), resultset.getString("pobreResistencia_interpretacion"), resultset.getString("velocidadMarcha"), resultset.getString("velocidadMarcha_interpretacion"), resultset.getString("fuerzaPresion"), fuerzaPresion, actividadFisica, resultset.getString("diagnostico"), resultset.getString("evaluacionFuncional"), resultset.getString("evaluacionCognitiva"), resultset.getString("evaluacionNutricional"), resultset.getString("evaluacionDeFragilidad"), resultset.getDate("fechaLlenado"), resultset.getInt("idUsuario"), resultset.getInt("idPaciente"));
                    return form;
                }
                //return session;
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static boolean deleteFormaFragilidad(String formaID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("DELETE FROM `AMSS_BDD`.`evaluacionFragilidad`\n"
                    + "WHERE idevaluacionFragilidad=" + formaID + ";");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean addFormaFragilidad(formaFragilidad forma) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            String rment = "INSERT INTO `AMSS_BDD`.`evaluacionFragilidad`\n"
                    + "("
                    + "`perdidaPeso`,\n"
                    + "`perdidaPeso_interpretacion`,\n"
                    + "`pobreResistencia`,\n"
                    + "`pobreResistencia_interpretacion`,\n"
                    + "`velocidadMarcha`,\n"
                    + "`velocidadMarcha_interpretacion`,\n"
                    + "`fuerzaPresion`,\n"
                    + "`fuerzaPresion_interpretacion`,\n"
                    + "`actividadFisica`,\n"
                    + "`actividadFisica_interpretacion`,\n"
                    + "`diagnostico`,\n"
                    + "`evaluacionFuncional`,\n"
                    + "`evaluacionCognitiva`,\n"
                    + "`evaluacionNutricional`,\n"
                    + "`evaluacionDeFragilidad`,\n"
                    + "`fechaLlenado`,\n"
                    + "`IdPaciente`,\n"
                    + "`idUsuario`)\n"
                    + "VALUES\n"
                    + "("
                    + "" + forma.getPerdidaPeso() + ",\n"
                    + "'" + forma.getPerdidaPeso_interpretacion() + "',\n"
                    + "" + forma.getPobreResistencia() + ",\n"
                    + "'" + forma.getPobreResistencia_interpretacion() + "',\n"
                    + "'" + forma.getVelocidadMarcha() + "',\n"
                    + "'" + forma.getVelocidadMarcha_interpretacion() + "',\n"
                    + "'" + forma.getFuerzaPresion() + "',\n"
                    + "'" + forma.getFuerzaPresion_interpretacion() + "',\n"
                    + "" + forma.getActividadFisica() + ",\n"
                    + "'" + forma.getActividadFisica_interpretacion() + "',\n"
                    + "'" + forma.getDiagnostico() + "',\n"
                    + "'" + forma.getEvaluacionFuncional() + "',\n"
                    + "'" + forma.getEvaluacionCognitiva() + "',\n"
                    + "'" + forma.getEvaluacionNutricional() + "',\n"
                    + "'" + forma.getEvaluacionDeFragilidad() + "',\n"
                    + "'" + forma.getFechaLlenado() + "',\n"
                    + "" + forma.getIdPaciente() + ",\n"
                    + "" + forma.getIdUsuario() + ");";
            System.out.println(rment);
            int rowsaffected = statement.executeUpdate(rment);
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean updateFormaFragilidad(formaFragilidad forma) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            String qer = "UPDATE `AMSS_BDD`.`evaluacionFragilidad`\n"
                    + "SET\n"
                    + "`perdidaPeso` = '" + forma.getPerdidaPeso() + "',\n"
                    + "`perdidaPeso_interpretacion` = '" + forma.getPerdidaPeso_interpretacion() + "',\n"
                    + "`pobreResistencia` = " + forma.getPobreResistencia() + ",\n"
                    + "`pobreResistencia_interpretacion` = '" + forma.getPobreResistencia_interpretacion() + "',\n"
                    + "`velocidadMarcha` = '" + forma.getVelocidadMarcha() + "',\n"
                    + "`velocidadMarcha_interpretacion` = '" + forma.getVelocidadMarcha_interpretacion() + "',\n"
                    + "`fuerzaPresion` = '" + forma.getFuerzaPresion() + "',\n"
                    + "`fuerzaPresion_interpretacion` = '" + forma.getFuerzaPresion_interpretacion() + "',\n"
                    + "`actividadFisica` = " + forma.getActividadFisica() + ",\n"
                    + "`actividadFisica_interpretacion` = '" + forma.getActividadFisica_interpretacion() + "',\n"
                    + "`diagnostico` = '" + forma.getDiagnostico() + "',\n"
                    + "`evaluacionFuncional` = '" + forma.getEvaluacionFuncional() + "',\n"
                    + "`evaluacionCognitiva` = '" + forma.getEvaluacionCognitiva() + "',\n"
                    + "`evaluacionNutricional` = '" + forma.getEvaluacionNutricional() + "',\n"
                    + "`evaluacionDeFragilidad` = '" + forma.getEvaluacionDeFragilidad() + "',\n"
                    + "`fechaLlenado` = '" + forma.getFechaLlenado() + "',\n"
                    + "`IdPaciente` = " + forma.getIdPaciente() + ",\n"
                    + "`idUsuario` = " + forma.getIdUsuario() + "\n"
                    + "WHERE `idevaluacionFragilidad` = " + forma.getIdevaluacionFragilidad() + ";";
            System.out.println(qer);
            int rowsaffected = statement.executeUpdate(qer);
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static formaGerontologia formaGerontologiaSearch(String idForm) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(host, huser, hpassword); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT * FROM AMSS_BDD.ValoracionGerontologica WHERE idValoracionGerontologica=" + idForm + ";");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    formaGerontologia form;
                    form = new formaGerontologia(resultset.getInt("idvaloracionGerontologica"), resultset.getString("dispositivosUso"), resultset.getString("dispositivoMayorUso"), resultset.getString("frecuenciaUso"), resultset.getString("actividadesUso"), resultset.getString("usosFavorecer"), resultset.getString("apoyosocialPercibido"), resultset.getString("actividadesComunitarias"), resultset.getString("impresionDiagnostica"), resultset.getDate("fechaLlenado"), resultset.getInt("idUsuario"), resultset.getInt("idPaciente"));
                    return form;
                }
                //return session;
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static formaGerontologia[] getAllformaGerontologia(String idPaciente) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(host, huser, hpassword); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT * FROM AMSS_BDD.ValoracionGerontologica WHERE idPaciente="+idPaciente+";");
                //if there is no data on the data set, the session return will be false
                ArrayList<formaGerontologia> array = new ArrayList<>();
                while (resultset.next()) {
                    formaGerontologia form;
                    form = new formaGerontologia(resultset.getInt("idvaloracionGerontologica"), resultset.getString("dispositivosUso"), resultset.getString("dispositivoMayorUso"), resultset.getString("frecuenciaUso"), resultset.getString("actividadesUso"), resultset.getString("usosFavorecer"), resultset.getString("apoyosocialPercibido"), resultset.getString("actividadesComunitarias"), resultset.getString("impresionDiagnostica"), resultset.getDate("fechaLlenado"), resultset.getInt("idUsuario"), resultset.getInt("idPaciente"));
                    array.add(form);
                }
                return array.toArray(new formaGerontologia[array.size()]);
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static boolean deleteFormaGerontologia(String idForm) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("DELETE FROM `AMSS_BDD`.`ValoracionGerontologica`\n"
                    + "WHERE idValoracionGerontologica=" + idForm + ";");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean addFormaGerontologia(formaGerontologia forma) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            String rment = "INSERT INTO `AMSS_BDD`.`ValoracionGerontologica`\n"
                    + "("
                    + "`dispositivosUso`,\n"
                    + "`dispositivoMayorUso`,\n"
                    + "`frecuenciaUso`,\n"
                    + "`actividadesUso`,\n"
                    + "`usosFavorecer`,\n"
                    + "`apoyosocialPercibido`,\n"
                    + "`actividadesComunitarias`,\n"
                    + "`impresionDiagnostica`,\n"
                    + "`fechaLlenado`,\n"
                    + "`idUsuario`,\n"
                    + "`idPaciente`)\n"
                    + "VALUES\n"
                    + "("
                    + "'" + forma.getDispositivosUso() + "',\n"
                    + "'" + forma.getDispositivoMayorUso() + "',\n"
                    + "'" + forma.getFrecuenciaUso() + "',\n"
                    + "'" + forma.getActividadesUso() + "',\n"
                    + "'" + forma.getUsosFavorecer() + "',\n"
                    + "'" + forma.getApoyosocialPercibido() + "',\n"
                    + "'" + forma.getActividadesComunitarias() + "',\n"
                    + "'" + forma.getImpresionDiagnostica() + "',\n"
                    + "'" + forma.getFechaLlenado() + "',\n"
                    + "" + forma.getIdUsuario() + ",\n"
                    + "" + forma.getIdPaciente() + ");";
            System.out.println(rment);
            int rowsaffected = statement.executeUpdate(rment);
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean updateFormaGerontologia(formaGerontologia forma) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            String qer = "UPDATE `AMSS_BDD`.`ValoracionGerontologica`\n"
                    + "SET\n"
                    + "`dispositivosUso` = '" + forma.getDispositivosUso() + "',\n"
                    + "`dispositivoMayorUso` = '" + forma.getDispositivoMayorUso() + "',\n"
                    + "`frecuenciaUso` = '" + forma.getFrecuenciaUso() + "',\n"
                    + "`actividadesUso` = '" + forma.getActividadesUso() + "',\n"
                    + "`usosFavorecer` = '" + forma.getUsosFavorecer() + "',\n"
                    + "`apoyosocialPercibido` = '" + forma.getApoyosocialPercibido() + "',\n"
                    + "`actividadesComunitarias` = '" + forma.getActividadesComunitarias() + "',\n"
                    + "`impresionDiagnostica` = '" + forma.getImpresionDiagnostica() + "',\n"
                    + "`fechaLlenado` = '" + forma.getFechaLlenado() + "',\n"
                    + "`idUsuario` = " + forma.getIdUsuario() + ",\n"
                    + "`idPaciente` = " + forma.getIdPaciente() + "\n"
                    + "WHERE `idValoracionGerontologica` = " + forma.getIdvaloracionGerontologica() + ";";
            System.out.println(qer);
            int rowsaffected = statement.executeUpdate(qer);
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

// ____            _     _ _ _     
//|    \ ___ _____|_|___|_| |_|___ 
//|  |  | . |     | |  _| | | | . |
//|____/|___|_|_|_|_|___|_|_|_|___|
//
    /**
     *
     * @param dom
     * @return
     */
    public static boolean deleteDomicilio(String usuario) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("DELETE FROM `AMSS_BDD`.`Domicilio`\n"
                    + "WHERE usuario='" + usuario + "';");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean addDomicilio(Domicilio dom) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            String rment = "INSERT INTO `AMSS_BDD`.`Domicilio`\n"
                    + "("
                    + "`pais`,\n"
                    + "`estado`,\n"
                    + "`ciudad`,\n"
                    + "`colonia`,\n"
                    + "`calle`,\n"
                    + "`numeroExterno`,\n"
                    + "`numeroInterno`,\n"
                    + "`codigoPostal`,\n"
                    + "`usuario`)\n"
                    + "VALUES(\n"
                    + "'" + dom.getPais() + "',\n"
                    + "'" + dom.getEstado() + "',\n"
                    + "'" + dom.getCiudad() + "',\n"
                    + "'" + dom.getColonia() + "',\n"
                    + "'" + dom.getCalle() + "',\n"
                    + "" + dom.getNumeroExterno() + ",\n"
                    + "" + dom.getNumeroInterno() + ",\n"
                    + "'" + dom.getCodigoPostal() + "',\n"
                    + "'" + dom.getUsuario() + "');";
            System.out.println(rment);
            int rowsaffected = statement.executeUpdate(rment);
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean updateDomicilio(Domicilio dom) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(host, huser, hpassword);
            Statement statement = connection.createStatement();
            String qer = "UPDATE `AMSS_BDD`.`Domicilio`\n"
                    + "SET\n"
                    + "`pais` = '" + dom.getPais() + "',\n"
                    + "`estado` = '" + dom.getEstado() + "',\n"
                    + "`ciudad` = '" + dom.getCiudad() + "',\n"
                    + "`colonia` = '" + dom.getColonia() + "',\n"
                    + "`calle` = '" + dom.getCalle() + "',\n"
                    + "`numeroExterno` = " + dom.getNumeroExterno() + ",\n"
                    + "`numeroInterno` = " + dom.getNumeroInterno() + ",\n"
                    + "`codigoPostal` = '" + dom.getCodigoPostal() + "',\n"
                    + "`usuario` = '" + dom.getUsuario() + "'\n"
                    + "WHERE `usuario` = '"+dom.getUsuario()+"';";
            System.out.println(qer);
            int rowsaffected = statement.executeUpdate(qer);
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static Domicilio searchDomicilio(String user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(host, huser, hpassword); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT `Domicilio`.`idDomicilio`,\n"
                        + "    `Domicilio`.`pais`,\n"
                        + "    `Domicilio`.`estado`,\n"
                        + "    `Domicilio`.`ciudad`,\n"
                        + "    `Domicilio`.`colonia`,\n"
                        + "    `Domicilio`.`calle`,\n"
                        + "    `Domicilio`.`numeroExterno`,\n"
                        + "    `Domicilio`.`numeroInterno`,\n"
                        + "    `Domicilio`.`codigoPostal`,\n"
                        + "    `Domicilio`.`usuario`\n"
                        + "FROM `AMSS_BDD`.`Domicilio` WHERE usuario='" + user + "';");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    Domicilio dom;
                    dom = new Domicilio(resultset.getString("pais"), resultset.getString("estado"), resultset.getString("ciudad"), resultset.getString("colonia"), resultset.getString("calle"), resultset.getString("codigoPostal"), resultset.getString("usuario"), resultset.getInt("numeroInterno"), resultset.getInt("numeroExterno"));
                    return dom;
                }
                //return session;
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

}
