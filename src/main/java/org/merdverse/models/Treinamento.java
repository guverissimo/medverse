package org.merdverse.models;

public class Treinamento {
	private int id;
	private String nome;
	private int pontos;
	private int pontosMinimo;
	private Professor professor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Professor getProfessor() {
		return professor;
	}
	public int getPontosMinimo() {
		return pontosMinimo;
	}
	public void setPontosMinimo(int pontosMinimo) {
		this.pontosMinimo = pontosMinimo;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
}
