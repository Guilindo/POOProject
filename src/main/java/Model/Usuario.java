package Model;

public class Usuario {

    private int codigo;
    private String nome;
    private String email;
    private String senha;
    private int codigoFilial;
    private String nomeFilial;
    private int setor;
    private String nomeSetor;

    public Usuario() {

    }

    public Usuario(String nome, String email, String senha, int codigoFilial, int setor) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.setor = setor;
        this.codigoFilial = codigoFilial;
    }

    public Usuario(int codigo, String nome, String email, String senha, int codigoFilial, int setor) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.setor = setor;
        this.codigoFilial = codigoFilial;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getSetor() {
        return setor;
    }

    public void setSetor(int setor) {
        this.setor = setor;
    }

    public String getNomeFilial() {
        return nomeFilial;
    }

    public void setNomeFilial(String nomeFilial) {
        this.nomeFilial = nomeFilial;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public int getCodigoFilial() {
        return codigoFilial;
    }

    public void setCodigoFilial(int codigoFilial) {
        this.codigoFilial = codigoFilial;
    }

}
