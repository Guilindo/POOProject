package DAO;

import Model.Relatorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RelatorioDAO {

    private static final Database db = new Database();

    public static ArrayList<Relatorio> getRelatorioGeral() {
        ArrayList<Relatorio> relatorio = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT v.id_venda, p.nome, v.id_produto, v.qtd_itens, "
                    + "(v.qtd_itens*p.valor_unidade) AS valor_total, v.cpf_cliente, v.id_filial, "
                    + "CONCAT(f.cidade, ' - ', f.estado) AS nome_filial, v.id_usuario, v.data_venda "
                    + "FROM tbl_venda AS v INNER JOIN tbl_produtos AS p ON v.id_produto = p.id_produto "
                    + "INNER JOIN tbl_filial AS f ON v.id_filial = f.id_filial;");

            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    relatorio.add(new Relatorio(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getDouble(5),
                            rs.getString(6),
                            rs.getInt(7),
                            rs.getString(8),
                            rs.getInt(9),
                            rs.getString(10)
                    ));
                }
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return relatorio;
    }

    public static ArrayList<Relatorio> getRelatorioFilial(int filial) {
        ArrayList<Relatorio> relatorio = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT v.id_venda, p.nome, v.id_produto, v.qtd_itens, "
                    + "(v.qtd_itens*p.valor_unidade) AS valor_total, v.cpf_cliente, v.id_filial, "
                    + "CONCAT(f.cidade, ' - ', f.estado) AS nome_filial, v.id_usuario, v.data_venda "
                    + "FROM tbl_venda AS v INNER JOIN tbl_produtos AS p ON v.id_produto = p.id_produto "
                    + "INNER JOIN tbl_filial AS f ON v.id_filial = f.id_filial WHERE v.id_filial = ?;");

            query.setInt(1, filial);
            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    relatorio.add(new Relatorio(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getDouble(5),
                            rs.getString(6),
                            rs.getInt(7),
                            rs.getString(8),
                            rs.getInt(9),
                            rs.getString(10)
                    ));
                }
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return relatorio;
    }

    public static ArrayList<Relatorio> getRelatorioData(String dataDe, String dataAte) {
        ArrayList<Relatorio> relatorio = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT v.id_venda, p.nome, v.id_produto, v.qtd_itens, "
                    + "(v.qtd_itens*p.valor_unidade) AS valor_total, v.cpf_cliente, v.id_filial, "
                    + "CONCAT(f.cidade, ' - ', f.estado) AS nome_filial, v.id_usuario, v.data_venda "
                    + "FROM tbl_venda AS v INNER JOIN tbl_produtos AS p ON v.id_produto = p.id_produto "
                    + "INNER JOIN tbl_filial AS f ON v.id_filial = f.id_filial WHERE v.data_venda >= ? AND v.data_venda <= ?;");

            query.setString(1, dataDe);
            query.setString(2, dataAte);
            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    relatorio.add(new Relatorio(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getDouble(5),
                            rs.getString(6),
                            rs.getInt(7),
                            rs.getString(8),
                            rs.getInt(9),
                            rs.getString(10)
                    ));
                }
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return relatorio;
    }

    public static ArrayList<Relatorio> getRelatorioCompleto(int filial, String dataDe, String dataAte) {
        ArrayList<Relatorio> relatorio = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT v.id_venda, p.nome, v.id_produto, v.qtd_itens, "
                    + "(v.qtd_itens*p.valor_unidade) AS valor_total, v.cpf_cliente, v.id_filial, "
                    + "CONCAT(f.cidade, ' - ', f.estado) AS nome_filial, v.id_usuario, v.data_venda "
                    + "FROM tbl_venda AS v INNER JOIN tbl_produtos AS p ON v.id_produto = p.id_produto "
                    + "INNER JOIN tbl_filial AS f ON v.id_filial = f.id_filial "
                    + "WHERE v.id_filial = ? AND v.data_venda >= ? AND v.data_venda <= ?");

            query.setInt(1, filial);
            query.setString(2, dataDe);
            query.setString(3, dataAte);
            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    relatorio.add(new Relatorio(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getDouble(5),
                            rs.getString(6),
                            rs.getInt(7),
                            rs.getString(8),
                            rs.getInt(9),
                            rs.getString(10)
                    ));
                }
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return relatorio;
    }

    public static ArrayList<Relatorio> getEstornoProduto(String eCodProduto) {
        ArrayList<Relatorio> relatorio = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM tbl_venda AS v WHERE v.id_produto = ?;");

            query.setString(1, eCodProduto);
            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    relatorio.add(new Relatorio(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7)
                    ));
                }
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return relatorio;
    }

    public static ArrayList<Relatorio> getEstornoFilial(String eCodFilial) {
        ArrayList<Relatorio> relatorio = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM tbl_venda AS v WHERE v.id_filial = ?;");

            query.setString(1, eCodFilial);
            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    relatorio.add(new Relatorio(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7)
                    ));
                }
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return relatorio;
    }

    public static ArrayList<Relatorio> getEstornoCpf(String eCpfCliente) {
        ArrayList<Relatorio> relatorio = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM tbl_venda AS v WHERE v.cpf_cliente = ?;");

            query.setString(1, eCpfCliente);
            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    relatorio.add(new Relatorio(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7)
                    ));
                }
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return relatorio;
    }

    public static ArrayList<Relatorio> getEstornoGeral() {
        ArrayList<Relatorio> relatorio = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM tbl_venda AS v;");

            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    relatorio.add(new Relatorio(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7)
                    ));
                }
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return relatorio;
    }

}
