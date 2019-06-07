package br.ithappens.repository.jpa;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ithappens.models.Filial;
import br.ithappens.repository.FilialRepository;

@Named
public class JpaFilialRepositoryImpl implements FilialRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(Filial c) {
		if (c.getId() == null) {
			em.persist(c);
		} else {
			em.merge(c);
		}
	}

	public List<Filial> findAll() {
		return em.createQuery("from Filial", Filial.class).getResultList();
	}

	@Override
	public Filial findById(Integer id) {
		return em.find(Filial.class, id);
	}

	@Override
	public void remove(Filial f) {
		em.remove(em.merge(f));
	}


}
