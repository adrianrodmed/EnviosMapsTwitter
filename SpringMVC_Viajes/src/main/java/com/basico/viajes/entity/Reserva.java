package com.basico.viajes.entity;
 
import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 
@Entity
@Table(name = "Reservas")
public class Reserva implements Serializable {
 
    private static final long serialVersionUID = 7550745928843183535L;
 
    private String idreserva;
    private double preciototal;
    private double precio;
    private int cantidad;
    private int numplazas;
    private Compra compra;
    private Destino destino;
    
    @Id
    @Column(name = "IdReserva", length = 50, nullable = false)
    public String getIdreserva() {
		return idreserva;
		}
	public void setIdreserva(String idreserva) {
		this.idreserva = idreserva;
		}
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCompra", nullable = false, //
    foreignKey = @ForeignKey(name = "Destino_Compra_FK") )
    public Compra getCompra() {
		return compra;
		}
	public void setCompra(Compra compra) {
		this.compra = compra;
		}
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdDestino", nullable = false, //
    foreignKey = @ForeignKey(name = "Reserva_Destino_FK") )
    public Destino getDestino() {
		return destino;
		}
	public void setDestino(Destino destino) {
		this.destino = destino;
		}
 
    @Column(name = "Cantidad", nullable = false)
    public int getCantidad() {
		return cantidad;
		}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
		}
 
    @Column(name = "Precio", nullable = false)
    public double getPrecio() {
		return precio;
		}
	public void setPrecio(double precio) {
		this.precio = precio;
		}
 
    @Column(name = "PrecioTotal", nullable = false)
    public double getPreciototal() {
		return preciototal;
		}
	public void setPreciototal(double preciototal) {
		this.preciototal = preciototal;
		}
    
    @Column(name = "NumPlazas", nullable = false)
    public int getNumplazas() {
		return numplazas;
		}
	public void setNumplazas(int numplazas) {
		this.numplazas = numplazas;
		}
	}