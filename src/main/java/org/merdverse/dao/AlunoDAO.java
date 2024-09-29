package org.merdverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.merdverse.models.Aluno;

public class AlunoDAO {
    private static final String INSERT_ALUNO = "INSERT INTO aluno (nome, email, pontos, senha) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALUNO_BY_ID = "SELECT * FROM aluno WHERE id = ?";
    private static final String SELECT_ALL_ALUNOS = "SELECT * FROM aluno";
    private static final String UPDATE_ALUNO = "UPDATE aluno SET nome = ?, email = ?, pontos = ?, nivel = ? WHERE id = ?";
    private static final String DELETE_ALUNO = "DELETE FROM aluno WHERE id = ?";
    
    public void create(Aluno aluno) {
    	try(Connection conn = ConexaoDB.getConnection();
    		PreparedStatement ps = conn.prepareStatement(INSERT_ALUNO)) 
    	{
    		ps.setString(1, aluno.getNome());
    		ps.setString(2, aluno.getEmail());
    		ps.setInt(3, aluno.getPontos());
    		ps.setString(4, aluno.getSenha());
    		ps.executeUpdate();
    		
    		System.out.println("Aluno cadastrado: " + aluno.getNome());
    		
    	} catch (SQLException e) {
    		System.out.println("Cannot create Aluno: " + e.getMessage());
    	}
    	
    }

}
