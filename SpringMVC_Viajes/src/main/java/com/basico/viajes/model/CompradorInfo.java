package com.basico.viajes.model;
 
public class CompradorInfo {
 
    private String nombrecomprador;
    private String direccioncomprador;
    private String mailcomprador;
    private String telefonocomprador;
    
    private boolean valid;

	public String getNombrecomprador() {
		return nombrecomprador;
		}
	public void setNombrecomprador(String nombrecomprador) {
		this.nombrecomprador = nombrecomprador;
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

	public String getTelefonocomprador() {
		return telefonocomprador;
		}
	public void setTelefonocomprador(String telefonocomprador) {
		this.telefonocomprador = telefonocomprador;
		}

	public boolean isValid() {
		return valid;
		}
	public void setValid(boolean valid) {
		this.valid = valid;
		}
	}