package br.ithappens.services;

import java.util.List;

import br.ithappens.models.Produto;

public interface ProdutoService {

	public abstract void save(Produto p);

	public abstract List<Produto> findAll();

	public Produto findById(Long id);
	
	public void remove(Produto Produto);

}