package br.ithappens.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.ithappens.auxiliary.StatusItensPedido;

@Entity
public class ItensPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Produto produto;
	private StatusItensPedido status;
	private Double valorTotal;
	private Integer quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public StatusItensPedido getStatus() {
		return status;
	}

	public void setStatus(StatusItensPedido status) {
		this.status = status;
	}

	public void configureValorTotal() {
		valorTotal = produto.getValor() * quantidade;
	}
	
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
