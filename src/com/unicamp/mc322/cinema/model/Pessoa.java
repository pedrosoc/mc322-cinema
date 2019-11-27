package com.unicamp.mc322.cinema.model;

public class Pessoa {

	private String nome;
	private String login;
	private String senha;
	private boolean logado;

	public Pessoa(String nome) {
		super();
		this.nome = nome;
		this.logado = false;
	}

	public Pessoa(String nome, String login, String senha) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.logado = false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}
}
