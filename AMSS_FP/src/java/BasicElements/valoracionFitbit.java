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
public class valoracionFitbit {
    private int idValoracionFitbit;
    private String usuario;
    private String datosMovilidad;
    private Date fechaPedida;
    private String tiempoPedido;

    public valoracionFitbit(int idValoracionFitbit, String usuario, String datosMovilidad, Date fechaPedida, String tiempoPedido) {
        this.idValoracionFitbit = idValoracionFitbit;
        this.usuario = usuario;
        this.datosMovilidad = datosMovilidad;
        this.fechaPedida = fechaPedida;
        this.tiempoPedido = tiempoPedido;
    }

    public int getIdValoracionFitbit() {
        return idValoracionFitbit;
    }

    public void setIdValoracionFitbit(int idValoracionFitbit) {
        this.idValoracionFitbit = idValoracionFitbit;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDatosMovilidad() {
        return datosMovilidad;
    }

    public void setDatosMovilidad(String datosMovilidad) {
        this.datosMovilidad = datosMovilidad;
    }

    public Date getFechaPedida() {
        return fechaPedida;
    }

    public void setFechaPedida(Date fechaPedida) {
        this.fechaPedida = fechaPedida;
    }

    public String getTiempoPedido() {
        return tiempoPedido;
    }

    public void setTiempoPedido(String tiempoPedido) {
        this.tiempoPedido = tiempoPedido;
    }

    private static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
