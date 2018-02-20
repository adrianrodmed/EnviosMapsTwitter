package com.ecodeup.articulo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "Acceso con Sesiones", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
        }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		 //me llega la url "proyecto/login/out"
//		System.out.println("Peticion Get");
//        String action=(request.getPathInfo()!=null?request.getPathInfo():"/out");
//        HttpSession sesion = request.getSession();
//        if(action.equals("/out")){
//            sesion.invalidate();
//            response.sendRedirect("login.jsp");
//            }
//        else{}
        }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		HttpSession sesion = request.getSession();
        String usu;
		String pass;
        usu = request.getParameter("user");
        pass = request.getParameter("password");
        //deberíamos buscar el usuario en la base de datos, pero dado que se escapa de este tema, ponemos un ejemplo en el mismo código
        if(usu.equals("admin") && pass.equals("admin") && sesion.getAttribute("usuario") == null){
            //si coincide usuario y password y además no hay sesión iniciada
        	System.out.println("Acierto de login");
            sesion.setAttribute("usuario", usu);
            //redirijo a página con información de login exitoso
            response.sendRedirect("adminArticulo?action=formulario");
            }
        else{
        	System.out.println("Error de login");
        	response.sendRedirect("adminArticulo?action=nuevo");
        	}
        }
	}