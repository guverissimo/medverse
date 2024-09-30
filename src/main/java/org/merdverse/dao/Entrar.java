package org.merdverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Entrar {
    private static final String SELECT_ALUNO = "SELECT * FROM aluno WHERE email = ? AND senha = ?";
    
    public int entrar(String email, String senha) {
        ResultSet resultSet = null;

        try (Connection conn = ConexaoDB.getConnection()) {
            // Verificar se é um aluno
            PreparedStatement ps = conn.prepareStatement(SELECT_ALUNO);
            ps.setString(1, email);
            ps.setString(2, senha);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return 0; // Aluno encontrado
            } else {
                // Se não for aluno, verificar se é professor
                String queryProfessor = "SELECT * FROM professor WHERE email = ? AND senha = ?";
                PreparedStatement psProfessor = conn.prepareStatement(queryProfessor);
                psProfessor.setString(1, email);
                psProfessor.setString(2, senha);
                ResultSet resultSetProfessor = psProfessor.executeQuery();
                
                if (resultSetProfessor.next()) {
                    return 1; // Professor encontrado
                } else {
                    return 2; // Nenhum usuário encontrado
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
            return -1; // Retorno em caso de erro
        } finally {
            // Fechar o ResultSet do aluno
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar ResultSet: " + e.getMessage());
            }
        }
    }
}
