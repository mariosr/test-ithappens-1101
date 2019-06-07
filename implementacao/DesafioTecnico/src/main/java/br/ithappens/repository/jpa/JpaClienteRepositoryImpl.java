package br.ithappens.repository.jpa;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ithappens.models.Cliente;
import br.ithappens.repository.ClienteRepository;

@Named
public class JpaClienteRepositoryImpl implements ClienteRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(Cliente c) {
		if (c.getId() == null) {
			em.persist(c);
		} else {
			em.merge(c);
		}
	}

	public List<Cliente> findAll() {
		return em.createQuery("from Cliente", Cliente.class).getResultList();
	}

	@Override
	public Cliente findById(Integer id) {
		return em.find(Cliente.class, id);
	}

	@Override
	public void remove(Cliente cliente) {
		em.remove(em.merge(cliente));
	}

	@Override
	public Cliente findByCpf(String cpf) {
		try {
			Query query = em.createQuery("select c from Cliente c where c.cpf =:cpf", Cliente.class).setParameter("cpf",
					cpf);
			return (Cliente) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

}
