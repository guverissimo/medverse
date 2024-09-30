package org.merdverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.merdverse.models.Aluno;
import org.merdverse.models.LoginResult;
import org.merdverse.models.Professor;

public class Entrar {
    private static final String SELECT_ALUNO = "SELECT * FROM aluno WHERE email_aluno = ? AND senha_aluno = ?";
    private static final String SELECT_PROFESSOR = "SELECT * FROM professor WHERE email_prof = ? AND senha_prof = ?";
    
    public LoginResult entrar(String email, String senha) {
        try (Connection conn = ConexaoDB.getConnection()) {
            // Verificar se é um aluno
            Aluno aluno = verificarAluno(conn, email, senha);
            if (aluno != null) {
                return new LoginResult(0, aluno); // Código 0 para aluno
            }

            // Se não for aluno, verificar se é professor
            Professor professor = verificarProfessor(conn, email, senha);
            if (professor != null) {
                return new LoginResult(1, professor); // Código 1 para professor
            }

            return null; // Nenhum usuário encontrado
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
            return null; // Retorno em caso de erro
        }
    }

    private Aluno verificarAluno(Connection conn, String email, String senha) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SELECT_ALUNO)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new Aluno(resultSet.getString("nome_aluno"), 
                                 resultSet.getString("email_aluno"),
                                 resultSet.getDate("data_nasc_aluno").toLocalDate());
            }
            return null; // Aluno não encontrado
        }
    }

    private Professor verificarProfessor(Connection conn, String email, String senha) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SELECT_PROFESSOR)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet resultSetProfessor = ps.executeQuery();
            if (resultSetProfessor.next()) {
                return new Professor(resultSetProfessor.getString("nome_prof"), 
                                     resultSetProfessor.getString("email_prof"),
                                     resultSetProfessor.getDate("data_nasc_prof").toLocalDate());
            }
            return null; // Professor não encontrado
        }
    }
}
