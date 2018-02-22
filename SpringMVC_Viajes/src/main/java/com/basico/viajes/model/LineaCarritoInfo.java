package com.basico.viajes.model;
 
public class LineaCarritoInfo {
 
    private DestinoInfo destinoInfo;
    private int cantidad;
 
    public LineaCarritoInfo() {
        this.cantidad = 0;
        }
 
    public DestinoInfo getDestinoInfo() {
		return destinoInfo;
		}
	public void setDestinoInfo(DestinoInfo destinoInfo) {
		this.destinoInfo = destinoInfo;
		}
 
    public int getCantidad() {
		return cantidad;
		}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
		}
 
    public double getPreciototal() {
        return this.destinoInfo.getPrecio() * this.cantidad;
        }
    }