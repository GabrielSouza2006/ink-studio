package com.ink.studio.tattoo.inkstudiotattoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orcamento")
public class Orcamentos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double valor;
	private String servico;
	private String cor;
	private String horas;
	//@ManyToOne
    //@JoinColumn(name = "id")
    //private Funcionario funcionario;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
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
