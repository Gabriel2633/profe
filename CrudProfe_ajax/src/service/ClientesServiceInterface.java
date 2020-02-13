package service;

import java.util.ArrayList;

import model.Cliente;

public interface ClientesServiceInterface {
    public void add(Cliente cli);
    public void update(Cliente cli);
    public void delete(int id);
    public Cliente findById(int id);
    public ArrayList<Cliente> getAll();
}
