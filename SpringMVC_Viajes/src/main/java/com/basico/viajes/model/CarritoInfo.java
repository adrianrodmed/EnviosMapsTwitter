package com.basico.viajes.model;
 
import java.util.ArrayList;
import java.util.List;
 
public class CarritoInfo {
 
    private int numcompra;
 
    private CompradorInfo compradorInfo;
 
    private final List<LineaCarritoInfo> lineaCarrito = new ArrayList<LineaCarritoInfo>();
 
    public CarritoInfo() {
 
    }
 
    public int getNumcompra() {
		return numcompra;
		}
	public void setNumcompra(int numcompra) {
		this.numcompra = numcompra;
		}

	public CompradorInfo getCompradorInfo() {
		return compradorInfo;
		}
	public void setCompradorInfo(CompradorInfo compradorInfo) {
		this.compradorInfo = compradorInfo;
		}

	public List<LineaCarritoInfo> getLineaCarrito() {
		return lineaCarrito;
		}
 
    private LineaCarritoInfo findLineById(String iddestino) {
        for (LineaCarritoInfo linea : this.lineaCarrito) {
            if (linea.getDestinoInfo().getIddestino().equals(iddestino)) {
                return linea;
                }
            }
        return null;
        }
 
    public void addDestino(DestinoInfo destinoInfo, int cantidad) {
        LineaCarritoInfo linea = this.findLineById(destinoInfo.getIddestino());
 
        if (linea == null) {
            linea = new LineaCarritoInfo();
            linea.setCantidad(0);
            linea.setDestinoInfo(destinoInfo);;
            this.lineaCarrito.add(linea);
            }
        int nuevaCantidad = linea.getCantidad() + cantidad;
        if (nuevaCantidad <= 0) {
            this.lineaCarrito.remove(linea);
            }
        else {
            linea.setCantidad(nuevaCantidad);
            }
        }
 
    public void validate() {
 
    }
 
    public void updateDestino(String iddestino, int cantidad) {
        LineaCarritoInfo linea = this.findLineById(iddestino);
 
        if (linea != null) {
            if (cantidad <= 0) {
                this.lineaCarrito.remove(linea);
                }
            else {
                linea.setCantidad(cantidad);
                }
            }
        }
 
    public void removeDestino(DestinoInfo destinoInfo) {
        LineaCarritoInfo linea = this.findLineById(destinoInfo.getIddestino());
        if (linea != null) {
            this.lineaCarrito.remove(linea);
            }
        }
 
    public boolean isEmpty() {
        return this.lineaCarrito.isEmpty();
        }
 
    public boolean isValidComprador() {
        return this.compradorInfo != null && this.compradorInfo.isValid();
        }
 
    public int getCantidadTotal() {
        int cantidad = 0;
        for (LineaCarritoInfo linea : this.lineaCarrito) {
            cantidad += linea.getCantidad();
            }
        return cantidad;
        }
 
    public double getPrecioTotal() {
        double total = 0;
        for (LineaCarritoInfo linea : this.lineaCarrito) {
            total += linea.getPreciototal();
            }
        return total;
        }
 
    public void updateCantidad(CarritoInfo carritoForm) {
        if (carritoForm != null) {
            List<LineaCarritoInfo> lineas = carritoForm.getLineaCarrito();
            for (LineaCarritoInfo linea : lineas) {
                this.updateDestino(linea.getDestinoInfo().getIddestino(), linea.getCantidad());
                }
            }
        }
    }