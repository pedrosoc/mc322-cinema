package com.unicamp.mc322.cinema.controller;

import com.unicamp.mc322.cinema.model.Ingresso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarrinhoController {

    private List<Ingresso> ingressos;

    public CarrinhoController() {
        this.ingressos = new ArrayList<>();
    }

    public void adicionarIngressos(List<Ingresso> ingressos) {
        this.ingressos.addAll(ingressos);
        System.out.println("Ingresso adicionado ao carrinho com sucesso!");
    }

    public void exibir() {
        int qtdIngressos = this.ingressos.size();

        System.out.println();
        System.out.println(String.format("Seu carrinho está com %d item(s)", qtdIngressos));
        System.out.println();

        if (qtdIngressos > 0) {
            System.out.println("Conteúdo:");
            for (Ingresso ingresso : this.ingressos) {
                float preco = ingresso.getPreco();
                String nome = ingresso.getComprador().getNome();

                System.out.println(String.format("Ingresso no valor de %.2f para %s", preco, nome));
            }
        }
    }

    public List<Ingresso> getIngressos() {
        return Collections.unmodifiableList(this.ingressos);
    }

    public void limparCarrinho() {
        this.ingressos = new ArrayList<>();
        System.out.println("Carrinho limpo com sucesso!");
    }
}
