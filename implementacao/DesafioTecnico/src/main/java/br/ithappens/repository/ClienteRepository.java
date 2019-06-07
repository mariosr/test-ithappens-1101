package br.ithappens.repository;

import java.util.List;

import br.ithappens.models.Cliente;

public interface ClienteRepository {

	public abstract void save(Cliente c);

	public abstract List<Cliente> findAll();

	public Cliente findById(Integer id);
	
	public Cliente findByCpf(String cpf);
	
	public void remove(Cliente c);
	
}