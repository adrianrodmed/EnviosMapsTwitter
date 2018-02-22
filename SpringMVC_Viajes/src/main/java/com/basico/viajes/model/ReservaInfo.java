package com.basico.viajes.model;
 
public class ReservaInfo {
    private String idreserva;
    private String iddestino;/*Id del destino*/
    private String nombre;/*Nombre del destino*/
    private int cantidad;
    private double precio;
    private double preciototal;
    private int numplazas;
 
    public ReservaInfo() {
    	
    }

    public ReservaInfo(String idreserva, String iddestino, String nombre, int cantidad, double precio,
			double preciototal, int numplazas) {
		this.idreserva = idreserva;
		this.iddestino = iddestino;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.preciototal = preciototal;
		this.numplazas = numplazas;
		}

	public String getIdreserva() {
		return idreserva;
		}
	public void setIdreserva(String idreserva) {
		this.idreserva = idreserva;
		}

	public String getIddestino() {
		return iddestino;
		}
	public void setIddestino(String iddestino) {
		this.iddestino = iddestino;
		}

	public String getNombre() {
		return nombre;
		}
	public void setNombre(String nombre) {
		this.nombre = nombre;
		}

	public int getCantidad() {
		return cantidad;
		}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
		}

	public double getPrecio() {
		return precio;
		}
	public void setPrecio(double precio) {
		this.precio = precio;
		}

	public double getPreciototal() {
		return preciototal;
		}
	public void setPreciototal(double preciototal) {
		this.preciototal = preciototal;
		}

	public int getNumplazas() {
		return numplazas;
		}
	public void setNumplazas(int numplazas) {
		this.numplazas = numplazas;
		}
	}