/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.Conexao;
import beans.Profissional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class ProfissionalDAO {
    private Conexao conexao;
    private Connection conn;
    
    public ProfissionalDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Profissional profissional){
        String sql = "INSERT INTO profissional (NOME, ESPECIALIDADE, TELEFONE) VALUES (?,?,?)";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            
            stmt.setString(1, profissional.getNome());
            stmt.setString(2, profissional.getEspecialidade());
            stmt.setString(3, profissional.getTelefone());
            
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao inserir um novo profissional: " + ex.getMessage());
        }
    }
    public Profissional getProfissional(int id){
        String sql = "SELECT * FROM profissional WHERE id = ? ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Profissional p = new Profissional();
            
            rs.first();
            p.setId(id);
            p.setNome(rs.getString("NOME"));
            p.setEspecialidade(rs.getString("ESPECIALIDADE"));
            p.setTelefone(rs.getString("TELEFONE"));
            return p;
        } catch(SQLException ex){
            System.out.println("Erro ao consultar profissional: " + ex.getMessage());
            return null;
        }
    }
    public void editar(Profissional profissional){
        try{
            String sql = "UPDATE profissional set NOME=?, ESPECIALIDADE=?, TELEFONE=? WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, profissional.getNome());
            stmt.setString(2, profissional.getEspecialidade());
            stmt.setString(3, profissional.getTelefone());
            stmt.setInt(4, profissional.getId());
            
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao atualizar o profissional: " + ex.getMessage());
        }
    }
    public void excluir(int id){
        try{
            String sql = "delete from profissional WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            
        } catch (SQLException ex){
            System.out.println("Erro ao excluir profissional: " + ex.getMessage());
        }
    }
    public List<Profissional> getProfissionais(){
        String sql = "SELECT * FROM profissional";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rs = stmt.executeQuery();
            List<Profissional> listaProfissionais = new ArrayList();
            
            while(rs.next()){
                Profissional p = new Profissional();
                p.setId(rs.getInt("ID"));
                p.setNome(rs.getString("NOME"));
                p.setEspecialidade(rs.getString("ESPECIALIDADE"));
                p.setTelefone(rs.getString("TELEFONE"));
                
                listaProfissionais.add(p);
            }
            return listaProfissionais;
        } catch (SQLException ex){
            System.out.println("Erro ao consultar todas os profissionais: " + ex.getMessage());
            return null;
        }
    }
}
