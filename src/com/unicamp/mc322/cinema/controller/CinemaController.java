package com.unicamp.mc322.cinema.controller;

import com.google.gson.Gson;
import com.sun.xml.internal.ws.handler.HandlerException;
import com.unicamp.mc322.cinema.model.Cinema;
import com.unicamp.mc322.cinema.model.Filme;
import com.unicamp.mc322.cinema.model.Ingresso;
import com.unicamp.mc322.cinema.model.IngressoInteira;
import com.unicamp.mc322.cinema.model.IngressoMeia;
import com.unicamp.mc322.cinema.model.Pessoa;
import com.unicamp.mc322.cinema.model.Sala;
import com.unicamp.mc322.cinema.model.Sessao;
import com.unicamp.mc322.cinema.util.MockUtils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.unicamp.mc322.cinema.util.TerminalUtil.getSimpleInt;
import static com.unicamp.mc322.cinema.util.TerminalUtil.getSimpleString;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


public class CinemaController {

    private static final String PATH_FILE = "../json/banco_cinema.txt";

    private Cinema cinema;

    public CinemaController() {
        this.cinema = this.getCinemaCadastrado();
    }

    public List<Ingresso> reservarIngresso(List<Ingresso> ingressosCarrinho) throws HandlerException {

        Filme filme;
        Sessao sessao;

        do {
            filme = this.selecionarFilme();
            sessao = this.selecionarSessao(filme, ingressosCarrinho);
        } while (isNull(sessao));

        Sessao finalSessao = sessao;
        List<Ingresso> ingressosSessao = ingressosCarrinho
                .stream()
                .filter(i -> finalSessao.equals(i.getSessao()))
                .collect(Collectors.toList());

        int qtdIngressos = this.selecionarQuantidadeIngressos(sessao, ingressosSessao.size());

        return this.separarIngressos(sessao, qtdIngressos);
    }

    private List<Ingresso> separarIngressos(Sessao sessao, int qtdIngressos) throws HandlerException {
        List<Ingresso> ingressos = new ArrayList<>();

        for (int i = 0; i < qtdIngressos; i++) {
            System.out.println();
            System.out.println(String.format("Compra no ingresso nº %d", i + 1));
            System.out.println();

            String nome = getSimpleString("o nome");
            Pessoa pessoa = new Pessoa(nome);

            System.out.println();
            System.out.println("Digite:");
            System.out.println("1 - Meia Entrada");
            System.out.println("2 - Inteira");
            System.out.println();

            int op = getSimpleInt("a opção desejada");

            if (op != 1 && op != 2)
                throw new HandlerException(new Throwable("Opção inválida!"));

            if (op == 1)
                ingressos.add(new IngressoMeia(15l, pessoa, sessao));
            else
                ingressos.add(new IngressoInteira(30l, pessoa, sessao));

        }

        return ingressos;
    }

    private Sala selecionarSalaPorSessao(Sessao sessao) {
        List<Sala> salas = this.cinema.getSalas();

        Optional<Sala> optional = salas
                .stream()
                .filter(s -> s.getNumeroSala() == sessao.getNumeroSala())
                .findFirst();

        if (optional.isPresent())
            return optional.get();

        return null;
    }

    private int selecionarQuantidadeIngressos(Sessao sessao, int carrinho) throws HandlerException {
        System.out.println();

        int op = getSimpleInt("quantos ingressos deseja adquirir");

        if (op <= 0) {
            throw new HandlerException(new Throwable("Você não pode comprar menos de 1 ingresso"));
        }

        int disponiveis = sessao.getQtdIngressosDisponiveis() - carrinho;
        if (op > disponiveis) {
            throw new HandlerException(new Throwable(String.format("Sala possue apenas %d ingressos disponíveis.", disponiveis)));
        }

        return op;
    }

    private Filme selecionarFilme() throws HandlerException {
        System.out.println();
        System.out.println("Selecione o filme");
        System.out.println();
        System.out.println("Digite:");

        List<Filme> filmes = this.cinema.getFilmes();
        for (int i = 0; i < filmes.size(); i++) {
            Filme filme = filmes.get(i);
            System.out.println(String.format("%d - %s", i, filme.getNome()));
        }

        System.out.println();

        int op = getSimpleInt("a opção desejada");

        if (op < 0 || op >= filmes.size()) {
            throw new HandlerException(new Throwable("Opção inválida!"));
        }

        return filmes.get(op);
    }

    private Sessao selecionarSessao(Filme filme, List<Ingresso> ingressosCarrinho) throws HandlerException {
        List<Sala> salas = cinema.getSalas();

        List<Sessao> sessoes = salas
                .stream()
                .flatMap(s -> s.getSessoes().stream())
                .filter(s -> filme.getNome().equals(s.getFilme().getNome()))
                .sorted(Comparator.comparing(Sessao::getHorario))
                .collect(Collectors.toList());

        if (sessoes.size() <= 0) {
            throw new HandlerException(new Throwable("Ainda não temos nenhuma sessão para esse filme, por favor, selecione outro"));
        }

        System.out.println();
        System.out.println("Selecione a sessão");
        System.out.println();
        System.out.println("Digite:");

        for (int i = 0; i < sessoes.size(); i++) {
            Sessao sessao = sessoes.get(i);

            List<Ingresso> ingressosSessao = ingressosCarrinho
                    .stream()
                    .filter(a -> sessao.equals(a.getSessao()))
                    .collect(Collectors.toList());

            SimpleDateFormat diaFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            String dia = diaFormatter.format(sessao.getHorario());

            int disponiveis = sessao.getQtdIngressosDisponiveis() - ingressosSessao.size();
            System.out.println(String.format("%d - Dia: %s - Ingressos disponíveis: %d", i, dia, disponiveis));
        }

        System.out.println();

        int op = getSimpleInt("a opção desejada");

        if (op < 0 || op >= sessoes.size()) {
            throw new HandlerException(new Throwable("Opção inválida!"));
        }

        Sessao sessao = sessoes.get(op);

        List<Ingresso> ingressosSessao = ingressosCarrinho
                .stream()
                .filter(a -> sessao.equals(a.getSessao()))
                .collect(Collectors.toList());

        if (sessao.getQtdIngressosDisponiveis() - ingressosSessao.size() <= 0) {
            throw new HandlerException(new Throwable("Essa sessão não possue mais ingressos disponíveis, por favor, selecione outra!"));
        }

        return sessao;
    }

    public boolean finalizarCompra(List<Ingresso> ingressos) {
        List<Sala> salas = cinema.getSalas();


        for (Ingresso ingresso: ingressos) {
            Optional<Sessao> first = salas
                    .stream()
                    .flatMap(s -> s.getSessoes().stream())
                    .filter(s -> s.equals(ingresso.getSessao()))
                    .findFirst();

            if (first.isPresent()) {
                Sessao sessao = first.get();
                sessao.addIngressoVendido(ingresso);
            }
        }

        return true;
    }

    private Cinema getCinemaCadastrado() {
        InputStream is = getClass().getResourceAsStream(PATH_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

        Cinema cinema = new Gson().fromJson(br, Cinema.class);

        return nonNull(cinema) ? cinema : MockUtils.cinema();
    }

    public void atualizarCinema() {
        Gson gson = new Gson();
        String json = gson.toJson(this.cinema);

        URL path = getClass().getResource(PATH_FILE);
        try (FileWriter writer = new FileWriter(path.getPath())) {
            writer.write(json);
        } catch (IOException e) {
            System.err.println("IOException");
        }
    }

}
