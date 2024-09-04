package com.ink.studio.tattoo.inkstudiotattoo.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto incremento do SQL Server
	@Column(name = "id")
	private Long id;
	private String nome;
	private String nascimento;
	private String descricao;
	private String servico;
	private String cpf;
	private String email;
	private String telefone;
	private String senha;
	private String statusUsuario;
	private byte[] fotoPerfil;
	private byte[] fotoTattoo;
	
	
	public String getStatusUsuario() {
		return statusUsuario;
	}
	public void setStatusUsuario(String statusUsuario) {
		this.statusUsuario = statusUsuario;
	}
	public byte[] getFotoTattoo() {
		return fotoTattoo;
	}
	public void setFotoTattoo(byte[] fotoTattoo) {
		this.fotoTattoo = fotoTattoo;
	}
	
	public byte[] getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(byte[] fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
