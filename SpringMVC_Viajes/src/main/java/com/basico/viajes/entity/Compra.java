package com.basico.viajes.entity;
 
import java.io.Serializable;
import java.util.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
 
@Entity
@Table(name = "Compras", //
uniqueConstraints = { @UniqueConstraint(columnNames = "numcompra") })
public class Compra implements Serializable {
 
    private static final long serialVersionUID = -2576670215015463100L;
 
    private String idcompra;
    private double preciototal;
    private String direccioncomprador;
    private String mailcomprador;
    private String nombrecomprador;
    private String telefonocomprador;
    private Date fechacompra;
    private int numcompra;
 
    @Id
    @Column(name = "IdCompra", length = 50)
    public String getIdcompra() {
		return idcompra;
		}
    public void setIdcompra(String idcompra) {
		this.idcompra = idcompra;
		}
  
    @Column(name = "FechaCompra", nullable = false)
    public Date getFechacompra() {
		return fechacompra;
		}
	public void setFechacompra(Date fechacompra) {
		this.fechacompra = fechacompra;
		}
 
    @Column(name = "NumCompra", nullable = false)
    public int getNumcompra() {
		return numcompra;
		}

	public void setNumcompra(int numcompra) {
		this.numcompra = numcompra;
		}
 
    @Column(name = "PrecioTotal", nullable = false)
    public double getPreciototal() {
		return preciototal;
		}
	public void setPreciototal(double preciototal) {
		this.preciototal = preciototal;
		}
    
    @Column(name = "NombreComprador", length = 255, nullable = false)
    public String getNombrecomprador() {
		return nombrecomprador;
		}
	public void setNombrecomprador(String nombrecomprador) {
		this.nombrecomprador = nombrecomprador;
		}
 
    @Column(name = "DireccionComprador", length = 255, nullable = false)
    public String getDireccioncomprador() {
		return direccioncomprador;
		}

	public void setDireccioncomprador(String direccioncomprador) {
		this.direccioncomprador = direccioncomprador;
		}
 
    @Column(name = "MailComprador", length = 255, nullable = false)
    public String getMailcomprador() {
		return mailcomprador;
		}
	public void setMailcomprador(String mailcomprador) {
		this.mailcomprador = mailcomprador;
		}
 
    @Column(name = "TelefonoComprador", length = 255, nullable = false)
	public String getTelefonocomprador() {
		return telefonocomprador;
		}
	public void setTelefonocomprador(String telefonocomprador) {
		this.telefonocomprador = telefonocomprador;
		}
	}