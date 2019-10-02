package com.unicamp.mc322.cinema.controller;

import com.unicamp.mc322.cinema.model.Cinema;
import com.unicamp.mc322.cinema.model.Filme;
import com.unicamp.mc322.cinema.model.Ingresso;
import com.unicamp.mc322.cinema.model.IngressoInteira;
import com.unicamp.mc322.cinema.model.IngressoMeia;
import com.unicamp.mc322.cinema.model.Pessoa;
import com.unicamp.mc322.cinema.model.Sala;
import com.unicamp.mc322.cinema.model.Sessao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.unicamp.mc322.cinema.util.TerminalUtil.getSimpleInt;
import static com.unicamp.mc322.cinema.util.TerminalUtil.getSimpleString;

public class CinemaController {

    private Cinema cinema;

    public CinemaController(Cinema cinema) {
        this.cinema = cinema;
    }

    public List<Ingresso> reservarIngresso() {

        Filme filme = this.selecionarFilme();
        Sessao sessao = this.selecionarSessao(filme);

        int qtdIngressos = this.selecionarQuantidadeIngressos(sessao);

        return this.separarIngressos(sessao, qtdIngressos);
    }

    private List<Ingresso> separarIngressos(Sessao sessao, int qtdIngressos) {
        List<Ingresso> ingressos = new ArrayList<>();

        for (int i = 0; i < qtdIngressos; i++) {
            System.out.println();
            System.out.println(String.format("Compra no ingresso nº %d", i + 1));
            System.out.println();

            String nome = getSimpleString("o seu nome");
            Pessoa pessoa = new Pessoa(nome);

            while (true) {
                System.out.println();
                System.out.println("Digite:");
                System.out.println("1 - Meia Entrada");
                System.out.println("2 - Inteira");
                System.out.println();

                int op = getSimpleInt("a opção desejada");

                if (op != 1 && op != 2)
                    continue;

                if (op == 1)
                    ingressos.add(new IngressoMeia(15l, pessoa, sessao));
                else
                    ingressos.add(new IngressoInteira(30l, pessoa, sessao));

                break;
            }
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

    private int selecionarQuantidadeIngressos(Sessao sessao) {
        while (true) {
            System.out.println();

            int op = getSimpleInt("quantos ingressos deseja adquirir");

            int disponiveis = sessao.getQtdIngressosDisponiveis();
            if (op <= 0) {
                System.out.println();
                System.out.println("Você deve comprar no minimo 1 ingresso");
                continue;
            }

            if (op > disponiveis) {
                System.out.println();
                System.out.println(String.format("Sala possue apenas %d ingressos disponíveis.", disponiveis));
                continue;
            }

            return op;
        }
    }

    private Filme selecionarFilme() {
        while (true) {
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
                System.out.println();
                System.out.println("Selecione uma das opções indicadas.");
                continue;
            }

            return filmes.get(op);
        }
    }

    private Sessao selecionarSessao(Filme filme) {
        while (true) {
            System.out.println();
            System.out.println("Selecione a sessão");
            System.out.println();
            System.out.println("Digite:");

            List<Sala> salas = cinema.getSalas();

            List<Sessao> sessoes = salas
                    .stream()
                    .flatMap(s -> s.getSessoes().stream())
                    .filter(s -> filme.equals(s.getFilme()))
                    .sorted(Comparator.comparing(Sessao::getHorario))
                    .collect(Collectors.toList());

            for (int i = 0; i < sessoes.size(); i++) {
                Sessao sessao = sessoes.get(i);

                SimpleDateFormat diaFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                String dia = diaFormatter.format(sessao.getHorario());

                System.out.println(String.format("%d - Dia: %s - Ingressos disponíveis: %d", i, dia, sessao.getQtdIngressosDisponiveis()));
            }

            System.out.println();

            int op = getSimpleInt("a opção desejada");

            if (op < 0 || op >= sessoes.size()) {
                System.out.println();
                System.out.println("Selecione uma das opções indicadas.");
                continue;
            }

            Sessao sessao = sessoes.get(op);
            if (sessao.getQtdIngressosDisponiveis() <= 0) {
                System.out.println();
                System.out.println("Essa sessão não possue mais ingressos disponíveis, por favor, selecione outra!");
                continue;
            }

            return sessao;
        }
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

}
