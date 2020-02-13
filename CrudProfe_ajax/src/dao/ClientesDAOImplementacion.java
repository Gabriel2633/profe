package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.Cliente;

public class ClientesDAOImplementacion implements ClientesDAOInterface{

	ConexionDB con = new ConexionDB();
	
	
	@Override
	public void add(Cliente cli) {
		con.update("INSERT INTO clientes (nombre,apellidos,fnacimiento) values ("
				+ "'" + cli.getNombre() +"',"
				+ "'" + cli.getApellidos() +"',"
				+ "'" + cli.getFnacimientoToString() +"')"
				);
	}

	@Override
	public void update(Cliente cli) {
		con.update("UPDATE clientes SET "
				+ "nombre='" + cli.getNombre() + "',"
				+ "apellidos='" + cli.getApellidos() + "',"
				+ "fnacimiento='" + cli.getFnacimientoToString() + "' "
				+ "WHERE id=" + cli.getId()
				);
	
	}

	@Override
	public void delete(int id) {
		con.update("DELETE FROM clientes "
				+ "WHERE id=" + id);
	}

	@Override
	public Cliente findById(int id) {
		String query= "SELECT * FROM clientes WHERE id=" + id;
		try {
			ResultSet rs = con.select(query);
			if (rs.next()) return new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),rs.getDate("fnacimiento"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Cliente> getAll() {
		String query = "SELECT * FROM clientes";
		ResultSet rs;
		// Create an ArrayList to save resulting records
		ArrayList<Cliente> clis = new ArrayList<Cliente>();	
		try {
			rs = con.select(query);
			// Iterate through the results and create Employee objects
			while (rs.next()) {
				clis.add(new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getDate("fnacimiento")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(clis);
		return clis;
	
	}

}
