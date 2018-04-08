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
public class Domicilio {

    private String pais, estado, ciudad, colonia, calle, codigoPostal, usuario;
    private int numeroInterno, numeroExterno;

    public Domicilio(String pais, String estado, String ciudad, String colonia, String calle, String codigoPostal, String usuario, int numeroInterno, int numeroExterno) {
        this.pais = pais;
        this.estado = estado;
        this.ciudad = ciudad;
        this.colonia = colonia;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.usuario = usuario;
        this.numeroInterno = numeroInterno;
        this.numeroExterno = numeroExterno;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getNumeroInterno() {
        return numeroInterno;
    }

    public void setNumeroInterno(int numeroInterno) {
        this.numeroInterno = numeroInterno;
    }

    public int getNumeroExterno() {
        return numeroExterno;
    }

    public void setNumeroExterno(int numeroExterno) {
        this.numeroExterno = numeroExterno;
    }

}
