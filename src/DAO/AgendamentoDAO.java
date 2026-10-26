
package DAO;

import Conexao.Conexao;
import beans.Agendamento;
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
public class AgendamentoDAO {
    private Conexao conexao;
    private Connection conn;

    public AgendamentoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Agendamento agendamento) {
        String sql = "INSERT INTO agendamento (DATA, HORARIO, PROFISSIONAL, NOME_DO_CLIENTE, TELEFONE_DO_CLIENTE, PROCEDIMENTO, VALOR) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, agendamento.getData());
            stmt.setString(2, agendamento.getHorario());
            stmt.setString(3, agendamento.getProfissional());
            stmt.setString(4, agendamento.getNomeCliente());
            stmt.setString(5, agendamento.getTelefoneCliente());
            stmt.setString(6, agendamento.getProcedimento());
            stmt.setDouble(7, agendamento.getValor());
        

            stmt.execute();

        } catch (SQLException ex) {
            System.out.println("Erro ao inserir um novo agendamento: " + ex.getMessage());
        }
    }
    public Agendamento getAgendamento(int id){
        String sql = "SELECT * FROM agendamento WHERE id = ? ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_UPDATABLE);
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Agendamento a = new Agendamento();
            
            rs.first();
            a.setId(id);
            a.setData(rs.getString("DATA"));
            a.setHorario(rs.getString("HORARIO"));
            a.setProfissional(rs.getString("PROFISSIONAL"));
            a.setNomeCliente(rs.getString("NOME_DO_CLIENTE"));
            a.setTelefoneCliente(rs.getString("TELEFONE_DO_CLIENTE"));
            a.setProcedimento(rs.getString("PROCEDIMENTO"));
            a.setValor(rs.getDouble("VALOR"));
            return a;
        } catch(SQLException ex){
            System.out.println("Erro ao consultar agendamento: " + ex.getMessage());
            return null;
        }
    }
    public void editar(Agendamento agendamento){
        try{
            String sql = "UPDATE agendamento set DATA=?, HORARIO=?, PROFISSIONAL=?, NOME_DO_CLIENTE=?, TELEFONE_DO_CLIENTE=?, PROCEDIMENTO=?, VALOR=? WHERE ID=? ";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, agendamento.getData());
            stmt.setString(2, agendamento.getHorario());
            stmt.setString(3, agendamento.getProfissional());
            stmt.setString(4, agendamento.getNomeCliente());
            stmt.setString(5, agendamento.getTelefoneCliente());
            stmt.setString(6, agendamento.getProcedimento());
            stmt.setDouble(7, agendamento.getValor());
            stmt.setInt(8, agendamento.getId());
        

            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao atualizar o agendamento: " + ex.getMessage());
        }
    }
    
    public void excluir(int id){
        try{
            String sql = "delete from agendamento WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            
        } catch (SQLException ex){
            System.out.println("Erro ao excluir agendamento: " + ex.getMessage());
        }
    }
    
    public List<Agendamento> getAgendamentos(){
        String sql = "SELECT * FROM agendamento";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rs = stmt.executeQuery();
            List<Agendamento> listaAgendamentos = new ArrayList();
            
            while (rs.next()){
                Agendamento a = new Agendamento();
                a.setId(rs.getInt("ID"));
                a.setData(rs.getString("DATA"));
                a.setHorario(rs.getString("HORARIO"));
                a.setProfissional(rs.getString("PROFISSIONAL"));
                a.setNomeCliente(rs.getString("NOME_DO_CLIENTE"));
                a.setTelefoneCliente(rs.getString("TELEFONE_DO_CLIENTE"));
                a.setProcedimento(rs.getString("PROCEDIMENTO"));
                a.setValor(rs.getDouble("VALOR"));
                listaAgendamentos.add(a);
                
            }
            return listaAgendamentos;
        } catch (SQLException ex){
            System.out.println("Erro ao consultar todas as pessoas: " + ex.getMessage());
            return null;
        }
    }
  
    public List<Agendamento> getAgendamentosPorProfissional(String profissional) {

    List<Agendamento> lista = new ArrayList<>();

    String sql = "SELECT * FROM agendamento WHERE PROFISSIONAL = ?";

    try {
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, profissional);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Agendamento a = new Agendamento();

            a.setId(rs.getInt("ID"));
            a.setData(rs.getString("DATA"));
            a.setHorario(rs.getString("HORARIO"));
            a.setProfissional(rs.getString("PROFISSIONAL"));
            a.setNomeCliente(rs.getString("NOME_DO_CLIENTE"));
            a.setTelefoneCliente(rs.getString("TELEFONE_DO_CLIENTE"));
            a.setProcedimento(rs.getString("PROCEDIMENTO"));
            a.setValor(rs.getDouble("VALOR"));

            lista.add(a);
        }

        rs.close();
        stmt.close();

    } catch (SQLException ex) {
        System.out.println("Erro ao filtrar agendamentos: "
                + ex.getMessage());
    }

    return lista;
}

}
