/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicElements;

import java.util.Date;

/**
 *
 * @author Luna
 */
public class Reporte {
    private int idreportePaciente;
    private String usuario;
    private int idGeriatra,idNutricion,idMovilidad,idGerontologia;
    private String conclusion;
    private Date fechaLlenado;

    public Reporte(int idreportePaciente, String usuario, int idGeriatra, int idNutricion, int idMovilidad, int idGerontologia, String conclusion, Date fechaLlenado) {
        this.idreportePaciente = idreportePaciente;
        this.usuario = usuario;
        this.idGeriatra = idGeriatra;
        this.idNutricion = idNutricion;
        this.idMovilidad = idMovilidad;
        this.idGerontologia = idGerontologia;
        this.conclusion = conclusion;
        this.fechaLlenado = fechaLlenado;
    }
    
    

    public int getIdreportePaciente() {
        return idreportePaciente;
    }

    public void setIdreportePaciente(int idreportePaciente) {
        this.idreportePaciente = idreportePaciente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdGeriatra() {
        return idGeriatra;
    }

    public void setIdGeriatra(int idGeriatra) {
        this.idGeriatra = idGeriatra;
    }

    public int getIdNutricion() {
        return idNutricion;
    }

    public void setIdNutricion(int idNutricion) {
        this.idNutricion = idNutricion;
    }

    public int getIdMovilidad() {
        return idMovilidad;
    }

    public void setIdMovilidad(int idMovilidad) {
        this.idMovilidad = idMovilidad;
    }

    public int getIdGerontologia() {
        return idGerontologia;
    }

    public void setIdGerontologia(int idGerontologia) {
        this.idGerontologia = idGerontologia;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public Date getFechaLlenado() {
        return fechaLlenado;
    }

    public void setFechaLlenado(Date fechaLlenado) {
        this.fechaLlenado = fechaLlenado;
    }
    
    
}
