package DAO;

import Model.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VendaDAO {

    private static final Database db = new Database();

    public static boolean salvarVenda(Venda v) {
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("INSERT INTO"
                    + " tbl_venda(id_produto, id_usuario, id_filial, qtd_itens, cpf_cliente, status, data_venda)"
                    + "VALUES (?, ?, ?, ?, ?, ?, NOW());");

            query.setInt(1, v.getCodigoProduto());
            query.setInt(2, v.getIdFuncionario());
            query.setInt(3, v.getCodigoFilial());
            query.setInt(4, v.getQuantidadeVenda());
            query.setString(5, v.getCpfCliente());
            query.setInt(6, 0);

            int rs = query.executeUpdate();

            if (rs != 0) {
                atualizaEstoque(v.getQuantidadeVenda(), v.getCodigoProduto());
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception" + e);
            return false;
        }

        return true;
    }

    public static boolean excluirVenda(int vCodigo) {
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("UPDATE tbl_venda SET status = 1 WHERE id_venda = ?");

            query.setInt(1, vCodigo);
            ResultSet linhasAfetadas = query.executeQuery();
            
            conn.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception" + e);
            return false;
        }

        return true;
    }

    public static ArrayList<Venda> getProdutosVenda() {
        ArrayList<Venda> venda = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT id_produto, nome FROM tbl_produtos WHERE status = 0;");

            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Venda v = new Venda();
                    v.setCodigoProduto(rs.getInt(1));
                    v.setNomeProduto(rs.getString(2));

                    venda.add(v);
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return venda;
    }

    public static ArrayList<Venda> getUsuariosVenda() {
        ArrayList<Venda> venda = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT id_usuario, nome FROM tbl_usuario WHERE status = 0;");

            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Venda v = new Venda();
                    v.setIdFuncionario(rs.getInt(1));
                    v.setNomeFuncionario(rs.getString(2));

                    venda.add(v);
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return venda;
    }

    public static ArrayList<Venda> getFiliaisVenda() {
        ArrayList<Venda> venda = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT id_filial, concat(cidade, \" - \",estado) AS nome_filial "
                    + " FROM tbl_filial WHERE status = 0;");

            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Venda v = new Venda();
                    v.setCodigoFilial(rs.getInt(1));
                    v.setNomeFilial(rs.getString(2));

                    venda.add(v);
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return venda;
    }

    public static void atualizaEstoque(int quantidadeVendida, int codigProduto) {
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("UPDATE tbl_produtos AS p "
                    + " SET p.qtd_estoque = (p.qtd_estoque - ?) WHERE p.id_produto = ?;");

            query.setInt(1, quantidadeVendida);
            query.setInt(2, codigProduto);

            int rs = query.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception" + e);
        }
    }

}
