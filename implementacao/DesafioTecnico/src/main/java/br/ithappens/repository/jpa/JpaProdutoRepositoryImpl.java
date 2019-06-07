package br.ithappens.repository.jpa;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ithappens.models.Produto;
import br.ithappens.repository.ProdutoRepository;

@Named
public class JpaProdutoRepositoryImpl implements ProdutoRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(Produto c) {
		if (c.getId() == null) {
			em.persist(c);
		} else {
			em.merge(c);
		}
	}

	public List<Produto> findAll() {
		return em.createQuery("from Produto", Produto.class).getResultList();
	}

	@Override
	public Produto findById(Long id) {
		return em.find(Produto.class, id);
	}

	@Override
	public void remove(Produto produto) {
		em.remove(em.merge(produto));
	}

}
