package org.merdverse.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%-30s | %-30s | %-30s | %-15s | %-10d", 
                             nome, 
                             email, 
                             senha,
                             dataNasc.format(formatter), 
                             pontos);
    }
}

