package dao;

import java.util.ArrayList;

import model.Cliente;

public interface ClientesDAOInterface {
    public void add(Cliente cli);
    public void update(Cliente cli);
    public void delete(int id);
    public Cliente findById(int id);
    public ArrayList<Cliente> getAll();

}
