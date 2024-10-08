package com.ink.studio.tattoo.inkstudiotattoo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="agenda")
public class Agenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto incremento do SQL Server
	@Column(name = "id")
	private Long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	private LocalTime horas;
	private String servico;
	private double valor;
	private String statusAgenda;
	@ManyToOne
	@JoinColumn(name = "Id_funcionario")
	private Funcionario funcionario;
	@ManyToOne
	@JoinColumn(name = "Id_usuario")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "Id_Orcamento")
	private Orcamentos orcamento;
	
	public String getStatusAgenda() {
		return statusAgenda;
	}
	public void setStatusAgenda(String statusAgenda) {
		this.statusAgenda = statusAgenda;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Orcamentos getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(Orcamentos orcamento) {
		this.orcamento = orcamento;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalTime getHoras() {
		return horas;
	}
	public void setHoras(LocalTime horas) {
		this.horas = horas;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
