package br.ithappens.services;

import java.util.List;

import br.ithappens.models.ItensPedido;

public interface ItensPedidoService {

	public abstract void save(ItensPedido p);

	public abstract List<ItensPedido> findAll();

	public ItensPedido findById(Long id);

	public void remove(ItensPedido p);


}