package com.uniovi.sdi;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletCarrito
 */
@WebServlet("/incluirEnCarrito")
public class ServletCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductosService ps;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCarrito() {
		super();
		ps= new ProductosService();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// LLenamos la lista
		HttpSession session=request.getSession();
		HashMap<String,Integer> productosTienda = llenarCarrito(ps.getProductos());
				
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Tienda SDI: Todos los productos</TITLE></HEAD>");
		out.println("<BODY>");
		out.println(carritoEnHTML(productosTienda)+"<br>");
		//out.println("<a href=\"tienda.html\">Volver</a></BODY></HTML>");
		out.println("<a href=\"index.jsp\">Volver</a></BODY></HTML>");
	}

	private String carritoEnHTML(HashMap<String, Integer> carrito) {
		// TODO Auto-generated method stub
		String carritoEnHTML="";
		for(String key:carrito.keySet()) {
			carritoEnHTML+="<p>["+key+"], "+carrito.get(key)+" unidades</p>";
		}
		return carritoEnHTML;
	}

	private void insertarEnCarrito(HashMap<String, Integer> carrito, String claveProducto) {
		// TODO Auto-generated method stub
		if(carrito.get(claveProducto)==null) {
			carrito.put(claveProducto, new Integer(1));
		}else {
			int numeroArticulos=(Integer)carrito.get(claveProducto).intValue();
			carrito.put(claveProducto, new Integer(numeroArticulos+1));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private HashMap<String,Integer> llenarCarrito(List<Producto>p){
		HashMap<String, Integer> lista = new HashMap<String, Integer>();
		int n=0;
		for(Producto x :p) {
			lista.put(x.getNombre(), n);
			n++;
		}
		return lista;
		
	}

}
