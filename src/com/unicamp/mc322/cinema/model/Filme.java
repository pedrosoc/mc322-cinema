package com.unicamp.mc322.cinema.model;


import java.util.ArrayList;
import java.util.List;

public class Filme {
	private String nome;
	private int duracao;
	private String diretor;
	private List<String> atores;
	private String genero;
	private int classificacaoIndicativa;
	
	public Filme() {
		this.atores = new ArrayList<String>();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public List<String>  getAtores() {
		return atores;
	}

	public void addAtores(String atores) {
		this.atores.add(atores);
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getClassificacaoIndicativa() {
		return classificacaoIndicativa;
	}

	public void setClassificacaoIndicativa(int classificacaoIndicativa) {
		this.classificacaoIndicativa = classificacaoIndicativa;
	}

}
