package com.basico.viajes.model;
 
import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.basico.viajes.entity.Destino;
 
public class DestinoInfo {
    private String iddestino;
    private String nombre;
    private String descripcioncorta;
    private Date fecha;
    private int numplazas;
    private double precio;
 
    private boolean newDestino=false;
 
    // Upload file.
    private CommonsMultipartFile fileData;
 
    public DestinoInfo() {
    }
 
    public DestinoInfo(Destino destino) {
        this.iddestino = destino.getIddestino();
        this.nombre = destino.getNombre();
        this.descripcioncorta = destino.getDescripcioncorta();
        this.fecha = destino.getFecha();
        this.numplazas = destino.getNumplazas();
        this.precio = destino.getPrecio();
        }
 
    public DestinoInfo(String iddestino, String nombre, String descripcioncorta, Date fecha, int numplazas,
			double precio) {
		this.iddestino = iddestino;
		this.nombre = nombre;
		this.descripcioncorta = descripcioncorta;
		this.fecha = fecha;
		this.numplazas = numplazas;
		this.precio = precio;
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

	public String getDescripcioncorta() {
		return descripcioncorta;
		}
	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
		}

	public Date getFecha() {
		return fecha;
		}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
		}
	
	public int getNumplazas() {
		return numplazas;
		}
	public void setNumplazas(int numplazas) {
		this.numplazas = numplazas;
		}

	public double getPrecio() {
		return precio;
		}
	public void setPrecio(double precio) {
		this.precio = precio;
		}

	public boolean isNewDestino() {
		return newDestino;
		}
	public void setNewDestino(boolean newDestino) {
		this.newDestino = newDestino;
		}

	public CommonsMultipartFile getFileData() {
		return fileData;
		}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
		} 
	}