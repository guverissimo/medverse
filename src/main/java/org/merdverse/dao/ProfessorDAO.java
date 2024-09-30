package org.merdverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import org.merdverse.models.Professor;

public class ProfessorDAO {
	private static final String INSERT_PROFESSOR = "INSERT INTO professor (nome, email, data_nasc, senha) VALUES (?, ?, ?, ?)";
	private static final String SELECT_PROFESSOR_BY_ID = "SELECT * FROM professor WHERE id = ?";
	private static final String SELECT_ALL_PROFESSORS = "SELECT * FROM professor";
	private static final String UPDATE_PROFESSOR = "UPDATE professor SET nome = ?, email = ? WHERE id = ?";
	private static final String DELETE_PROFESSOR = "DELETE FROM professor WHERE id = ?";	
	
	public void create(Professor professor) {
		try (Connection conn = ConexaoDB.getConnection();
			 PreparedStatement ps = conn.prepareStatement(INSERT_PROFESSOR))
		{
    		ps.setString(1, professor.getNome());
    		ps.setString(2, professor.getEmail());
    		LocalDate dataNascimento = professor.getDataNasc();
    		ps.setDate(3, java.sql.Date.valueOf(dataNascimento));
    		ps.setString(4, professor.getSenha());
    		ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Erro ao criar professor: " + e.getMessage());
		}
	}
	
    public void updatePassword(String email, String senha) {
        String sql = "UPDATE professor SET senha = ? WHERE email = ?";

        // Obtendo a conexão
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, senha);
            stmt.setString(2, email);
            stmt.executeUpdate();
            
            System.out.println("Senha atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
