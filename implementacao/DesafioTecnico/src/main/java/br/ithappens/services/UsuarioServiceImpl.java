package br.ithappens.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ithappens.models.Usuario;
import br.ithappens.repository.UsuarioRepository;

@Named
public class UsuarioServiceImpl implements UsuarioService {

	@Inject
	private UsuarioRepository pr;

	public UsuarioServiceImpl() {
	}

	@Override
	@Transactional
	public void save(Usuario p) {
		pr.save(p);
	}

	@Override
	@Transactional
	public List<Usuario> findAll() {
		List<Usuario> l = pr.findAll();
		return l;
	}

	@Override
	@Transactional
	public Usuario findById(Integer id) {
		Usuario g = pr.findById(id);
		return g;
	}

	@Override
	@Transactional
	public void remove(Usuario g) {
		pr.remove(g);
	}

	@Override
	public boolean findByVipCode(String codigoVip) {
		return pr.findByVipCode(codigoVip);
	}

}
