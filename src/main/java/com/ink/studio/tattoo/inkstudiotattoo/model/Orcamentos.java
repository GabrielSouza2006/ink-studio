package com.ink.studio.tattoo.inkstudiotattoo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="orcamento")
public class Orcamentos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String cor;
	private String horas;
	private double valor;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	private String statusOrcamento;
	@ManyToOne
	@JoinColumn(name = "Id_funcionario")
	private Funcionario funcionario;
	@ManyToOne
	@JoinColumn(name = "Id_usuario")
	private Usuario usuario;
	
	
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getStatusOrcamento() {
		return statusOrcamento;
	}
	public void setStatusOrcamento(String statusOrcamento) {
		this.statusOrcamento = statusOrcamento;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getHoras() {
		return horas;
	}
	public void setHoras(String horas) {
		this.horas = horas;
	}
	
	
}
