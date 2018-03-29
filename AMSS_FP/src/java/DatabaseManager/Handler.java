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

    /**
     * Searches and retrieves information about an User
     *
     * @param username
     * @return
     */
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

    public static Paciente pacienteSearch(String username, String select) {
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
                    return new Paciente(resultset.getInt("pacienteID"), resultset.getInt("genero"), resultset.getInt("estadoCivil"), resultset.getInt("cohabitacion"), resultset.getString("primerNombre"), resultset.getString("segundoNombre"), resultset.getString("email"), resultset.getString("email"), resultset.getString("nacionalidad"), resultset.getString("estadoNacimiento"), resultset.getString("tipoSangre"), resultset.getString("afiliacionMedica"), resultset.getString("amai"), resultset.getDate("fechaNacimiento"));
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
                    array.add(new Paciente(resultset.getInt("idPaciente"), resultset.getInt("genero"), resultset.getInt("estadoCivil"), resultset.getInt("cohabitacion"), resultset.getString("primerNombre"), resultset.getString("segundoNombre"), resultset.getString("usuario"),resultset.getString("email") , resultset.getString("nacionalidad"), resultset.getString("estadoNacimiento"), resultset.getString("tipoSangre"), resultset.getString("afiliacionMedica"), resultset.getString("amai"), resultset.getDate("fechaNacimiento")));
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

    public static boolean addPaciente(Paciente paciente, String password) {
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
                    + "`idDomicilio`,\n"
                    + "`FITAPP_CONSUMER_KEY`)\n"
                    + "VALUES\n"
                    + "'" + paciente.getPrimerNombre() + "',\n"
                    + "'" + paciente.getSegundoNombre() + "',\n"
                    + "'" + paciente.getPacienteID() + "',\n"
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
                    + "0,\n"
                    + "0,\n"
                    + ");";
            System.out.println(rment);
            int rowsaffected = statement.executeUpdate(rment);
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean updatePaciente(Usuario user, String password) {
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

}
