package br.ithappens.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.ithappens.auxiliary.PessoaSexo;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "id", "login" }))
public class Usuario {

	public Usuario() {
		super();
	}

	public Usuario(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String login;

	@Column(nullable = false)
	private String password;
	private boolean habilitado = true;
	private String nome;
	private String cpf;
	private Integer idade;
	private String email;
	private String codigoVip;
	private PessoaSexo sexo;

	@ManyToMany
	@JoinTable(name = "papel_usuario", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "papel_id"))
	private List<Papel> papeis;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCodigoVip() {
		return codigoVip;
	}

	public void setCodigoVip(String codigoVip) {
		this.codigoVip = codigoVip;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public PessoaSexo getSexo() {
		return sexo;
	}

	public void setSexo(PessoaSexo sexo) {
		this.sexo = sexo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + "]";
	}

}
