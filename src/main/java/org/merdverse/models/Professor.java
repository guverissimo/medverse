package org.merdverse.models;

import java.time.LocalDate;

public class Professor extends Usuario{
	int id;

	public Professor(String nome, String email, LocalDate dataNasc) {
		super(nome, email, dataNasc);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		System.out.println(this.nome);
		System.out.println(this.email);
		return "a";
	}
	
}
