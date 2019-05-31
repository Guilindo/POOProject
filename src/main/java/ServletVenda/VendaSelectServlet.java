package ServletVenda;

import DAO.VendaDAO;
import Model.Venda;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VendaSelectServlet", urlPatterns = {"/venda/cadastro_vendas"})
public class VendaSelectServlet extends HttpServlet {

    private void processaRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Venda> produtosVenda = VendaDAO.getProdutosVenda();
        ArrayList<Venda> usuariosVenda = VendaDAO.getUsuariosVenda();
        ArrayList<Venda> filiaisVenda = VendaDAO.getFiliaisVenda();

        if (produtosVenda.isEmpty()) {
            Venda uv = new Venda();

            uv.setNomeProduto("Não há produtos cadastrados");
            produtosVenda.add(uv);

            request.setAttribute("listaProdutos", produtosVenda);
        } else {
            request.setAttribute("listaProdutos", produtosVenda);
        }

        if (usuariosVenda.isEmpty()) {
            Venda uv = new Venda();

            uv.setNomeFuncionario("Não há usuarios cadastrados");
            usuariosVenda.add(uv);

            request.setAttribute("listaUsuarios", usuariosVenda);
        } else {
            request.setAttribute("listaUsuarios", usuariosVenda);
        }

        if (filiaisVenda.isEmpty()) {
            Venda uv = new Venda();

            uv.setNomeFilial("Não há filiais cadastradas");
            filiaisVenda.add(uv);

            request.setAttribute("listaFiliais", filiaisVenda);
        } else {
            request.setAttribute("listaFiliais", filiaisVenda);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/venda/cadastro_vendas.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processaRequisicao("GET", req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processaRequisicao("POST", req, resp); //To change body of generated methods, choose Tools | Templates.
    }

}
