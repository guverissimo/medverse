package org.merdverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import org.merdverse.models.Aluno;

import br.com.fiap.challenge.dao.ConexaoDB;

public class AlunoDAO {
    private static final String INSERT_ALUNO = "INSERT INTO aluno (nome, email, pontos, data_nasc, senha) VALUES (?, ?, ?, ?, ?)";
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
    		LocalDate dataNascimento = aluno.getDataNasc();
    		ps.setDate(4, java.sql.Date.valueOf(dataNascimento));
    		ps.setString(5, aluno.getSenha());
    		ps.executeUpdate();
    		
    		System.out.println("Aluno cadastrado: " + aluno.getNome());
    		
    	} catch (SQLException e) {
    		System.out.println("Cannot create Aluno: " + e.getMessage());
    	}
    	
    }
    
    public void updatePassword(String email, String senha) {
        String sql = "UPDATE aluno SET senha = ? WHERE email = ?";

        // Obtendo a conexão
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, senha);
            stmt.setString(2, email);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}
