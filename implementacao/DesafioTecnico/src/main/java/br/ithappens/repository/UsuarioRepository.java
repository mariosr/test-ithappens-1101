package br.ithappens.repository;

import java.util.List;

import br.ithappens.models.Usuario;

public interface UsuarioRepository {

	public abstract void save(Usuario u);

	public abstract List<Usuario> findAll();

	public Usuario findById(Integer id);
	
	public void remove(Usuario usuario);
	
	public boolean findByVipCode(String codigoVip);
	
}