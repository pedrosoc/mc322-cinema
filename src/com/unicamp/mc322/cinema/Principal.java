package com.unicamp.mc322.cinema;

import com.unicamp.mc322.cinema.model.Cinema;
import com.unicamp.mc322.cinema.model.Filme;
import com.unicamp.mc322.cinema.model.Sala;
import com.unicamp.mc322.cinema.model.Sessao;
import com.unicamp.mc322.cinema.view.TerminalCinema;

import java.util.Arrays;
import java.util.Date;

public class Principal {

    public static void main(String args[]) {

        Cinema cinema = new Cinema("Cinemark", "Campinas", "123");

        Filme filme1 = new Filme("It: A coisa");
        Filme filme2 = new Filme("Rei Leão");
        Filme filme3 = new Filme("Toy Store");

        Date horario1 = new Date();

        Date horario2 = new Date();
        horario2.setHours(14);

        Sessao sessao1 = new Sessao(1, filme1, horario1);
        Sessao sessao2 = new Sessao(1, filme2, horario2);
        Sessao sessao3 = new Sessao(2, filme1, horario2);

        sessao1.setQtdIngressosDisponiveis(3);
        sessao2.setQtdIngressosDisponiveis(3);
        sessao3.setQtdIngressosDisponiveis(3);

        Sala sala1 = new Sala(1, 3);
        Sala sala2 = new Sala(2, 3);

        sala1.addSessao(sessao1);
        sala1.addSessao(sessao2);
        sala2.addSessao(sessao3);

        cinema.setFilmes(Arrays.asList(filme1, filme2, filme3));
        cinema.setSalas(Arrays.asList(sala1, sala2));

        TerminalCinema terminal = new TerminalCinema(cinema);
        terminal.iniciar();

    }

}
