package com.unicamp.mc322.cinema.model;

import java.util.Date;

public class Sessao {

	private int numeroSala;
	private Filme filme;
	private int qtdIngressosDisponiveis;
	private Date horario;
	
	public Sessao(int numeroSala, Filme filme, Date horario) {
		super();
		this.numeroSala = numeroSala;
		this.filme = filme;
		this.qtdIngressosDisponiveis = 0;
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

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public int getQtdIngressosDisponiveis() {
		return qtdIngressosDisponiveis;
	}

	public void setQtdIngressosDisponiveis(int qtdIngressosDisponiveis) {
		this.qtdIngressosDisponiveis = qtdIngressosDisponiveis;
	}
	
	public void addIngressoVendido(Ingresso ingresso) {
		this.qtdIngressosDisponiveis--;
	}
	
	public boolean equals(Sessao sessao) {
		if(sessao == null)
			return false;
		if(!(sessao instanceof Sessao))
			return false;
		if(sessao.getNumeroSala()!= this.getNumeroSala())
			return false;
		if(sessao.getHorario().compareTo(sessao.getHorario()) != 0)
			return false;
		
		return true;
	}
	
}
