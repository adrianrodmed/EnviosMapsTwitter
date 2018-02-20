package com.ecodeup.articulo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecodeup.articulos.dao.ArticuloDAO;
import com.ecodeup.articulos.model.Articulo;

/**
 * Servlet implementation class AdminArticulo
 */
@WebServlet("/adminArticulo")
public class AdminArticulo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticuloDAO articuloDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {

			articuloDAO = new ArticuloDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminArticulo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "nuevo":
				nuevo(request, response);
				break;
			case "register":
				System.out.println("entro");
				try {
					registrar(request, response);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "buscar":
				buscar(request, response);
				break;
			case "mostrar":
				mostrar(request, response);
				break;
			case "mostrar2":
				mostrar2(request, response);
				break;
			case "formulario":
				formulario(request, response);
				break;
//			case "showedit":
//				showEditar(request, response);
//				break;	
//			case "editar":
//				editar(request, response);
//				break;
//			case "eliminar":
//				eliminar(request, response);
//				break;
			default:
				break;
			}			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
		
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		doGet(request, response);
	}
	
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//mostrar(request, response);
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, NumberFormatException, ParseException {
		
		String string = request.getParameter("fecha");
		DateFormat format = new SimpleDateFormat("yyyy-dd-MM");
		Date date = format.parse(string);
		
		Articulo articulo = new Articulo(0, request.getParameter("origen"), request.getParameter("destino"),  request.getParameter("paquete"), date, request.getParameter("remitente"), request.getParameter("transportista"), Double.parseDouble(request.getParameter("precio")));
		articuloDAO.insertar(articulo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/login.jsp");
		dispatcher.forward(request, response);
	}
	
	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/buscar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void formulario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/register.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/mostrar.jsp");
		List<Articulo> listaArticulos= articuloDAO.listarArticulos();
		request.setAttribute("lista", listaArticulos);
		dispatcher.forward(request, response);
	}
	
	private void mostrar2(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("vista/mostrar.jsp");
		List<Articulo> listaArticulos= null;
		String origen = request.getParameter("origen");
		String destino = request.getParameter("destino");
		try {
			listaArticulos = articuloDAO.listarArticulos2(origen, destino);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		request.setAttribute("lista", listaArticulos);
		dispatcher.forward(request, response);
	}	
	
//	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//		Articulo articulo = articuloDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
//		request.setAttribute("articulo", articulo);
//		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/editar.jsp");
//		dispatcher.forward(request, response);
//	}
//	
//	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
//		Articulo articulo = new Articulo(Integer.parseInt(request.getParameter("id")), request.getParameter("origen"), request.getParameter("destino"),  request.getParameter("paquete"), request.getParameter("fecha"), request.getParameter("remitente"), request.getParameter("transportista"), Double.parseDouble(request.getParameter("precio")));
//		articuloDAO.actualizar(articulo);
//		index(request, response);
//	}
//	
//	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
//		Articulo articulo = articuloDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
//		articuloDAO.eliminar(articulo);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//		dispatcher.forward(request, response);}
}