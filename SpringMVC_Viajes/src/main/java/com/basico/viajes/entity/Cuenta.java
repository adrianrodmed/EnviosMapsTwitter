package com.basico.viajes.entity;
 
import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "Cuentas")
public class Cuenta implements Serializable {
 
    private static final long serialVersionUID = -2054386655979281969L;
      
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";
    
    private String nombre;
    private boolean active;
    private String pass;
    private String role;

    @Id
    @Column(name = "Nombre", length = 20, nullable = false)
    public String getNombre() {
		return nombre;
		}
	public void setNombre(String nombre) {
		this.nombre = nombre;
		}
 
    @Column(name = "Pass", length = 20, nullable = false)
    public String getPass() {
		return pass;
		}
	public void setPass(String pass) {
		this.pass = pass;
		}
 
    @Column(name = "Active", length = 1, nullable = false)
    public boolean isActive() {
        return active;
    }
 
    public void setActive(boolean active) {
        this.active = active;
    }
 
    @Column(name = "Role", length = 20, nullable = false)
    public String getRole() {
		return role;
		}
	public void setRole(String role) {
		this.role = role;
		}
    
    @Override
    public String toString()  {
        return "["+ this.nombre+","+ this.pass+","+ this.role+"]";
        }  
    }