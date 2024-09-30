package org.merdverse.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Usuario {
	protected String 	nome;
	protected String 	email;
	protected String    senha;
	protected LocalDate dataNasc;	
	
	public Usuario(String nome, String email, LocalDate dataNasc) {
		this.nome     = nome;
		this.email    = email;
		this.dataNasc = dataNasc;
		this.generateSenha(dataNasc);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public void generateSenha(LocalDate dataNasc) {
		DateTimeFormatter data = DateTimeFormatter.ofPattern("ddMMyy");
        String senha = dataNasc.format(data);
        setSenha(senha);
	}
}
