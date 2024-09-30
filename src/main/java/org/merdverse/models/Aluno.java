package org.merdverse.models;

import java.time.LocalDate;

public class Aluno extends Usuario {
    int id;
    private int pontos;

    public Aluno(String nome, String email, LocalDate dataNasc) {
        super(nome, email, dataNasc);
        this.pontos = 0;
    }

    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }


    public int getId() {
        return id;
    }
}

