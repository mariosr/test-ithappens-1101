package br.ithappens.repository;

import java.util.List;

import br.ithappens.models.ItensPedido;

public interface ItensPedidoRepository {

	public abstract void save(ItensPedido p);

	public abstract List<ItensPedido> findAll();

	public ItensPedido findById(Long id);

	public void remove(ItensPedido p);

}