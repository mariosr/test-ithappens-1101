package br.ithappens.repository.jpa;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ithappens.models.PedidoEstoque;
import br.ithappens.repository.PedidoRepository;

@Named
public class JpaPedidoRepositoryImpl implements PedidoRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(PedidoEstoque p) {
		if (p.getId() == null) {
			em.persist(p);
		} else {
			em.merge(p);
		}
	}

	public List<PedidoEstoque> findAll() {
		return em.createQuery("from PedidoEstoque", PedidoEstoque.class).getResultList();
	}

	@Override
	public PedidoEstoque findById(Long id) {
		return em.find(PedidoEstoque.class, id);
	}

	@Override
	public void remove(PedidoEstoque pedido) {
		em.remove(em.merge(pedido));
	}

}
