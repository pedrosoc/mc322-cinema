package com.unicamp.mc322.cinema.controller;

import com.unicamp.mc322.cinema.model.Cinema;
import com.unicamp.mc322.cinema.model.Filme;
import com.unicamp.mc322.cinema.model.Ingresso;
import com.unicamp.mc322.cinema.model.IngressoInteira;
import com.unicamp.mc322.cinema.model.Pessoa;
import com.unicamp.mc322.cinema.model.Sala;
import com.unicamp.mc322.cinema.model.Sessao;

import java.util.Arrays;
import java.util.List;

import static com.unicamp.mc322.cinema.util.TerminalUtil.getSimpleInt;

public class CinemaController {

    private Cinema cinema;

    public CinemaController(Cinema cinema) {
        this.cinema = cinema;
    }

    public List<Ingresso> reservarIngresso() {

        Filme filme = this.selecionarFilme();
        this.selecionarSessao(filme);

        Pessoa pessoa = new Pessoa("Lucas Pedroso");
        IngressoInteira ingresso = new IngressoInteira(30f, pessoa);

        return Arrays.asList(ingresso);
    }

    public boolean finalizarCompra(List<Ingresso> ingressos) {
        return true;
    }

    public void cancelarReservas(List<Ingresso> ingressos) {

    }

    private Filme selecionarFilme() {
        while (true) {
            System.out.println();
            System.out.println("Selecione o filme");
            System.out.println();
            System.out.println("Digite:");

            List<Filme> filmes = this.cinema.getFilmes();
            for (int i = 0; i < filmes.size().; i++) {
                Filme filme = filmes.get(i);
                System.out.println(String.format("%d - %s", i, filme.getNome()));
            }

            System.out.println();

            int op = getSimpleInt("a opção desejada");

            if (op < 0 && op >= filmes.size())
                continue;

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
            for (Sala sala: salas) {
                sala.getSessoes();
            }

//            for (int i = 0; i < filmes.size().; i++) {
//                Filme filme = filmes.get(i);
//                System.out.println(String.format("%d - %s", i, filme.getNome()));
//            }
//
//            System.out.println();
//
//            int op = getSimpleInt("a opção desejada");
//
//            if (op < 0 && op >= filmes.size())
//                continue;
//
//            return filmes.get(op);

            return new Sessao();
        }
    }
}
