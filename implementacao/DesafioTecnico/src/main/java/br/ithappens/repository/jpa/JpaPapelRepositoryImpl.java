package br.ithappens.repository.jpa;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ithappens.models.Papel;
import br.ithappens.repository.PapelRepository;

@Named
public class JpaPapelRepositoryImpl implements
		PapelRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(Papel c) {
		if (c.getId() == null) {
			em.persist(c);
		} else {
			em.merge(c);
		}
	}

	public List<Papel> findAll() {
		return em.createQuery("from Papel", Papel.class)
				.getResultList();
	}

	@Override
	public void remove(Papel categoria) {
		em.remove(em.merge(categoria));
	}

	@Override
	public Papel findByName(String name) {
		return em.createQuery("from Papel c where c.nome = '"+name+"'", Papel.class).getSingleResult();
	}

}
