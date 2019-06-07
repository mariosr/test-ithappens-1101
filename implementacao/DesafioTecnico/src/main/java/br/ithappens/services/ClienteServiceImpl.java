package br.ithappens.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ithappens.models.Cliente;
import br.ithappens.repository.ClienteRepository;

@Named
public class ClienteServiceImpl implements ClienteService {

	@Inject
	private ClienteRepository clienteRepository;

	public ClienteServiceImpl() {
	}

	@Override
	@Transactional
	public void save(Cliente c) {
	     clienteRepository.save(c);
	}

	@Override
	@Transactional
	public List<Cliente> findAll() {
		List<Cliente> l = clienteRepository.findAll();
		return l;
	}

	@Override
	@Transactional
	public Cliente findById(Integer id) {
		Cliente c = clienteRepository.findById(id);
		return c;
	}

	@Override
	@Transactional
	public void remove(Cliente c) {
		clienteRepository.remove(c);
	}

	@Override
	public Cliente findByCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}

}
