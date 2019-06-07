package br.ithappens.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ithappens.models.Produto;
import br.ithappens.repository.ProdutoRepository;

@Named
public class ProdutoServiceImpl implements ProdutoService {

	@Inject
	private ProdutoRepository produtoRepository;

	public ProdutoServiceImpl() {
	}

	@Override
	@Transactional
	public void save(Produto p) {
		produtoRepository.save(p);
	}

	@Override
	@Transactional
	public List<Produto> findAll() {
		List<Produto> l = produtoRepository.findAll();
		return l;
	}

	@Override
	@Transactional
	public Produto findById(Long id) {
		Produto p = produtoRepository.findById(id);
		return p;
	}

	@Override
	@Transactional
	public void remove(Produto produto) {
		produtoRepository.remove(produto);
	}

}
