package br.ithappens.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ithappens.auxiliary.StatusItensPedido;
import br.ithappens.auxiliary.StatusPedido;
import br.ithappens.models.Cliente;
import br.ithappens.models.Filial;
import br.ithappens.models.ItensPedido;
import br.ithappens.models.PedidoEstoque;
import br.ithappens.models.Produto;
import br.ithappens.models.Usuario;
import br.ithappens.repository.ItensPedidoRepository;
import br.ithappens.repository.PedidoRepository;
import br.ithappens.repository.ProdutoRepository;

@Named
public class PedidoServiceImpl implements PedidoService {

	@Inject
	private PedidoRepository pedidoRepository;
	@Inject
	private ProdutoRepository produtoRepository;
	
	@Inject
	private ItensPedidoRepository itensPedidoRrepository;

	public PedidoServiceImpl() {
	}

	@Override
	@Transactional
	public void save(PedidoEstoque p) {
		pedidoRepository.save(p);
	}

	@Override
	@Transactional
	public List<PedidoEstoque> findAll() {
		List<PedidoEstoque> l = pedidoRepository.findAll();
		return l;
	}

	@Override
	@Transactional
	public PedidoEstoque findById(Long id) {
		PedidoEstoque p = pedidoRepository.findById(id);
		return p;
	}

	@Override
	@Transactional
	public void remove(PedidoEstoque produto) {
		pedidoRepository.remove(produto);
	}

	@Override
	@Transactional
	public PedidoEstoque solicitarPedido(List<Long> produtoSolicitados, String observacao, Filial filial,
			Cliente cliente, Usuario usuario, List<Long> qtdProdutos) {

		PedidoEstoque pedido = new PedidoEstoque();
		List<ItensPedido> ipList = new ArrayList<ItensPedido>();
		List<Produto> produtosFilial = filial.getProdutos();
		Produto p = null;

		for (int i = 0; i < produtoSolicitados.size(); i++) {
			Long id = produtoSolicitados.get(i);
			p = produtoRepository.findById(id);

			for (Produto produto : produtosFilial) {
				if (produto.getId() == id) {
					if (qtdProdutos.get(i) > 0 && produto.getQuantidadeEstoque() >= qtdProdutos.get(i)) {
						
						ItensPedido ip = new ItensPedido();
						ip.setStatus(StatusItensPedido.ATIVO);
						ip.setProduto(p);
						ip.setQuantidade(qtdProdutos.get(i).intValue());
						ip.configureValorTotal();
						itensPedidoRrepository.save(ip);
						
						ipList.add(ip);
						
						// atualizando valor do estoque
						int qtdEstoqueAtualizada = (p.getQuantidadeEstoque().intValue() - qtdProdutos.get(i).intValue());
						p.setQuantidadeEstoque(qtdEstoqueAtualizada);
						produtoRepository.save(p);
					} else {
						// sem produto no estoque
						throw new IllegalArgumentException();
					}
				}
			}
		}
		
		pedido.setItensPedidos(ipList);
		pedido.setStatus(StatusPedido.ATIVO);
		pedido.setObservacao(observacao);
		pedido.setFilial(filial);
		pedido.setCliente(cliente);
		pedido.setUsuario(usuario);

		pedidoRepository.save(pedido);

		return pedido;

	}
}
