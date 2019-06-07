package br.ithappens.repository.jpa;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ithappens.models.ItensPedido;
import br.ithappens.repository.ItensPedidoRepository;

@Named
public class JpaItensPedidoRepositoryImpl implements ItensPedidoRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(ItensPedido p) {
		if (p.getId() == null) {
			em.persist(p);
		} else {
			em.merge(p);
		}
	}

	public List<ItensPedido> findAll() {
		return em.createQuery("from ItensPedido", ItensPedido.class).getResultList();
	}

	@Override
	public ItensPedido findById(Long id) {
		return em.find(ItensPedido.class, id);
	}

	@Override
	public void remove(ItensPedido pedido) {
		em.remove(em.merge(pedido));
	}

}
