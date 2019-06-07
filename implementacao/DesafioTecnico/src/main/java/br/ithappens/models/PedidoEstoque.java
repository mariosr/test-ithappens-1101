package br.ithappens.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;

import br.ithappens.auxiliary.StatusPedido;

@Entity
public class PedidoEstoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Cliente cliente;
	@ManyToOne
	private Usuario usuario;
	private String observacao;
	@ManyToOne
	private Filial filial;
	@ManyToOne
	private FormaPagamento formaPagamento;

	private StatusPedido status;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE })
	@JoinTable(name = "PEDIDO_TEM_ITENS", joinColumns = {
			@JoinColumn(name = "PEDIDO_ID", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "PEDIDO_ITENS_ID", referencedColumnName = "id") })
	private List<ItensPedido> itensPedidos;

	public PedidoEstoque() {
		super();
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ItensPedido> getItensPedidos() {
		return itensPedidos;
	}

	public void setItensPedidos(List<ItensPedido> itensPedidos) {
		this.itensPedidos = itensPedidos;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

}
