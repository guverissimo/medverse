package org.merdverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.merdverse.models.Professor;

public class ProfessorDAO {
	private static final String INSERT_PROFESSOR = "INSERT INTO professor (nome, email, senha) VALUES (?, ?, ?)";
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
            ps.setString(3, professor.getSenha());
            ps.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("Erro ao criar professor: " + e.getMessage());
		}
	}
}
