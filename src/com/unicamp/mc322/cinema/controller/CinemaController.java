package com.unicamp.mc322.cinema.controller;

import com.unicamp.mc322.cinema.model.Ingresso;
import com.unicamp.mc322.cinema.model.IngressoInteira;
import com.unicamp.mc322.cinema.model.Pessoa;

import java.util.Arrays;
import java.util.List;

public class CinemaController {

    public List<Ingresso> reservarIngresso() {
        Pessoa pessoa = new Pessoa("Lucas Pedroso");
        IngressoInteira ingresso = new IngressoInteira(30f, pessoa);

        return Arrays.asList(ingresso);
    }

    public boolean finalizarCompra(List<Ingresso> ingressos) {
        return true;
    }

    public void cancelarReservas(List<Ingresso> ingressos) {

    }
}
