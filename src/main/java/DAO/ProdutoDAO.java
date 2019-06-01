package DAO;

import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutoDAO {

    private static final Database db = new Database();

    public static boolean salvarProduto(Produto p) {
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("INSERT INTO"
                    + " tbl_produtos(nome, descricao, tipo, fk_filial, qtd_estoque, valor_unidade, status)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);");
            query.setString(1, p.getNome());
            query.setString(2, p.getDescricao());
            query.setString(3, p.getTipo());
            query.setInt(4, p.getCodigoFilial());
            query.setInt(5, p.getQuantidadeEstoque());
            query.setDouble(6, p.getValorUnitario());
            query.setInt(7, 0);

            int rs = query.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    public static boolean atualizarProduto(Produto produto) {
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("UPDATE"
                    + " tbl_produtos SET "
                    + " nome = ?,"
                    + " descricao = ?,"
                    + " tipo = ?,"
                    + " fk_filial = ?,"
                    + " qtd_estoque = ?,"
                    + " valor_unidade = ? WHERE id_produto = ?;");

            query.setString(1, produto.getNome());
            query.setString(2, produto.getDescricao());
            query.setString(3, produto.getTipo());
            query.setInt(4, produto.getCodigoFilial());
            query.setInt(5, produto.getQuantidadeEstoque());
            query.setDouble(6, produto.getValorUnitario());
            query.setInt(7, produto.getCodigo());

            int linhasAfetadas = query.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    public static boolean excluirProduto(int pCodigo) {
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("UPDATE tbl_produtos SET status = 1 WHERE id_produto = ?");

            query.setInt(1, pCodigo);

            query.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    public static boolean excluirProdutos(String[] codigos) {
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("UPDATE tbl_produtos SET status = 1 WHERE id_produto = ?");

            for (String codigo : codigos) {
                query.setInt(1, Integer.parseInt(codigo));
                query.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        return true;
    }

    public static ArrayList<Produto> getProdutos() {
        ArrayList<Produto> produtos = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT p.id_produto, p.nome, p.descricao, p.tipo,"
                    + " p.fk_filial, p.qtd_estoque, p.valor_unidade,"
                    + " CONCAT(cidade, \" - \", estado) \n"
                    + " FROM tbl_produtos AS p INNER JOIN tbl_filial AS f"
                    + " ON p.fk_filial = f.id_filial WHERE p.status = 0;");

            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Produto produto = new Produto(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getInt(6),
                            rs.getDouble(7));
                    produto.setNomeFilial(rs.getString(8));
                    produtos.add(produto);

                }
            }

            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return produtos;
    }

    public static Produto getProduto(int codigo) {
        Produto produto = null;
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT p.id_produto, p.nome, p.descricao,"
                    + " p.tipo, p.fk_filial, p.qtd_estoque, p.valor_unidade,"
                    + " CONCAT(cidade, \" - \", estado) \n"
                    + " FROM tbl_produtos AS p INNER JOIN tbl_filial AS f ON p.fk_filial = f.id_filial\n"
                    + " WHERE p.id_produto = ? AND p.status = 0;");

            query.setInt(1, codigo);
            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Produto prod = new Produto(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getInt(6),
                            rs.getDouble(7)
                    );
                    prod.setNomeFilial(rs.getString(8));
                    produto = prod;
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return produto;
    }
}
