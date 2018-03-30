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
public class formaFragilidad {
    int idevaluacionFragilidad,pobreResistencia,actividadFisica;

    public formaFragilidad(int idevaluacionFragilidad, int pobreResistencia, int actividadFisica, String perdidaPeso, String perdidaPeso_interpretacion, String pobreResistencia_interpretacion, String velocidadMarcha, String velocidadMarcha_interpretacion, String fuerzaPresion, String fuerzaPresion_interpretacion, String actividadFisica_interpretacion, String diagnostico, String evaluacionFuncional, String evaluacionCognitiva, String evaluacionNutricional, String evaluacionDeFragilidad, Date fechaLlenado, int idUsuario, int idPaciente) {
        this.idevaluacionFragilidad = idevaluacionFragilidad;
        this.pobreResistencia = pobreResistencia;
        this.actividadFisica = actividadFisica;
        this.perdidaPeso = perdidaPeso;
        this.perdidaPeso_interpretacion = perdidaPeso_interpretacion;
        this.pobreResistencia_interpretacion = pobreResistencia_interpretacion;
        this.velocidadMarcha = velocidadMarcha;
        this.velocidadMarcha_interpretacion = velocidadMarcha_interpretacion;
        this.fuerzaPresion = fuerzaPresion;
        this.fuerzaPresion_interpretacion = fuerzaPresion_interpretacion;
        this.actividadFisica_interpretacion = actividadFisica_interpretacion;
        this.diagnostico = diagnostico;
        this.evaluacionFuncional = evaluacionFuncional;
        this.evaluacionCognitiva = evaluacionCognitiva;
        this.evaluacionNutricional = evaluacionNutricional;
        this.evaluacionDeFragilidad = evaluacionDeFragilidad;
        this.fechaLlenado = fechaLlenado;
        this.idUsuario = idUsuario;
        this.idPaciente = idPaciente;
    }

    public int getIdevaluacionFragilidad() {
        return idevaluacionFragilidad;
    }

    public void setIdevaluacionFragilidad(int idevaluacionFragilidad) {
        this.idevaluacionFragilidad = idevaluacionFragilidad;
    }

    public int getPobreResistencia() {
        return pobreResistencia;
    }

    public void setPobreResistencia(int pobreResistencia) {
        this.pobreResistencia = pobreResistencia;
    }

    public int getActividadFisica() {
        return actividadFisica;
    }

    public void setActividadFisica(int actividadFisica) {
        this.actividadFisica = actividadFisica;
    }

    public String getPerdidaPeso() {
        return perdidaPeso;
    }

    public void setPerdidaPeso(String perdidaPeso) {
        this.perdidaPeso = perdidaPeso;
    }

    public String getPerdidaPeso_interpretacion() {
        return perdidaPeso_interpretacion;
    }

    public void setPerdidaPeso_interpretacion(String perdidaPeso_interpretacion) {
        this.perdidaPeso_interpretacion = perdidaPeso_interpretacion;
    }

    public String getPobreResistencia_interpretacion() {
        return pobreResistencia_interpretacion;
    }

    public void setPobreResistencia_interpretacion(String pobreResistencia_interpretacion) {
        this.pobreResistencia_interpretacion = pobreResistencia_interpretacion;
    }

    public String getVelocidadMarcha() {
        return velocidadMarcha;
    }

    public void setVelocidadMarcha(String velocidadMarcha) {
        this.velocidadMarcha = velocidadMarcha;
    }

    public String getVelocidadMarcha_interpretacion() {
        return velocidadMarcha_interpretacion;
    }

    public void setVelocidadMarcha_interpretacion(String velocidadMarcha_interpretacion) {
        this.velocidadMarcha_interpretacion = velocidadMarcha_interpretacion;
    }

    public String getFuerzaPresion() {
        return fuerzaPresion;
    }

    public void setFuerzaPresion(String fuerzaPresion) {
        this.fuerzaPresion = fuerzaPresion;
    }

    public String getFuerzaPresion_interpretacion() {
        return fuerzaPresion_interpretacion;
    }

    public void setFuerzaPresion_interpretacion(String fuerzaPresion_interpretacion) {
        this.fuerzaPresion_interpretacion = fuerzaPresion_interpretacion;
    }

    public String getActividadFisica_interpretacion() {
        return actividadFisica_interpretacion;
    }

    public void setActividadFisica_interpretacion(String actividadFisica_interpretacion) {
        this.actividadFisica_interpretacion = actividadFisica_interpretacion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getEvaluacionCognitiva() {
        return evaluacionCognitiva;
    }

    public void setEvaluacionCognitiva(String evaluacionCognitiva) {
        this.evaluacionCognitiva = evaluacionCognitiva;
    }

    public String getEvaluacionNutricional() {
        return evaluacionNutricional;
    }

    public void setEvaluacionNutricional(String evaluacionNutricional) {
        this.evaluacionNutricional = evaluacionNutricional;
    }

    public String getEvaluacionDeFragilidad() {
        return evaluacionDeFragilidad;
    }

    public void setEvaluacionDeFragilidad(String evaluacionDeFragilidad) {
        this.evaluacionDeFragilidad = evaluacionDeFragilidad;
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

    public String getEvaluacionFuncional() {
        return evaluacionFuncional;
    }

    public void setEvaluacionFuncional(String evaluacionFuncional) {
        this.evaluacionFuncional = evaluacionFuncional;
    }
    
    private static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);
    }
    String perdidaPeso,perdidaPeso_interpretacion,pobreResistencia_interpretacion,velocidadMarcha,velocidadMarcha_interpretacion,fuerzaPresion,fuerzaPresion_interpretacion,actividadFisica_interpretacion,diagnostico,evaluacionCognitiva,evaluacionNutricional,evaluacionDeFragilidad,evaluacionFuncional;
    Date fechaLlenado;
    int idUsuario,idPaciente;
}
