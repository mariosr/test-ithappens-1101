package br.ithappens.services;

import java.util.List;

import br.ithappens.models.Cliente;
import br.ithappens.models.Filial;
import br.ithappens.models.PedidoEstoque;
import br.ithappens.models.Usuario;

public interface PedidoService {

	public abstract void save(PedidoEstoque p);

	public abstract List<PedidoEstoque> findAll();

	public PedidoEstoque findById(Long id);

	public void remove(PedidoEstoque p);

	public PedidoEstoque solicitarPedido(List<Long> produtoSolicitados, String observacao, Filial filial, Cliente cliente, Usuario usuario, List<Long> qtdProdutos);

}