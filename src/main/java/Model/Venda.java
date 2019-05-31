package Model;

public class Venda {

    private int codigoProduto;
    private String nomeProduto;
    private int idFuncionario;
    private String nomeFuncionario;
    private String nomeFilial;
    private int codigoFilial;
    private int quantidadeVenda;
    private String cpfCliente;

    public Venda(){
    }
    
    public Venda(int codigoProduto, int idFuncionario, int codigoFilial) {
        this.codigoProduto = codigoProduto;
        this.idFuncionario = idFuncionario;
        this.codigoFilial = codigoFilial;
    }
    
    public Venda(int codigoProduto, int idFuncionario, int codigoFilial, int quantidadeVenda) {
        this.codigoProduto = codigoProduto;
        this.idFuncionario = idFuncionario;
        this.codigoFilial = codigoFilial;
        this.quantidadeVenda = quantidadeVenda;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getQuantidadeVenda() {
        return quantidadeVenda;
    }

    public void setQuantidadeVenda(int quantidadeVenda) {
        this.quantidadeVenda = quantidadeVenda;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getCodigoFilial() {
        return codigoFilial;
    }

    public void setCodigoFilial(int codigoFilial) {
        this.codigoFilial = codigoFilial;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNomeFilial() {
        return nomeFilial;
    }

    public void setNomeFilial(String nomeFilial) {
        this.nomeFilial = nomeFilial;
    }

    
}
