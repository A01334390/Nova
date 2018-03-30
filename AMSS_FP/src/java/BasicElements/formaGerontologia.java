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
public class formaGerontologia {
    int idvaloracionGerontologica;
    String dispositivosUso,dispositivoMayorUso,frecuenciaUso,actividadesUso,usosFavorecer,apoyosocialPercibido,actividadesComunitarias,impresionDiagnostica;
    int idUsuario,idPaciente;
    Date fechaLlenado;

    public formaGerontologia(int idvaloracionGerontologica, String dispositivosUso, String dispositivoMayorUso, String frecuenciaUso, String actividadesUso, String usosFavorecer, String apoyosocialPercibido, String actividadesComunitarias, String impresionDiagnostica,Date fechaLlenado, int idUsuario, int idPaciente) {
        this.idvaloracionGerontologica = idvaloracionGerontologica;
        this.dispositivosUso = dispositivosUso;
        this.dispositivoMayorUso = dispositivoMayorUso;
        this.frecuenciaUso = frecuenciaUso;
        this.actividadesUso = actividadesUso;
        this.usosFavorecer = usosFavorecer;
        this.apoyosocialPercibido = apoyosocialPercibido;
        this.actividadesComunitarias = actividadesComunitarias;
        this.impresionDiagnostica = impresionDiagnostica;
        this.fechaLlenado = fechaLlenado;
        this.idUsuario = idUsuario;
        this.idPaciente = idPaciente;
    }

    public String getFechaLlenado() {
        return dateToString(fechaLlenado);
    }

    public void setFechaLlenado(Date fechaLlenado) {
        this.fechaLlenado = fechaLlenado;
    }
    

    public int getIdvaloracionGerontologica() {
        return idvaloracionGerontologica;
    }

    public void setIdvaloracionGerontologica(int idvaloracionGerontologica) {
        this.idvaloracionGerontologica = idvaloracionGerontologica;
    }

    public String getDispositivosUso() {
        return dispositivosUso;
    }

    public void setDispositivosUso(String dispositivosUso) {
        this.dispositivosUso = dispositivosUso;
    }

    public String getDispositivoMayorUso() {
        return dispositivoMayorUso;
    }

    public void setDispositivoMayorUso(String dispositivoMayorUso) {
        this.dispositivoMayorUso = dispositivoMayorUso;
    }

    public String getFrecuenciaUso() {
        return frecuenciaUso;
    }

    public void setFrecuenciaUso(String frecuenciaUso) {
        this.frecuenciaUso = frecuenciaUso;
    }

    public String getActividadesUso() {
        return actividadesUso;
    }

    public void setActividadesUso(String actividadesUso) {
        this.actividadesUso = actividadesUso;
    }

    public String getUsosFavorecer() {
        return usosFavorecer;
    }

    public void setUsosFavorecer(String usosFavorecer) {
        this.usosFavorecer = usosFavorecer;
    }

    public String getApoyosocialPercibido() {
        return apoyosocialPercibido;
    }

    public void setApoyosocialPercibido(String apoyosocialPercibido) {
        this.apoyosocialPercibido = apoyosocialPercibido;
    }

    public String getActividadesComunitarias() {
        return actividadesComunitarias;
    }

    public void setActividadesComunitarias(String actividadesComunitarias) {
        this.actividadesComunitarias = actividadesComunitarias;
    }

    public String getImpresionDiagnostica() {
        return impresionDiagnostica;
    }

    public void setImpresionDiagnostica(String impresionDiagnostica) {
        this.impresionDiagnostica = impresionDiagnostica;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    
    private static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);
    }
    
}
