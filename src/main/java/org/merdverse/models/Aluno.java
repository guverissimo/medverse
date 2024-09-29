package org.merdverse.models;


public class Aluno extends Usuario {
    int id;
    private int pontos;
    private int nivel;

    public Aluno(String nome, String email, String senha) {
        super(nome, email, senha);
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }
}

