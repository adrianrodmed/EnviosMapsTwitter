package com.filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="/Filtro", urlPatterns="/vista/register.jsp")
public class Filtro implements Filter {
	
    public Filtro() {
        // TODO Auto-generated constructor stub
    	System.out.println("Ha entrao en el Filtro");
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		
		if(session == null || session.getAttribute("usuario") == null){
			System.out.println("No puedes pasar");
			res.sendRedirect("vista/login.jsp");
			}
		else{
			chain.doFilter(request, response);
			}
		}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Inicio Filtro");
	}

}
