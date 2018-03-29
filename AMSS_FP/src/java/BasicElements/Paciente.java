/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicElements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Luna
 */
public class Paciente {
    
    private int pacienteID, genero, estadoCivil, cohabitacion;
    private String primerNombre, segundoNombre, email, nacionalidad, estadoNacimiento, tipoSangre, afiliacionMedica, amai,usuario;
    private Date fechaDeNacimiento;

    public Paciente(int pacienteID, int genero, int estadoCivil, int cohabitacion, String primerNombre, String segundoNombre, String usuario, String email, String nacionalidad, String estadoNacimiento, String tipoSangre, String afiliacionMedica, String amai, Date fechaDeNacimiento) {
        this.pacienteID = pacienteID;
        this.genero = genero;
        this.estadoCivil = estadoCivil;
        this.cohabitacion = cohabitacion;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.usuario = usuario;
        this.email = email;
        this.nacionalidad = nacionalidad;
        this.estadoNacimiento = estadoNacimiento;
        this.tipoSangre = tipoSangre;
        this.afiliacionMedica = afiliacionMedica;
        this.amai = amai;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPacienteID() {
        return pacienteID;
    }

    public void setPacienteID(int pacienteID) {
        this.pacienteID = pacienteID;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public int getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(int estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public int getCohabitacion() {
        return cohabitacion;
    }

    public void setCohabitacion(int cohabitacion) {
        this.cohabitacion = cohabitacion;
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEstadoNacimiento() {
        return estadoNacimiento;
    }

    public void setEstadoNacimiento(String estadoNacimiento) {
        this.estadoNacimiento = estadoNacimiento;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getAfiliacionMedica() {
        return afiliacionMedica;
    }

    public void setAfiliacionMedica(String afiliacionMedica) {
        this.afiliacionMedica = afiliacionMedica;
    }

    public String getAmai() {
        return amai;
    }

    public void setAmai(String amai) {
        this.amai = amai;
    }

    public String getFechaDeNacimiento() {
        return dateToString(fechaDeNacimiento);
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    
    private static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);
    }

}
