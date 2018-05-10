/*
 * Built in 2017© By Worker's in Kraken Tech Ltd.
 * Compiled and Revised by Advisors outside the company
 * Refer to documentation attached for any other reference on code or anything related to this Source Code
 */
package DatabaseManager;

import BasicElements.*;
import SecurityElements.RSADecryption;
import SecurityElements.RSAEncryption;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.Security;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.naming.*;

/**
 *
 * @author a01334390
 */
public class Handler {

    static RSADecryption rsadec = new RSADecryption();
    static RSAEncryption rsaenc = new RSAEncryption();

    private static String getHost() throws NamingException {
        Context ctx = new InitialContext();
        Context env = (Context) ctx.lookup("java:comp/env");
        final String hoster = (String) env.lookup("mysql-address");
        final String table = (String) env.lookup("mysql-table");

        return "jdbc:mysql://" + hoster + table;
    }

    private static String getHuser() throws NamingException {
        Context ctx = new InitialContext();
        Context env = (Context) ctx.lookup("java:comp/env");
        final String user = (String) env.lookup("mysql-user");

        return user;
    }

    private static String getHpassword() throws NamingException {
        Context ctx = new InitialContext();
        Context env = (Context) ctx.lookup("java:comp/env");
        String pass = (String) env.lookup("mysql-password");
        if (pass.equals("none")) {
            pass = "";
        }
        return pass;
    }

    private static String getPrivateKeyFilePath() throws NamingException {
        Context ctx = new InitialContext();
        Context env = (Context) ctx.lookup("java:comp/env");
        String filepath = (String) env.lookup("private-key-location");

        return filepath;
    }

    private static String getPublicKeyFilePath() throws NamingException {
        Context ctx = new InitialContext();
        Context env = (Context) ctx.lookup("java:comp/env");
        String filepath = (String) env.lookup("public-key-location");

        return filepath;
    }

    /**
     * This method validates if the user exit, however, it does so without ever
     * touching the password
     *
     * @param username, as String
     * @param password, as String
     * @return if the user exists in the database
     * @throws java.io.UnsupportedEncodingException
     */
    public static boolean userValidation(String username, String password) throws UnsupportedEncodingException, NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                PreparedStatement pre = connection.prepareStatement("SELECT usuario FROM AMSS_BDD.Usuario WHERE usuario=? AND contrasenia=?;");
                pre.setString(1, username);
                //Create the HASH
                DigestSHA3 md = new DigestSHA3(256);
                md.update(password.getBytes());
                //Make the byte to a String
                byte[] digest = md.digest();
                StringBuffer buff = new StringBuffer();

                for (byte b : digest) {
                    buff.append(String.format("%02x", b & 0xFF));
                }
                String paswuord = buff.toString();
                pre.setString(2, paswuord);
                ResultSet resultset = pre.executeQuery();
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

    public static boolean loginPaciente(String idPaciente, String usuario) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                PreparedStatement pre = connection.prepareStatement("SELECT idPaciente FROM AMSS_BDD.Paciente WHERE idPaciente=? AND usuario=?;");
                pre.setString(1, idPaciente);
                pre.setString(2, usuario);
                ResultSet resultset = pre.executeQuery();
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

