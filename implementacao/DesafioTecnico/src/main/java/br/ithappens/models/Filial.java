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

import org.hibernate.annotations.Cascade;

@Entity
public class Filial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE })
	@JoinTable(name = "FILIAL_TEM_PRODUTOS", joinColumns = {
			@JoinColumn(name = "FILIAL_ID", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "FILIAL_PRODUTO_ID", referencedColumnName = "id") })
	private List<Produto> produtos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
