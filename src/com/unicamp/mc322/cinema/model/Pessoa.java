package com.unicamp.mc322.cinema.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pessoa {

	private String nome;
	private String login;
	private String senha;
	private boolean logado;
	private List<String> historicoCompras;

	public Pessoa(String nome) {
		super();
		this.nome = nome;
		this.logado = false;
		this.historicoCompras = new ArrayList<>();
	}

	public Pessoa(String nome, String login, String senha) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.logado = false;
		this.historicoCompras = new ArrayList<>();
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

	public List<String> getHistoricoCompras() {
		return historicoCompras;
	}

	public void setHistoricoCompras(List<String> historicoCompras) {
		this.historicoCompras = historicoCompras;
	}

	public void adicionarCompras(List<Ingresso> ingressos) {
		for (Ingresso i: ingressos) {
			String s = "%s - Comprou um ingresso %s no valor de R$ %.2f para o filme %s, sessão das %s, para %s";
			SimpleDateFormat diaFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String diaSessao = diaFormatter.format(i.getSessao().getHorario());
			String now = diaFormatter.format(new Date());

			String historico = String.format(s, now, i.getTipo(), i.getPreco(), i.getSessao().getFilme().getNome(), diaSessao, i.getComprador().nome);
			this.historicoCompras.add(historico);
		}
	}
}
