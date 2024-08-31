package com.ink.studio.tattoo.inkstudiotattoo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="agenda")
public class Agenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto incremento do SQL Server
	@Column(name = "id")
	private Long id;
	private LocalDate data;
	private LocalTime horas;
	private String servico;
	private String profissional;
	private double valor;
	
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
	public String getProfissional() {
		return profissional;
	}
	public void setProfissional(String profissional) {
		this.profissional = profissional;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