    public static Usuario userSearch(String username, String select) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT " + select + " FROM AMSS_BDD.Usuario WHERE usuario='" + username + "'");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    Usuario us = new Usuario(resultset.getInt("idUsuario"), resultset.getString("primerNombre"), resultset.getString("segundoNombre"), resultset.getString("email"), resultset.getString("usuario"), resultset.getDate("fechaNacimiento"), resultset.getDate("fechaValidez"), resultset.getInt("privilegio"), null);
                    return us;
                }
                //return session;
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static Usuario userSearchid(String username, String select) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT " + select + " FROM AMSS_BDD.Usuario WHERE idUsuario='" + username + "'");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    return new Usuario(resultset.getInt("idUsuario"), resultset.getString("primerNombre"), resultset.getString("segundoNombre"), resultset.getString("email"), resultset.getString("usuario"), resultset.getDate("fechaNacimiento"), resultset.getDate("fechaValidez"), resultset.getInt("privilegio"), resultset.getBinaryStream("image"));
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static Usuario[] getAllUsers() throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT * FROM AMSS_BDD.Usuario");
                //if there is no data on the data set, the session return will be false
                ArrayList<Usuario> array = new ArrayList<>();
                while (resultset.next()) {
                    array.add(new Usuario(resultset.getInt("idUsuario"), resultset.getString("primerNombre"), resultset.getString("segundoNombre"), resultset.getString("email"), resultset.getString("usuario"), resultset.getDate("fechaNacimiento"), resultset.getDate("fechaValidez"), resultset.getInt("privilegio"), resultset.getBinaryStream("image")));
                }
                return array.toArray(new Usuario[array.size()]);
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static boolean deleteUsuario(String usuarioID) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
            PreparedStatement pre = connection.prepareStatement("DELETE FROM AMSS_BDD.Usuario WHERE usuario='" + usuarioID + "';");
            int rowsaffected = pre.executeUpdate();
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean addUser(Usuario user, String password) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
            PreparedStatement pre = connection.prepareStatement("INSERT INTO AMSS_BDD.Usuario(primerNombre,segundoNombre,fechaNacimiento,email,usuario,contrasenia,fechaValidez,privilegio) VALUES (?,?,?,?,?,?,?,?);");
            pre.setString(1, user.getPrimerNombre());
            pre.setString(2, user.getSegundoNombre());
            pre.setDate(3, convertFromJAVADateToSQLDate(user.getFechaNacimiento()));
            pre.setString(4, user.getEmail());
            pre.setString(5, user.getUsuario());
            //HASH THE PASSWORD
            DigestSHA3 md = new DigestSHA3(256);
            md.update(password.getBytes());
            //Make the byte to a String
            byte[] digest = md.digest();
            StringBuffer buff = new StringBuffer();

            for (byte b : digest) {
                buff.append(String.format("%02x", b & 0xFF));
            }
            String paswuord = buff.toString();
            //SET IT
            pre.setString(6, paswuord);
            pre.setDate(7, convertFromJAVADateToSQLDate(user.getFechaValidez()));
            pre.setInt(8, user.getPrivilegio());
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean updateUser(Usuario user, String password, int photolength) throws ParseException, NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
            PreparedStatement pre = connection.prepareStatement("UPDATE `AMSS_BDD`.`Usuario`\n"
                    + "SET\n"
                    + "`primerNombre` = ?,\n"
                    + "`segundoNombre` = ?,\n"
                    + "`fechaNacimiento` = ?,\n"
                    + "`email` = ?,\n"
                    + "`usuario` = ?,\n"
                    + "`contrasenia` = ?,\n"
                    + "`fechaValidez` = ?,\n"
                    + "`privilegio` = ?,\n"
                    + "`image` = ?\n"
                    + "WHERE `usuario` = ?;");

            pre.setString(1, user.getPrimerNombre());
            pre.setString(2, user.getSegundoNombre());
            pre.setDate(3, convertFromJAVADateToSQLDate(user.getFechaNacimiento()));
            pre.setString(4, user.getEmail());
            pre.setString(5, user.getUsuario());
            //HASH THE PASSWORD
            DigestSHA3 md = new DigestSHA3(256);
            md.update(password.getBytes());
            //Make the byte to a String
            byte[] digest = md.digest();
            StringBuffer buff = new StringBuffer();

            for (byte b : digest) {
                buff.append(String.format("%02x", b & 0xFF));
            }
            String paswuord = buff.toString();
            //SET IT
            pre.setString(6, paswuord);
            pre.setDate(7, convertFromJAVADateToSQLDate(user.getFechaValidez()));
            pre.setInt(8, user.getPrivilegio());
            pre.setBinaryStream(9, user.getPhoto(), photolength);
            pre.setString(10, user.getUsuario());
            int rowsaffected = pre.executeUpdate();
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
    public static Paciente pacienteSearch(String username, String select) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT " + select + " FROM AMSS_BDD.Paciente WHERE usuario='" + username + "'");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    String primerNombre = rsadec.decrypt(getPrivateKeyFilePath(), resultset.getString("primerNombre"));
                    String segundoNombre = rsadec.decrypt(getPrivateKeyFilePath(), resultset.getString("segundoNombre"));
                    return new Paciente(resultset.getInt("idPaciente"), resultset.getInt("genero"), resultset.getInt("estadoCivil"), resultset.getInt("cohabitacion"), primerNombre, segundoNombre, resultset.getString("usuario"), resultset.getString("email"), resultset.getString("nacionalidad"), resultset.getString("estadoNacimiento"), resultset.getString("tipoSangre"), resultset.getString("afiliacionMedica"), resultset.getString("amai"), resultset.getDate("fechaDeNacimiento"), resultset.getString("escolaridadMaxima"), resultset.getString("autopadecimiento"));

                }
                //return session;
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static Paciente pacienteSearchid(String username, String select) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT " + select + " FROM AMSS_BDD.Paciente WHERE idPaciente=" + username + "");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    String primerNombre = rsadec.decrypt(getPrivateKeyFilePath(), resultset.getString("primerNombre"));
                    String segundoNombre = rsadec.decrypt(getPrivateKeyFilePath(), resultset.getString("segundoNombre"));
                    return new Paciente(resultset.getInt("idPaciente"), resultset.getInt("genero"), resultset.getInt("estadoCivil"), resultset.getInt("cohabitacion"), primerNombre, segundoNombre, resultset.getString("usuario"), resultset.getString("email"), resultset.getString("nacionalidad"), resultset.getString("estadoNacimiento"), resultset.getString("tipoSangre"), resultset.getString("afiliacionMedica"), resultset.getString("amai"), resultset.getDate("fechaDeNacimiento"), resultset.getString("escolaridadMaxima"), resultset.getString("autopadecimiento"));
                }
                //return session;
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static Paciente[] getAllPacientes() throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT * FROM AMSS_BDD.Paciente;");
                //if there is no data on the data set, the session return will be false
                ArrayList<Paciente> array = new ArrayList<>();
                while (resultset.next()) {
                    String primerNombre = rsadec.decrypt(getPrivateKeyFilePath(), resultset.getString("primerNombre"));
                    String segundoNombre = rsadec.decrypt(getPrivateKeyFilePath(), resultset.getString("segundoNombre"));
                    Paciente pac = new Paciente(resultset.getInt("idPaciente"), resultset.getInt("genero"), resultset.getInt("estadoCivil"), resultset.getInt("cohabitacion"), primerNombre, segundoNombre, resultset.getString("usuario"), resultset.getString("email"), resultset.getString("nacionalidad"), resultset.getString("estadoNacimiento"), resultset.getString("tipoSangre"), resultset.getString("afiliacionMedica"), resultset.getString("amai"), resultset.getDate("fechaDeNacimiento"), resultset.getString("escolaridadMaxima"), resultset.getString("autopadecimiento"));
                    array.add(pac);
                }
                return array.toArray(new Paciente[array.size()]);
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static boolean deletePaciente(String usuarioID) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("DELETE FROM AMSS_BDD.Paciente WHERE usuario='" + usuarioID + "';");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean addPaciente(Paciente paciente) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
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
                    + "`escolaridadMaxima`,\n"
                    + "`autopadecimiento`,\n"
                    + "`cohabitacion`)\n"
                    + "VALUES(\n"
                    + "'" + rsaenc.encrypt(getPublicKeyFilePath(), paciente.getPrimerNombre()) + "',\n"
                    + "'" + rsaenc.encrypt(getPublicKeyFilePath(), paciente.getSegundoNombre()) + "',\n"
                    + "'" + paciente.getUsuario() + "',\n"
                    + "'" + paciente.getFechaDeNacimiento() + "',\n"
                    + "" + paciente.getGenero() + ",\n"
                    + "'" + paciente.getEmail() + "',\n"
                    + "'" + paciente.getNacionalidad() + "',\n"
                    + "'" + paciente.getEstadoNacimiento() + "',\n"
                    + "" + paciente.getEstadoCivil() + ",\n"
                    + "'" + paciente.getTipoSangre() + "',\n"
                    + "'" + paciente.getAfiliacionMedica() + "',\n"
                    + "'" + paciente.getAmai() + "',\n"
                    + "'" + paciente.getEscolaridadMaxima() + "',\n"
                    + "'" + paciente.getAutopadecimiento() + "',\n"
                    + "" + paciente.getCohabitacion() + "\n"
                    + ");";
            System.out.println(rment);
            int rowsaffected = statement.executeUpdate(rment);
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean updatePaciente(Paciente paciente) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("UPDATE `AMSS_BDD`.`Paciente`\n"
                    + "SET\n"
                    + "`primerNombre` = '" + rsaenc.encrypt(getPublicKeyFilePath(), paciente.getPrimerNombre()) + "',\n"
                    + "`segundoNombre` = '" + rsaenc.encrypt(getPublicKeyFilePath(), paciente.getSegundoNombre()) + "',\n"
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
                    + "`escolaridadMaxima` = '" + paciente.getEscolaridadMaxima() + "',\n"
                    + "`autopadecimiento` = '" + paciente.getAutopadecimiento() + "',\n"
                    + "`cohabitacion` = " + paciente.getCohabitacion() + "\n"
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
    public static formaGeriatria formaGeriatriaSearch(String idForma) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
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

    public static formaGeriatria[] getAllformaGeriatria(String idPaciente) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT * FROM AMSS_BDD.formatoGeriatria WHERE idPaciente = " + idPaciente + ";");
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

    public static boolean deleteFormaGeriatria(String formaID) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("DELETE FROM `AMSS_BDD`.`formatoGeriatria`\n"
                    + "WHERE idformatoGeriatria=" + formaID + ";");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean addFormaGeriatria(formaGeriatria forma) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
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

    public static boolean updateFormaGeriatria(formaGeriatria forma) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
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
    public static formaFragilidad[] getAllformaFragilidad(String idPaciente) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
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
                        + "FROM `AMSS_BDD`.`evaluacionFragilidad` WHERE idPaciente=" + idPaciente + ";");
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

    public static formaFragilidad formaFragilidadSearch(String idForma) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
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

    public static boolean deleteFormaFragilidad(String formaID) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("DELETE FROM `AMSS_BDD`.`evaluacionFragilidad`\n"
                    + "WHERE idevaluacionFragilidad=" + formaID + ";");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean addFormaFragilidad(formaFragilidad forma) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
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

    public static boolean updateFormaFragilidad(formaFragilidad forma) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
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

    public static formaGerontologia formaGerontologiaSearch(String idForm) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
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

    public static formaGerontologia[] getAllformaGerontologia(String idPaciente) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT * FROM AMSS_BDD.ValoracionGerontologica WHERE idPaciente=" + idPaciente + ";");
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

    public static boolean deleteFormaGerontologia(String idForm) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("DELETE FROM `AMSS_BDD`.`ValoracionGerontologica`\n"
                    + "WHERE idValoracionGerontologica=" + idForm + ";");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean addFormaGerontologia(formaGerontologia forma) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
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

    public static boolean updateFormaGerontologia(formaGerontologia forma) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
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
    public static boolean deleteDomicilio(String usuario) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("DELETE FROM `AMSS_BDD`.`Domicilio`\n"
                    + "WHERE usuario='" + usuario + "';");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean addDomicilio(Domicilio dom) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
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

    public static boolean updateDomicilio(Domicilio dom) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
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
                    + "WHERE `usuario` = '" + dom.getUsuario() + "';";
            System.out.println(qer);
            int rowsaffected = statement.executeUpdate(qer);
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static Domicilio searchDomicilio(String user) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
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

    public static Fitbit searchFitbit(String OAUTH_CLIENTID) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT * FROM AMSS_BDD.FitbitUserData WHERE OAUTH_CLIENTID='" + OAUTH_CLIENTID + "';");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    Fitbit fitbit = new Fitbit(resultset.getInt("idFitbitUserData"), resultset.getString("FITBIT_URL"), resultset.getString("FITBIT_API_URL"), resultset.getString("OAUTH_CLIENTID"), resultset.getString("CLIENT_SECRET"), resultset.getString("REDIRECT_URI"), resultset.getString("EXPIRATION_TIME"));
                    return fitbit;
                }
                //return session;
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static boolean deleteFitbit(String OAUTH_CLIENTID) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("DELETE FROM AMSS_BDD.FitbitUserData WHERE OAUTH_CLIENTID='" + OAUTH_CLIENTID + "';");
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static Fitbit getAllFitbit() throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                ResultSet resultset = statement.executeQuery("SELECT * FROM AMSS_BDD.FitbitUserData;");
                //if there is no data on the data set, the session return will be false
                while (resultset.next()) {
                    Fitbit fitbit = new Fitbit(resultset.getInt("idFitbitUserData"), resultset.getString("FITBIT_URL"), resultset.getString("FITBIT_API_URL"), resultset.getString("OAUTH_CLIENTID"), resultset.getString("CLIENT_SECRET"), resultset.getString("REDIRECT_URI"), resultset.getString("EXPIRATION_TIME"));
                    return fitbit;
                }
                //return session;
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static boolean addFitbit(Fitbit fitbit) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
            Statement statement = connection.createStatement();
            String rment = "INSERT INTO `AMSS_BDD`.`FitbitUserData`\n"
                    + "("
                    + "`FITBIT_URL`,\n"
                    + "`FITBIT_API_URL`,\n"
                    + "`OAUTH_CLIENTID`,\n"
                    + "`CLIENT_SECRET`,\n"
                    + "`REDIRECT_URI`,\n"
                    + "`EXPIRATION_TIME`)\n"
                    + "VALUES\n"
                    + "("
                    + "'" + fitbit.getFITBIT_URL() + "',\n"
                    + "'" + fitbit.getFITBIT_API_URL() + "',\n"
                    + "'" + fitbit.getOAUTH_CLIENTID() + "',\n"
                    + "'" + fitbit.getCLIENT_SECRET() + "',\n"
                    + "'" + fitbit.getREDIRECT_URI() + "',\n"
                    + "'" + fitbit.getEXPIRATION_TIME() + "');";
            System.out.println(rment);
            int rowsaffected = statement.executeUpdate(rment);
            return rowsaffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static boolean addValoracionFitbit(valoracionFitbit vf) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
            PreparedStatement pre = connection.prepareStatement("INSERT INTO `AMSS_BDD`.`valoracionFitbit`\n"
                    + "("
                    + "`usuario`,\n"
                    + "`datosMovilidad`,\n"
                    + "`fechaPedida`,\n"
                    + "`tiempoPedido`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?);");
            pre.setString(1, vf.getUsuario());
            byte[] byteContent = vf.getDatosMovilidad().getBytes();
            Blob blob = connection.createBlob();
            blob.setBytes(1, byteContent);
            pre.setBlob(2, blob);
            pre.setDate(3, convertFromJAVADateToSQLDate(vf.getFechaPedida()));
            pre.setString(4, vf.getTiempoPedido());
            pre.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
        return false;
    }

    public static java.sql.Date convertFromJAVADateToSQLDate(
            java.util.Date javaDate) {
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new Date(javaDate.getTime());
        }
        return sqlDate;
    }

    public static valoracionFitbit[] getAllValoracionFitbit(String usuario) throws NamingException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ArrayList<valoracionFitbit> array = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                PreparedStatement pre = connection.prepareStatement("SELECT * FROM AMSS_BDD.valoracionFitbit WHERE usuario=?;");
                pre.setString(1, usuario);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    Blob blob = rs.getBlob("datosMovilidad");
                    byte[] bdata = blob.getBytes(1, (int) blob.length());
                    String s = new String(bdata);
                    array.add(new valoracionFitbit(
                            rs.getInt("idvaloracionFitbit"),
                            rs.getString("usuario"),
                            s,
                            rs.getDate("fechaPedida"),
                            rs.getString("tiempoPedido")
                    ));
                }
                //return session;
            }
            return array.toArray(new valoracionFitbit[array.size()]);
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static valoracionFitbit searchValoracionFitbit(String valoracionID) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            valoracionFitbit vf = null;
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                PreparedStatement pre = connection.prepareStatement("SELECT * FROM AMSS_BDD.valoracionFitbit WHERE idvaloracionFitbit=?;");
                pre.setString(1, valoracionID);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    Blob blob = rs.getBlob("datosMovilidad");
                    byte[] bdata = blob.getBytes(1, (int) blob.length());
                    String s = new String(bdata);
                    vf = new valoracionFitbit(
                            rs.getInt("idvaloracionFitbit"),
                            rs.getString("usuario"),
                            s,
                            rs.getDate("fechaPedida"),
                            rs.getString("tiempoPedido")
                    );
                }
            }
            return vf;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static void deleteValoracionFitbit(String valoracionID) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM AMSS_BDD.valoracionFitbit WHERE idvaloracionFitbit=" + valoracionID + ";");
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
    }

    public static void addReporte(Reporte repo) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword());
            PreparedStatement pre = connection.prepareStatement("INSERT INTO `AMSS_BDD`.`reportePaciente`\n"
                    + "("
                    + "`usuario`,\n"
                    + "`idGeriatra`,\n"
                    + "`idNutricion`,\n"
                    + "`idMovilidad`,\n"
                    + "`idGerontologia`,\n"
                    + "`conclusion`,\n"
                    + "`fechaLlenado`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?,?,?);");
            pre.setString(1, repo.getUsuario());
            pre.setInt(2, repo.getIdGeriatra());
            pre.setInt(3, repo.getIdNutricion());
            pre.setInt(4, repo.getIdMovilidad());
            pre.setInt(5, repo.getIdGerontologia());
            pre.setString(6, repo.getConclusion());
            pre.setDate(7, convertFromJAVADateToSQLDate(repo.getFechaLlenado()));
            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }
    }

    public static Reporte[] getAllReportes(String usuario) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ArrayList<Reporte> array = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                PreparedStatement pre = connection.prepareStatement("SELECT * FROM AMSS_BDD.reportePaciente WHERE usuario=?;");
                pre.setString(1, usuario);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    array.add(new Reporte(
                            rs.getInt("idreportePaciente"),
                            rs.getString("usuario"),
                            rs.getInt("idGeriatra"),
                            rs.getInt("idNutricion"),
                            rs.getInt("idMovilidad"),
                            rs.getInt("idGerontologia"),
                            rs.getString("conclusion"),
                            rs.getDate("fechaLlenado")
                    ));
                }
                //return session;
            }
            return array.toArray(new Reporte[array.size()]);
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

    public static Reporte searchReporte(String idreportePaciente) throws NamingException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Reporte reporter = null;
            try (Connection connection = DriverManager.getConnection(getHost(), getHuser(), getHpassword()); Statement statement = connection.createStatement()) {
                PreparedStatement pre = connection.prepareStatement("SELECT * FROM AMSS_BDD.reportePaciente WHERE idreportePaciente=?;");
                pre.setInt(1, Integer.parseInt(idreportePaciente));
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    reporter = new Reporte(
                            rs.getInt("idreportePaciente"),
                            rs.getString("usuario"),
                            rs.getInt("idGeriatra"),
                            rs.getInt("idNutricion"),
                            rs.getInt("idMovilidad"),
                            rs.getInt("idGerontologia"),
                            rs.getString("conclusion"),
                            rs.getDate("fechaLlenado")
                    );
                }
            }
            return reporter;
        } catch (SQLException e) {
            System.out.println(e.getSQLState()); //Must be a JPopup or something
        }

        return null;
    }

}
