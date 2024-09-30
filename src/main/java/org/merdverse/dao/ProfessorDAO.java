package org.merdverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import org.merdverse.models.Professor;

public class ProfessorDAO {
	private static final String INSERT_PROFESSOR = "INSERT INTO professor (nome_prof, email_prof, data_nasc_prof, senha_prof) VALUES (?, ?, ?, ?)";
	
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
        String sql = "UPDATE professor SET senha_prof = ? WHERE email_prof = ?";

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
