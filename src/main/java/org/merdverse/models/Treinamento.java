package org.merdverse.models;

public class Treinamento {
	private String id;
	private String nome;
	private int pontos;
	private int pontosMinimo;
	private String email_professor;
	
	public Treinamento (String id, String nome, int pontos, int pontosMinimo, String email_professor) {
		setId(id);
		setNome(nome);
		setPontos(pontos);
		setPontosMinimo(pontosMinimo);
		setProfessor(email_professor);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	public String getProfessor() {
		return email_professor;
	}
	public int getPontosMinimo() {
		return pontosMinimo;
	}
	public void setPontosMinimo(int pontosMinimo) {
		this.pontosMinimo = pontosMinimo;
	}
	public void setProfessor(String professor) {
		this.email_professor = professor;
	}
}
