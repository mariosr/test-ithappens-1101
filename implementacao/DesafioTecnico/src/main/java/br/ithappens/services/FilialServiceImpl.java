package br.ithappens.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ithappens.models.Filial;
import br.ithappens.repository.FilialRepository;

@Named
public class FilialServiceImpl implements FilialService {

	@Inject
	private FilialRepository filialRepository;

	public FilialServiceImpl() {
	}

	@Override
	@Transactional
	public void save(Filial c) {
		filialRepository.save(c);
	}

	@Override
	@Transactional
	public List<Filial> findAll() {
		List<Filial> l = filialRepository.findAll();
		return l;
	}

	@Override
	@Transactional
	public Filial findById(Integer id) {
		Filial c = filialRepository.findById(id);
		return c;
	}

	@Override
	@Transactional
	public void remove(Filial c) {
		filialRepository.remove(c);
	}

}
