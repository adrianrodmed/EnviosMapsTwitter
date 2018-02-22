package com.basico.viajes.util;
 
import javax.servlet.http.HttpServletRequest;

import com.basico.viajes.model.CarritoInfo;
 
public class Utiles {
 
    public static CarritoInfo getSesionCarrito(HttpServletRequest request) {
 
        CarritoInfo carritoInfo = (CarritoInfo) request.getSession().getAttribute("miCarrito");
        
        if (carritoInfo == null) {
            carritoInfo = new CarritoInfo();
            request.getSession().setAttribute("miCarrito", carritoInfo);
            }
        return carritoInfo;
        }
 
    public static void eliminaSesionCarrito(HttpServletRequest request) {
        request.getSession().removeAttribute("miCarrito");
        }
 
    public static void guardaUltimaCompraSesionCarrito(HttpServletRequest request, CarritoInfo carritoInfo) {
        request.getSession().setAttribute("ultimoCarritoPedido", carritoInfo);
        }
    
    public static CarritoInfo getUltimaCompraSesionCarrito(HttpServletRequest request) {
        return (CarritoInfo) request.getSession().getAttribute("ultimoCarritoPedido");
        }
    }