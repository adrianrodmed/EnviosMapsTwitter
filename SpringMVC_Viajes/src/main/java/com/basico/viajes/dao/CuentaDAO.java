package com.basico.viajes.dao;
 
import com.basico.viajes.entity.Cuenta;
 
public interface CuentaDAO {
 
    public Cuenta findCuenta(String nombre);
    }