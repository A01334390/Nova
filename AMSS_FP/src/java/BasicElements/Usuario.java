/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicElements;

/**
 *
 * @author Luna
 */
import com.sun.xml.messaging.saaj.util.Base64;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Usuario {

    private String primerNombre, segundoNombre, usuario;
    private String email;
    private Date fechaNacimiento, fechaValidez;
    private int privilegio, id;
    private InputStream photo;
    private byte[] preparedPhoto;

    public Usuario(int id, String primerNombre, String segundoNombre, String email, String usuario, Date fechaNacimiento, Date fechaValidez, int privilegio, InputStream photo) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.email = email;
        this.usuario = usuario;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaValidez = fechaValidez;
        this.privilegio = privilegio;
        this.id = id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaNacimiento() {
        return (fechaNacimiento);
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaValidez() {
        return (fechaValidez);
    }

    public void setFechaValidez(Date fechaValidez) {
        this.fechaValidez = fechaValidez;
    }

    public int getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(int privilegio) {
        this.privilegio = privilegio;
    }

    public int getId() {
        return this.id;
    }

    public InputStream getPhoto() {
        return photo;
    }

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }
    
    public void setPreparedPhoto(byte[] byteArray){
        this.preparedPhoto = byteArray;
    }
    
    public String getPreparedPhoto() throws SQLException{
        byte[] imageByte = preparedPhoto;
        byte[] encodedImage = Base64.encode(imageByte);
        return Arrays.toString(encodedImage);
    }   

}
