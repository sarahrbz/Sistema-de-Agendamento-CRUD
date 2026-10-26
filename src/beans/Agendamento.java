
package beans;

/**
 *
 * @author aluno
 */
public class Agendamento {
    private int id;
    private String data;
    private String horario;
    private String profissional;
    private String nomeCliente;
    private String telefoneCliente;
    private String procedimento;
    private double valor;
    
    public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getData() {
    return data;
}

public void setData(String data) {
    this.data = data;
}

public String getHorario() {
    return horario;
}

public void setHorario(String horario) {
    this.horario = horario;
}

public String getProfissional() {
    return profissional;
}

public void setProfissional(String profissional) {
    this.profissional = profissional;
}

public String getNomeCliente() {
    return nomeCliente;
}

public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
}

public String getTelefoneCliente() {
    return telefoneCliente;
}

public void setTelefoneCliente(String telefoneCliente) {
    this.telefoneCliente = telefoneCliente;
}

public String getProcedimento() {
    return procedimento;
}

public void setProcedimento(String procedimento) {
    this.procedimento = procedimento;
}

public double getValor() {
    return valor;
}

public void setValor(double valor) {
    this.valor = valor;
}

}
