package br.ithappens.repository.jpa;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ithappens.models.Usuario;
import br.ithappens.repository.UsuarioRepository;

@Named
public class JpaUsuarioRepositoryImpl implements UsuarioRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(Usuario g) {
		if (g.getId() == null) {
			em.persist(g);
		} else {
			em.merge(g);
		}
	}

	public List<Usuario> findAll() {
		return em.createQuery("from Usuario", Usuario.class).getResultList();
	}

	@Override
	public Usuario findById(Integer id) {
		return em.find(Usuario.class, id);
	}

	@Override
	public void remove(Usuario usuario) {
		em.remove(em.merge(usuario));
	}

	@Override
	public boolean findByVipCode(String codigoVip) {
		Query query = em.createQuery("select count(id) as qtd from Usuario p where p.codigoVip = :codigoVip ", Long.class)
				.setParameter("codigoVip", codigoVip);
		Long qtd = (Long) query.getSingleResult();
		return qtd > 0 ? true : false; 
	}

}
