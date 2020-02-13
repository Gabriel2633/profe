package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import service.ClientesServiceImplementacion;

/**
 * Servlet implementation class Cliente
 */
@WebServlet("/clientes")
public class Clientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientesServiceImplementacion csi = new ClientesServiceImplementacion();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Clientes() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		if (operacion == null)
			operacion = "";

		if (operacion.equals("enviar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			if (id == 0) { // quiere uno nuevo
				Cliente cli = new Cliente(request.getParameter("nombre"), request.getParameter("apellidos"),
						request.getParameter("fnacimiento"));
				csi.add(cli);
			} else {// quiere actualizar uno existente
				Cliente cli=csi.findById(id);
				cli.setNombre(request.getParameter("nombre"));
				cli.setApellidos(request.getParameter("apellidos"));
				cli.setFnacimiento(request.getParameter("fnacimiento"));
				csi.update(cli);
			}
		} else if (operacion.equals("eliminar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			csi.delete(id);
		} else {
			// INYECTAMOS LISTA DE CLIENTES ACTUALIZADA:
			request.setAttribute("clientes", csi.getAll());
			RequestDispatcher view = request.getRequestDispatcher("/clientes.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
