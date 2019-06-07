package br.ithappens.services;

import java.util.List;

import br.ithappens.models.Usuario;

public interface UsuarioService {

	public abstract void save(Usuario p);

	public abstract List<Usuario> findAll();

	public Usuario findById(Integer id);
	
	public void remove(Usuario Pessoa);
	
	public boolean findByVipCode(String codigoVip);

}