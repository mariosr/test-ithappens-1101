package br.ithappens.repository.jpa;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ithappens.auxiliary.AuxiliaryMethods;
import br.ithappens.models.Usuario;
import br.ithappens.repository.AuthenticationRepository;

@Named
public class JpaAuthenticationRepositoryImpl implements AuthenticationRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Usuario login(String login, String senha) {

		AuxiliaryMethods am = new AuxiliaryMethods();

		try {
			Query query = em.createQuery("select p from Usuario p where p.login = '" + login + "' AND p.password = '"
					+ am.convertStringToSHA512(senha) + "'", Usuario.class);
			return (Usuario) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public boolean isComandanteGeral(Integer idUser) {
		Query query = em
				.createQuery("select count(cg.id) from ComandoGeral cg where cg.comandante.id  = :id ", Long.class)
				.setParameter("id", idUser);
		Long qtd = (Long) query.getSingleResult();
		return qtd > 0 ? true : false;
	}

	@Override
	public boolean isGrandeComando(Integer idUser) {
		Query query = em
				.createQuery("select count(gc.id) from GrandeComando gc where gc.comandante.id  = :id ", Long.class)
				.setParameter("id", idUser);
		Long qtd = (Long) query.getSingleResult();
		return qtd > 0 ? true : false;
	}

	@Override
	public Usuario getComandanteByIdOPM(Integer idOpm, Integer currentRole) {
		try {

			Query query = null;

			switch (currentRole) {
			case 2:
				query = em.createQuery("select cg.comandante from ComandoGeral cg where cg.id = :id", Usuario.class)
						.setParameter("id", idOpm);
				break;
			case 3:
				query = em.createQuery("select gc.comandante from GrandeComando gc where gc.id = :id", Usuario.class)
						.setParameter("id", idOpm);
				break;
			case 4:
				query = em.createQuery("select b.comandante from Batalhao b where b.id = :id", Usuario.class)
						.setParameter("id", idOpm);
				break;
			case 5:
				query = em.createQuery("select c.comandante from Companhia c where c.id = :id", Usuario.class)
						.setParameter("id", idOpm);
				break;
			case 6:
				query = em.createQuery("select m.pessoa from Municipio m where m.id = :id", Usuario.class)
						.setParameter("id", idOpm);
				break;
			default:
				break;
			}

			return (Usuario) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

}
