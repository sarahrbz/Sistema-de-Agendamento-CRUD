/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.Conexao;
import beans.Procedimento;
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
public class ProcedimentoDAO {
    private Conexao conexao;
    private Connection conn;
    
    public ProcedimentoDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Procedimento procedimento){
        String sql = "INSERT INTO procedimento (NOME, DESCRICAO, VALOR) VALUES (?,?,?)";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            
            stmt.setString(1, procedimento.getNome());
            stmt.setString(2, procedimento.getDescricao());
            stmt.setDouble(3, procedimento.getValor());
            
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao inserir um novo procedimento: " + ex.getMessage());
        }
    }
    public Procedimento getProcedimento(int id){
        String sql = "SELECT * FROM procedimento WHERE id = ? ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Procedimento p = new Procedimento();
            
            rs.first();
            p.setId(id);
            p.setNome(rs.getString("NOME"));
            p.setDescricao(rs.getString("DESCRICAO"));
            p.setValor(rs.getDouble("VALOR"));
            return p;
        } catch(SQLException ex){
            System.out.println("Erro ao consultar procedimento: " + ex.getMessage());
            return null;
        }
    }
    public void editar(Procedimento procedimento){
        try{
            String sql = "UPDATE procedimento set NOME=?, DESCRICAO=?, VALOR=? WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, procedimento.getNome());
            stmt.setString(2, procedimento.getDescricao());
            stmt.setDouble(3, procedimento.getValor());
            stmt.setInt(4, procedimento.getId());
            
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao atualizar o procedimento: " + ex.getMessage());
        }
    }
    public void excluir(int id){
        try{
            String sql = "delete from procedimento WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            
        } catch (SQLException ex){
            System.out.println("Erro ao excluir procedimento: " + ex.getMessage());
        }
    }
    public List<Procedimento> getProcedimentos(){
        String sql = "SELECT * FROM procedimento";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rs = stmt.executeQuery();
            List<Procedimento> listaProcedimentos = new ArrayList();
            
            while(rs.next()){
                Procedimento p = new Procedimento();
                p.setId(rs.getInt("ID"));
                p.setNome(rs.getString("NOME"));
                p.setDescricao(rs.getString("DESCRICAO"));
                p.setValor(rs.getDouble("VALOR"));
                
                listaProcedimentos.add(p);
            }
            return listaProcedimentos;
        } catch (SQLException ex){
            System.out.println("Erro ao consultar todas os procedimentos: " + ex.getMessage());
            return null;
        }
    }
}
