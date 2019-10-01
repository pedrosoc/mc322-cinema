package com.unicamp.mc322.cinema.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sessao {

	private int numeroSala;
	private Filme filme;
	private List<Ingresso> ingressosVendidos;
	private int qtdIngressosVendidos;
	private Date horario;
	
	public Sessao(int numeroSala, Filme filme, Date horario) {
		super();
		this.numeroSala = numeroSala;
		this.filme = filme;
		this.ingressosVendidos = new ArrayList<>();
		this.qtdIngressosVendidos = 0;
		this.horario = horario;
	}

	public int getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public List<Ingresso> getIngressosVendidos() {
		return ingressosVendidos;
	}

	public void setIngressosVendidos(List<Ingresso> ingressosVendidos) {
		this.ingressosVendidos = ingressosVendidos;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public int getQtdIngressosVendidos() {
		return qtdIngressosVendidos;
	}

	public void setQtdIngressosVendidos(int qtdIngressosVendidos) {
		this.qtdIngressosVendidos = qtdIngressosVendidos;
	}
	
}
