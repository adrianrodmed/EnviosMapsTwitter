package com.basico.viajes.entity;
 
import java.io.Serializable;
import java.util.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@Entity
@Table(name = "Destinos")
public class Destino implements Serializable {
 
    private static final long serialVersionUID = -1000119078147252957L;
 
    private String iddestino;
    private Date fecha;
    private byte[] imagen;
    private String nombre;
    private double precio;
    private String descripcion;
    private String descripcioncorta;
    private int numplazas;
 
    public Destino() {
    }
 
    @Id
    @Column(name = "IdDestino", length = 20, nullable = false)
    public String getIddestino() {
		return iddestino;
		}
	public void setIddestino(String iddestino) {
		this.iddestino = iddestino;
		}
 
    @Column(name = "Nombre", length = 255, nullable = false)
    public String getNombre() {
		return nombre;
		}
	public void setNombre(String nombre) {
		this.nombre = nombre;
		}
 
    @Column(name = "Precio", nullable = false)
    public double getPrecio() {
		return precio;
		}
	public void setPrecio(double precio) {
		this.precio = precio;
		}
 
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Fecha", nullable = false)
    public Date getFecha() {
		return fecha;
		}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
		}
 
    @Lob
    @Column(name = "Imagen", length = Integer.MAX_VALUE, nullable = true)
    public byte[] getImagen() {
		return imagen;
		}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
		}
    
    @Column(name = "Descripcion", length = 1500, nullable = false)
    public String getDescripcion() {
		return descripcion;
		}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		}
    
    @Column(name = "DescripcionCorta", length = 255, nullable = false)
    public String getDescripcioncorta() {
		return descripcioncorta;
		}
	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
		}
    
    @Column(name = "NumPlazas", nullable = false)
    public int getNumplazas() {
		return numplazas;
		}
	public void setNumplazas(int numplazas) {
		this.numplazas = numplazas;
		}
	}