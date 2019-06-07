package br.ithappens.repository;

import java.util.List;

import br.ithappens.models.PedidoEstoque;

public interface PedidoRepository {

	public abstract void save(PedidoEstoque p);

	public abstract List<PedidoEstoque> findAll();

	public PedidoEstoque findById(Long id);
	
	public void remove(PedidoEstoque p);
	
}