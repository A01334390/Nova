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
public class formaGeriatria {

    private int idformatoGeriatra;

    private String katz, katz_interpretacion, barthel, barthel_interpretacion, lawtonBrody, lawtonBrody_interpretacion, estadoMental, estadoMental_interpretacion, escalaDepresion, escalaDepresion_interpretacion, cribadoNutricional, cribadoNutricional_interpretacion, pruebaDesempenio, pruebaDesempenio_interpretacion, levantateAnda, levantateAnda_interpretacion;
    private Date fechaLlenado;
    private int idUsuario, idPaciente;

    public formaGeriatria(int formatoGeriatra, String katz, String katz_interpretacion, String barthel, String barthel_interpretacion, String lawtonBrody, String lawtonBrody_interpretacion, String estadoMental, String estadoMental_interpretacion, String escalaDepresion, String escalaDepresion_interpretacion, String cribadoNutricional, String cribadoNutricional_interpretacion, String pruebaDesempenio, String pruebaDesempenio_interpretacion, String levantateAnda, String levantateAnda_interpretacion, Date fechaLlenado, int idUsuario, int idPaciente) {
        this.katz = katz;
        this.katz_interpretacion = katz_interpretacion;
        this.barthel = barthel;
        this.barthel_interpretacion = barthel_interpretacion;
        this.lawtonBrody = lawtonBrody;
        this.lawtonBrody_interpretacion = lawtonBrody_interpretacion;
        this.estadoMental = estadoMental;
        this.estadoMental_interpretacion = estadoMental_interpretacion;
        this.escalaDepresion = escalaDepresion;
        this.escalaDepresion_interpretacion = escalaDepresion_interpretacion;
        this.cribadoNutricional = cribadoNutricional;
        this.cribadoNutricional_interpretacion = cribadoNutricional_interpretacion;
        this.pruebaDesempenio = pruebaDesempenio;
        this.pruebaDesempenio_interpretacion = pruebaDesempenio_interpretacion;
        this.levantateAnda = levantateAnda;
        this.levantateAnda_interpretacion = levantateAnda_interpretacion;
        this.fechaLlenado = fechaLlenado;
        this.idUsuario = idUsuario;
        this.idPaciente = idPaciente;
        this.idformatoGeriatra = formatoGeriatra;
    }

    public int getIdformatoGeriatra() {
        return idformatoGeriatra;
    }

    public String getKatz() {
        return katz;
    }

    public void setKatz(String katz) {
        this.katz = katz;
    }

    public String getKatz_interpretacion() {
        return katz_interpretacion;
    }

    public void setKatz_interpretacion(String katz_interpretacion) {
        this.katz_interpretacion = katz_interpretacion;
    }

    public String getBarthel() {
        return barthel;
    }

    public void setBarthel(String barthel) {
        this.barthel = barthel;
    }

    public String getBarthel_interpretacion() {
        return barthel_interpretacion;
    }

    public void setBarthel_interpretacion(String barthel_interpretacion) {
        this.barthel_interpretacion = barthel_interpretacion;
    }

    public String getLawtonBrody() {
        return lawtonBrody;
    }

    public void setLawtonBrody(String lawtonBrody) {
        this.lawtonBrody = lawtonBrody;
    }

    public String getLawtonBrody_interpretacion() {
        return lawtonBrody_interpretacion;
    }

    public void setLawtonBrody_interpretacion(String lawtonBrody_interpretacion) {
        this.lawtonBrody_interpretacion = lawtonBrody_interpretacion;
    }

    public String getEstadoMental() {
        return estadoMental;
    }

    public void setEstadoMental(String estadoMental) {
        this.estadoMental = estadoMental;
    }

    public String getEstadoMental_interpretacion() {
        return estadoMental_interpretacion;
    }

    public void setEstadoMental_interpretacion(String estadoMental_interpretacion) {
        this.estadoMental_interpretacion = estadoMental_interpretacion;
    }

    public String getEscalaDepresion() {
        return escalaDepresion;
    }

    public void setEscalaDepresion(String escalaDepresion) {
        this.escalaDepresion = escalaDepresion;
    }

    public String getEscalaDepresion_interpretacion() {
        return escalaDepresion_interpretacion;
    }

    public void setEscalaDepresion_interpretacion(String escalaDepresion_interpretacion) {
        this.escalaDepresion_interpretacion = escalaDepresion_interpretacion;
    }

    public String getCribadoNutricional() {
        return cribadoNutricional;
    }

    public void setCribadoNutricional(String cribadoNutricional) {
        this.cribadoNutricional = cribadoNutricional;
    }

    public String getCribadoNutricional_interpretacion() {
        return cribadoNutricional_interpretacion;
    }

    public void setCribadoNutricional_interpretacion(String cribadoNutricional_interpretacion) {
        this.cribadoNutricional_interpretacion = cribadoNutricional_interpretacion;
    }

    public String getPruebaDesempenio() {
        return pruebaDesempenio;
    }

    public void setPruebaDesempenio(String pruebaDesempenio) {
        this.pruebaDesempenio = pruebaDesempenio;
    }

    public String getPruebaDesempenio_interpretacion() {
        return pruebaDesempenio_interpretacion;
    }

    public void setPruebaDesempenio_interpretacion(String pruebaDesempenio_interpretacion) {
        this.pruebaDesempenio_interpretacion = pruebaDesempenio_interpretacion;
    }

    public String getLevantateAnda() {
        return levantateAnda;
    }

    public void setLevantateAnda(String levantateAnda) {
        this.levantateAnda = levantateAnda;
    }

    public String getLevantateAnda_interpretacion() {
        return levantateAnda_interpretacion;
    }

    public void setLevantateAnda_interpretacion(String levantateAnda_interpretacion) {
        this.levantateAnda_interpretacion = levantateAnda_interpretacion;
    }

    public String getFechaLlenado() {
        return dateToString(fechaLlenado);
    }

    public void setFechaLlenado(Date fechaLlenado) {
        this.fechaLlenado = fechaLlenado;
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
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        return dateFormat.format(date);
    }
}
