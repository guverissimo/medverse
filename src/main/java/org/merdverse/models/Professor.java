package org.merdverse.models;

import java.time.LocalDate;

import org.merdverse.dao.ProfessorDAO;

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
	
	public void updatePassword(String email, String senhaAntiga, String senhaNova) {
		ProfessorDAO professorDAO = new ProfessorDAO();
		if (this.senha != senhaAntiga) {
			System.out.println("Senha incorreta"); }
		
		professorDAO.updatePassword(email, senhaNova);
		
	}
	
}
