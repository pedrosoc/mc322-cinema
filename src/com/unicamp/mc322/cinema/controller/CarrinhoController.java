package com.unicamp.mc322.cinema.controller;

import com.unicamp.mc322.cinema.model.Carrinho;
import com.unicamp.mc322.cinema.model.Ingresso;
import com.unicamp.mc322.cinema.model.Pagador;

import java.util.List;

import static com.unicamp.mc322.cinema.util.TerminalUtil.getSimpleInt;

public class CarrinhoController {
	
	private Carrinho carrinho;
	
    public CarrinhoController() {
        this.carrinho = new Carrinho();
    }

    public void exibir() {
        System.out.println();
        System.out.println("Seu carrinho contem: ");
    	this.carrinho.listarItens();
    }

    public List<Ingresso> getIngressos() {
    	return carrinho.getIngressos();
    }
    
    public void adicionarIngresso(List<Ingresso> ingressos) {
		this.carrinho.addIngresso(ingressos);
		
	}

    public void limparCarrinho() {
    	carrinho.limparCarrinho();
    }
    
    public void realizarPagamento() {
    	int forma = this.formaPagamento();
    	boolean sucesso= false;
    	if(forma == 0) {
    		sucesso = Pagador.pagarViaCartao(this.carrinho);
    	}else if(forma == 1){
    		sucesso = Pagador.pagarViaBoleto(this.carrinho);
    	}
    	
    	if(sucesso) {
    		limparCarrinho();
    	}
    }
    
    private int formaPagamento() {
        while (true) {
            System.out.println();
            System.out.println("Qual metodo de pagamento deseja realizar?");
            System.out.println("Digite:");
            System.out.println("0 - Cartão");
            System.out.println("1 - Boleto");
            System.out.println();

            int op = getSimpleInt("a opção desejada");

            if (op != 0 && op != 1) {
                System.out.println();
                System.out.println("Opção inválida!");
                continue;
            }

            return op;
        }
    }

}
