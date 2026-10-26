    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.Conexao;
import beans.Cliente;
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
public class ClienteDAO {
    private Conexao conexao;
    private Connection conn;

    public ClienteDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Cliente cliente){
        String sql = "INSERT INTO cliente (NOME, TELEFONE, EMAIL) VALUES (?,?,?)";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao inserir um novo cliente: " + ex.getMessage());
        }
    }
    public Cliente getCliente(int id){
        String sql = "SELECT * FROM cliente WHERE id = ? ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Cliente c = new Cliente();
            
            rs.first();
            c.setId(id);
            c.setNome(rs.getString("NOME"));
            c.setTelefone(rs.getString("TELEFONE"));
            c.setEmail(rs.getString("EMAIL"));
            return c;
        } catch(SQLException ex){
            System.out.println("Erro ao consultar cliente: " + ex.getMessage());
            return null;
        }
    }
    public void editar(Cliente cliente){
        try{
            String sql = "UPDATE cliente set NOME=?, TELEFONE=?, EMAIL=? WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setInt(4, cliente.getId());
            
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao atualizar o cliente: " + ex.getMessage());
        }
    }
    public void excluir(int id){
        try{
            String sql = "delete from cliente WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            
        } catch (SQLException ex){
            System.out.println("Erro ao excluir cliente: " + ex.getMessage());
        }
    }
    public List<Cliente> getClientes(){
        String sql = "SELECT * FROM cliente";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rs = stmt.executeQuery();
            List<Cliente> listaClientes = new ArrayList();
            
            while(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt("ID"));
                c.setNome(rs.getString("NOME"));
                c.setTelefone(rs.getString("TELEFONE"));
                c.setEmail(rs.getString("EMAIL"));
                
                listaClientes.add(c);
            }
            return listaClientes;
        } catch (SQLException ex){
            System.out.println("Erro ao consultar todas os clientes: " + ex.getMessage());
            return null;
        }
    }
    
    
}
