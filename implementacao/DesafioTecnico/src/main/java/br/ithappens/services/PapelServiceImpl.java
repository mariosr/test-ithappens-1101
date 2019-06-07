package br.ithappens.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ithappens.models.Papel;
import br.ithappens.repository.PapelRepository;

@Named
public class PapelServiceImpl implements PapelService {

	@Inject
	private PapelRepository papelRepository;

	public PapelServiceImpl() {
	}

	@Override
	@Transactional
	public void save(Papel c) {
		papelRepository.save(c);
	}

	@Override
	@Transactional
	public List<Papel> findAll() {
		List<Papel> l = papelRepository.findAll();
		return l;
	}

	@Override
	@Transactional
	public void remove(Papel c) {
		papelRepository.remove(c);
	}

	@Override
	public Papel findByName(String name) {
		return papelRepository.findByName(name);
	}

}
