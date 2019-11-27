package com.unicamp.mc322.cinema.controller;

import com.google.gson.Gson;
import com.sun.xml.internal.ws.handler.HandlerException;
import com.unicamp.mc322.cinema.model.BancoUsuarios;
import com.unicamp.mc322.cinema.model.Ingresso;
import com.unicamp.mc322.cinema.model.Pessoa;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.unicamp.mc322.cinema.util.StringUtils.isNullOrEmpty;
import static com.unicamp.mc322.cinema.util.TerminalUtil.getSimpleString;
import static java.util.Objects.nonNull;

public class LoginController {

    private static final String PATH_FILE = "../json/data.txt";

    private Pessoa usuarioLogado;

    public void cadastrarUsuario() throws HandlerException {
        while (true) {
            System.out.println();
            System.out.println("Digite suas informações cadastrais");

            String nome = getSimpleString("o seu nome");
            String login = getSimpleString("o login desejado");
            String senha = getSimpleString("a senha desejada");

            if (isNullOrEmpty(nome) || isNullOrEmpty(login) || isNullOrEmpty(senha)) {
                System.out.println();
                System.out.println("Campos não podem ser vazios!");
                continue;
            }

            BancoUsuarios bancoUsuarios = this.getUsuarioCadastrados();
            Pessoa pessoa = new Pessoa(nome, login, senha);

            if (this.estaCadastrado(bancoUsuarios, pessoa))
                throw new HandlerException(new Throwable("Usuário já cadastrado"));

            bancoUsuarios.adicionarUsuario(pessoa);
            this.salvarBancoUsuarios(bancoUsuarios);

            break;
        }
    }

    public Pessoa getUsuarioLogado() {
        return this.usuarioLogado;
    }

    public boolean logarUsuario() throws HandlerException {
        while (true) {
            System.out.println();

            String login = getSimpleString("o login");
            String senha = getSimpleString("a senha");

            if (isNullOrEmpty(login) || isNullOrEmpty(senha)) {
                System.out.println();
                System.out.println("Campos não podem ser vazios!");
                continue;
            }

            return this.mudarStatusUsuario(login, senha, true);
        }
    }

    public boolean deslogarUsuario() throws HandlerException {
        return this.mudarStatusUsuario(this.usuarioLogado.getLogin(), this.usuarioLogado.getSenha(), false);
    }

    public void registrarCompra(List<Ingresso> ingressos) {
        BancoUsuarios bancoUsuarios = this.getUsuarioCadastrados();

        Optional<Pessoa> first = bancoUsuarios
                .getUsuarios()
                .stream()
                .filter(p -> p.getLogin().equals(this.usuarioLogado.getLogin()))
                .findFirst();

        Pessoa pessoa = first.get();
        pessoa.adicionarCompras(ingressos);

        this.salvarBancoUsuarios(bancoUsuarios);
    }

    private boolean mudarStatusUsuario(String login, String senha, boolean status) throws HandlerException {
        BancoUsuarios bancoUsuarios = this.getUsuarioCadastrados();

        if (!this.estaCadastrado(bancoUsuarios, new Pessoa("", login, senha)))
            throw new HandlerException(new Throwable("Usuário inexistênte"));

        Optional<Pessoa> first = bancoUsuarios
                .getUsuarios()
                .stream()
                .filter(p -> p.getLogin().equals(login))
                .findFirst();

        Pessoa pessoa = first.get();

        if (!pessoa.getSenha().equals(senha))
            throw new HandlerException(new Throwable("Senha incorreta"));

        pessoa.setLogado(status);

        this.usuarioLogado= status ? pessoa : null;
        this.salvarBancoUsuarios(bancoUsuarios);

        return true;
    }

    private BancoUsuarios getUsuarioCadastrados() {
        InputStream is = getClass().getResourceAsStream(PATH_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

        BancoUsuarios bancoUsuarios = new Gson().fromJson(br, BancoUsuarios.class);

        return nonNull(bancoUsuarios) ? bancoUsuarios : new BancoUsuarios();
    }

    private void salvarBancoUsuarios(BancoUsuarios bancoUsuarios) {
        Gson gson = new Gson();
        String json = gson.toJson(bancoUsuarios);

        URL path = getClass().getResource(PATH_FILE);
        try (FileWriter writer = new FileWriter(path.getPath())) {
            writer.write(json);
        } catch (IOException e) {
            System.err.println("IOException");
        }
    }

    private boolean estaCadastrado(BancoUsuarios usuarios, Pessoa pessoa) {
        return usuarios.getUsuarios().stream().anyMatch(p -> p.getLogin().equals(pessoa.getLogin()));
    }

}
