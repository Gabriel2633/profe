package service;

import java.util.ArrayList;

import dao.ClientesDAOImplementacion;
import model.Cliente;

public class ClientesServiceImplementacion implements ClientesServiceInterface{

	ClientesDAOImplementacion cdi=new ClientesDAOImplementacion();
	
	@Override
	public void add(Cliente cli) {
			cdi.add(cli);
	}

	@Override
	public void update(Cliente cli) {
		cdi.update(cli);
	}

	@Override
	public void delete(int id) {
		cdi.delete(id);
	}

	@Override
	public Cliente findById(int id) {
		return cdi.findById(id);
	}

	@Override
	public ArrayList<Cliente> getAll() {
		return cdi.getAll();
	}

}
