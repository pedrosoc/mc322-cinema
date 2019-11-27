package com.unicamp.mc322.cinema.model;

import java.util.ArrayList;
import java.util.List;

public class BancoUsuarios {

    private List<Pessoa> usuarios = new ArrayList<>();

    public void adicionarUsuario(Pessoa p) {
        this.usuarios.add(p);
    }

    public List<Pessoa> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Pessoa> usuarios) {
        this.usuarios = usuarios;
    }

}
