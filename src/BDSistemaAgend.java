
import Conexao.Conexao;
import beans.Agendamento;
import DAO.AgendamentoDAO;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aluno
 */
public class BDSistemaAgend {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Agendamento a = new Agendamento();
        a.setData("12/10/2026");
        a.setHorario("12:30");
        a.setProfissional("Amanda");
        a.setNomeCliente("Joseleine");
        a.setTelefoneCliente("15 99092763");
        a.setProcedimento("Esmaltação");
        a.setValor(60);
        
        AgendamentoDAO aDAO = new AgendamentoDAO();
        aDAO.inserir(a);
        
    }
    
}
