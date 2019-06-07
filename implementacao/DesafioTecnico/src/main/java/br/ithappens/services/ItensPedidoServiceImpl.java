package br.ithappens.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ithappens.models.ItensPedido;
import br.ithappens.repository.ItensPedidoRepository;

@Named
public class ItensPedidoServiceImpl implements ItensPedidoService {

	@Inject
	private ItensPedidoRepository pedidoRepository;

	public ItensPedidoServiceImpl() {
	}

	@Override
	@Transactional
	public void save(ItensPedido p) {
		pedidoRepository.save(p);
	}

	@Override
	@Transactional
	public List<ItensPedido> findAll() {
		List<ItensPedido> l = pedidoRepository.findAll();
		return l;
	}

	@Override
	@Transactional
	public ItensPedido findById(Long id) {
		ItensPedido p = pedidoRepository.findById(id);
		return p;
	}

	@Override
	@Transactional
	public void remove(ItensPedido produto) {
		pedidoRepository.remove(produto);
	}
}
