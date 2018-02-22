package com.basico.viajes.model;
 
import java.util.Date;
import java.util.List;
 
public class CompraInfo {
 
    private String idcompra;
    private Date fechacompra;
    private int numcompra;
    private double preciototal;
    private String direccioncomprador;
    private String mailcomprador;
    private String nombrecomprador;
    private String telefonocomprador;
    

    private List<ReservaInfo> reservas;
 
    public CompraInfo() {
 
    }
 
    public CompraInfo(String idcompra, Date fechacompra, int numcompra, double preciototal, String direccioncomprador, String mailcomprador,
			String nombrecomprador, String telefonocomprador) {
		this.idcompra = idcompra;
		this.preciototal = preciototal;
		this.direccioncomprador = direccioncomprador;
		this.mailcomprador = mailcomprador;
		this.nombrecomprador = nombrecomprador;
		this.telefonocomprador = telefonocomprador;
		this.fechacompra = fechacompra;
		this.numcompra = numcompra;
		}

	public String getIdcompra() {
		return idcompra;
		}
	public void setIdcompra(String idcompra) {
		this.idcompra = idcompra;
		}

	public double getPreciototal() {
		return preciototal;
		}
	public void setPreciototal(double preciototal) {
		this.preciototal = preciototal;
		}
	
	public String getDireccioncomprador() {
		return direccioncomprador;
		}
	public void setDireccioncomprador(String direccioncomprador) {
		this.direccioncomprador = direccioncomprador;
		}

	public String getMailcomprador() {
		return mailcomprador;
		}
	public void setMailcomprador(String mailcomprador) {
		this.mailcomprador = mailcomprador;
		}

	public String getNombrecomprador() {
		return nombrecomprador;
		}
	public void setNombrecomprador(String nombrecomprador) {
		this.nombrecomprador = nombrecomprador;
		}

	public String getTelefonocomprador() {
		return telefonocomprador;
		}
	public void setTelefonocomprador(String telefonocomprador) {
		this.telefonocomprador = telefonocomprador;
		}

	public Date getFechacompra() {
		return fechacompra;
		}
	public void setFechacompra(Date fechacompra) {
		this.fechacompra = fechacompra;
		}

	public int getNumcompra() {
		return numcompra;
		}
	public void setNumcompra(int numcompra) {
		this.numcompra = numcompra;
		}

	public List<ReservaInfo> getReservas() {
		return reservas;
		}
	public void setReservas(List<ReservaInfo> reservas) {
		this.reservas = reservas;
		}
	}